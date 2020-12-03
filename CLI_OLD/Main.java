package com.project;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {



    public static void main(String[] args) {
        Properties p = new Properties();
        Scanner ask = new Scanner(System.in);
        boolean cont = true;
        while(cont){
            System.out.println("1) Add Property 2) Search 3) Quit");
            int choice = ask.nextInt();
            if(choice == 1){
                Storage add = new Storage();
                add.addPropertyValues();
            }else if(choice == 2){
                System.out.println("Search By : 1) Name 2) Address 3) Eircode 4) Estimated Market Value " +
                        "5) Category 6) Private Residence");
                int searchChoice = ask.nextInt();
                ask.nextLine();
                if(searchChoice == 1){
                    System.out.println("FirstName: ");
                    String firstname = ask.nextLine();
                    System.out.println("LastName: ");
                    String lastname = ask.nextLine();
                    ArrayList<Property> name = new ArrayList<>();
                    name = p.searchByName(new Owners(firstname,lastname));
                    for(Property prop : name){
                        System.out.println(prop);
                    }
                }

            }else if(choice == 3){
                cont = false;
            }
        }



    }
}
