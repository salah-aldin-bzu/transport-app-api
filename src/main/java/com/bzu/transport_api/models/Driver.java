package com.bzu.transport_api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

@Entity
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;

    private String userType = "Driver";

    private String firstName;
    private String lastName;

    private String mobileNumber;
    private String address;
    private String gender;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String imagePath;

    @OneToOne(mappedBy = "driver")
    private Vehicle vehicle;

    @OneToMany (mappedBy = "driver")
    @JsonIgnoreProperties({"driver","passenger"})
    private List<Bookmark> bookmarksList;

    @OneToMany(mappedBy = "driver")
    @JsonIgnoreProperties({"driver","passenger"})
    private List<Request> requests;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public List<Bookmark> getBookmarksList() {
        return bookmarksList;
    }

    public void setBookmarksList(List<Bookmark> bookmarksList) {
        this.bookmarksList = bookmarksList;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", userType='" + userType + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", address='" + address + '\'' +
                ", gender='" + gender + '\'' +
                ", password='" + password + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", vehicle=" + vehicle +
                ", bookmarksList=" + bookmarksList +
                ", requests=" + requests +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        Driver driver = (Driver) obj;
        return this.id == driver.id;
    }
}
