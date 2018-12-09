package com.chadcover.bikeridz.bike;

import java.util.ArrayList;
import java.util.List;

public class Part {

    private int partId;
    private String name;
    private String description;
    private String manufacturer;
    private PartType typeOfPart;
    private int bikeId;

    public Part() {
        this.name = "new part";
        this.description = "new description";
        this.manufacturer = "Huffy";
        this.typeOfPart = PartType.BIKE;
        this.bikeId = 0;
    }

    public Part(String name, String description, String manufacturer) {
        this.name = name;
        this.description = description;
        this.manufacturer = manufacturer;
    }

    public Part(String name, String description, String manufacturer, PartType partType, int bikeId) {
        this.name = name;
        this.description = description;
        this.manufacturer = manufacturer;
        this.typeOfPart = partType;
        this.bikeId = bikeId;

    }

    public Part(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public PartType getTypeOfPart() {
        return typeOfPart;
    }

    public String getTypeOfPartString() {
        return typeOfPart.getPartName();
    }

    public void setTypeOfPart(PartType typeOfPart) {
        this.typeOfPart = typeOfPart;
    }

    public int getBikeId() {
        return bikeId;
    }

    public void setBikeId(int bikeId) {
        this.bikeId = bikeId;
    }

    public int getPartId() {
        return partId;
    }

    public void setPartId(int partId) {
        this.partId = partId;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    // helper method to create sample parts list
    ///////////////////////////////////////////////////////////////////////////////////////////

    public static List<Part> createSampleParts() {
        Part iroFrame = new Part("Reynolds 631 Steel Frame", "An all-steel, all-around great commuter bike",
                "IRO", PartType.FRAME, 1);
        Part iroWheel1 = new Part("40-spoke Tandem Wheel", "Back wheel. 700Cx28mm. 40-spokes",
                "Peter White", PartType.WHEELS, 1);
        Part iroWheel2 = new Part("32-spoke Wheel", "Front wheel. 700Cx28mm, 32-spokes.",
                "Peter White", PartType.WHEELS, 1);
        Part iroSeat = new Part("Brooks Swallow", "Black leather, chrome rails and rivets", "Brooks",
                PartType.SEAT, 1);
        Part iroTires = new Part("Gatorskins", "700x28mm",
                "Continental", PartType.TIRES, 1);
        Part iroHandlebar = new Part("Bullhorn handlebar", "Nitto RB021 Bullhorn Handlebar", "Nitto", PartType.HANDLEBARS, 1);
        Part iroCrankset = new Part("Single crankset", "42 teeth, 170mm crank arm", "IRO", PartType.CRANKSET, 1);

        Part litespeedWheels = new Part("Kysyrium Elite", "Both wheels. 700Cx23mm, 24 bladed spokes.", "Mavic", PartType.WHEELS, 2);
        Part litespeedSeat = new Part("Gatorskins", "700x28mm",
                "Continental", PartType.TIRES, 2);
        Part litespeedDereiller = new Part("Ultegra", "Front and back dereilleurs", "Shimano", PartType.DEREILLEUR, 2);
        Part litespeedCrankset = new Part("Ultegra compact crankset", "50x34 double, 175mm crank arm", "Shimano", PartType.CRANKSET, 2);
        List<Part> parts = new ArrayList<>();
        parts.add(iroFrame);
        parts.add(iroWheel1);
        parts.add(iroWheel2);
        parts.add(iroSeat);
        parts.add(iroHandlebar);
        parts.add(iroCrankset);
        parts.add(litespeedWheels);
        parts.add(litespeedSeat);
        parts.add(litespeedDereiller);
        parts.add(litespeedCrankset);

        return parts;
    }

}
