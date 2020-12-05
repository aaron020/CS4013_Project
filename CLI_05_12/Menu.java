package com.project;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private Properties properties = new Properties();
    private PropertyTax propertyTax = new PropertyTax();
    Scanner ask = new Scanner(System.in);
    public void start(){
        CSV csv = new CSV();
        properties.addProperties(csv.read());
        boolean run = true;
        while (run){
            System.out.println("1) Find My Properties \n2) Add Property \n3) Property Tax Info \n" +
                    "4) Available Properties (By Owner)");
            int option = ask.nextInt();
            switch (option){
                case 1:
                    byName();
                    break;
                case 2:
                    addProperty();
                    break;
                case 3:
                    PropertyTaxInfo();
                    break;
                case 4:
                    ArrayList<String> s = properties.getPropertiesByName();
                    for(String name : s){
                        System.out.println(name);
                    }
            }
        }
    }

    private void byName(){
        ask.nextLine();
        System.out.println("Enter first name: ");
        String firstname = ask.nextLine();
        System.out.println("Enter last name: ");
        String lastname = ask.nextLine();
        ArrayList<Property> searchByName;
        searchByName = properties.searchByName(new Owners(firstname,lastname));
        Property choice = choice(searchByName);
        ArrayList<PropertyTaxData> tx = propertyTax.findByProperty(choice);
        for(PropertyTaxData t:tx){
            System.out.println(t);
        }

    }

    private void addProperty(){
        ask.nextLine();
        System.out.println("Firstname: ");
        String first = ask.nextLine();

        System.out.println("Lastname: ");
        String last = ask.nextLine();

        System.out.println("Address: ");
        String address = ask.nextLine();

        System.out.println("Eircode: ");
        String eircode = ask.nextLine();

        System.out.println("Estimated Market Value: ");
        double estMarketValue = ask.nextDouble();
        ask.nextLine();
        System.out.println("Category: ");
        String category = ask.nextLine();

        System.out.println("Private Residence: (Y/N) ");
        String privateResidence = ask.nextLine();

        boolean privateRes;
        if(privateResidence.compareToIgnoreCase("Y") ==0){
            privateRes = true;
        }else{
            privateRes = false;
        }
        Property name = new Property(new Owners(first,last),address,
                eircode,estMarketValue,category,privateRes);
        CSV write = new CSV();
        write.add(name);
        properties.addProperty(name);
        PropertyTax t = new PropertyTax();
        t.addPropertyTax(name);
    }

    private void PropertyTaxInfo(){
        boolean run = true;
        while(run){
            System.out.println("1) Property Tax by name \n" + "2) All Property tax info \n" +
                    "3) Overdue Property tax \n" + "4) Overdue Property tax (Based on routing key) \n"+
                    "5) Property tax statistics (Based on routing key)");
            int choice = ask.nextInt();
            switch (choice){
                case 1:
                    byName();
                    break;
                case 2:
                    Property picked = choice(properties.getProperties());
                    ArrayList<PropertyTaxData> tx = propertyTax.findByProperty(picked);
                    for(PropertyTaxData t:tx){
                        System.out.println(t);
                    }
                    break;
                case 3:
                    System.out.println("Enter year: ");
                    int year = ask.nextInt();
                    ArrayList<PropertyTaxData> overdue = propertyTax.overdue(year);
                    for(PropertyTaxData t:overdue){
                        System.out.println(t.toStringMore() + "\n");
                    }
                    break;
                case 4:
                    System.out.println("Enter year: ");
                    int year2 = ask.nextInt();
                    ask.nextLine();
                    System.out.println("Enter routing key: ");
                    String routing = ask.nextLine();
                    ArrayList<PropertyTaxData> overdue2 = propertyTax.overdue(year2,routing);
                    for(PropertyTaxData t : overdue2){
                        System.out.println(t.toStringMore() + "\n");
                    }
                    break;
                case 5:
                    PropertyTaxStats();
                    break;
            }
        }
    }

    private void PropertyTaxStats(){
        boolean run = true;
        while (run){
            System.out.println("1) Total tax paid \n2) Average tax paid \n3) Number and percentage of " +
                    "property taxes paid");
            int choice = ask.nextInt();
            switch (choice){
                case 1:
                    ask.nextLine();
                    System.out.println("Enter routing code: ");
                    String routing = ask.nextLine();
                    System.out.println(propertyTax.totalTax(routing));
                    break;
                case 2:

                    break;
            }
        }
    }

    private Property choice(ArrayList<Property> p){
        int count = 0;
        for(Property prop : p){
            count ++;
            System.out.println(count+") " + prop);
        }
        Scanner in = new Scanner(System.in);
        System.out.println("Enter number value to display Property Tax information: ");
        int choice = in.nextInt();
        return p.get(choice-1);

    }
}
