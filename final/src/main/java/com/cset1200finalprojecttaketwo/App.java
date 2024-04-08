package com.cset1200finalprojecttaketwo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        
        Inventory inv = new Inventory();
        inv.viewInventory();
        inv.removeInventory(3);
        inv.viewInventory();
        inv.addInventory(12, null, null, null, 0, 0, 0);
        inv.viewInventory();

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
    }
    void viewInventory(){
        for (Listing listing : listings) {
            System.out.println("id: " + listing.getItemId() + "\n" + "Item: " + listing.getItemName() + "\n" + "Description: " + listing.getItemDescription() + "\n" + "Price: " + listing.getItemPrice() + "\n" + "Item Rating: " + listing.getItemRating() + "\n" + "Item Seller: " + listing.getItemSeller() + "\n" + "Seller Rating: " + listing.getSellerRating() + "\n\n");
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
}