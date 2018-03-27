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
public class BranchUrlRepository {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    protected JdbcTemplate jdbc;

    public BranchUrl getBranchbyUrl(String VanityUrl){
        return jdbc.queryForObject("SELECT * FROM dbo.branch001 WHERE urlname WHERE VanityUrl=?", branchurlMapper, VanityUrl);
    }

    public List<BranchUrl> getBranchesbyurl(String[] VanityUrls) {

        String inVanityUrls = StringUtils.arrayToCommaDelimitedString(ObjectUtils.toObjectArray(VanityUrls));
        return jdbc.query("SELECT *  FROM dbo.branch001 WHERE urlname IN(" + inVanityUrls  +")", branchurlMapper);
    }

    private static final RowMapper<BranchUrl> branchurlMapper = new RowMapper<BranchUrl>() {
        public BranchUrl mapRow(ResultSet rs, int rowNum) throws SQLException {
            BranchUrl branchurl = new BranchUrl(rs.getString("VanityUrl"), rs.getString("Manager"));
            branchurl.BranchEmail = rs.getString("BranchEmail");
            branchurl.Address1 = rs.getString("Address1");
            branchurl.City = rs.getString("City");
            branchurl.ZipCode = rs.getString("ZipCode");
            branchurl.Phone = rs.getString("Phone");
            branchurl.CostCenterCode = rs.getString("CostCenterCode");
            branchurl.FinePrint = rs.getString("FinePrint").replaceAll("(\\r|\\n)","");
            return branchurl;
        }
    };
}
