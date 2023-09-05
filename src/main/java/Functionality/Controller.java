package Functionality;

import Entities.Person;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.css.themes.Stylesheets;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Controller {
    public static ObservableList<Person> personObservableList = FXCollections.observableArrayList();
    public static ArrayList<Person> personArrayList = new ArrayList<>();
    private static MFXTextField firstName = Styles.textField("First Name");
    private static MFXTextField middleName = Styles.textField("Middle Name");
    private static MFXTextField lastName = Styles.textField("Last Name");
    private static MFXTextField phoneNo = Styles.textField("Phone Number");
    private static MFXTextField areaCode = Styles.textField("Area Code");
    private static MFXTextField address = Styles.textField("Address");


    public static Parent controller() {

        firstName.setTextLimit(10);

        middleName.setTextLimit(10);

        lastName.setTextLimit(10);

        phoneNo.setTextLimit(13);

        areaCode.setTextLimit(5);

        address.setTextLimit(255);

        MFXButton addButton = Styles.DashboardButton("ADD");
        MFXButton updateButton = Styles.DashboardButton("UPDATE");
        MFXButton removeButton = Styles.DashboardButton("REMOVE");
        MFXButton printBookButton = Styles.DashboardButton("Print");


        HBox nameBox = new HBox(firstName, middleName, lastName);
        nameBox.setSpacing(10);
        HBox phoneBox = new HBox(phoneNo, areaCode, address);
        phoneBox.setSpacing(10);
        HBox addressBox = new HBox(address);


        HBox buttonBox = new HBox(addButton, updateButton, removeButton, printBookButton);
        buttonBox.setSpacing(10);

        VBox controlsBox = new VBox(nameBox, phoneBox, addressBox, buttonBox);
        controlsBox.setPadding(new Insets(10));
        controlsBox.setSpacing(10);
        controlsBox.getStylesheets().add(Stylesheets.TEXT_FIELD.loadTheme());


        addButton.setOnAction(e -> {
            if (!firstName.getText().isEmpty() && !phoneNo.getText().isEmpty()) {
                Person person = new Person(
                        firstName.getText(), middleName.getText(), lastName.getText(),
                        phoneNo.getText(), areaCode.getText(), address.getText()
                );
                if (!checkRecord(person)) {
                    personObservableList.add(person);
                    personArrayList.add(person);
                    StorageHandler.storeData();
                } else {
                    System.out.println("Person Already");
                }
            }
        });
        updateButton.setOnAction(e -> {
            if (BookTable.bookTable.getSelectionModel().getSelectedItem() != null) {
                updateRecord(getRecordIndex());
            }
        });
        removeButton.setOnAction(e -> {
            if (BookTable.bookTable.getSelectionModel().getSelectedItem() != null) {
                removeRecord();
            }
        });

        printBookButton.setOnAction(e->{
            try {
                PDFDocument pdfDocument = new PDFDocument();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        BookTable.bookTable.setOnMouseClicked(e -> {
            getRecord();

        });
        return controlsBox;
    }

    private static void getRecord() {
        if (BookTable.bookTable.getSelectionModel().getSelectedItem() != null) {
            Person person = BookTable.bookTable.getSelectionModel().getSelectedItem();
            firstName.setText(person.getFirstName());
            middleName.setText(person.getMiddleName());
            lastName.setText(person.getLastName());
            phoneNo.setText(person.getPhoneNo());
            areaCode.setText(person.getAreaCode());
            address.setText(person.getAddress());
        }
    }

    public static void updateRecord(int index) {
        if (!firstName.getText().isEmpty() && !phoneNo.getText().isEmpty()) {
            Person person = new Person(
                    firstName.getText(), middleName.getText(), lastName.getText(),
                    phoneNo.getText(), areaCode.getText(), address.getText()
            );
            personArrayList.set(index, person);
        }
        StorageHandler.refreshObservableArray();
    }
    private static void removeRecord(){
        Person person = BookTable.bookTable.getSelectionModel().getSelectedItem();
        for(int i =0;i<personArrayList.size();i++){
            if(personArrayList.get(i).getPhoneNo().equals(person.getPhoneNo())) {
                personArrayList.remove(i);
                break;
            }
        }
        StorageHandler.refreshObservableArray();
    }

    private static boolean checkRecord(Person person){
        for (int i = 0;i<Controller.personArrayList.size();i++){
            if(Controller.personArrayList.get(i).getPhoneNo().equals(person.getPhoneNo())){
                return true;
            }
        }
        return false;
    }
    private static Integer getRecordIndex(){
        if(BookTable.bookTable.getSelectionModel().getSelectedItem()!=null) {
            Person person = BookTable.bookTable.getSelectionModel().getSelectedItem();
            for (int i = 0; i < personArrayList.size(); i++) {
                if (personArrayList.get(i).getPhoneNo().equals(person.getPhoneNo())) {
                    return i;
                }
            }
        }
        return null;
    }
}
