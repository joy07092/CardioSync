package com.example.cardiosync;

import java.util.ArrayList;

public class RecordList {   //list of records
    public static ArrayList<ModelClass> mcl= new ArrayList<>();

    public void addRecord(ModelClass modelClass){  //method to add new record
        if(mcl.contains(modelClass)){
            throw new IllegalArgumentException();
        }
        mcl.add(modelClass);
    }

    public void deleteRecord(int position){  //method to delete record
        if (position>=0 && position<mcl.size()) {
            mcl.remove(position);
        }
        else {
            throw new IllegalArgumentException() ;
        }
    }

    public int  count(){
        return  mcl.size();
    }   //method to count the number of total records
}
