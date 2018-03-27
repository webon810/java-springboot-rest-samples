package com.lhfs.reasqlrest.javaspring2rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RealtorRepository {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    protected JdbcTemplate jdbc;

    public Realtor getRealtorByReaUrl(String ReaVanityUrl){
        return jdbc.queryForObject("SELECT c.ContactID AS ReaID,c.ContactTypeID AS ReaTypeID,c.FirstName as ReaFirstName, c.HomePhoneNumber as ReaHomePhoneNumber, \n" +
                "\t\tc.WorkPhoneNumber as ReaWorkPhoneNumber, c.MobilePhoneNumber as ReaMobilePhoneNumber, ws.REA_CRM_Web_URL1 AS ReaCrmUrl1, \n" + 
                cc01.Address2 AS LoccAddress2,cc01.City AS LoccCity,cc01.Phone as LoccPhoneNumber,cc01.Fax as LoccFaxNumber,cc01.PCL_CompanyId as LoccPclId,cc01.VanityUrl AS LoccVanityUrl,\n" +
                 "FROM dbo.[Contact] C \n" +
                "JOIN dbo.[WebSiteInfo] ws ON C.ContactID = ws.ContactID\n" +
                "LEFT JOIN dbo.[ContactImages] i1 ON C.ContactID = i1.ContactID AND i1.ImageTypeId = 1\n" +
                "LEFT JOIN (SELECT cc1.ContactID AS RealtorId, clo.ContactId, clo.ContactTypeId, clo.FirstName, clo.LastName, clo.TagLine, clo.Bio,clo.WorkPhoneNumber, clo.Email, clo.Address1, clo.City, clo.StateAbbrv, clo.PostalCode, clo.PID,ROW_NUMBER() OVER (PARTITION BY cc1.ContactID ORDER BY clo.ContactID) rn \n" +
                "\tFROM dbo.[Assignments] cc1 JOIN dbo.[Contact_CarrierCode_Assignments] cc2 ON cc1.ZipCodeCarrierRouteId = cc2.ZipCodeCarrierRouteId JOIN dbo.[Contact] clo ON clo.ContactID = cc2.ContactId WHERE clo.ContactTypeID = 1) lo ON lo.RealtorId = C.ContactID AND rn = 1\n" +
                "LEFT JOIN dbo.[CImages] k1 ON lo.ContactID = k1.ContactID AND k1.ImageTypeId = 1\n" +
                "LEFT JOIN dbo.[PageLinks] idx1 ON C.ContactID = idx1.ContactID\n" +
                "LEFT JOIN dbo.[SocialMedia] so ON C.ContactID = so.ContactID \n" +
                "LEFT JOIN (SELECT o1.PCL_EmployeeId, o1.PID, o1.FinePrint, p1.FirstName, p1.LastName, p1.Active, p1.VanityURL, p1.NickName, p1.cc FROM dbo.[Originator] o1 JOIN dbo.[Personnel] p1 ON p1.PID = o1.PID WHERE LEFT(o1.Employ_Status,8) = 'Employee' AND LEFT(o1.UpdateUser,12) = 'Loan Officer' AND p1.Active = 0) loc ON lo.PID = loc.PID \n" +
                "LEFT JOIN (SELECT PID, ContactType, Number FROM dbo.[Contact] WHERE ContactType = 'EWC' AND Number IS NOT NULL) ec ON ec.PID = lo.PID  -- work mobile \n" +
                "LEFT JOIN (SELECT PID, ContactType, Number AS PhoneNumber, ROW_NUMBER() OVER (PARTITION BY PID ORDER BY ID DESC) rn FROM dbo.[Contact] WHERE ContactType = 'BOP')ec01 ON ec01.PID = lo.PID AND ec01.rn = 1 -- Direct Desk\n" +
                "WHERE ws.VanityURL = ? AND C.Active = 1 AND C.ContactTypeID = 2 AND ws.VanityURL <>''", realtorMapper, ReaVanityUrl);
    }

    public List<Realtor> getRealtorByReaUrls(String[] ReaVanityUrls){

        String inReaUrls = StringUtils.arrayToCommaDelimitedString(ObjectUtils.toObjectArray(ReaVanityUrls));

        return jdbc.query("SELECT c.ContactID AS ReaID,c.ContactTypeID AS ReaTypeID,c.FirstName AS ReaFirstName, c.LastName AS ReaLastName, c.TagLine AS ReaShortDesc, c.Bio AS ReaLongDesc, c.Address1 AS ReaAddr1, c.Address2 AS ReaAddr2, c.City AS ReaCity, c.StateAbbrv AS ReaStateCode, c.PostalCode AS ReaPostalCode, c.Email AS ReaEmail, c.HomePhoneNumber AS ReaHomePhoneNumber, \n" +
                "c.WorkPhoneNumber AS ReaWorkPhoneNumber, c.MobilePhoneNumber AS ReaMobilePhoneNumber, c.FaxPhoneNumber AS ReaFaxNumber,c.OtherPhoneNumber AS ReaOtherPhoneNumber,c.Company AS ReaBizName, c.REA_Lic_BRE AS ReaBizLicense, c.PID AS RealtorPid,c.Active AS ReaIsActive,ws.VanityURL AS ReaVanityUrl, ws.BusinessWebAddress1 AS ReaBizWeb1, ws.REA_CRM_Web_URL1 AS ReaCrmUrl1, \n" +
                "FROM dbo.[Contact] C \n" +
                "JOIN dbo.[WebSiteInfo] ws ON C.ContactID = ws.ContactID \n" +
                "LEFT JOIN dbo.[ContactImages] i1 ON C.ContactID = i1.ContactID AND i1.ImageTypeId = 1 \n" +
                "LEFT JOIN (SELECT cc1.ContactID AS RealtorId, clo.ContactId, clo.ContactTypeId, clo.FirstName, clo.LastName, clo.TagLine, clo.Bio,clo.WorkPhoneNumber, clo.Email, clo.Address1, clo.City, clo.StateAbbrv, clo.PostalCode, clo.PID,ROW_NUMBER() OVER (PARTITION BY cc1.ContactID ORDER BY clo.ContactID) rn \n" +
                "FROM dbo.[Assignments] cc1 JOIN dbo.[Contact_CarrierCode_Assignments] cc2 ON cc1.ZipCodeCarrierRouteId = cc2.ZipCodeCarrierRouteId JOIN dbo.[Contact] clo ON clo.ContactID = cc2.ContactId WHERE clo.ContactTypeID = 1) lo ON lo.RealtorId = C.ContactID AND rn = 1 \n" +
                "LEFT JOIN dbo.[CImages] k1 ON lo.ContactID = k1.ContactID AND k1.ImageTypeId = 1 \n" +
                "LEFT JOIN dbo.[PageLinks] idx1 ON C.ContactID = idx1.ContactID \n" +
                "LEFT JOIN dbo.[SocialMedia] so ON C.ContactID = so.ContactID \n" +
                "LEFT JOIN (SELECT * FROM dbo.[ref_State]) r ON r.StateId = cc01.StateId \n" +
                "LEFT JOIN (SELECT DISTINCT EmailAddress, PID, EmailType FROM dbo.[Email] WHERE EmailType = 'EWE') e ON e.PID = lo.PID -- LO LHFS Email \n" +
                "LEFT JOIN (SELECT PID, ContactType, Number FROM dbo.[Contact] WHERE ContactType = 'EWC' AND Number IS NOT NULL) ec ON ec.PID = lo.PID  -- work mobile \n" +
                "LEFT JOIN (SELECT PID, ContactType, Number AS PhoneNumber, ROW_NUMBER() OVER (PARTITION BY PID ORDER BY ID DESC) rn FROM dbo.[Contact] WHERE ContactType = 'BOP')ec01 ON ec01.PID = lo.PID AND ec01.rn = 1  -- Direct Desk \n" +
                "WHERE C.Active = 1 AND C.ContactTypeID = 2 AND ws.VanityURL <>'' AND ws.VanityURL IN (" + inReaUrls + ")", realtorMapper);
    }

    private static final RowMapper<Realtor> realtorMapper = new RowMapper<Realtor>() {
        public Realtor mapRow(ResultSet resultSet, int i) throws SQLException {
            Realtor realtor = new Realtor(resultSet.getString("ReaVanityUrl"), resultSet.getString("ReaFirstName"));
            realtor.ReaFirstName = resultSet.getString("ReaFirstName");");
            realtor.ReaBizLogoUrl = resultSet.getString("ReaBizLogoUrl");
            realtor.LoNickName = resultSet.getString("LoNickName");
            realtor.LoVanityURL = "https://yourdomain100.com/" + resultSet.getString("LoVanityURL");
            realtor.LoccLat = resultSet.getString("LoccLat");
            realtor.LoccLong = resultSet.getString("LoccLong");
            realtor.LoccVanityUrl = "https://yourdomain100.com/" + resultSet.getString("LoccVanityUrl");
            return realtor;
        }
    };

}
