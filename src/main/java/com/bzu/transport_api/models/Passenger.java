package com.bzu.transport_api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

@Entity
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;

    private String userType = "Passenger";

    private String firstName;
    private String lastName;
    private String gender;

    private String mobileNumber;
    private String address;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String imagePath;

    @OneToMany(mappedBy = "passenger")
    @JsonIgnoreProperties({"passenger","driver"})
    private List<Bookmark> bookmarks;

    @OneToMany(mappedBy = "passenger")
    @JsonIgnoreProperties({"passenger","driver"})
    private  List<Request> requests;

    @OneToOne(mappedBy = "passenger")
    @JsonIgnoreProperties({"passenger", "driver"})
    private Location location;

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

    public List<Bookmark> getBookmarks() {
        return bookmarks;
    }

    public void setBookmarks(List<Bookmark> bookmarksList) {
        this.bookmarks = bookmarksList;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Passenger passenger = (Passenger) o;

        if (id != passenger.id) return false;
        if (userType != null ? !userType.equals(passenger.userType) : passenger.userType != null) return false;
        if (firstName != null ? !firstName.equals(passenger.firstName) : passenger.firstName != null) return false;
        if (lastName != null ? !lastName.equals(passenger.lastName) : passenger.lastName != null) return false;
        if (gender != null ? !gender.equals(passenger.gender) : passenger.gender != null) return false;
        if (mobileNumber != null ? !mobileNumber.equals(passenger.mobileNumber) : passenger.mobileNumber != null)
            return false;
        if (address != null ? !address.equals(passenger.address) : passenger.address != null) return false;
        if (password != null ? !password.equals(passenger.password) : passenger.password != null) return false;
        if (imagePath != null ? !imagePath.equals(passenger.imagePath) : passenger.imagePath != null) return false;
        if (bookmarks != null ? !bookmarks.equals(passenger.bookmarks) : passenger.bookmarks != null) return false;
        if (requests != null ? !requests.equals(passenger.requests) : passenger.requests != null) return false;
        return location != null ? location.equals(passenger.location) : passenger.location == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (userType != null ? userType.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (mobileNumber != null ? mobileNumber.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (imagePath != null ? imagePath.hashCode() : 0);
        result = 31 * result + (bookmarks != null ? bookmarks.hashCode() : 0);
        result = 31 * result + (requests != null ? requests.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        return result;
    }
}
