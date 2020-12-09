package com.project;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Scenes {
    private Scene baseScene, findMyProperty,addProperty,availableOwner,propertyTaxInfo
            , allInfo,overdue, stats;
    private Stage window;
    private Properties properties = new Properties();
    private PropertyTax propertyTax = new PropertyTax();
    private TextField firstname = new TextField();
    private TextField lastname = new TextField();
    private TextField choice = new TextField();
    private TextArea propertyByName = new TextArea();

    private TextField addFirstname = new TextField();
    private TextField addLastname = new TextField();
    private TextArea address = new TextArea();
    private TextField addEircode = new TextField();
    private TextField addMarketValue = new TextField();
    private ChoiceBox<String> category = new ChoiceBox<String>();
    private CheckBox privateProp = new CheckBox("Private Property");

    private ChoiceBox<String> names = new ChoiceBox<>();
    private TextField choice2 = new TextField();
    private TextArea allInfoText = new TextArea();

    private ChoiceBox<Integer> year = new ChoiceBox<>();
    private ChoiceBox<String> routingKey = new ChoiceBox<>();
    private TextArea overdueText = new TextArea();

    private TextField total = new TextField();
    private TextField average = new TextField();
    private TextField percentage = new TextField();

    private ChoiceBox<String> routingKey2 = new ChoiceBox<>();

    public Scenes(Stage window){
        this.window = window;
        CSV csv = new CSV();
        properties.addProperties(csv.read());
        //All scenes are loaded
        baseScene();
        findMyProperty();
        addProperty();
        availableByOwner();
        propertyTaxInfo();
        allInfo();
        overdue();
        stats();
        //Only needs to be loaded once
        routingChoice();
        routingChoice2();
    }

    /**
     * Base Scene i.e. the first menu that appears when you open the GUI
     */
    private void baseScene(){
        BorderPane border = new BorderPane();
        Label label1 = new Label("Property Management \nSystem");
        label1.setTextAlignment(TextAlignment.CENTER);
        label1.setFont(new Font("Arial",50));
        border.setTop(label1);
        HBox hbox = baseSceneHBox();
        border.setCenter(hbox);
        BorderPane.setAlignment(label1,Pos.CENTER);
        baseScene = new Scene(border,600,600);
    }

    /**
     * All the buttons in the base scene are places in a HBox
     * @return
     */
    private HBox baseSceneHBox() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(10, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #95DAFF;");

        Button findMyProperty = new Button("Find My Property");
        findMyProperty.setPrefSize(120, 20);
        findMyProperty.setOnAction(e -> window.setScene(this.findMyProperty));

        Button addProperty = new Button("Add Property");
        addProperty.setPrefSize(100, 20);
        addProperty.setOnAction(e -> window.setScene(this.addProperty));

        Button propertyTaxInfo = new Button("Property Tax Info");
        addProperty.setPrefSize(100, 20);
        propertyTaxInfo.setOnAction(e -> window.setScene(this.propertyTaxInfo));

        Button availableByOwner = new Button("Available Properties");
        addProperty.setPrefSize(100, 20);
        availableByOwner.setOnAction(e -> window.setScene(this.availableOwner));

        Button CLI = new Button("CLI");
        CLI.setPrefSize(75, 20);
        CLI.setOnAction(e -> window.close());
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(findMyProperty, addProperty,propertyTaxInfo,availableByOwner,CLI);
        return hbox;
    }

    /**
     * Find My property scene -> Opened when "Find My Property" button is pressed
     */
    private void findMyProperty(){
        BorderPane border = new BorderPane();
        border.setLeft(nameSearchVBox());
        propertyByName.setEditable(false);
        border.setCenter(propertyByName);
        findMyProperty = new Scene(border,600,600);
    }

    /**
     * Find my property has a Vbox with search button and text fields to enter firstname, lastname and
     * property number
     * @return
     */
    private VBox nameSearchVBox(){
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(30,10,15,10));
        vbox.setStyle("-fx-background-color: #95DAFF;");
        vbox.setSpacing(10);

        Button back = new Button("Back");
        back.setOnAction(e-> window.setScene(this.baseScene));

        Label l1 = new Label("Firstname: ");
        l1.setStyle("-fx-font-weight: bold");

        Label l2 = new Label("Lastname: ");
        l2.setStyle("-fx-font-weight: bold");

        Button searchNames = new Button("Search");
        searchNames.setOnAction(e -> searchByName());

        Label l3 = new Label("Enter property Number: ");
        l3.setStyle("-fx-font-weight: bold");
        choice.setEditable(false);
        Button propertyTax = new Button("Property Tax");
        propertyTax.setOnAction(e -> propertyTaxByName());


        vbox.getChildren().addAll(back,l1,firstname,l2,lastname, searchNames,l3,choice,propertyTax);
        return vbox;
    }

    /**
     * When the search button is clicked, the properties owned by the specified person are
     * set to the textarea in the centre of the border pane
     */
    private void searchByName(){
        choice.setEditable(true);
        String firstname = this.firstname.getText();
        String lastname = this.lastname.getText();
        ArrayList<Property> searchByName;
        searchByName = properties.searchByName(new Owners(firstname,lastname));
        StringBuilder s = new StringBuilder();
        if(searchByName.size() == 0){
            choice.setEditable(false);
            s = new StringBuilder("No properties found");
        }else{
            int count = 0;
            for(Property p : searchByName){
                count++;
                s.append(count).append(")").append(p.toString()).append("\n\n");
            }
        }
        propertyByName.setText(s.toString());
    }

    /**
     * Displays the property tax info of the specified property
     */
    private void propertyTaxByName(){
        String firstname = this.firstname.getText();
        String lastname = this.lastname.getText();
        String choice = this.choice.getText();
        StringBuilder s = new StringBuilder();
        ArrayList<Property> searchByName;
        searchByName = properties.searchByName(new Owners(firstname,lastname));
        int num;
        try {
            num = Integer.parseInt(choice);
        }catch (NumberFormatException e){
            return;
        }
        if(searchByName.size() == 0){
            s = new StringBuilder("No properties found");
        }else{
            if(num <= searchByName.size() && num > 0){
                Property p = searchByName.get(num -1);
                ArrayList<PropertyTaxData> tx = propertyTax.findByProperty(p);
                for(PropertyTaxData t : tx){
                    s.append(t.toString()).append("\n\n");
                }
            }else{
                return;
            }

        }
        propertyByName.setText(s.toString());
    }


    private void addProperty(){
        BorderPane border = new BorderPane();
        border.setCenter(addPropertyVBox());
        border.setBottom(addPropertyHBox());
        addProperty = new Scene(border,600,600);
    }

    private VBox addPropertyVBox(){
        VBox vbox= new VBox();
        vbox.setPadding(new Insets(30,10,15,10));
        vbox.setStyle("-fx-background-color: #95DAFF;");
        vbox.setSpacing(10);

        Label l1 = new Label("Firstname: ");
        Label l2 = new Label("Lastname: ");
        Label l3 = new Label("Address: ");
        Label l4 = new Label("Eircode: ");
        Label l5 = new Label("Estimated Market Value: ");
        String[] s = {"City","Large Town","Small town","Village","Countryside"};
        for(String category : s){
            this.category.getItems().add(category);
        }
        address.setPrefHeight(50);


        vbox.getChildren().addAll(l1,addFirstname,l2,addLastname,l3,address,l4,addEircode,l5,addMarketValue,category,privateProp);
        return vbox;
    }

    private HBox addPropertyHBox(){
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(30,10,15,10));
        hbox.setStyle("-fx-background-color: #95DAFF;");
        hbox.setSpacing(10);

        Button add = new Button("Add");
        add.setOnAction(e -> add());
        Button clear = new Button("Clear");
        clear.setOnAction(e -> clear());
        Button back = new Button("Back");
        back.setOnAction(e -> window.setScene(baseScene));
        hbox.getChildren().addAll(add,clear,back);

        return hbox;
    }

    private void clear(){
        addFirstname.clear();
        addLastname.clear();
        address.clear();
        addEircode.clear();
        addMarketValue.clear();
        category.getSelectionModel().clearSelection();
        privateProp.setSelected(false);
    }

    private void add(){
        String marketVal = addMarketValue.getText();
        double marketValue = Double.parseDouble(marketVal);
        String value = category.getValue();
        Property name = new Property(new Owners(addFirstname.getText(),addLastname.getText()),
                address.getText(),addEircode.getText(),marketValue,value,privateProp.isSelected());
        CSV write = new CSV();
        write.add(name);
        properties.addProperty(name);
        propertyTax.addPropertyTax(name);
    }

    private void availableByOwner(){
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(30,10,15,10));
        vbox.setStyle("-fx-background-color: #95DAFF;");
        vbox.setSpacing(10);
        TextArea names = new TextArea();
        ArrayList<String> s = properties.getPropertiesByName();
        StringBuilder owner = new StringBuilder();
        for(String str : s){
            owner.append(str).append("\n");
        }
        names.setText(owner.toString());
        names.setStyle("-fx-font-size: 30");
        names.setEditable(false);

        Button back = new Button("Back");
        back.setOnAction(e -> window.setScene(baseScene));
        vbox.getChildren().addAll(names,back);
        availableOwner = new Scene(vbox,600,600);

    }

    private void propertyTaxInfo(){
        BorderPane border = new BorderPane();
        Label label1 = new Label("Property Tax \nInfo");
        label1.setTextAlignment(TextAlignment.CENTER);
        label1.setFont(new Font("Arial",50));
        border.setTop(label1);
        BorderPane.setAlignment(label1,Pos.CENTER);
        border.setCenter(propertyTaxInfoHBox());

        propertyTaxInfo = new Scene(border,600,600);
    }

    private HBox propertyTaxInfoHBox(){
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(10, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #95DAFF;");

        Button allInfo = new Button("All Info");
        allInfo.setPrefSize(120, 20);
        allInfo.setOnAction(e -> window.setScene(this.allInfo));

        Button overdue = new Button("Overdue");
        overdue.setPrefSize(100, 20);
        overdue.setOnAction(e -> window.setScene(this.overdue));

        Button stats = new Button("Statistics");
        stats.setPrefSize(100, 20);
        stats.setOnAction(e -> window.setScene(this.stats));

        Button back = new Button("Back");
        back.setPrefSize(100, 20);
        back.setOnAction(e -> window.setScene(baseScene));
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(allInfo, overdue,stats,back);
        return hbox;
    }

    private void allInfo(){
        BorderPane border = new BorderPane();
        border.setLeft(allInfoVBox());
        allInfoText.setEditable(false);
        border.setCenter(allInfoText);
        allInfo = new Scene(border,600,600);
    }

    private VBox allInfoVBox(){
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(30,10,15,10));
        vbox.setStyle("-fx-background-color: #95DAFF;");
        vbox.setSpacing(10);
        ArrayList<String> s = properties.getPropertiesByName();
        for(String name : s){
            names.getItems().add(name);
        }

        Button search = new Button("Search");
        search.setOnAction(e -> allInfoText());

        Label l1 = new Label("Enter property Number: ");
        choice2.setEditable(false);

        Button propTax= new Button("Property Tax");
        propTax.setOnAction(e -> allInfoTextTax());

        Button back = new Button("Back");
        back.setOnAction(e -> window.setScene(propertyTaxInfo));

        vbox.getChildren().addAll(names,search,l1,choice2,propTax,back);
        return vbox;
    }

    private void allInfoText(){
        choice2.setEditable(true);
        String fullname = names.getValue();
        String[] name = fullname.split(" ");
        ArrayList<Property> searchByName = properties.searchByName(new Owners(name[0],name[1]));
        StringBuilder s = new StringBuilder();
        int count = 0;
        for(Property p : searchByName){
            count++;
            s.append(count).append(")").append(p.toString()).append("\n\n");
        }
        allInfoText.setText(s.toString());

    }

    private void allInfoTextTax(){
        String fullname = names.getValue();
        String[] name = fullname.split(" ");
        String choice2 = this.choice2.getText();
        StringBuilder s = new StringBuilder();
        ArrayList<Property> searchByName;
        searchByName = properties.searchByName(new Owners(name[0],name[1]));
        int num;
        try {
            num = Integer.parseInt(choice2);
        }catch (NumberFormatException e){
            return;
        }
        if(searchByName.size() == 0){
            s = new StringBuilder("No properties found");
        }else{
            if(num <= searchByName.size() && num > 0){
                Property p = searchByName.get(num -1);
                ArrayList<PropertyTaxData> tx = propertyTax.findByProperty(p);
                for(PropertyTaxData t : tx){
                    s.append(t.toString()).append("\n\n");
                }
            }else{
                return;
            }

        }
        allInfoText.setText(s.toString());
    }

    private void overdue(){
        BorderPane border = new BorderPane();
        border.setLeft(overdueVBox());
        border.setCenter(overdueText);
        overdueText.setEditable(false);
        overdue = new Scene(border,600,600);
    }

    private VBox overdueVBox(){
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(30,10,15,10));
        vbox.setStyle("-fx-background-color: #95DAFF;");
        vbox.setSpacing(10);
        Label l1 = new Label("Set Year: ");
        for(int i = 2000; i <= 2020; i++){
            year.getItems().add(i);
        }

        Button search = new Button("Search");
        search.setOnAction(e -> overdueText());
        Button back = new Button("Back");
        back.setOnAction(e -> window.setScene(propertyTaxInfo));
        Label l2 = new Label("Sort by routing key: ");
        vbox.getChildren().addAll(l1,year,search,l2, routingKey2,back);
        return vbox;
    }

    private void overdueText(){
        int year = this.year.getValue();
        String routingKey = this.routingKey.getValue();
        StringBuilder s = new StringBuilder();
        if(routingKey == null){
            ArrayList<PropertyTaxData> overdueYear = propertyTax.overdue(year);
            if(overdueYear.size() == 0){
                s.append("No data Found");
            }else{
                for(PropertyTaxData t: overdueYear){
                    s.append(t.toStringMore()).append("\n\n");
                }
            }

        }else{
            ArrayList<PropertyTaxData> overdueRoute = propertyTax.overdue(year,routingKey);
            if(overdueRoute.size() == 0){
                s.append("No data Found");
            }else{
                for(PropertyTaxData t: overdueRoute){
                    s.append(t.toStringMore()).append("\n\n");
                }
            }

        }
        this.routingKey.getSelectionModel().clearSelection();
        overdueText.setText(s.toString());
    }

    private void routingChoice(){
        ArrayList<String> routingKeys = propertyTax.getRouting();
        for(String str : routingKeys){
            routingKey.getItems().add(str);
        }
    }

    private void routingChoice2(){
        ArrayList<String> routingKeys = propertyTax.getRouting();
        for(String str : routingKeys){
            routingKey2.getItems().add(str);
        }
    }

    private void stats(){
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(20,10,15,10));
        vbox.setStyle("-fx-background-color: #95DAFF;");
        vbox.setSpacing(25);
        Label l1 = new Label("Select Routing Key: ");

        Label l2 = new Label("Total tax Paid: ");
        Button calc1 = new Button("Calculate");
        total.setEditable(false);
        calc1.setOnAction(e -> total());

        Label l3 = new Label("Average tax Paid: ");
        Button calc2 = new Button("Calculate");
        average.setEditable(false);
        calc2.setOnAction(e -> average());

        Label l4 = new Label("Percentage of property taxes paid: ");
        Button calc3 = new Button("Calculate");
        percentage.setEditable(false);
        calc3.setOnAction(e -> percentage());

        Button back = new Button("Back");
        back.setOnAction(e -> window.setScene(propertyTaxInfo));


        vbox.getChildren().addAll(l1,routingKey,l2,total,calc1,l3,average,calc2,l4,percentage,calc3,back);
        this.stats = new Scene(vbox,600,600);
    }

    private void total(){
        if(routingKey.getValue() != null){
            double total = propertyTax.totalTax(routingKey.getValue());
            String t = Double.toString(total);
            this.total.setText(t);
        }else{
            this.total.setText("No routing key entered.");
        }

    }

    private void average(){
        if(routingKey.getValue() != null){
            double average = propertyTax.averageTax(routingKey.getValue());
            String t = Double.toString(average);
            this.average.setText(t);
        }else{
            this.average.setText("No routing key entered.");
        }

    }

    private void percentage(){
        if(routingKey.getValue() != null){
            double percentage = propertyTax.taxPercent(routingKey.getValue());
            String t = Double.toString(percentage);
            this.percentage.setText(t);
        }else{
            this.percentage.setText("No routing key entered.");
        }

    }


    public Scene getBaseScene() {
        return baseScene;
    }
}
