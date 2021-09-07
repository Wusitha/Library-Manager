/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import entities.Book;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.CommonConstants;

/**
 *
 * @author Wsuitha
 */
public class BookServices {
    private ArrayList<Book> allBooks = new ArrayList<Book>();
    Book book = new Book();
    /*
        *Method: setBookObject
        *Return: void
        *Parameters: ResultSet object, Book object.
    
        *Operation: set attributes in given Book object using given ResultSet
    */
    public void setBookObject(ResultSet result) {
        
        try {
            if(result.next())
                this.book.setIsbn(result.getString(this.book.isbn_col));
                this.book.setName(result.getString(this.book.name_col));
                this.book.setSubject(result.getString(this.book.subject_col));
                this.book.setAuthor(result.getString(this.book.author_col));
                this.book.setPublisher(result.getString(this.book.publisher_col));
                this.book.setCost(result.getDouble(this.book.cost_col));
                
        } catch (SQLException ex) {
            Logger.getLogger(BookServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    /*
        *Method: setBookObjectList
        *Return: void
        *Parameters: ResultSet object
    
        *Operation: set book objects list using given ResultSet
    */
    private void setBookObjectList(ResultSet results) {
        this.allBooks.clear();
        
        
        try {
            while(results.next()){
                Book item = new Book();
                item.setIsbn(results.getString(book.isbn_col));
                item.setName(results.getString(book.name_col));
                item.setSubject(results.getString(book.subject_col));
                item.setAuthor(results.getString(book.author_col));
                item.setPublisher(results.getString(book.publisher_col));
                item.setCost(results.getDouble(book.cost_col));
                this.allBooks.add(item);
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
    /*
        *Method: getBookByIsbn
        *Return: Book object
        *Parameters: String object (isbn).
    
        *Operation: Return Book object for the given isbn number
    */
    public Book getBookByIsbn(String isbn){
        
        String sql = "select* from "+this.book.table_Name+" where isbn=?";
        PreparedStatement statement = DbServices.getStatement(sql);
        try {
            statement.setString(1, isbn);
            ResultSet result = DbServices.getData(statement);
            setBookObject(result);
        } catch (SQLException ex) {
            Logger.getLogger(BookServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return this.book;
    }
    
    
    /*
        *Method: getAllBooks
        *Return: Array List
        *Parameters: 
    
        *Operation: Load all records in book table
    */
    public ArrayList<Book> getAllBooks() {
        
        String sql = "select * from "+this.book.table_Name;
        PreparedStatement statement = DbServices.getStatement(sql);
        
        
        try {
            ResultSet results = DbServices.getData(statement);
            //set object list
            setBookObjectList(results);
            
        } catch (SQLException ex) {
            Logger.getLogger(BookServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return allBooks;
    }
    
    
    
    /*
        *Method: saveBook
        *Return: int value
        *Parameters: Book object.
    
        *Operation: Save Book object
    */
    public int saveBook(Book book) {
        int check = CommonConstants.OPERATION_FAILED;
        String sql = "insert into "+book.table_Name+" "
                + "("+book.isbn_col+
                ","+book.name_col+
                ","+book.subject_col+
                ","+book.author_col+
                ","+book.publisher_col+
                ","+book.cost_col+")"
                + " values(?,?,?,?,?,?)";
        
        
        PreparedStatement statement = DbServices.getStatement(sql);
        try {
            //Set statement column variables
            statement.setString(1, book.getIsbn());
            statement.setString(2, book.getName());
            statement.setString(3, book.getSubject());
            statement.setString(4, book.getAuthor());
            statement.setString(5, book.getPublisher());
            statement.setDouble(6, book.getCost());
            
            //Save data
            DbServices.executeStatement(statement);
            check = CommonConstants.OPRTATOION_SUCCESS;
            
        } catch (SQLException ex) {
            Logger.getLogger(BookServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return check;
        
    }
    
    
    
    /*
        *Method: updateBook
        *Return: int value
        *Parameters: Book object.
    
        *Operation: Update Book object
    */
    public int updateBook(Book book) {
        int check = CommonConstants.OPERATION_FAILED;
        String sql = "update "+book.table_Name+" set "
                +book.name_col+" = ?,"
                +book.subject_col+" = ?,"
                +book.author_col+" = ?,"
                +book.publisher_col+" = ?,"
                +book.cost_col+" = ? "
                + "where "+book.isbn_col+" = ?";
        
        
        PreparedStatement statement = DbServices.getStatement(sql);
        try {
            //Set statement column variables
            statement.setString(1, book.getName());
            statement.setString(2, book.getSubject());
            statement.setString(3, book.getAuthor());
            statement.setString(4, book.getPublisher());
            statement.setDouble(5, book.getCost());
            statement.setString(6, book.getIsbn());
            
            //Save data
            DbServices.executeStatement(statement);
            check = CommonConstants.OPRTATOION_SUCCESS;
            
        } catch (SQLException ex) {
            Logger.getLogger(BookServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return check;
        
    }
    
    
    
    
    /*
        *Method: deleteBook
        *Return: int value
        *Parameters: Book object.
    
        *Operation: Delete Book object
    */
    public int deleteBook(Book book) {
        int result = CommonConstants.OPERATION_FAILED;
        String sql = "delete from "+book.table_Name+" where "+book.isbn_col+"=?";
        PreparedStatement statement = DbServices.getStatement(sql);
        try {
            //Set columns in statemnet
            statement.setString(1, book.getIsbn());
            DbServices.executeStatement(statement);
            result = CommonConstants.OPRTATOION_SUCCESS;
        } catch (SQLException ex) {
            Logger.getLogger(BookServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
    
    
    /*
        *Method: searchBookByName
        *Return: Array List
        *Parameters: String object.
    
        *Operation: Search Book records using given key
    */
    public ArrayList<Book> searchBookByName(String key) {
        
        String sql = "select * from "+this.book.table_Name+" where "+this.book.name_col+" like ?";
        PreparedStatement statement = DbServices.getStatement(sql);
        try {
            //Set values in statement
            statement.setString(1, "%"+key+"%");
            ResultSet results = DbServices.getData(statement);
            
            
            //set object list
            setBookObjectList(results);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(BookServices.class.getName()).log(Level.SEVERE, null, ex);
        }

        return this.allBooks;
    }
    
    
    
}
