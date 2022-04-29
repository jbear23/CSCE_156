package com.cinco;

public abstract class Product {

    public Product(String code, String type, String name) {
        this.Code = code;
        this.Name = name;
        this.Type = type;
    }

    private String Code;
    private String Name;
    private String Type;

    public String getCode() {
        return Code;
    }

    public String getName() {
        return Name;
    }
    public String getType() {
        return Type;
    }

}