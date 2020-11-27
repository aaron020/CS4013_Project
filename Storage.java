package com.project;

import java.util.Scanner;

public class Storage {


    public void addPropertyValues(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Firstname: ");
        String firstname = scanner.nextLine();

        System.out.println("Lastname: ");
        String lastname = scanner.nextLine();

        System.out.println("Address \n FirstLine: ");
        String firstLine = scanner.nextLine();

        System.out.println("SecondLine: ");
        String secondLine = scanner.nextLine();

        System.out.println("City ");
        String city = scanner.nextLine();

        System.out.println("County: ");
        String county = scanner.nextLine();

        System.out.println("Country: ");
        String country = scanner.nextLine();

        System.out.println("Eircode: ");
        String eircode = scanner.nextLine();

        System.out.println("Estimated Market Value: ");
        double estMarketValue = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Category: ");
        String category = scanner.nextLine();

        System.out.println("Private Residence: (Y/N) ");
        String privateResidence = scanner.nextLine();

        boolean privateRes;
        if(privateResidence.compareToIgnoreCase("Y") ==0){
            privateRes = true;
        }else{
            privateRes = false;
        }
        Property name = new Property(new Owners(firstname,lastname),new Address(firstLine,secondLine,city,county,country),
                eircode,estMarketValue,category,privateRes);
        CSV writer = new CSV();
        writer.add(name);
        Properties TempStorage = new Properties();
        TempStorage.addProperty(name);
    }
}
