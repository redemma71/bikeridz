package com.chadcover.bikeridz.wishlist;

import com.chadcover.bikeridz.bike.Part;
import static android.util.Half.NaN;

public class Wishlist {

    private int bikeId;
    private int itemId;
    private Part[] items;

    public Wishlist() {
        this.bikeId = NaN;
        this.items = new Part[100];
        for (int i = 0; i < items.length; i++) {
            this.items[i] = null;
        }

    }

    public int getBikeId() {
        return bikeId;
    }

    public void setBikeId(int bikeId) {
        this.bikeId = bikeId;
    }

    public Part[] getItems() {
        return items;
    }

    public void setItems(Part[] items) {
        this.items = items;
    }

    // helper functions

    public void addToWishlist(Part[] wishlist, Part part) {
        // TODO: implement logic to handle full inventory (100 items) and to groom array on delete
        for (int i = 0; i < wishlist.length; i++ ) {
            if (wishlist[i] == null) {
                wishlist[i] = part;
                break;
            }
        }
    }



}
