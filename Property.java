package com.company;

/**
 * A property
 */
public class Property {
    private String owners;
    private String address;
    private String eircode;
    private double estMarketVal;
    private String category;
    private boolean privateRes;

    /**
     * Constructs a Product Object
     * @param owners The owner of the property.
     * @param address The address of the property.
     * @param eircode The eircode of the property.
     * @param estMarketVal The estimated market value of the property.
     * @param category The location property of the property.
     * @param privateRes If the property is a private residence
     */
    public Property(String owners, String address, String eircode,
                    double estMarketVal, String category, boolean privateRes) {
        this.owners = owners;
        this.address = address;
        this.eircode = eircode;
        this.estMarketVal = estMarketVal;
        this.category = category;
        this.privateRes = privateRes;
    }

    /**
     * Calculates the property tax
     * @return property tax as a double.
     */

    public double calcPropertyTax(){
        double PropertyTax = 100;
        int[] Values = {150_000,400_000,650_000};
        double[] Rates = {0,.01,.02,.04};
        for(int i = 0; i < Values.length;i++){
            if(estMarketVal <= Values[i]){
                PropertyTax = PropertyTax + estMarketVal*Rates[i]/100;
                break;
            }else if(estMarketVal > 650000){
                PropertyTax = PropertyTax + estMarketVal*.04/100;
                break;
            }
        }

        String[] categories = {"City","Large Town","Small Town","Village","countryside"};
        int[] charge = {100,80,60,50,25};
        for(int i = 0; i< categories.length;i++){
            if(category.compareToIgnoreCase(categories[i]) == 0){
                PropertyTax = PropertyTax + charge[i];
            }
        }
        if(!privateRes){
            PropertyTax = PropertyTax  + 100;
        }

        return PropertyTax;

    }
}
