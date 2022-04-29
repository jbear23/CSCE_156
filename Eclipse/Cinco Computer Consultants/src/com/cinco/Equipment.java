package com.cinco;

public class Equipment extends Product {

    public Equipment(String code, String name, String type, String pricePerUnit) {
        super(code, name, type);
        PricePerUnit = pricePerUnit;
    }

    private String PricePerUnit;

    public String getPricePerUnit() {
        return PricePerUnit;
    }
}