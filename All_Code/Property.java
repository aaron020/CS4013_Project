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
    private Calculator c;

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
        //Remove all , to stop errors in the CSV
        address = address.replaceAll(","," ");
        eircode = eircode.replaceAll(","," ");
        this.owners = owners;
        this.address = address;
        this.eircode = eircode;
        this.estMarketVal = estMarketVal;
        this.category = category;
        this.privateRes = privateRes;
        c = new Calculator(estMarketVal,category,privateRes);
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
        return this.c.getPropertyTax();
    }

    public void changeCalc(double[] newRates,int[] charge){
        c.changeCalc(newRates,charge);
    }

    @Override
    public String toString() {
        return "Name : "+ owners.toString() + "\n" +"Address: "+ address + "\n"
                +"Eircode: "+ eircode + "\n"+ "Estimated Market Value: " + estMarketVal + "\n"
                +"Category: "+ category + "\n" +
                "Property Tax: " + c.getPropertyTax() + "\n";
    }
}
