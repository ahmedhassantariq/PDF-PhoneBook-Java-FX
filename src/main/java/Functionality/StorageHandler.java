package Functionality;

import Entities.Person;

import java.io.*;
import java.util.ArrayList;

public class StorageHandler {




    public static void storeData() {
        try{
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("Book.data"));
            objectOutputStream.writeObject(Controller.personArrayList);
            objectOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void retrieveData() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("Book.data"));
            Controller.personArrayList = (ArrayList<Person>) objectInputStream.readObject();
            objectInputStream.close();
            refreshObservableArray();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static void refreshObservableArray(){
        Controller.personObservableList.clear();
        for(int i = 0; i<Controller.personArrayList.size();i++){
            Controller.personObservableList.add(Controller.personArrayList.get(i));
        }



    }
}
