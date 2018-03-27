package com.lhfs.ensqlrest.javaspringrest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class LhfsprogramLookUpRepository {

    @Autowired
    protected JdbcTemplate jdbc;

    public LhfsprogramLookup getLhfsprogramLookup(Integer EmployeeID, String LastName, String SSN4) {
        return jdbc.queryForObject("SELECT EmployeeID, LTRIM(RTRIM(LastName)) as LastName, LTRIM(RTRIM(ZIP)) as ZIP, Status, IsActive " +
                "FROM dbo.Employee100 " +
                "WHERE IsActive = 1 and EmployeeID = ? and LastName = ? and SSN4 = ?", lhfsprogramMapper, EmployeeID, LastName, SSN4);
    }


    private static final RowMapper<LhfsprogramLookup> lhfsprogramMapper = new RowMapper<LhfsprogramLookup>() {
        public LhfsprogramLookup mapRow(ResultSet rs, int rowNum) throws SQLException {
            LhfsprogramLookup lhfsprogramlookup = new LhfsprogramLookup(rs.getInt("EmployeeID"), rs.getString("LastName"), rs.getString("SSN4"));
            lhfsprogramlookup.EmployeeID = rs.getInt("EmployeeID");
            lhfsprogramlookup.LastName = rs.getString("LastName");
            lhfsprogramlookup.ZIP = rs.getString("ZIP");
            lhfsprogramlookup.Status = rs.getString("Status");
            return lhfsprogramlookup;
        }
    };



}
