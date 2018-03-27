package com.lhfs.reasqlrest.javaspring2rest;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping("uat/realtor")
public class RealtorController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RealtorRepository realtorrepo;

    @ApiIgnore
    @RequestMapping("reatest")
    public String realtortest() {
        log.info("lo test here");
        return "OK realtor test here";
    }

    @CrossOrigin
    @ApiOperation(value = "get realtor info by realtorurl", notes = "")
    @ApiImplicitParam(name = "realtor by realtorurl", value = "Realtor info", required = false, dataType = "Realtor")
    @RequestMapping(value = "/reaurl/{ReaVanityUrl}", method=RequestMethod.GET)
    public  Realtor getRealtorByReaurl(@PathVariable("ReaVanityUrl") String ReaVanityUrl) throws Exception {
        log.info("Get Realtor Info");
        return realtorrepo.getRealtorByReaUrl(ReaVanityUrl);
    }

    @CrossOrigin
    @ApiOperation(value = "get list of Realtors info by realtorurls", notes = "")
    @ApiImplicitParam(name = "realtors by relatorurls", value = "List of Realtors Info", required = false, dataType = "Realtors")
    @RequestMapping(path = "/reaurls/{ReaVanityUrls}", method= RequestMethod.GET)
    public List<Realtor> getRealtorsByReaurls(@PathVariable("ReaVanityUrls") String[] ReaVanityUrls) throws Exception {
        log.info("Get list of Realtors info");
        return realtorrepo.getRealtorByReaUrls(ReaVanityUrls);
    }


}
