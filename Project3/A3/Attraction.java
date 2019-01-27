package com.gupta.sudhanshu.cs478.project3_sdmp;

public class Attraction {

    private String name;
    private String url;

    public Attraction(String name, String url){
        this.name = name;
        this.url = url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
