package com.example.linkb;

public class Main_SampleData {
    private String title;
    private String context;

    public Main_SampleData(String title, String context){
        this.title = title;
        this.context = context;
    }

    public String getTitle(){
        return this.title;
    }

    public String getContext(){
        return this.context;
    }
}
