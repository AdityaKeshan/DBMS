package com.work.fc;

public class Fac {
    String name;
    String distance;
    String non_rush;

    public Fac(String name, String distance, String non_rush) {
        this.name = name;
        this.distance = distance;
        this.non_rush = non_rush;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getNonRush() {
        return non_rush;
    }

    public void setNonRush(String non_rush) {
        this.non_rush = non_rush;
    }
}
