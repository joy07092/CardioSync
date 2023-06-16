package com.example.cardiosync;

import java.util.ArrayList;

/**
 * This is class for manipulation of data in the list
 */
public class RecordList {
    public static ArrayList<ModelClass> mcl= new ArrayList<>();

    /**
     * This is the method for adding new record in the list
     *
     * @param modelClass
     *              this is record that will be added in the list
     */
    public void addRecord(ModelClass modelClass){
        if(mcl.contains(modelClass)){
            throw new IllegalArgumentException();
        }
        mcl.add(modelClass);
    }

    /**
     * This is the method for deleting record from the list
     *
     * @param position
     *              this is position of the record that will be deleted from the list
     */

    public void deleteRecord(int position){
        if (position>=0 && position<mcl.size()) {
            mcl.remove(position);
        }
        else {
            throw new IllegalArgumentException() ;
        }
    }

    /**
     * This method is for returning the size of the list
     * @return
     *      Return the size of list of record
     */
    public int  count(){
        return  mcl.size();
    }
}
