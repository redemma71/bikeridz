package com.chadcover.bikeridz.wishlist;

import com.chadcover.bikeridz.bike.Part;

import java.util.List;

import static android.util.Half.NaN;

public class Wishlist {

    private int bikeId;
    private int itemId;
    private List<Part> items;

    public Wishlist() {
        this.bikeId = NaN;
        items.add(new Part());
    }

    public int getBikeId() {
        return bikeId;
    }

    public void setBikeId(int bikeId) {
        this.bikeId = bikeId;
    }

    public List<Part> getItems() {
        return items;
    }

    public void setItems(List<Part> items) {
        this.items = items;
    }

    // helper functions

    public void addToWishlist(List<Part> wishlist, Part part) {
        wishlist.add(part);
    }



}
