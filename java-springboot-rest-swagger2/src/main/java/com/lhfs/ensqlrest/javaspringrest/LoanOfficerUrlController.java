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
@RequestMapping("uat/loanofficerurl")
public class LoanOfficerUrlController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private LoanOfficerUrlRepository loanofficerurlrepo;

    @ApiIgnore
    @RequestMapping("lourltest")
    public String lourltest(){
        log.info("lo url test here");
        return "OK LO Url Test here !";
    }

    @CrossOrigin
    @ApiOperation(value = "get LO info by Url", notes = "")
    @ApiImplicitParam(name ="loanofficer by url", value = "LO info", required = false, dataType = "LoanOfficerUrl")
    @RequestMapping(path="/lourl/{LoVanityUrl}", method=RequestMethod.GET)
    public LoanOfficerUrl getLoanOfficerbyUrl(@PathVariable("LoVanityUrl") String LoVanityUrl) throws Exception{
        log.info("Get Loan Officer info by Url");
        return loanofficerurlrepo.getLoanOfficerbyUrl(LoVanityUrl);
    }

    @CrossOrigin
    @ApiOperation(value = "get list of LO info by list of Urls", notes = "")
    @ApiImplicitParam(name = "loanofficers by Urls", value = "LOs Info", required = false, dataType = "LoanOfficerUrl")
    @RequestMapping(path="/lourls/{LoVanityUrls}", method=RequestMethod.GET)
    public List<LoanOfficerUrl> getLoanOfficerbyUrls(@PathVariable("LoVanityUrls") String[] LoVanityUrls) throws Exception {
        log.info("Get list of loan officers info by urls");
        return loanofficerurlrepo.getLoanOfficersbyUrls(LoVanityUrls);
    }


}
