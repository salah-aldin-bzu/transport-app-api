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

    @OneToOne(mappedBy = "driver")
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

        Driver driver = (Driver) o;

        if (id != driver.id) return false;
        if (userType != null ? !userType.equals(driver.userType) : driver.userType != null) return false;
        if (firstName != null ? !firstName.equals(driver.firstName) : driver.firstName != null) return false;
        if (lastName != null ? !lastName.equals(driver.lastName) : driver.lastName != null) return false;
        if (mobileNumber != null ? !mobileNumber.equals(driver.mobileNumber) : driver.mobileNumber != null)
            return false;
        if (address != null ? !address.equals(driver.address) : driver.address != null) return false;
        if (gender != null ? !gender.equals(driver.gender) : driver.gender != null) return false;
        if (password != null ? !password.equals(driver.password) : driver.password != null) return false;
        if (imagePath != null ? !imagePath.equals(driver.imagePath) : driver.imagePath != null) return false;
        if (vehicle != null ? !vehicle.equals(driver.vehicle) : driver.vehicle != null) return false;
        if (bookmarksList != null ? !bookmarksList.equals(driver.bookmarksList) : driver.bookmarksList != null)
            return false;
        if (requests != null ? !requests.equals(driver.requests) : driver.requests != null) return false;
        return location != null ? location.equals(driver.location) : driver.location == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (userType != null ? userType.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (mobileNumber != null ? mobileNumber.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (imagePath != null ? imagePath.hashCode() : 0);
        result = 31 * result + (vehicle != null ? vehicle.hashCode() : 0);
        result = 31 * result + (bookmarksList != null ? bookmarksList.hashCode() : 0);
        result = 31 * result + (requests != null ? requests.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        return result;
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
                ", location='" + location + '\'' +
                '}';
    }
}
