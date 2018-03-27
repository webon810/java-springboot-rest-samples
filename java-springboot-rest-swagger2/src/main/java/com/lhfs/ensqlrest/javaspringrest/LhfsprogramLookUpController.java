package com.lhfs.ensqlrest.javaspringrest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("uat/lhfsprogramlookup")
public class LhfsprogramLookUpController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private LhfsprogramLookUpRepository lhfsprogramLookUpRepo;

    @ApiIgnore
    @RequestMapping("lhfsprogramtest")
    public String lhfsprgramtest() {
        log.info("lhfs-program-test");
        return "lhfs-program test OK";
    }

    /*this mapping not work yet, only ok for one employeeid, just OK to run for now 09/28/17 */
    @CrossOrigin
    /* @RequestMapping("lhfsprogramlookup") */
    /*@RequestMapping(path="/lhfsprogramlookup/{EmployeeID}/{LastName}/{SSN4}") */
    /*public LhfsprogramLookup getLhfsprogramlookup(@RequestParam("employeeid") Integer EmployeeID, @RequestParam("lastname") String LastName, @RequestParam("ssn4") String SSN4) {*/
    /* public LhfsprogramLookup getLhfsprogramlookup(@RequestParam("employeeid") Integer EmployeeID) { */
    @RequestMapping(path = "/id/{EmployeeID}/LastName/{LastName}/SSN4/{SSN4}", method=RequestMethod.GET)
    public LhfsprogramLookup getLhfsprogramlookup(@PathVariable("EmployeeID") Integer EmployeeID, @PathVariable("LastName") String LastName, @PathVariable("SSN4") String SSN4) {
        log.info("Get LHFS Program Look up");
        /*return lhfsprogramLookUpRepo.getLhfsprogramLookup(EmployeeID, LastName, SSN4);*/
        return lhfsprogramLookUpRepo.getLhfsprogramLookup(EmployeeID, LastName,SSN4);
    }
}
