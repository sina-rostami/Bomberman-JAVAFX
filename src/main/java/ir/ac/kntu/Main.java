package ir.ac.kntu;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class Main {
    public static void main(String[] args) {
        Person person1 = new Person("morteza", "tahgaddomi");
        Person person2 = new Person("Ali", "Alavi");

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("persons")); 
                oos.writeObject(person1);
                oos.writeObject(person2);
        }catch (Exception e) {
            System.out.println(e);
        }


        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("persons")); 
            Person p1 = (Person) ois.readObject();
            System.out.println(p1);
            Person p2 = (Person) ois.readObject();
            System.out.println(p2);
            }catch(Exception e) {
                e.printStackTrace();
            }

            
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("persons")); 
                oos.writeObject(person1);
                oos.writeObject(person2);
        }catch (Exception e) {
            System.out.println(e);
        }


        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("persons")); 
            Person p1 = (Person) ois.readObject();
            System.out.println(p1);
            Person p2 = (Person) ois.readObject();
            System.out.println(p2);
            }catch(Exception e) {
                e.printStackTrace();
            }
    }
    
}