package com.lhfs.ensqlrest.javaspringrest;

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
public class LoanOfficerUrlRepository {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    protected JdbcTemplate jdbc;

    public LoanOfficerUrl getLoanOfficerbyUrl(String LoVanityUrl){
        return jdbc.queryForObject("SELECT p.PID as LoLHFSPID,p.FirstName as LoFirstName,p.LastName as LoLastName \n" +
                "FROM dbo.[database001] p \n" +
                "WHERE p.active = 0 and (p.cc between '5%' and '6%') and (ori.UpdateUser like 'Loan Officer%')  and p.VanityURL = ?", loanofficerurlMapper, LoVanityUrl);
    }

    public List<LoanOfficerUrl> getLoanOfficersbyUrls(String[] LoVanityUrls) {
        String inVanityUrls = StringUtils.arrayToCommaDelimitedString(ObjectUtils.toObjectArray(LoVanityUrls));

        return jdbc.query("SELECT p.PID AS LoLHFSPID,p.FirstName AS LoFirstName,p.LastName AS LoLastName,p.NickName \n" +
                "FROM dbo.[database001] p \n" +
                "WHERE p.active = 0 AND (ori.UpdateUser LIKE 'Loan Officer%') AND p.VanityURL IN (" + inVanityUrls + ")", loanofficerurlMapper);
    }

    private static final RowMapper<LoanOfficerUrl> loanofficerurlMapper = new RowMapper<LoanOfficerUrl>() {

        public LoanOfficerUrl mapRow(ResultSet resultSet, int i) throws SQLException {
            LoanOfficerUrl loanofficerurl = new LoanOfficerUrl(resultSet.getString("LoVanityUrl"), resultSet.getString("LoFullName"));
            loanofficerurl.LoLHFSPID = resultSet.getString("LoLHFSPID");
            loanofficerurl.LoFirstName = resultSet.getString("LoFirstName");
            loanofficerurl.LoLastName = resultSet.getString("LoLastName");
            loanofficerurl.LoEmail = resultSet.getString("LoEmail");
            //loanofficerurl.LoPhotoUrl = resultSet.getString("LoPhotoUrl");
            loanofficerurl.LoPhotoWebUrl = "https://youdomain100.com/user_images/lo/" + resultSet.getString("LoPhotoUrl");
            return loanofficerurl;
        }
    };

}
