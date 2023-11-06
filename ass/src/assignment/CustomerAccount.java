/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author NgPoh
 */
public class CustomerAccount {

    private String name;
    private String password;
    private String email;
    private String contactNo;
    private char gender;
    private int yearOfBirth;
    private String location;

    public CustomerAccount() {
    }

    public CustomerAccount(String name, String password, String email, String contactNo, char gender, int yearOfBirth, String location) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.contactNo = contactNo;
        this.gender = gender;
        this.yearOfBirth = yearOfBirth;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getContactNo() {
        return contactNo;
    }

    public char getGender() {
        return gender;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public String getLocation() {
        return location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public static int checkAlpha(String input, int x) {
        boolean hasDigits = false;
        for (int i = 0; i < input.length(); i++) {
            if (Character.isDigit(input.charAt(i)) == true) {
                hasDigits = true;
            }
        }
        if (hasDigits) {
            System.out.println("Please enter Alphabets only!");
        } else {
            x += 1;
        }
        return x;
    }

    public static int checkDigit(String input, int y) {
        boolean hasAlphabets = false;
        for (int i = 0; i < input.length(); i++) {
            if (Character.isAlphabetic(input.charAt(i)) == true) {
                hasAlphabets = true;
            }
        }
        if (hasAlphabets) {
            System.out.println("Please enter Digits only!");
        } else {
            y += 1;
        }
        return y;
    }

    public static String loginUser(String fileName) throws IOException {
        String logInName = null;
        Scanner scan = new Scanner(System.in);
        FileInputStream fileInputStream = new FileInputStream(fileName);        //file objects
        StringBuilder stringBuilder = new StringBuilder();
        String emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";            //email validation
        Pattern emailPat = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
        int success = 0;
        int byteRead;
        String combinedInput = null;
        while ((byteRead = fileInputStream.read()) != -1) {
            char character = (char) byteRead;
            stringBuilder.append(character);
        }
        String fileContents = stringBuilder.toString();
        do {
            success = 0;
            String logInEmail = null;
            String logInPassword = null;
            do {
                System.out.print("Enter Username > ");
                logInName = scan.nextLine();
                if (checkAlpha(logInName, 0) != 1) {
                    System.out.println("Please enter alphabets only!");
                }
            } while (checkAlpha(logInName, 0) == 0);

            do {
                System.out.print("Enter email > ");
                logInEmail = scan.nextLine();
                Matcher matcher = emailPat.matcher(logInEmail);
                if (matcher.find() == false) {
                    System.out.println("Email is entered with incorrect format! Please try again!");
                } else {
                    success = 1;
                }
            } while (success != 1);

            do {
                System.out.print("Enter Password (6 - 16 characters) > ");
                logInPassword = scan.nextLine();
                if (logInPassword.length() > 16 || logInPassword.length() < 6) {
                    System.out.println("Please enter password between 6 to 16 characters!");
                } else {
                    success = 2;
                }
            } while (success != 2);
            
            combinedInput = logInName + ':' + logInEmail + ':' + logInPassword + ":";
            if (fileContents.contains(combinedInput)) {
                System.out.println("Log In Successfully!");
            } else {
                System.out.println("Username or Email or Password incorrect!");
            }
            //return logInName;
        } while (!fileContents.contains(combinedInput));
        fileInputStream.close();
        return logInName;
       
    }

    public static boolean checkFileContents(String fileName, String content) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(fileName);
        StringBuilder stringBuilder = new StringBuilder();

        int byteRead;

        while ((byteRead = fileInputStream.read()) != -1) {
            char character = (char) byteRead;
            stringBuilder.append(character);
        }
        String fileContents = stringBuilder.toString();         //convert contents of file into a string
        fileInputStream.close();
        if (fileContents.contains(content)) {
            return true;
        } else {
            return false;
        }
    }

    public void registerAccount(CustomerAccount account, String fileName) throws IOException {
        int correctInput = 0;
        FileWriter fileWrite = new FileWriter(fileName, true);          //file objects
        PrintWriter bufferedWrite = new PrintWriter(fileWrite);
        String emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";      //email validation
        Pattern emailPat = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
        Scanner scan = new Scanner(System.in);
        //prompt name
        do {
            System.out.print("Enter Name > ");
            String nonMemberName = scan.nextLine();
            account.setName(nonMemberName);
            correctInput = checkAlpha(account.getName(), correctInput);
        } while (correctInput == 0);

        //prompt Password
        do {
            System.out.print("Enter Password (6 - 16 characters) > ");
            String nonMemberPassword = scan.nextLine();
            account.setPassword(nonMemberPassword);
            if (account.getPassword().length() > 16 || account.getPassword().length() < 6) {
                System.out.println("Please enter password between 6 to 16 characters!");
            } else {
                correctInput = 2;
            }
        } while (correctInput != 2);

        //prompt email
        do {

            System.out.print("Enter email > ");
            String nonMemberEmail = scan.next();
            account.setEmail(nonMemberEmail);
            Matcher matcher = emailPat.matcher(account.getEmail());
            if (matcher.find() == false) {
                System.out.println("Incorrect Email Format! Please reenter!");
            } else if (checkFileContents("customer.txt", account.getEmail()) == true || checkFileContents("member.txt", account.getEmail()) == true) {
                System.out.println("Email already registered!");
            } else {
                correctInput = 3;
            }
        } while (correctInput != 3);

        //prompt contact No
        do {
            System.out.print("Enter Contact Number > ");
            String nonMemberNum = scan.next();
            account.setContactNo(nonMemberNum);
            correctInput = checkDigit(account.getContactNo(), correctInput);
        } while (correctInput != 4);

        //prompt customer gender
        do {
            System.out.print("Enter gender > ");
            try {
                char nonMemberGender = scan.next().charAt(0);
                nonMemberGender = Character.toUpperCase(nonMemberGender);
                account.setGender(nonMemberGender);
                if (account.getGender() != 'F' && account.getGender() != 'M') {
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
                int nonMemberYear = scan.nextInt();
                account.setYearOfBirth(nonMemberYear);
                if (account.getYearOfBirth() < 1940 || account.getYearOfBirth() > 2010) {
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
            String nonMemberLocation = scan.nextLine();
            account.setLocation(nonMemberLocation);
            correctInput = checkAlpha(account.getLocation(), correctInput);
        } while (correctInput != 7);
        bufferedWrite.println(account.getName() + ":" + account.getEmail() + ":" + account.getPassword() + ":" + account.getYearOfBirth() + ':' + account.getContactNo() + ":" + account.getGender() + ":" + account.getLocation());
        bufferedWrite.close();
        System.out.println(account.toString() + "\n");
    }

    @Override
    public String toString() {
        return "Welcome! " + name + "!";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CustomerAccount custAcc = (CustomerAccount) obj;
        return true;
    }
}
