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
                    "4) Available Properties (By Owner) \n5) Exit");
            int option = ask.nextInt();
            switch (option){
                case 1:
                    byName();
                    System.out.println("------------------------");
                    break;
                case 2:
                    addProperty();
                    break;
                case 3:
                    PropertyTaxInfo();
                    break;
                case 4:
                    ArrayList<String> s = properties.getPropertiesByName();
                    for(String name : s) {
                        System.out.println(name);
                    }
                    System.out.println("------------------------");
                    break;
                case 5:
                    run = false;
                    break;

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
        if(searchByName.size() == 0){
            System.out.println("Name not found");
            return;
        }else{
            Property choice = choice(searchByName);
            ArrayList<PropertyTaxData> tx = propertyTax.findByProperty(choice);
            for(PropertyTaxData t:tx){
                System.out.println(t);
            }
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
        String category = category();
        ask.nextLine();
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
        propertyTax.addPropertyTax(name);
    }

    private void PropertyTaxInfo(){
        boolean run = true;
        while(run){
            System.out.println("1) Property Tax by name \n" + "2) All Property tax info \n" +
                    "3) Overdue Property tax \n" + "4) Overdue Property tax (Based on routing key) \n"+
                    "5) Property tax statistics (Based on routing key) \n" + "6) Quit");
            int choice = ask.nextInt();
            switch (choice){
                case 1:
                    byName();
                    System.out.println("------------------------");
                    break;
                case 2:
                    Property picked = choice(properties.getProperties());
                    ArrayList<PropertyTaxData> tx = propertyTax.findByProperty(picked);
                    for(PropertyTaxData t:tx){
                        System.out.println(t);
                    }
                    System.out.println("------------------------");
                    break;
                case 3:
                    System.out.println("Enter year: ");
                    int year = ask.nextInt();
                    ArrayList<PropertyTaxData> overdue = propertyTax.overdue(year);
                    for(PropertyTaxData t:overdue){
                        System.out.println(t.toStringMore() + "\n");
                    }
                    System.out.println("------------------------");
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
                case 6:
                    run =false;
                    break;
            }
        }
    }

    private void PropertyTaxStats(){
        boolean run = true;
        while (run){
            System.out.println("1) Total tax paid \n2) Average tax paid \n3) Number and percentage of " +
                    "property taxes paid \n4) Quit");
            int choice = ask.nextInt();
            switch (choice){
                case 1:
                    ask.nextLine();
                    System.out.println("Enter routing code: ");
                    String routing = ask.nextLine();
                    System.out.println(propertyTax.totalTax(routing));
                    break;
                case 2:
                    ask.nextLine();
                    System.out.println("Enter routing code: ");
                    String routing2 = ask.nextLine();
                    System.out.println(propertyTax.averageTax(routing2));
                    break;
                case 3:
                    ask.nextLine();
                    System.out.println("Enter routing code: ");
                    String routing3 = ask.nextLine();
                    System.out.println(Math.round(propertyTax.taxPercent(routing3)) + "%");
                    break;
                case 4:
                    run = false;
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
        System.out.println("Enter number value to display Property Tax information: ");
        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();
        if(choice <= p.size()){
            return p.get(choice-1);
        }else{
            System.out.println("Invalid Number.");
            return p.get(0);
        }
    }

    private String category(){
        String[] categories = {"City","Large town","Small town","Village","Countryside"};
        int i = 0;
        for(String a : categories){
            i++;
            System.out.println(i+ ")" + a);
        }
        System.out.println("Choose a category: ");
        int choice = ask.nextInt();
        return categories[choice-1];

    }
}
