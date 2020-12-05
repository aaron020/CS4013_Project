package com.project;

public class PropertyTaxData {
    private String eircode;
    private int year;
    private double propertyTax;
    private boolean paid;

    public PropertyTaxData(String eircode, int year, double propertyTax, boolean paid) {
        this.eircode = eircode;
        this.year = year;
        this.propertyTax = propertyTax;
        this.paid = paid;
    }

    public PropertyTaxData(String eircode,int year,double propertyTax, String paid){
        if(paid.compareToIgnoreCase("Not Paid") == 0){
            this.eircode = eircode;
            this.year = year;
            this.propertyTax = propertyTax;
            this.paid = false;
        }else{
            this.eircode = eircode;
            this.year = year;
            this.propertyTax = propertyTax;
            this.paid = true;
        }
    }

    public boolean equals(String eircode){
        if(eircode.compareToIgnoreCase(this.eircode) == 0){
            return true;
        }else{
            return false;
        }
    }

    public boolean equalRoute(String route){
        return route.compareToIgnoreCase(getRouting()) == 0;
    }

    public boolean overdue(int year){
        if(year == this.year){
            return !paid;
        }
        return false;
    }

    public boolean overdue(String routing){
        if(equalRoute(routing)){
            return !paid;
        }
        return false;
    }

    private String getRouting(){
        return eircode.substring(0,3);
    }

    public String toStringMore(){
        return eircode + " " + propertyTax + " was not paid.";
    }

    public double getPropertyTax() {
        return propertyTax;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        if(paid){
            return year + ",  " + propertyTax + " was paid";
        }else{
            return year + ",  " + propertyTax + " was not paid";
        }

    }

}
