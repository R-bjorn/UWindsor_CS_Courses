package com.example.assignment4;

import android.content.Context;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileSaver {

    public static final String FILENAME = "todofile.dat";

    public static void writeData(ArrayList<String> items, Context context){
        try{
            FileOutputStream fileOutputStream = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(items);
            objectOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> readData(Context context){
         ArrayList<String> itemsList = null;

         try{
             FileInputStream fileInputStream = context.openFileInput(FILENAME);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

             itemsList = (ArrayList<String>) objectInputStream.readObject();
         } catch (FileNotFoundException e) {
             itemsList = new ArrayList<String>();
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         } catch (ClassNotFoundException e) {
             e.printStackTrace();
         }

        return itemsList;
    }
}
