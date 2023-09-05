package Entities;

import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;

public class Person implements Serializable {
    private String firstName;
    private String middleName;
    private String lastName;
    private String phoneNo;
    private String areaCode;
    private String address;


    public Person(String firstName,String middleName, String lastName, String phoneNo, String areaCode, String address){
        this.firstName = (firstName);
        this.middleName = (middleName);
        this.lastName = (lastName);
        this.phoneNo = (phoneNo);
        this.areaCode = (areaCode);
        this.address = (address);
    }

    public SimpleStringProperty firstNameProperty() {
        return new SimpleStringProperty(firstName);
    }


    public SimpleStringProperty middleNameProperty() {
        return new SimpleStringProperty(middleName);
    }


    public SimpleStringProperty lastNameProperty() {
        return new SimpleStringProperty(lastName);
    }

    public SimpleStringProperty phoneNoProperty() {
        return new SimpleStringProperty(phoneNo);
    }

    public SimpleStringProperty areaCodeProperty() {
        return new SimpleStringProperty(areaCode);
    }

    public SimpleStringProperty addressProperty() {
        return new SimpleStringProperty(address);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
