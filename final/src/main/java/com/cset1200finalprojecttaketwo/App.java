package com.cset1200finalprojecttaketwo;

public class App {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) { // clear terminal so user can see program
            System.out.println("\n");
        }
        AsciiArt ascii = new AsciiArt();
        ascii.printOpening();

        Inventory inv = new Inventory(); // master inventory class that manages all listings
        do {
            Integer input = inv.promptForMenu(); // ask user what they want to do
            if (input == 1) { // code for showing all listings
                inv.viewInventory();
            } else if (input == 2) { // code for purchasing/removing an item
                Boolean validId = inv.purchaseItem();
                if (validId == null)
                    continue;
            }
            else if (input == 3) { //code for adding a listing
                Integer newId = inv.getdNewItemId();
                if (newId == null)
                    continue;
                String newName = inv.getNewItemName();
                if (newName == null)
                    continue;
                String newDesc = inv.getNewItemDesc();
                if (newDesc == null)
                    continue;
                Integer newPrice = inv.getNewItemPrice();
                if (newPrice == null)
                    continue;
                Double newRating = inv.getNewItemRating();
                if (newRating == null)
                    continue;
                String newItemSeller = inv.getNewItemSeller();
                if (newItemSeller == null)
                    continue;
                Double newSellerRating = inv.getNewSellerRating();
                if (newSellerRating == null)
                    continue;
                inv.addInventory(newId, newName, newDesc, newItemSeller, newRating, newSellerRating, newPrice);
                System.out.println("Listing successfully added");
            } else if (input == 4) {
                inv.deleteListing();
            
            } else if (input == 5) { // code for updating a listing
                inv.updateListing();
            } else if (input == 6) { // code for viewing the log
                inv.viewLog();
            }
            else if (input == 7) { // code for quiting program
                inv.closeScanner();
                ascii.printClosing();
                System.exit(1);
            }
        } while (true);
    }
}