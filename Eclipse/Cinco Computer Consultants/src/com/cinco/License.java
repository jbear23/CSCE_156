package com.cinco;

public class License extends Product{

    public License(String code, String name, String type, String serviceFee, String annualLicenseFee) {
        super(code, name, type);
        ServiceFee = serviceFee;
        AnnualLicenseFee = annualLicenseFee;
    }

    private String ServiceFee;
    private String AnnualLicenseFee;

    public String getServiceFee() {
        return ServiceFee;
    }

    public String getAnnualLicenseFee() {
        return AnnualLicenseFee;
    }
}