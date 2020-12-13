package com.project;

/**
 * Used for calculating property tax
 * @author Aaron Meade(19271034)
 */

public class Calculator {
    private double marketVal;
    private double[] rates = {0,.01,.02,.04};
    private int[] charge = {100,80,60,50,25};
    private String[] categories = {"City","Large Town","Small Town","Village","Countryside"};
    private String category;
    private boolean privateRes;

    /**
     * Constructs a Calculator object
     * @param marketval Estimated Market Value
     * @param category Category of the property
     * @param privateRes If it is a private residence (true) or not (false)
     */
    public Calculator(double marketval, String category,boolean privateRes){
        this.marketVal = marketval;
        this.category = category;
        this.privateRes = privateRes;
    }

    /**
     * Calculates the property tax
     * @return
     */
    private double calc(){
        double propertyTax = 0;
        propertyTax = propertyTax + 100 + Rates() + Categories();
        if(!privateRes){
            propertyTax = propertyTax  + 100;
        }
        return propertyTax;

    }

    /**
     * Calculates the rate
     * @return
     */
    private double Rates(){
        double PropertyTax = 0;
        int[] Values = {150_000,400_000,650_000};
        for(int i = 0; i < Values.length;i++){
            if(marketVal <= Values[i]){
                PropertyTax = PropertyTax + marketVal *rates[i]/100;
                return PropertyTax;
            }else if(marketVal > 650000){
                PropertyTax = PropertyTax + marketVal *rates[3]/100;
                return PropertyTax;
            }
        }
        return PropertyTax;
    }

    /**
     * Calculates charge based on the category
     * @return
     */
    private double Categories(){
        double PropertyTax = 0;
        int[] charge = {100,80,60,50,25};
        for(int i = 0; i< categories.length;i++){
            if(category.compareToIgnoreCase(categories[i]) == 0){
                PropertyTax = PropertyTax + charge[i];
            }
        }
        return PropertyTax;
    }

    /**
     * Allows you to change the rates and charges
     * @param rates Rates by estimated market value
     * @param charge Charges by category
     */
    public void changeCalc(double[] rates,int[] charge){
        for(int j = 0; j < this.rates.length;j++){
            if(rates[j] != -1){
                this.rates[j] = rates[j];
            }
        }
        for(int j = 0; j < this.charge.length;j++){
            if(charge[j] != -1){
                this.charge[j] = charge[j];
            }
        }
    }

    /**
     * Gets the property tax
     * @return
     */
    public double getPropertyTax() {
        return calc();
    }
}
