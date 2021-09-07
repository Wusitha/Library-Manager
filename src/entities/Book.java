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
public class Book {
    private String isbn;
    private String name;
    private String subject;
    private String author;
    private String publisher;
    private double cost;
    
    //Table column names
    public String isbn_col = "isbn";
    public String name_col = "name";
    public String subject_col = "subject";
    public String author_col = "author";
    public String publisher_col = "publisher";
    public String cost_col = "cost";
    public String table_Name = "book";

    public Book() {
    }

    //Overloaded constructor
    public Book(String isbn, String name, String subject, String author, String publisher, double cost) {
        this.isbn = isbn;
        this.name = name;
        this.subject = subject;
        this.author = author;
        this.publisher = publisher;
        this.cost = cost;
    }

    
    //Getters

    public String getIsbn() {
        return isbn;
    }

    public String getName() {
        return name;
    }

    public String getSubject() {
        return subject;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public double getCost() {
        return cost;
    }
    
    
    //setters

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
    
    
}
