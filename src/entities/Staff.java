/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;
import java.sql.*;
import util.CommonConstants;

/**
 *
 * @author Wsuitha
 */
public class Staff {
    
    //Table attributes
    private int id;
    private String password;
    private String fname;
    private String lname;
    private int age;
    private String gender;
    private String phone;
    private String email;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private Date appointmentDate;
    private String type;

    
    //Table column names
    public String id_col = "id";
    public String password_col = "password";
    public String fname_col = "fname";
    public String lanme_col = "lname";
    public String age_col = "age";
    public String gender_col = "gender";
    public String phone_col = "phone";
    public String email_col = "email";
    public String add1_col = "addressLine1";
    public String add2_col = "addressLine2";
    public String add3_col = "addressLine3";
    public String appointmentDate_col = "appointmentDate";
    public String type_col = "type";
    public String tableName = "staff";
    
    
    //Constructors

    public Staff() {
    }
    
    
    public Staff(int id, String password, String fname, String lname, int age, String gender, String phone, String email, String addressLine1, String addressLine2, String addressLine3, Date appointmentDate) {
        this.id = id;
        this.password = password;
        this.fname = fname;
        this.lname = lname;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.addressLine3 = addressLine3;
        this.appointmentDate = appointmentDate;
        this.type = CommonConstants.USER_TYPE_STAFF;
    }
    
    //getters ans setters

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public String getAddressLine3() {
        return addressLine3;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public String getType() {
        return type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
    
    
}
