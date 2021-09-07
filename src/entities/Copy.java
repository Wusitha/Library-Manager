/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Wsuitha
 */
public class Copy {
    
    //Attributes
    private int id;
    private String isbn;
    private String state;
    
    //Table columns
    public String id_col = "id";
    public String isbn_col = "isbn";
    public String state_col = "state";
    public String tableName = "copy";

    //default constructor
    public Copy() {
    }
    
    //overloaded constructors

    public Copy(String isbn, String state) {
        this.isbn = isbn;
        this.state = state;
    }
    
    

    public Copy(int id, String isbn, String state) {
        this.id = id;
        this.isbn = isbn;
        this.state = state;
    }
    
    //getters

    public int getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getState() {
        return state;
    }
    
    
    //setters

    public void setId(int id) {
        this.id = id;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setState(String state) {
        this.state = state;
    }
    
}
