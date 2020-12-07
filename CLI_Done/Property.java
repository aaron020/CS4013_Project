package com.project;

/**
 * A property object
 * Written by Aaron Meade(19271034)
 */
public class Property {
    private Owners owners;
    private String address;
    private String eircode;
    private double estMarketVal;
    private String category;
    private boolean privateRes;
    private double propertyTax;

    /**
     * Constructs a Product Object
     * @param owners The owner of the property.
     * @param address The address of the property.
     * @param eircode The eircode of the property.
     * @param estMarketVal The estimated market value of the property.
     * @param category The location property of the property.
     * @param privateRes If the property is a private residence
     */
    public Property(Owners owners, String address, String eircode,
                    double estMarketVal, String category, boolean privateRes) {
        this.owners = owners;
        this.address = address;
        this.eircode = eircode;
        this.estMarketVal = estMarketVal;
        this.category = category;
        this.privateRes = privateRes;
        calcPropertyTax();
    }

    /**
     * Returns owners
     * @return String
     */
    public Owners getOwners() {
        return owners;
    }

    /**
     * Returns address
     * @return String
     */
    public String getAddress() {
        return address;
    }

    /**
     * Returns eircode
     * @return String
     */
    public String getEircode() {
        return eircode;
    }

    /**
     * Returns estMarketVal
     * @return double
     */
    public double getEstMarketVal() {
        return estMarketVal;
    }

    /**
     * Returns category
     * @return String
     */
    public String getCategory() {
        return category;
    }

    /**
     * Returns privateRes
     * @return boolean
     */
    public boolean isPrivateRes() {
        return privateRes;
    }

    /**
     * Returns propertyTax
     * @return double
     */
    public double getPropertyTax() {
        return propertyTax;
    }

    /**
     * Calculates the property tax
     */
    private void calcPropertyTax(){
        propertyTax = propertyTax + 100 + Rates() + Categories();
        if(!privateRes){
            propertyTax = propertyTax  + 100;
        }
    }

    /**
     * Calculates rate for property tax
     * @return double
     */
    private double Rates(){
        double PropertyTax = 0;
        int[] Values = {150_000,400_000,650_000};
        double[] Rates = {0,.01,.02,.04};
        for(int i = 0; i < Values.length;i++){
            if(estMarketVal <= Values[i]){
                PropertyTax = PropertyTax + estMarketVal*Rates[i]/100;
                return PropertyTax;
            }else if(estMarketVal > 650000){
                PropertyTax = PropertyTax + estMarketVal*.04/100;
                return PropertyTax;
            }
        }
        return PropertyTax;
    }

    /**
     * Calculates property tax based off of category
     * @return double
     */
    private double Categories(){
        double PropertyTax = 0;
        String[] categories = {"City","Large Town","Small Town","Village","Countryside"};
        int[] charge = {100,80,60,50,25};
        for(int i = 0; i< categories.length;i++){
            if(category.compareToIgnoreCase(categories[i]) == 0){
                PropertyTax = PropertyTax + charge[i];
            }
        }
        return PropertyTax;
    }

    @Override
    public String toString() {
        return "Name : "+ owners.toString() + "\n" +"Address: "+ address + "\n"
                +"Eircode: "+ eircode + "\n"+ "Estimated Market Value: " + estMarketVal + "\n"
                +"Category: "+ category + "\n" +
                "Property Tax: " + propertyTax + "\n";
    }
}
