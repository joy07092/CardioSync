package com.example.cardiosync;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class UnitTest {  //mainly the methods of recordlists are being tested here
    /**
     * This is Unit test for adding a new record in the list
     */
    @Test
    public void testAddRecord(){
        RecordList recordList= new RecordList();
        ModelClass modelClass= new ModelClass("2000-01-07","10:20","89","67","67","dummy");
        recordList.addRecord(modelClass);
        assertEquals(1, recordList.mcl.size());

        ModelClass modelClass1=new ModelClass("2001-02-25","11:20","89","67","67","dummy");
        recordList.addRecord(modelClass1);
        assertEquals(2,recordList.mcl.size());
        assertTrue(recordList.mcl.contains(modelClass));
        assertTrue(recordList.mcl.contains(modelClass1));

    }

    /**
     * This is Unit test for deleting existing record from the list
     */
    @Test
    public void testDeleteRecord() {
        RecordList recordList = new RecordList();
        ModelClass record1 = new ModelClass("2000-01-07","10:20","89","67","67","dummy");
        recordList.addRecord(record1);
        assertEquals(1, recordList.mcl.size());

        RecordList recordList1 = new RecordList();
        ModelClass record2 = new ModelClass("2000-01-07","10:20","89","67","67","dummy");
        recordList.addRecord(record2);
        assertEquals(2, recordList.mcl.size());


        RecordList recordList2 = new RecordList();
        ModelClass record3 = new ModelClass("2000-01-07","10:20","89","67","67","dummy");
        recordList.addRecord(record3);
        assertEquals(3, recordList.mcl.size());

        assertTrue(recordList.mcl.contains(record1));
        assertTrue(recordList.mcl.contains(record2));
        assertTrue(recordList.mcl.contains(record3));

        recordList.deleteRecord(0);
        assertEquals(2, recordList.mcl.size());
        assertFalse(recordList.mcl.contains(record1));

        recordList.deleteRecord(0);
        assertEquals(1, recordList.mcl.size());
        assertFalse(recordList.mcl.contains(record2));

        assertThrows(IllegalArgumentException.class, () -> recordList.deleteRecord(1));
    }
    /**
     * This is Unit test for counting the total records in the list
     */
    @Test
    public void testCount(){
        RecordList recordList=new RecordList();
        ModelClass modelClass1=new ModelClass("2000-01-07","10:20","89","67","67","dummy");
        recordList.addRecord(modelClass1);
        assertEquals(1,recordList.count());

    }
}
