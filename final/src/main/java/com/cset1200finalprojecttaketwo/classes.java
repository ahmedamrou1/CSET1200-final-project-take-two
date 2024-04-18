/*
 * This java file holds the three classes used in this project: Listing, Inventory, and asciiArt. Listing is a class
 * that allows for item listings to be created: objects which have attributes and methods relevant to an item listing
 * on a shopping app. Inventory is a class that holds most of the methods of this project. It acts as the catalog of
 * listings, and allows for several getters and setters for each listing. asciiArt is simply a method I decided
 * to create a class for so that the App.java file was not overcrowded.
 */

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
    int id = 0; // all of the attributes of a listing: id, name, description...
    String itemName;
    String itemDescription;
    String itemSeller;
    Double itemRating;
    Double sellerRating;
    int itemPrice;

    public Listing(int idd, String name, String description, String seller, Double rating, Double sellerrating,
            int price) { // initialization method
        id = idd;
        itemName = name;
        itemDescription = description;
        itemSeller = seller;
        itemRating = rating;
        sellerRating = sellerrating;
        itemPrice = price;
    }

    public int getItemId() { // getter methods, useful for printing out the attributes when viewing inventory
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

    public void setItemId(Integer newId) {
        id = newId;
    }

    public void setItemName(String name) { // setter methods, useful for updating listings
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

    public void setSellerRating(Double rate) {
        sellerRating = rate;
    }

    public void setItemPrice(int price) {
        itemPrice = price;
    }
}

class Inventory {
    Scanner scanner = new Scanner(System.in); // for reading user input. had to be instantiated here rather than in App
                                              // to use scanner in methods
    ArrayList<Listing> listings = new ArrayList<>(); // ArrayLists are dynamic, which is important when adding and
                                                     // deleting listings
    ArrayList<String> log = new ArrayList<>(); // same case here

    public Inventory() { // Instantiation of Inventory. This is done with dummy data, generated from
                         // ChatGPT.
        String[] names = {
                "iPhone 12 Pro",
                "Samsung Galaxy S21 Ultra",
                "MacBook Pro 2021",
                "Sony PlayStation 5",
                "Nintendo Switch",
                "DJI Mavic Air 2",
                "GoPro Hero 9 Black",
                "Bose Headphones 700",
                "Logitech MX Master 3",
                "Amazon Echo Dot (4th Gen)"
        };

        String[] descriptions = {
                "The latest iPhone model with Pro features.",
                "Flagship smartphone from Samsung with advanced camera capabilities.",
                "Powerful laptop for professionals with M1 chip.",
                "Next-gen gaming console with high-end graphics.",
                "Hybrid gaming console for home and portable gaming.",
                "Compact drone with 4K camera and intelligent features.",
                "Action camera for adventurers with high-quality video recording.",
                "Premium headphones with excellent noise cancellation.",
                "Advanced wireless mouse with customizable buttons.",
                "Smart speaker with Alexa voice assistant."
        };

        for (int i = 1; i <= names.length; i++) { // add all the dummy data to Listings listing by listing
            int id = i;
            String name = names[i - 1];
            String description = descriptions[i - 1];
            String seller = "Seller " + i;
            double rating = 4.0 + (i * 0.1);
            double sellerRating = 4.0 + (i * 0.1);
            int price = 100 * i;

            Listing listing = new Listing(id, name, description, seller, rating, sellerRating, price);
            listings.add(listing);
        }
    }

    Integer promptForMenu() { // method that gets a menu selection from user
        while (true) {
            System.out.println(
                    "Choose from the following menu:\n1 - View Inventory\n2 - Purchase Inventory\n3 - Add Inventory\n4 - Delete Listing\n5 - Update Listing\n6 - View Logs\n7 - Exit");
            try {
                String inputAsString = scanner.nextLine();
                Integer inputAsInteger = Integer.parseInt(inputAsString);
                if (inputAsInteger < 1 || inputAsInteger > 7)
                    continue;
                else
                    return inputAsInteger;
            } catch (Exception e) { // NumberFormatException from parseInt could be raised, in that case simply
                                    // ignore it and reprompt
                continue;
            }
        }
    }

    void closeScanner() { // the scanner should be closed only at the very end of App, this method allows
                          // App to access Inventory's scanner
        scanner.close();
    }

    void addToLog(String s) { // code for timestamping actions and adding them to the log, which is an
                              // ArrayList
        LocalDateTime unformattedDateTime = LocalDateTime.now();
        OffsetDateTime offsetDateTime = unformattedDateTime.atOffset(ZoneOffset.UTC);
        DateTimeFormatter formatter = DateTimeFormatter.RFC_1123_DATE_TIME.withLocale(Locale.US);
        String timeOfAction = offsetDateTime.format(formatter);
        log.add(timeOfAction + " --- " + s);
    }

    void viewLog() {
        System.out.println("--------------------------- log ---------------------------\n");
        if (log.isEmpty()) {
            System.out.println("\nNo actions this session - log is empty\n");
        }
        for (String action : log) {
            System.out.println(action + "\n");
        }
        System.out.println("--------------------------- end of log ---------------------------\n");
    }

    void viewInventory() { // inventory view format. StringUtils ensures that each column has the enough
                           // space (id has less than description, for example, since id is always shorter)
        System.out.println(StringUtils.rightPad("id", 8) + StringUtils.rightPad("Item", 34)
                + StringUtils.rightPad("Description", 82) + StringUtils.rightPad("Price", 12)
                + StringUtils.rightPad("Rating", 12) + StringUtils.rightPad("Seller", 22) + "Seller Rating");
        for (Listing listing : listings) {
            String id = StringUtils.rightPad(String.valueOf(listing.getItemId()), 6);
            String itemName = StringUtils.rightPad(listing.getItemName(), 32);
            String itemDescription = StringUtils.rightPad(listing.getItemDescription(), 80);
            String price = StringUtils.rightPad("$" + String.valueOf(listing.getItemPrice()), 10);
            String itemRating = StringUtils.rightPad(String.valueOf(listing.getItemRating()), 10);
            String itemSeller = StringUtils.rightPad(listing.getItemSeller(), 20);
            String sellerRating = String.valueOf(listing.getSellerRating());

            System.out.printf("%s  %s  %s  %s  %s  %s  %s%n\n", id, itemName, itemDescription, price, itemRating,
                    itemSeller, sellerRating);
        }
        addToLog("User viewed inventory");
    }

    void addInventory(int idd, String name, String description, String seller, Double rating, Double sellerrating,
            int price) {
        Listing newListing = new Listing(idd, name, description, seller, rating, sellerrating, price);
        listings.add(newListing);
        addToLog(String.format("User added item with id %d to inventory", idd));
    }

    void removeInventory(Integer i) {
        Iterator<Listing> iterator = listings.iterator(); // used iterator to avoid ConcurrentModificationException,
                                                          // which is thrown when trying to modify the current accessed
                                                          // item in place
        while (iterator.hasNext()) {
            Listing listing = iterator.next();
            if (listing.getItemId() == i) {
                iterator.remove();
            }
        }
    }

    ArrayList<Integer> getAllItemIds() { // used when trying to check if an id is in use or exists for a listing already
        ArrayList<Integer> ids = new ArrayList<>();
        for (Listing listing : listings) {
            ids.add(listing.getItemId());
        }
        return ids;
    }

    ArrayList<Listing> getInventory() { // returns the ArrayList of all listings that exist in Inventory
        return listings;
    }

    Listing getItem(Integer id) { // get an individual listing by id
        for (Listing listing : listings) {
            if (listing.getItemId() == id) {
                return listing;
            }
        }
        return null; // if it user gets to this point, no item exists with the parameter id so it
                     // will return null
    }

    Boolean purchaseItem() { // this method asks for the id as a string, changes to an int, ensures its a
                             // valid id of a listing, removes it, and adds to log
        System.out.println("What is the id of the item you would like to purchase? (to go back, type '418')"); // google
                                                                                                               // HTML
                                                                                                               // status
                                                                                                               // code
                                                                                                               // 418 (:
        while (true) {
            String idAsString = scanner.nextLine();
            try {
                Integer idAsInteger = Integer.parseInt(idAsString);
                if (idAsInteger == 418) {
                    return null;
                }
                if (!getAllItemIds().contains(idAsInteger)) {
                    throw new Exception();
                }
                removeInventory(idAsInteger);
                addToLog(String.format("User purchased item with id %d", idAsInteger));
                return true;
            } catch (Exception e) {
                System.out.print(
                        "This id is either invalid or is not assocaited with a product. Please type a valid id (to go back, type '418')\n");
            }
        }
    }

    Integer getdNewItemId() { // the following methods are used as response validation while adding a listing
        System.out.println("Enter an integer for a new item ID which is not being used (type 418 to go back)");
        while (true) {
            String idAsString = scanner.nextLine();
            try {
                Integer idAsInteger = Integer.parseInt(idAsString);
                if (idAsInteger == 418) {
                    return null;
                }
                if (!getAllItemIds().contains(idAsInteger)) {
                    return idAsInteger;
                }
                System.out.println(
                        "This id is either in use or is invalid. Enter an integer for a new item ID which is not being used (type 418 to go back)");
            } catch (Exception NumberFormatException) {
                System.out.println(
                        "This id is either in use or is invalid. Enter an integer for a new item ID which is not being used (type 418 to go back)");
            }
        }
    }

    String getNewItemName() {
        System.out.println("Enter an item name (maximum 32 characters) (type 418 to go back)");
        while (true) {
            String itemName = scanner.next();
            if (itemName == "418") {
                return null;
            }
            if (itemName.length() <= 32) {
                return itemName;
            }
            System.out.println("Item name must be less than or equal to 32 characters");
        }
    }

    String getNewItemDesc() {
        System.out.println("Enter an brief item description (maxiumum 80 characters)(type 418 to go back)");
        while (true) {
            String itemDesc = scanner.nextLine();
            if (itemDesc == "") {
                itemDesc = scanner.nextLine();
            }
            if (itemDesc == "418") {
                return null;
            }
            if (itemDesc.length() <= 80) {
                return itemDesc;
            }
            System.out.println("Item name must be less than or equal to 80 characters");
        }
    }

    Integer getNewItemPrice() {
        System.out.println("Enter a price for the new item (ex. 3129)(type 418 to go back)");
        do {
            String newPriceAsString = scanner.nextLine();
            if (newPriceAsString == "") {
                newPriceAsString = scanner.nextLine();
            }
            try {
                Integer newPriceAsInteger = Integer.parseInt(newPriceAsString);
                if (newPriceAsInteger == 418) {
                    return null;
                }
                if (newPriceAsInteger < 0 || newPriceAsInteger > 1000000) {
                    System.out.println("Bro choose a different site to sell your million dollar item on");
                }
                return newPriceAsInteger;
            } catch (Exception NumberFormatException) {
                System.out.println(
                        "This price is invalid. Enter a price for the new item (ex. 3129)(type 418 to go back)");
            }
        } while (true);
    }

    Double getNewItemRating() {
        System.out.println("Enter the rating for the new item (ex. 4.3, 5.0, etc.)(type 418 to go back)");
        do {
            String newRatingAsString = scanner.nextLine();
            try {
                Double newRatingAsDouble = Double.parseDouble(newRatingAsString);
                if (newRatingAsString == "418") {
                    return null;
                }
                if (newRatingAsDouble < 0.1 || newRatingAsDouble > 5.0) {
                    System.out.println("Rating must be between 0.1 and 5.0");
                } else
                    return newRatingAsDouble;
            } catch (Exception NumberFormatException) {
                System.out.println(
                        "This rating is invalid. Enter a rating for the new item (ex. 4.3, 5.0, etc.)(type 418 to go back)");
            }
        } while (true);
    }

    String getNewItemSeller() {
        System.out.println("Enter the seller name (maximum 32 characters) (type 418 to go back)");
        while (true) {
            String itemSeller = scanner.next();
            if (itemSeller == "418") {
                return null;
            }
            if (itemSeller.length() <= 32) {
                return itemSeller;
            }
            System.out.println("Item seller must be less than or equal to 32 characters");
        }
    }

    Double getNewSellerRating() {
        System.out.println("Enter the rating for the seller of the new item (ex. 4.3, 5.0, etc.)(type 418 to go back)");
        do {
            String newRatingAsString = scanner.nextLine();
            if (newRatingAsString == "") {
                newRatingAsString = scanner.nextLine();
            }
            try {
                Double newRatingAsDouble = Double.parseDouble(newRatingAsString);
                if (newRatingAsString == "418") {
                    return null;
                }
                if (newRatingAsDouble < 0.1 || newRatingAsDouble > 5.0) {
                    System.out.println("Seller rating must be between 0.1 and 5.0");
                } else
                    return newRatingAsDouble;
            } catch (Exception NumberFormatException) {
                System.out.println(
                        "This rating is invalid. Enter a seller rating for the new item (ex. 4.3, 5.0, etc.)(type 418 to go back)");
            }
        } while (true);
    }

    void updateListing() { // method for changing a listing so that the user does not have to delete a
                           // listing and recreate it just to change a field
        System.out.println("What is the id of the listing you would like to update? (type 418 to go back)");
        while (true) {
            String idAsString = scanner.nextLine();
            if (idAsString == "") {
                idAsString = scanner.nextLine();
            }
            try {
                Integer idAsInteger = Integer.parseInt(idAsString);
                if (idAsInteger == 418) {
                    return;
                }
                if (!getAllItemIds().contains(idAsInteger)) {
                    throw new Exception();
                }
                System.out.println(
                        "Enter the name of the attribute you would like to change (i.e id, description, etc.) (type 418 to go back)");
                while (true) {
                    String field = scanner.nextLine();
                    if (field.equalsIgnoreCase("418")) {
                        return;
                    }
                    if (field.equalsIgnoreCase("id")) {
                        Integer newId = getdNewItemId();
                        if (newId == null) {
                            return;
                        }
                        getItem(idAsInteger).setItemId(newId);
                        System.out.println("Listing updated.\n");
                        addToLog(String.format("User updated the id of listing with original id %d to: %d", idAsInteger,
                                newId));
                        return;
                    } else if (field.equalsIgnoreCase("item")) {
                        String newItemName = getNewItemName();
                        if (newItemName == null) {
                            return;
                        }
                        getItem(idAsInteger).setItemName(newItemName);
                        System.out.println("Listing updated.\n");
                        addToLog(String.format("User updated the name of listing with id %d to: %s", idAsInteger,
                                newItemName));
                        return;
                    } else if (field.equalsIgnoreCase("description")) {
                        String newItemDescription = getNewItemDesc();
                        if (newItemDescription == null) {
                            return;
                        }
                        getItem(idAsInteger).setItemDescription(newItemDescription);
                        System.out.println("Listing updated.\n");
                        addToLog(String.format("User updated the description of listing with id %d to: %s", idAsInteger,
                                newItemDescription));
                        return;
                    } else if (field.equalsIgnoreCase("price")) {
                        Integer newItemPrice = getNewItemPrice();
                        if (newItemPrice == null) {
                            return;
                        }
                        getItem(idAsInteger).setItemPrice(newItemPrice);
                        System.out.println("Listing updated.\n");
                        addToLog(String.format("User updated the price of listing with id %d to: %d", idAsInteger,
                                newItemPrice));
                        return;
                    } else if (field.equalsIgnoreCase("rating")) {
                        Double newItemRating = getNewItemRating();
                        if (newItemRating == null) {
                            return;
                        }
                        getItem(idAsInteger).setItemRating(newItemRating);
                        System.out.println("Listing updated.\n");
                        addToLog(String.format("User updated the item rating of listing with id %d to: %d", idAsInteger,
                                newItemRating));
                        return;
                    } else if (field.equalsIgnoreCase("seller")) {
                        String newItemSeller = getNewItemSeller();
                        if (newItemSeller == null) {
                            return;
                        }
                        getItem(idAsInteger).setItemSeller(newItemSeller);
                        System.out.println("Listing updated.\n");
                        addToLog(String.format("User updated the seller of listing with id %d to: %s", idAsInteger,
                                newItemSeller));
                        return;
                    } else if (field.equalsIgnoreCase("seller rating")) {
                        Double newSellerRating = getNewSellerRating();
                        if (newSellerRating == null) {
                            return;
                        }
                        getItem(idAsInteger).setSellerRating(newSellerRating);
                        System.out.println("Listing updated.\n");
                        addToLog(String.format("User updated the seller rating of listing with id %d to: %d",
                                idAsInteger, newSellerRating));
                        return;
                    } else {
                        System.out.println(
                                "This entry is invalid. Please enter the name of the listing attribute you want to change (type 418 to go back)");
                    }
                }
            } catch (Exception e) {
                System.out.println("This id is invalid. Enter an id associated with a listing (type 418 to go back)");
            }
        }

    }

    Integer deleteListing() { //code for deleting a listing
        System.out.println("What is the id of the item you would like to remove? (to go back, type '418')");
        while (true) {
            String idAsString = scanner.nextLine();
            try {
                Integer idAsInteger = Integer.parseInt(idAsString);
                if (idAsInteger == 418) {
                    return null;
                }
                if (!getAllItemIds().contains(idAsInteger)) {
                    throw new Exception();
                }
                removeInventory(idAsInteger);
                addToLog(String.format("User removed item with id %d", idAsInteger));
                return 1;
            } catch (Exception e) {
                System.out.print(
                        "This id is either invalid or is not associated with a product. Please type a valid id (to go back, type '418')\n");
            }
        }
    }
}

class AsciiArt {
    AsciiArt() {
        // instantiate a new ascii art object to access methods below
    }

    void printOpening() {
        String ascii0 = "--------------------------------------------------------------------------------------------------------------------------------------------------------------------\n";
        String ascii1 = "██     ██ ███████ ██       ██████  ██████  ███    ███ ███████     ████████  ██████      ███████ ██   ██  ██████  ██████  ███████ ███    ███  █████  ██████  ████████\n";
        String ascii2 = "██     ██ ██      ██      ██      ██    ██ ████  ████ ██             ██    ██    ██     ██      ██   ██ ██    ██ ██   ██ ██      ████  ████ ██   ██ ██   ██    ██   \n";
        String ascii3 = "██  █  ██ █████   ██      ██      ██    ██ ██ ████ ██ █████          ██    ██    ██     ███████ ███████ ██    ██ ██████  ███████ ██ ████ ██ ███████ ██████     ██   \n";
        String ascii4 = "██ ███ ██ ██      ██      ██      ██    ██ ██  ██  ██ ██             ██    ██    ██          ██ ██   ██ ██    ██ ██           ██ ██  ██  ██ ██   ██ ██   ██    ██   \n";
        String ascii5 = " ███ ███  ███████ ███████  ██████  ██████  ██      ██ ███████        ██     ██████      ███████ ██   ██  ██████  ██      ███████ ██      ██ ██   ██ ██   ██    ██   \n";
        String ascii6 = "--------------------------------------------------------------------------------------------------------------------------------------------------------------------\n";
        System.out.println(ascii0 + ascii1 + ascii2 + ascii3 + ascii4 + ascii5 + ascii6);
    }

    void printClosing() {
        String ascii0 = "---------------------------------------------------------------\n";
        String ascii1 = " ██████   ██████   ██████  ██████  ██████  ██    ██ ███████ ██ \n";
        String ascii2 = "██       ██    ██ ██    ██ ██   ██ ██   ██  ██  ██  ██      ██ \n";
        String ascii3 = "██   ███ ██    ██ ██    ██ ██   ██ ██████    ████   █████   ██ \n";
        String ascii4 = "██    ██ ██    ██ ██    ██ ██   ██ ██   ██    ██    ██         \n";
        String ascii5 = " ██████   ██████   ██████  ██████  ██████     ██    ███████ ██ \n";
        String ascii6 = "---------------------------------------------------------------\n";
        System.out.println(ascii0 + ascii1 + ascii2 + ascii3 + ascii4 + ascii5 + ascii6);
    }
}