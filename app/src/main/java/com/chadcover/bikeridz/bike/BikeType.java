package com.chadcover.bikeridz.bike;

public enum BikeType {
        ROAD("Road"),
        COMMUTER("Commuter"),
        HYBRID("Hybrid"),
        MTB("MTB");

        private String typeName;

        BikeType(String name) {
            this.typeName = name;
        }

        public String getTypeName() {
            return this.typeName;
        }
}
