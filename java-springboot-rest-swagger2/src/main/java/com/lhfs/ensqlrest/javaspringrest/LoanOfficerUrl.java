package com.lhfs.ensqlrest.javaspringrest;

public class LoanOfficerUrl {
    public String LoLHFSPID;
    public String LoFirstName;
    public String LoLastName;
    public String LoFullName;
    public String LoEmail;
    public String LoNMLS;
    public String LoPCLID;
    public String LoSFDCUserId;
    public String LoCodeInRetail;
    public String LoCodeInRetail2;
    public String LoVanityUrl;
    public String LoLandingPageUrl;
    public String LoTitle;
    //public String LoPhotoUrl;
    public String LoPhotoWebUrl;
    public String LoProfileAboutMe;
    public String LoProfileWelcomeMsg;

    public LoanOfficerUrl(String LoVanityUrl, String LoFullName){
        this.LoLHFSPID = LoLHFSPID;
        this.LoFirstName = LoFirstName;
        this.LoLastName = LoLastName;
        this.LoFullName = LoFullName;
        this.LoEmail = LoEmail;
        this.LoNMLS = LoNMLS;
        this.LoPCLID = LoPCLID;
        this.LoSFDCUserId = LoSFDCUserId;
        this.LoCodeInRetail = LoCodeInRetail;
        this.LoCodeInRetail2 = LoCodeInRetail2;
        this.LoVanityUrl = LoVanityUrl;
        this.LoLandingPageUrl = "https://yourdomain100.com/" + LoVanityUrl;
        this.LoTitle = LoTitle;
        //this.LoPhotoUrl = LoPhotoUrl;
        this.LoPhotoWebUrl = LoPhotoWebUrl;;
        this.LoProfileAboutMe = LoProfileAboutMe;
        this.LoProfileWelcomeMsg = LoProfileWelcomeMsg;
    }

}
