/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

//Import files
import entities.Staff;
import java.awt.List;
import util.CommonConstants;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wsuitha
 */
public class StaffServices {

    private ArrayList<Staff> employees = new ArrayList<Staff>();
    Staff employee = new Staff();

    /*
        *Method setStaffObject
        *Return: void
        *Paramerters: ResultSet object, Staff object
    
        *Operation: Set attributes of Staff object using ResultSet object
     */
    public void setStaffObject(ResultSet result) {
        try {
            if (result.next()) {
                this.employee.setId(result.getInt(this.employee.id_col));
                this.employee.setPassword(result.getString((this.employee.password_col)));
                this.employee.setFname(result.getString((this.employee.fname_col)));
                this.employee.setLname(result.getString((this.employee.lanme_col)));
                this.employee.setAge(result.getInt(this.employee.age_col));
                this.employee.setGender(result.getString((this.employee.gender_col)));
                this.employee.setPhone(result.getString((this.employee.phone_col)));
                this.employee.setEmail(result.getString((this.employee.email_col)));
                this.employee.setAddressLine1(result.getString((this.employee.add1_col)));
                this.employee.setAddressLine2(result.getString((this.employee.add2_col)));
                this.employee.setAddressLine3(result.getString((this.employee.add3_col)));
                this.employee.setAppointmentDate(result.getDate(this.employee.appointmentDate_col));
                this.employee.setType(result.getString((this.employee.type_col)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StaffServices.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }

    /*
        *Method setStaffObjectsList
        *Return: void
        *Paramerters: ResultSet object
    
        *Operation: set Staff objects list using given ResultSet object
     */
    private void setStaffObjectsList(ResultSet result) throws SQLException {
        this.employees.clear();
        while (result.next()) {
            Staff employee = new Staff();
            employee.setId(result.getInt(employee.id_col));
            employee.setPassword(result.getString((employee.password_col)));
            employee.setFname(result.getString((employee.fname_col)));
            employee.setLname(result.getString((employee.lanme_col)));
            employee.setAge(result.getInt(employee.age_col));
            employee.setGender(result.getString((employee.gender_col)));
            employee.setPhone(result.getString((employee.phone_col)));
            employee.setEmail(result.getString((employee.email_col)));
            employee.setAddressLine1(result.getString((employee.add1_col)));
            employee.setAddressLine2(result.getString((employee.add2_col)));
            employee.setAddressLine3(result.getString((employee.add3_col)));
            employee.setAppointmentDate(result.getDate(employee.appointmentDate_col));
            employee.setType(result.getString((employee.type_col)));

            this.employees.add(employee);
        }

        
    }

    /*
        *Method staffLogin
        *Return: Staff object
        *Paramerters: String email, String password
    
        *Operation: Read database for given email and password
     */
    public Staff staffLogin(String email, String password) {

        String sql = "select * from " + this.employee.tableName + " where " + this.employee.email_col + "=? and " + this.employee.password_col + "=?";

        try {

            PreparedStatement statement = DbServices.getStatement(sql);
            //Set columns in prepared statement
            statement.setString(1, email);
            statement.setString(2, password);

            //get reuslts
            ResultSet result = DbServices.getData(statement);

            //set staff object
            setStaffObject(result);

        } catch (SQLException ex) {
            Logger.getLogger(StaffServices.class.getName()).log(Level.SEVERE, null, ex);
        }

        return this.employee;
    }

    /*
        *Method getEmployeeByID
        *Return: Staff object
        *Paramerters: Integer variable
    
        *Operation: Read database for given id (integer variable)
     */
    public Staff getEmployeeByID(int id) {

        String sql = "select * from " + employee.tableName + " where " + employee.id_col + "=?";

        try {
            PreparedStatement statement = DbServices.getStatement(sql);
            //Set columns in prepared statement
            statement.setInt(1, id);
            ResultSet result = DbServices.getData(statement);

            //set Staff object using database readings
            setStaffObject(result);

        } catch (SQLException ex) {
            Logger.getLogger(StaffServices.class.getName()).log(Level.SEVERE, null, ex);
        }

        return employee;
    }

    /*
        *Method getAllStaff
        *Return: Staff object list
        *Paramerters: 
    
        *Operation: Read all database records in staff table
     */
    public ArrayList<Staff> getAllStaff() {
        
        String sql = "select * from " + employee.tableName;
        PreparedStatement statement = DbServices.getStatement(sql);

        try {
            ResultSet results = DbServices.getData(statement);
            setStaffObjectsList(results);
        } catch (SQLException ex) {
            Logger.getLogger(StaffServices.class.getName()).log(Level.SEVERE, null, ex);
        }

        return employees;
    }

    /*
        *Method saveStaff
        *Return: Staff object
        *Paramerters: Staff object
    
        *Operation: Save one staff object in database
     */
    public int saveStaff(Staff employee) {
        int check = CommonConstants.OPERATION_FAILED;
        String sql = "insert into " + employee.tableName
                + " (" + employee.password_col
                + "," + employee.fname_col
                + "," + employee.lanme_col
                + "," + employee.age_col
                + "," + employee.gender_col
                + "," + employee.phone_col
                + "," + employee.email_col
                + "," + employee.add1_col
                + "," + employee.add2_col
                + "," + employee.add3_col
                + "," + employee.appointmentDate_col
                + "," + employee.type_col
                + ") values(?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement statement = DbServices.getStatement(sql);
        try {
            //Set values in statement
            statement.setString(1, employee.getPassword());
            statement.setString(2, employee.getFname());
            statement.setString(3, employee.getLname());
            statement.setInt(4, employee.getAge());
            statement.setString(5, employee.getGender());
            statement.setString(6, employee.getPhone());
            statement.setString(7, employee.getEmail());
            statement.setString(8, employee.getAddressLine1());
            statement.setString(9, employee.getAddressLine2());
            statement.setString(10, employee.getAddressLine3());
            statement.setDate(12, employee.getAppointmentDate());
            statement.setString(12, employee.getType());

            //Save object
            DbServices.executeStatement(statement);
            check = CommonConstants.OPRTATOION_SUCCESS;
        } catch (SQLException ex) {
            Logger.getLogger(StaffServices.class.getName()).log(Level.SEVERE, null, ex);
        }

        return check;

    }

    /*
        *Method updateStaff
        *Return: Staff object
        *Paramerters: Staff object
    
        *Operation: Update one staff object in database
     */
    public int updateStaff(Staff employee) {
        int check = CommonConstants.OPERATION_FAILED;
        String sql = "update " + employee.tableName
                + " set " + employee.password_col + " = ?,"
                + employee.fname_col + " = ?,"
                + employee.lanme_col + " = ?,"
                + employee.age_col + " = ?,"
                + employee.gender_col + " = ?,"
                + employee.phone_col + " = ?,"
                + employee.email_col + " = ?,"
                + employee.add1_col + " = ?,"
                + employee.add2_col + " = ?,"
                + employee.add3_col + " = ?,"
                + employee.appointmentDate_col + " = ?,"
                + employee.type_col + " = ? "
                + "where " + employee.id_col + " = ?";
        PreparedStatement statement = DbServices.getStatement(sql);
        try {
            //Set values in statement
            statement.setString(1, employee.getPassword());
            statement.setString(2, employee.getFname());
            statement.setString(3, employee.getLname());
            statement.setInt(4, employee.getAge());
            statement.setString(5, employee.getGender());
            statement.setString(6, employee.getPhone());
            statement.setString(7, employee.getEmail());
            statement.setString(8, employee.getAddressLine1());
            statement.setString(9, employee.getAddressLine2());
            statement.setString(10, employee.getAddressLine3());
            statement.setDate(12, employee.getAppointmentDate());
            statement.setString(12, employee.getType());
            statement.setInt(13, employee.getId());

            //Save object
            DbServices.executeStatement(statement);
            check = CommonConstants.OPRTATOION_SUCCESS;
        } catch (SQLException ex) {
            Logger.getLogger(StaffServices.class.getName()).log(Level.SEVERE, null, ex);
        }

        return check;

    }

    /*
        *Method deleteStaff
        *Return: int object
        *Paramerters: Staff object
    
        *Operation: delete one staff object in database
     */
    public int deleteStaff(Staff employee) {
        int check = CommonConstants.OPERATION_FAILED;
        String sql = "delete from " + employee.tableName + " where " + employee.id_col + "=?";
        PreparedStatement statement = DbServices.getStatement(sql);
        try {
            //Set column names
            statement.setInt(1, employee.getId());
            //Execute statement
            DbServices.executeStatement(statement);
            check = CommonConstants.OPRTATOION_SUCCESS;
        } catch (SQLException ex) {
            Logger.getLogger(StaffServices.class.getName()).log(Level.SEVERE, null, ex);
        }

        return check;
    }

    /*
        *Method: searchStaffByName
        *Return: Array List
        *Parameters: String object.
    
        *Operation: Search Staff records using given key
     */
    public ArrayList<Staff> searchStaffByName(String key) {
        
        String sql = "select * from " + this.employee.tableName + " "
                + "where " + this.employee.fname_col + " like ? || "
                + this.employee.lanme_col + " like ?";
        PreparedStatement statement = DbServices.getStatement(sql);
        try {
            //Set values in statemetnt
            statement.setString(1, "%" + key + "%");
            statement.setString(2, "%" + key + "%");

            //Search database
            ResultSet results = DbServices.getData(statement);
            setStaffObjectsList(results);

        } catch (SQLException ex) {
            Logger.getLogger(StaffServices.class.getName()).log(Level.SEVERE, null, ex);
        }

        return this.employees;
    }

}
