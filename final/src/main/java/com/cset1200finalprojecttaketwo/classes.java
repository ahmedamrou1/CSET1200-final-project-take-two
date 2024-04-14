package com.cset1200finalprojecttaketwo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


import org.apache.commons.lang3.StringUtils;

class Listing {
    private int id = 0;
    String itemName;
    String itemDescription;
    String itemSeller;
    Double itemRating;
    Double sellerRating;
    int itemPrice;
    java.time.LocalDateTime dateCreated = LocalDateTime.now();
    public Listing (int idd, String name, String description, String seller, Double rating, Double sellerrating, int price) {
        id = idd;
        itemName = name;
        itemDescription = description;
        itemSeller = seller;
        itemRating = rating;
        sellerRating = sellerrating;
        itemPrice = price;
    }

    public int getItemId() {
        return id;
    }
    public String getItemName() {
        return itemName;
    }
    public String getItemDescription() {
        return itemDescription;
    }
    public String getItemSeller() {
        return itemSeller;
    }
    public Double getItemRating() {
        return itemRating;
    }
    public Double getSellerRating() {
        return sellerRating;
    }
    public int getItemPrice() {
        return itemPrice;
    }
    public java.time.LocalDateTime getDateCreated() {
        return dateCreated;
    }
    public void setItemName(String name) {
        itemName = name;
    }
    public void setItemDescription(String desc) {
        itemDescription = desc;
    }
    public void setItemSeller(String seller) {
        itemSeller = seller;
    }
    public void setItemRating(Double rate) {
        itemRating = rate;
    }
    public void setSellerRating (Double rate) {
        sellerRating = rate;
    }
    public void setItemPrice (int price) {
        itemPrice = price;
    }
}

class Inventory {
    Scanner scanner = new Scanner(System.in);
    ArrayList<Listing> listings = new ArrayList<>();
    ArrayList<String> log = new ArrayList<>();

    public Inventory() {
        for (int i=0; i < 10; i++) {
            Listing thisListing = new Listing(i, ("Listing" + i), ("This is the description for listing" + i), "urmum", 5.0, 4.0, (300000 * i));
            listings.add(thisListing);
        }
        Listing testListing = new Listing(555, "The Bruh Button 5000,00000,0000", "This magnificnet button lorem ipsum blah blah blah blah blah, ya know?", "urmum products", 4.0, 3.0, 1010010);
        listings.add(testListing);
    }
    Integer promptForMenu(){
        while(true){
            System.out.println("Choose from the following menu:\n1 - View Inventory\n2 - Purchase Inventory\n3 - Add Inventory\n4 - View Logs\n5 - Exit\n");
            try{
                String inputAsString = scanner.nextLine();
                Integer inputAsInteger = Integer.parseInt(inputAsString);
                if(inputAsInteger < 1 || inputAsInteger > 5) continue;
                else return inputAsInteger;}
            catch(Exception e){
                continue;
            }
        }
    }
    void closeScanner(){
        scanner.close();
    }
    void addToLog(String s){
        LocalDateTime unformattedDateTime = LocalDateTime.now();
        OffsetDateTime offsetDateTime = unformattedDateTime.atOffset(ZoneOffset.UTC);
        DateTimeFormatter formatter = DateTimeFormatter.RFC_1123_DATE_TIME.withLocale(Locale.US);
        String timeOfAction = offsetDateTime.format(formatter);


        log.add(timeOfAction + " --- " + s);
    }
    void viewLog(){
        System.out.println("--------------------------- log ---------------------------\n");
        if(log.isEmpty()){
            System.out.println("\nNo actions this session - log is empty\n");
        }
        for(String action:log){
            System.out.println(action + "\n");
        }
        System.out.println("--------------------------- end of log ---------------------------\n");
    }
    void viewInventory() {
        addToLog("User viewed inventory");
        System.out.println(StringUtils.rightPad("id", 8) + StringUtils.rightPad("Item", 34) + StringUtils.rightPad("Description", 82) + StringUtils.rightPad("Price", 12) + StringUtils.rightPad("Rating", 12) + StringUtils.rightPad("Seller", 22) + "Seller Rating");
        for (Listing listing : listings) {
            String id = StringUtils.rightPad(String.valueOf(listing.getItemId()), 6);
            String itemName = StringUtils.rightPad(listing.getItemName(), 32);
            String itemDescription = StringUtils.rightPad(listing.getItemDescription(), 80);
            String price = StringUtils.rightPad("$" + String.valueOf(listing.getItemPrice()), 10);
            String itemRating = StringUtils.rightPad(String.valueOf(listing.getItemRating()), 10);
            String itemSeller = StringUtils.rightPad(listing.getItemSeller(), 20);
            String sellerRating = String.valueOf(listing.getSellerRating());
            
            System.out.printf("%s  %s  %s  %s  %s  %s  %s%n\n", id, itemName, itemDescription, price, itemRating, itemSeller, sellerRating);
        }
    }
    void addInventory(int idd, String name, String description, String seller, Double rating, Double sellerrating, int price){
        Listing newListing = new Listing(idd, name, description, seller, rating, sellerrating, price);
        addToLog(String.format("User added item with id %d to inventory", idd));
        listings.add(newListing);
    }
    void removeInventory(Integer i) {
        Iterator<Listing> iterator = listings.iterator();
        while (iterator.hasNext()) {
            Listing listing = iterator.next();
            if (listing.getItemId() == i) {
                iterator.remove();
            }
        }
    }
    ArrayList<Integer> getAllItemIds() {
        ArrayList<Integer> ids = new ArrayList<>();
        for(Listing listing:listings) {
            ids.add(listing.getItemId());
        }
        return ids;
    }
    ArrayList<Listing> getInventory() {
        return listings;
    }
    Listing getItem(Integer id) {
        for(Listing listing:listings){
            if(listing.getItemId() == id){
                return listing;
            }
        }
        return null;
    }
    Boolean purchaseItem(){
        System.out.println("What is the id of the item you would like to purchase? (to go back, type '418')");
        do{
            String idAsString = scanner.nextLine();
            try {
                Integer idAsInteger = Integer.parseInt(idAsString);
                if(idAsInteger == 418) {
                    return null;
                }
                if(!getAllItemIds().contains(idAsInteger)){
                    throw new Exception();
                }
                removeInventory(idAsInteger);
                addToLog(String.format("User purchased item with id %d", idAsInteger));
                return true;
            } catch (Exception e) {
                System.out.print("This id is either invalid or is not assocaited with a product. Please type a valid id (to go back, type '418')\n");
            } 
        } while(true);
    }
    Integer getdNewItemId() {
        System.out.println("Enter an integer for a new item ID which is not being used (type 418 to go back)");
        do {
            String idAsString = scanner.nextLine();
            if(idAsString == ""){
                idAsString = scanner.nextLine();
            }
            try {
                Integer idAsInteger = Integer.parseInt(idAsString);
                if(idAsInteger == 418) {
                    return null;
                }
                if(!getAllItemIds().contains(idAsInteger)){
                    return idAsInteger;
                }
                System.out.println("This id is either in use or is invalid. Enter an integer for a new item ID which is not being used (type 418 to go back)");
            } catch (Exception NumberFormatException) {
                System.out.println("This id is either in use or is invalid. Enter an integer for a new item ID which is not being used (type 418 to go back)");
            }
        }while(true);
    }
    String getNewItemName(){
        System.out.println("Enter an item name (maximum 32 characters) (type 418 to go back)");
        while(true){
            String itemName = scanner.next();
            if(itemName == "418"){
                return null;
            }
            if(itemName.length() <= 32){
                return itemName;
            }
            System.out.println("Item name must be less than or equal to 32 characters");
        }    
    }
    String getNewItemDesc(){
        System.out.println("Enter an brief item description (maxiumum 80 characters)(type 418 to go back)");
        while(true){
            String itemDesc = scanner.nextLine();
            if(itemDesc == ""){
                itemDesc = scanner.nextLine();
            }
            if(itemDesc == "418"){
                return null;
            }
            if(itemDesc.length() <= 80){
                return itemDesc;
            }
            System.out.println("Item name must be less than or equal to 80 characters");
        }    
    }
    Integer getNewItemPrice(){
        System.out.println("Enter a price for the new item (ex. 3129)(type 418 to go back)");
        do {
            String newPriceAsString = scanner.nextLine();
            if(newPriceAsString == ""){
                newPriceAsString = scanner.nextLine();
            }
            try {
                Integer newPriceAsInteger = Integer.parseInt(newPriceAsString);
                if(newPriceAsInteger == 418) {
                    return null;
                }
                if(newPriceAsInteger < 0 || newPriceAsInteger > 1000000){
                    System.out.println("Bro choose a different site to sell your million dollar item on");
                }
                return newPriceAsInteger;
            } catch (Exception NumberFormatException) {
                System.out.println("This price is invalid. Enter a price for the new item (ex. 3129)(type 418 to go back)");
            }
        }while(true);
    }
    Double getNewItemRating(){
        System.out.println("Enter the rating for the new item (ex. 4.3, 5.0, etc.)(type 418 to go back)");
        do {
            String newRatingAsString = scanner.nextLine();
            try {
                Double newRatingAsDouble = Double.parseDouble(newRatingAsString);
                if(newRatingAsString == "418") {
                    return null;
                }
                if(newRatingAsDouble < 0.1 || newRatingAsDouble > 5.0){
                    System.out.println("Rating must be between 0.1 and 5.0");
                }
                else return newRatingAsDouble;
            } catch (Exception NumberFormatException) {
                System.out.println("This rating is invalid. Enter a rating for the new item (ex. 4.3, 5.0, etc.)(type 418 to go back)");
            }
        }while(true);
    }
    String getNewItemSeller(){
        System.out.println("Enter the seller name (maximum 32 characters) (type 418 to go back)");
        while(true){
            String itemSeller = scanner.next();
            if(itemSeller == "418"){
                return null;
            }
            if(itemSeller.length() <= 32){
                return itemSeller;
            }
            System.out.println("Item seller must be less than or equal to 32 characters");
        }    
    }
    Double getNewSellerRating(){
        System.out.println("Enter the rating for the seller of the new item (ex. 4.3, 5.0, etc.)(type 418 to go back)");
        do {
            String newRatingAsString = scanner.nextLine();
            if(newRatingAsString == ""){
                newRatingAsString = scanner.nextLine();
            }
            try {
                Double newRatingAsDouble = Double.parseDouble(newRatingAsString);
                if(newRatingAsString == "418") {
                    return null;
                }
                if(newRatingAsDouble < 0.1 || newRatingAsDouble > 5.0){
                    System.out.println("Seller rating must be between 0.1 and 5.0");
                }
                else return newRatingAsDouble;
            } catch (Exception NumberFormatException) {
                System.out.println("This rating is invalid. Enter a seller rating for the new item (ex. 4.3, 5.0, etc.)(type 418 to go back)");
            }
        }while(true);
    }
}