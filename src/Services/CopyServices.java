/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import entities.Book;
import entities.Copy;
import util.CommonConstants;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wsuitha
 */
public class CopyServices {
    private Copy copy = new Copy();
    private ArrayList<Copy> copies = new ArrayList<Copy>();
    
    /*
        *Method: getCopyObject
        *Return: void
        *Parameters: ResultSet
    
        *Operation: Set Copy object using given result set
    */
    
    public void getCopyObject(ResultSet result) {
        
        
        try {
            //check result set
            if(result.next()) {
                this.copy.setId(result.getInt(this.copy.id_col));
                this.copy.setIsbn(result.getString(this.copy.isbn_col));
                this.copy.setState(result.getString(this.copy.state_col));
            }
            } catch (SQLException ex) {
            Logger.getLogger(CopyServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    
    /*
        *Method: getCopyObjectsList
        *Return: void
        *Parameters: ResultSet
    
        *Operation: Set Copy objects list using given result set
    */
    private void getCopyObjectsList(ResultSet result) throws SQLException{
        this.copies.clear();
        while(result.next()){
            Copy copy = new Copy();
            copy.setId(result.getInt(copy.id_col));
            copy.setIsbn(result.getString(copy.isbn_col));
            copy.setState(result.getString(copy.state_col));
                
            copies.add(copy);
        }
        
    }
    
    
    
    
    
    /*
    *Method: saveCopy
    *Return: int value
    *Parameters: Book object.
    
    *Operation: Save Copy object
    */
    
    public int saveCopy(Copy copy) {
        int check;
        
        String sql = "insert into "+this.copy.tableName+" ("
                +this.copy.isbn_col+", "
                +this.copy.state_col+") values(?,?)";
       //Create prepared statement
       PreparedStatement statement = DbServices.getStatement(sql);
       
        try {
            //Add values to statement
            statement.setString(1, copy.getIsbn());
            statement.setString(2, CommonConstants.COPY_STATE_AVAILABLE);
            
            //Execute statement
            DbServices.executeStatement(statement);
            check = CommonConstants.OPRTATOION_SUCCESS;
            
        } catch (SQLException ex) {
            check = CommonConstants.OPERATION_FAILED;
            Logger.getLogger(CopyServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return check;
    }
    
    /*
    *Method: updateCopy
    *Return: int value
    *Parameters: Book object.
    
    *Operation: Save Copy object
    */
    
    public int updateCopy(Copy copy) {
        int check;
        
        String sql = "update "+this.copy.tableName+" set "
                +this.copy.isbn_col+" = ?, "
                +this.copy.state_col+" = ? "
                + "where "+this.copy.id_col+" = ?";
       //Create prepared statement
       PreparedStatement statement = DbServices.getStatement(sql);
       
        try {
            //Add values to statement
            statement.setString(1, copy.getIsbn());
            statement.setString(2, copy.getState());
            statement.setInt(3, copy.getId());
            
            //Execute statement
            DbServices.executeStatement(statement);
            check = CommonConstants.OPRTATOION_SUCCESS;
            
        } catch (SQLException ex) {
            check = CommonConstants.OPERATION_FAILED;
            Logger.getLogger(CopyServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return check;
    }
    
    
    
    /*
        *Method: getAllCopiesByBook
        *Return: Array List of Copy objects
        *Parameters: Book object
    
        *Operation: Create list of copy objects according to the given Book object
    
    */
    
    public ArrayList<Copy> getAllCopiesByBook(Book book) {
        
        String sql = "select * from "+this.copy.tableName+" where "+this.copy.isbn_col+" = ?";
        PreparedStatement statement = DbServices.getStatement(sql);
        
        try {
            statement.setString(1, book.getIsbn());
            //get data from database
            ResultSet results = DbServices.getData(statement);
            getCopyObjectsList(results);
            
        } catch (SQLException ex) {
            Logger.getLogger(CopyServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return copies;
    }
    
    
    
    
    /*
        *Method: getCopyById
        *Return: Copy object
        *Parameters: int value
    
        *Operation: Create a Copy object using given id
    */
    public Copy getCopyById(int id) {
        
        String sql = "select * from "+this.copy.tableName+ " where "+this.copy.id_col+" = ?";
        PreparedStatement statement = DbServices.getStatement(sql);
        
        try {
            //set values in statement
            statement.setInt(1, id);
            ResultSet result = DbServices.getData(statement);
            getCopyObject(result);
        } catch (SQLException ex) {
            Logger.getLogger(CopyServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return copy;
    }
    
    
    
    /*
        *Method: deleteCopyById
        *Return: int value
        *Parameters: int value
    
        *Operation: Delete a Copy object using given id
    */
    public int deleteCopyById(int id) {
        int check = CommonConstants.OPERATION_FAILED;
        Copy copy = new Copy();
        String sql = "delete from "+this.copy.tableName+" where "+this.copy.id_col+" = ?";
        PreparedStatement statement = DbServices.getStatement(sql);

        try {
            //set values in statement
            statement.setInt(1, id);
            DbServices.executeStatement(statement);
            check = CommonConstants.OPRTATOION_SUCCESS;
        } catch (SQLException ex) {
            Logger.getLogger(CopyServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return check;
    }
    
    
    /*
        *Method: deleteCopyByBook
        *Return: int value
        *Parameters: int value
    
        *Operation: Delete all Copy objects using given Book object
    */
    public int deleteCopiesByBook(Book book) {
        int check = CommonConstants.OPERATION_FAILED;
        String sql = "delete from "+this.copy.tableName+" where "+this.copy.isbn_col+" = ?";
        PreparedStatement statement = DbServices.getStatement(sql);

        try {
            //set values in statement
            statement.setString(1, book.getIsbn());
            check = CommonConstants.OPRTATOION_SUCCESS;
        } catch (SQLException ex) {
            Logger.getLogger(CopyServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return check;
    }
    
    
}
