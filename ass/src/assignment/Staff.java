/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.IOException;

/**
 *
 * @author NgPoh
 */
public class Staff extends CustomerAccount {

    private int hireYear;
    private String position;
    private double salary;
    private String shift;

    public Staff() {
    }

    public Staff(int hireYear, String position, double salary, String supervisor, String shift, String name, String password, String email, String contactNo, char gender, int yearOfBirth, String location) {
        super(name, password, email, contactNo, gender, yearOfBirth, location);
        this.hireYear = hireYear;
        this.position = position;
        this.salary = salary;
        this.shift = shift;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getHireYear() {
        return hireYear;
    }

    public String getPosition() {
        return position;
    }

    public double getSalary() {
        return salary;
    }

    public String getShift() {
        return shift;
    }

    public void setHireYear(int hireYear) {
        this.hireYear = hireYear;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    @Override
    public void registerAccount(CustomerAccount account, String fileName) {
        Staff staff = (Staff) account;
        int correctInput = 0;
        try {
            FileWriter fileWrite = new FileWriter(fileName, true);          //file objects
            PrintWriter bufferedWrite = new PrintWriter(fileWrite);
            String emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";     //email validation
            Pattern emailPat = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
            Scanner scan = new Scanner(System.in);
            //prompt name
            do {
                System.out.print("Enter Name > ");
                String staffName = scan.nextLine();
                staff.setName(staffName);
                correctInput = checkAlpha(staff.getName(), correctInput);
            } while (correctInput == 0);

            //prompt Password
            do {
                System.out.print("Enter Password (6 - 16 characters) > ");
                String staffPassword = scan.nextLine();
                staff.setPassword(staffPassword);
                if (staff.getPassword().length() > 16 || staff.getPassword().length() < 6) {
                    System.out.println("Please enter password between 6 to 16 characters!");
                } else {
                    correctInput = 2;
                }
            } while (correctInput != 2);

            //prompt email
            do {

                System.out.print("Enter email > ");
                String staffEmail = scan.next();
                staff.setEmail(staffEmail);
                Matcher matcher = emailPat.matcher(account.getEmail());
                if (matcher.find() == false) {
                    System.out.println("Incorrect Email Format! Please reenter!");
                } else if (checkFileContents("customer.txt", staff.getEmail()) == true || checkFileContents("member.txt", staff.getEmail()) == true || checkFileContents("staff.txt", staff.getEmail())) {
                    System.out.println("Email already registered!");
                } else {
                    correctInput = 3;
                }
            } while (correctInput != 3);

            //prompt contact No
            do {
                System.out.print("Enter Contact Number > ");
                String staffNum = scan.next();
                staff.setContactNo(staffNum);
                correctInput = checkDigit(staff.getContactNo(), correctInput);
            } while (correctInput != 4);

            //prompt customer gender
            do {
                System.out.print("Enter gender > ");
                try {
                    char staffGender = scan.next().charAt(0);
                    staffGender = Character.toUpperCase(staffGender);
                    staff.setGender(staffGender);
                    if (staff.getGender() != 'F' && staff.getGender() != 'M') {
                        System.out.println("Please only enter F or M!");
                    } else {
                        correctInput = 5;
                    }
                } catch (Exception e) {
                    System.out.println("Please enter single character alphabet only!");
                    scan.nextLine();
                }
            } while (correctInput != 5);

            //prompt year of birth
            do {
                System.out.print("Enter Year of Birth > ");
                try {
                    int staffYear = scan.nextInt();
                    staff.setYearOfBirth(staffYear);
                    if (staff.getYearOfBirth() < 1940 || staff.getYearOfBirth() > 2010) {
                        System.out.println("Please enter a valid year of birth!");
                    } else {
                        correctInput = 6;
                    }
                } catch (Exception e) {
                    System.out.println("Please enter digits only!");
                    scan.nextLine();
                }
            } while (correctInput != 6);
            //prompt location
            do {
                scan.nextLine();
                System.out.print("Enter location > ");
                String staffLocation = scan.nextLine();
                staff.setLocation(staffLocation);
                correctInput = checkAlpha(staff.getLocation(), correctInput);
            } while (correctInput != 7);

            //prompt hireYear
            do {
                scan.nextLine();
                System.out.print("Enter hire Year > ");
                try {
                    int recruit = scan.nextInt();
                    staff.setHireYear(recruit);
                    if (staff.getHireYear() < (staff.getYearOfBirth() + 18)) {
                        System.out.println("Employee has to be at least 18 years old");
                    } else {
                        correctInput = 8;
                    }
                } catch (Exception e) {
                    System.out.println("Please enter digits only!");
                    scan.nextLine();
                }
            } while (correctInput != 8);
            
            //prompt position
            do {
                scan.nextLine();
                System.out.print("Enter Position (Cashier / Janitor / Security Guard) > ");
                String empPosition = scan.nextLine();
                staff.setPosition(empPosition);
                if (!staff.getPosition().equals("Cashier") && !staff.getPosition().equals("Janitor") && !staff.getPosition().equals("Security Guard")) {
                    System.out.println("Please enter Cashier / Janitor / Security Guard only!");
                } else {
                    correctInput = checkAlpha(staff.getPosition(), correctInput);
                }
            } while (correctInput != 9);

            //prompt shift
            do {
                System.out.print("Enter Shift (Night / Day) > ");
                String empShift = scan.nextLine();
                staff.setShift(empShift);
                if (!staff.getShift().equals("Night") && !staff.getShift().equals("Day")) {
                    System.out.println("Please enter only Night or Day Shift!");
                } else {
                    correctInput = checkAlpha(staff.getShift(), correctInput);
                    if (staff.getPosition().equals("Cashier")) {
                        if (staff.getShift().equals("Day")) {
                            staff.setSalary(1500.00);
                        } else {
                            staff.setSalary(2000.00);
                        }
                    } else if (staff.getPosition().equals("Janitor")) {
                        if (staff.getShift().equals("Day")) {
                            staff.setSalary(2000.00);
                        } else {
                            staff.setSalary(1000.00);
                        }
                    } else {
                        if (staff.getShift().equals("Day")) {
                            staff.setSalary(4500.00);
                        } else {
                            staff.setSalary(4700.00);
                        }
                    }
                }
            } while (correctInput != 10);
            System.out.println(toString());
            bufferedWrite.println(staff.getName() + ":" + staff.getEmail() + ":" + staff.getPassword() + ":" + staff.getYearOfBirth() + ':' + staff.getContactNo() + ":" + staff.getGender() + ":" + staff.getLocation() + ':' + staff.getHireYear() + ':' + staff.getPosition() + ':' + staff.getShift() + ':' + staff.getSalary());
            bufferedWrite.close();
        } catch (IOException e) {
            System.out.println("Error occured while reading the file!");
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Welcome! " + super.getName() + " !";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false; // Object is null or of a different class, they are not equal
        }
        Staff staff = (Staff) obj;
        return true;
    }
}
