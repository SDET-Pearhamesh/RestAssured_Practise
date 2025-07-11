package org.practise;
import org.testng.annotations.Test;

import java.io.*;

    // Make sure the Student class implements Serializable
    class Student implements Serializable {
        String name;
        int rollNumber;

        public Student(String name, int rollNumber) {
            this.name = name;
            this.rollNumber = rollNumber;
        }

        @Override
        public String toString() {
            return "Student [Name: " + name + ", Roll Number: " + rollNumber + "]";
        }
    }


    public class SerializationExample {

        @Test(enabled = false)
        public static void main(String[] args) {
            // --- Serialization ---
            Student student1 = new Student("Alice", 101);
            String filename = "student.ser"; // .ser is a common extension for serialized objects

            try {
                // Saving of object in a file
                FileOutputStream file = new FileOutputStream(filename);
                ObjectOutputStream out = new ObjectOutputStream(file);

                // Method for serialization of object
                out.writeObject(student1);

                out.close();
                file.close();

                System.out.println("Student object has been serialized!");
                System.out.println("Original Object: " + student1);

            } catch (IOException ex) {
                System.out.println("Serialization failed: " + ex.getMessage());
            }

            // --- Deserialization ---
            Student student2 = null;

            try {
                // Reading the object from a file
                FileInputStream file = new FileInputStream(filename);
                ObjectInputStream in = new ObjectInputStream(file);

                // Method for deserialization of object
                student2 = (Student) in.readObject();

                in.close();
                file.close();

                System.out.println("\nStudent object has been deserialized!");
                System.out.println("Deserialized Object: " + student2);

            } catch (IOException ex) {
                System.out.println("Deserialization failed (IO): " + ex.getMessage());
            } catch (ClassNotFoundException ex) {
                System.out.println("Deserialization failed (Class not found): " + ex.getMessage());
            }
        }
    }

