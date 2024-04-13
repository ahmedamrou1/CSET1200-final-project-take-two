package com.cset1200finalprojecttaketwo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;


import org.apache.commons.lang3.StringUtils;


public class App 
{
    public static void main( String[] args )
    {
        boolean status = true;
        Inventory inv = new Inventory();
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Choose from the following menu:\n1 - View Inventory\n2 - Purchase Inventory\n3 - Add Inventory\n4 - View Logs\n5 - Exit\n");
            int input = scanner.nextInt();

            if(input == 1){
                inv.viewInventory();
            }

            else if(input == 2){
                System.out.println("What is the id of the item you would like to purchase? (to go back, type '418')\n");
                int purchaseId = scanner.nextInt();
                if (input != 418 && inv.getAllItemIds().contains(purchaseId)) {
                    System.out.println(String.format("\n%s has been purchased\n", inv.getItem(purchaseId).getItemName()));
                    inv.removeInventory(purchaseId);
                }
            }

            else if(input == 3){
                System.out.println("Enter an item ID for this new listing. (to go back, type '418')\n");
                
            }


            else if(input == 5) {
                System.exit(1);
            }
        } 
        while (status);
        scanner.close();
    }
}

class Listing {
    private int id = 0;
    String itemName;
    String itemDescription;
    String itemSeller;
    int itemRating;
    int sellerRating;
    int itemPrice;
    java.time.LocalDateTime dateCreated = LocalDateTime.now();
    public Listing (int idd, String name, String description, String seller, int rating, int sellerrating, int price) {
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
    public int getItemRating() {
        return itemRating;
    }
    public int getSellerRating() {
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
    public void setItemRating(int rate) {
        itemRating = rate;
    }
    public void setSellerRating (int rate) {
        sellerRating = rate;
    }
    public void setItemPrice (int price) {
        itemPrice = price;
    }
}

class Inventory {
    ArrayList<Listing> listings = new ArrayList<>();

    public Inventory() {
        for (int i=0; i < 10; i++) {
            Listing thisListing = new Listing(i, ("Listing" + i), ("This is the description for listing" + i), "urmum", 5, 4, (300000 * i));
            listings.add(thisListing);
        }
        Listing testListing = new Listing(555, "The Bruh Button 5000,00000,0000", "This magnificnet button lorem ipsum blah blah blah blah blah, ya know?", "urmum products", 4, 3, 1010010);
        listings.add(testListing);
    }
    void viewInventory() {
        // Print table header
        System.out.println(StringUtils.rightPad("id", 8) + StringUtils.rightPad("Item", 34) + StringUtils.rightPad("Description", 82) + StringUtils.rightPad("Price", 12) + StringUtils.rightPad("Rating", 12) + StringUtils.rightPad("Seller", 22) + "Seller Rating");
        // Print each listing
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
    void addInventory(int idd, String name, String description, String seller, int rating, int sellerrating, int price){
        Listing newListing = new Listing(idd, name, description, seller, rating, sellerrating, price);
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
}