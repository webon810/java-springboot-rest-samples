package com.lhfs.ensqlrest.javaspringrest;

public class BranchUrl {
    public String CostCenterCode;
    public String Address1;
    public String City;
    public String ZipCode;
    public String Phone;
    public String Manager;
    public String BranchEmail;
    public String BranchLandingPageUrl;
    public String FinePrint;

    public BranchUrl (String VanityUrl, String Manager){
        this.CostCenterCode = CostCenterCode;
        this.Address1 = Address1;
        this.City = City;
        this.ZipCode = ZipCode;
        this.Manager = Manager;
        this.Phone = Phone;
        this.BranchEmail = BranchEmail;
        this.VanityUrl = VanityUrl;
        this.BranchLandingPageUrl = "https://yourdomainname100.com/" + VanityUrl;
        this.FinePrint = FinePrint;
    }
}
