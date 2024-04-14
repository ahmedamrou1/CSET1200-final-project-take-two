package com.cset1200finalprojecttaketwo;

import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
        Inventory inv = new Inventory();
        Scanner scanner = new Scanner(System.in);
        do {
            Integer input = inv.promptForMenu();
            if(input == 1){
                inv.viewInventory();
            }

            else if(input == 2){
                Boolean validId = inv.purchaseItem();
                if(validId == null) continue;
            }

            else if(input == 3){
                Integer newId = inv.getdNewItemId();
                if(newId == null) continue;
                String newName = inv.getNewItemName();
                if(newName == null) continue;
                String newDesc = inv.getNewItemDesc();
                if(newDesc == null) continue;
                Integer newPrice = inv.getNewItemPrice();
                if(newPrice == null) continue;
                Double newRating = inv.getNewItemRating();
                if(newRating == null) continue;
                String newItemSeller = inv.getNewItemSeller();
                if(newItemSeller == null) continue;
                Double newSellerRating = inv.getNewSellerRating();
                if(newSellerRating == null) continue;
                inv.addInventory(newId, newName, newDesc, newItemSeller, newRating, newSellerRating, newPrice);
                System.out.println("Listing successfully added");
            }
            else if(input == 4){
                inv.viewLog();
            }

            else if(input == 5) {
                inv.closeScanner();
                System.exit(1);
            }
        } 
        while(true);
    }
}