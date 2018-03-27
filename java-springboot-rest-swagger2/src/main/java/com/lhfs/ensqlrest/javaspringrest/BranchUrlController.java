package com.lhfs.ensqlrest.javaspringrest;


import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;


@RestController
@RequestMapping("uat/branchurl")
public class BranchUrlController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private BranchUrlRepository branchurlrepo;

    @ApiIgnore
    @RequestMapping("ccurltest")
    public String ccurltest(){
        return "OK cost center url test here !";
    }

    @CrossOrigin
    @ApiOperation(value = "get branch info by cost center url", notes = "")
    @ApiImplicitParam(name = "branchurl", value = "branch info by Url", required = false, dataType = "BranchUrl")
    @RequestMapping(value = "/ccurl/{VanityUrl}", method= RequestMethod.GET)
    public BranchUrl getBranchbyUrl(@PathVariable("VanityUrl") String VanityUrl) {
        log.info("Get a branch info by url");
        return branchurlrepo.getBranchbyUrl(VanityUrl);

    }

    @CrossOrigin
    @ApiOperation(value = "get list of branches info by cc url", notes = "")
    @ApiImplicitParam(name = "branches", value = "branches info", required = false, dataType = "BranchUrls")
    @RequestMapping(value = "/ccurls/{VanityUrls}", method=RequestMethod.GET)
    public List<BranchUrl> getBranchesbyurl(@PathVariable("VanityUrls") String[] VanityUrls){
        log.info("Get list of Branches by Urls");
        return branchurlrepo.getBranchesbyurl(VanityUrls);
    }


}
