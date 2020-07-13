package ir.ac.kntu;

import java.io.Serializable;


public class Person implements Serializable {
    private static final long serialVersionUID = 1; 
    public Person(String fname, String lname) {
        this.lname = lname;
        this.fname = fname;
    }

    private String fname; 
    private String lname; 

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }
    
    @Override
    public String toString() {
        return fname + " " + lname; 
    }
}