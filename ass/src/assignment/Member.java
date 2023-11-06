/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

import java.io.BufferedReader;
import java.io.File;
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
public class Member extends CustomerAccount {

    private char memberType;
    private int memberActivationYear;
    private int memberExpiryYear;

    private static int shortMembershipExtensionPeriod = 3;
    private static int longMembershipExtensionPeriod = 10;
    private static double discountLong = 0.70;
    private static double discountShort = 0.80;

    public Member() {
    }

    public Member(char memberType, int memberActivationYear, int memberExpiryYear, String name, String password, String email, String contactNo, char gender, int yearOfBirth, String location) {
        super(name, password, email, contactNo, gender, yearOfBirth, location);
        this.memberType = memberType;
        this.memberActivationYear = memberActivationYear;
        this.memberExpiryYear = memberExpiryYear;
    }

    public char getMemberType() {
        return memberType;
    }

    public int getMemberActivationYear() {
        return memberActivationYear;
    }

    public int getMemberExpiryYear() {
        return memberExpiryYear;
    }

    public static double getDiscountLong() {
        return discountLong;
    }

    public static double getDiscountShort() {
        return discountShort;
    }

    public void setMemberType(char memberType) {
        this.memberType = memberType;
    }

    public void setMemberActivationYear(int memberActivationYear) {
        this.memberActivationYear = memberActivationYear;
    }

    public void setMemberExpiryYear() {
        if (memberType == 'L') {
            memberExpiryYear += memberActivationYear + longMembershipExtensionPeriod;
        } else {
            memberExpiryYear += memberActivationYear + shortMembershipExtensionPeriod;
        }
    }

    @Override
    public void registerAccount(CustomerAccount account, String fileName) {
        Member member = (Member) account;   //convert account into a member account specifically
        int correctInput = 0;
        try {
            FileWriter fileWrite = new FileWriter(fileName, true);      //file objects
            PrintWriter bufferedWrite = new PrintWriter(fileWrite);
            String emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";   //email validations
            Pattern emailPat = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
            Scanner scan = new Scanner(System.in);
            //prompt name
            do {
                System.out.print("Enter Name > ");
                String memberName = scan.nextLine();
                member.setName(memberName);
                correctInput = checkAlpha(member.getName(), correctInput);
            } while (correctInput == 0);

            //prompt Password
            do {
                System.out.print("Enter Password (6 - 16 characters) > ");
                String memberPassword = scan.nextLine();
                member.setPassword(memberPassword);
                if (member.getPassword().length() > 16 || member.getPassword().length() < 6) {
                    System.out.println("Please enter password between 6 to 16 characters!");
                } else {
                    correctInput = 2;
                }
            } while (correctInput != 2);

            //prompt email
            do {

                System.out.print("Enter email > ");
                String memberEmail = scan.next();
                member.setEmail(memberEmail);
                Matcher matcher = emailPat.matcher(member.getEmail());
                if (matcher.find() == false) {
                    System.out.println("Incorrect Email Format! Please reenter!");
                } else if (checkFileContents("customer.txt", member.getEmail()) == true || checkFileContents("member.txt", member.getEmail()) == true || checkFileContents("staff.txt", member.getEmail())) {
                    System.out.println("Email already registered!");
                } else {
                    correctInput = 3;
                }
            } while (correctInput != 3);

            //prompt contact No
            do {
                System.out.print("Enter Contact Number > ");
                String memberNum = scan.next();
                member.setContactNo(memberNum);
                correctInput = checkDigit(member.getContactNo(), correctInput);
            } while (correctInput != 4);

            //prompt customer gender
            do {
                System.out.print("Enter gender > ");
                try {
                    char memberGender = scan.next().charAt(0);
                    memberGender = Character.toUpperCase(memberGender);
                    member.setGender(memberGender);
                    if (member.getGender() != 'F' && member.getGender() != 'M') {
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
                    int memberYear = scan.nextInt();
                    member.setYearOfBirth(memberYear);
                    if (member.getYearOfBirth() < 1940 || member.getYearOfBirth() > 2010) {
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
                String memberLocation = scan.nextLine();
                member.setLocation(memberLocation);
                correctInput = checkAlpha(member.getLocation(), correctInput);
            } while (correctInput != 7);

            //prompt member membership type
            do {
                System.out.println("\n   Available Member Types");
                System.out.println("----------------------------");
                System.out.println("1. Long Term Membership (L)");
                System.out.println("2. Short Term Membership (S)");
                System.out.println("----------------------------");
                System.out.print("Please select a single type of membership (L / S)");
                try {
                    char memType = scan.next().charAt(0);
                    memType = Character.toUpperCase(memType);
                    member.setMemberType(memType);
                    if (member.getMemberType() != 'L' && member.getMemberType() != 'S') {
                        System.out.println("Please enter L or S only!");
                    } else {
                        correctInput = 8;
                    }
                } catch (Exception e) {
                    System.out.println("Please enter single character alphabet only!");
                    scan.nextLine();
                }
            } while (correctInput != 8);

            //prompt member activation and set expiry year
            do {
                System.out.print("Enter Membership Activation Year > ");
                try {
                    int memberActivate = scan.nextInt();
                    member.setMemberActivationYear(memberActivate);
                    if (member.getMemberActivationYear() < member.getYearOfBirth() + 18) {
                        System.out.println("Invalid Member Activation Year! Please reenter!");
                    } else {
                        correctInput = 9;
                        member.setMemberExpiryYear();
                    }
                } catch (Exception e) {
                    System.out.println("Please enter digits only!");
                    scan.nextLine();
                }
            } while (correctInput != 9);
            bufferedWrite.println(member.getName() + ":" + member.getEmail() + ":" + member.getPassword() + ":" + member.getYearOfBirth() + ':' + member.getContactNo() + ":" + member.getGender() + ":" + member.getLocation() + ':' + member.getMemberType() + ':' + member.getMemberActivationYear() + ':' + member.getMemberExpiryYear());
            System.out.println(toString());
            bufferedWrite.close();
            fileWrite.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void customerToMember() {
        Scanner scan = new Scanner(System.in);
        int validInput = 0;
        String username = null;
        String email = null;
        validInput = 0;

        do {
            System.out.print("Enter Customer Username > ");
            username = scan.nextLine();
            try {
                if (CustomerAccount.checkFileContents("customer.txt", username) == false) {
                    if (CustomerAccount.checkFileContents("member.txt", username) == true) {
                        System.out.println("Currrent User is already a member! Please try again!");
                    }else{
                        System.out.println("Username is not registered! Please try again!");
                    }
                } else if (CustomerAccount.checkAlpha(username, 0) != 1) {
                    System.out.println("Please enter alphabets only!");
                } else {
                    validInput = 1;
                }
            } catch (IOException e) {
                System.out.println("Error occured while reading the file!");
                e.printStackTrace();
            }
        } while (validInput != 1);

        validInput = 0;
        do {
            System.out.print("Enter customer email > ");
            email = scan.nextLine();
            try {
                if (CustomerAccount.checkFileContents("customer.txt", email) == false) {
                    if (CustomerAccount.checkFileContents("member.txt", email) == true) {
                        System.out.println("Email is already registered as member! Please try again!");
                    }else{
                        System.out.println("Email not yet registered! Please try again!");
                    }
                } else {
                    validInput = 2;
                }
            } catch (IOException e) {
                System.out.println("Error occured while reading the file!");
                e.printStackTrace();
            }
        } while (validInput != 2);

        try {
            String lineToDelete = username + ':' + email;      //username and email given from user
            File oldFile = new File("customer.txt");
            File newFile = new File("temp.txt");
            String currentLine;

            FileWriter tempWrite = new FileWriter("temp.txt", true);
            PrintWriter tempPrint = new PrintWriter(tempWrite);
            FileWriter memberWrite = new FileWriter("member.txt", true);
            PrintWriter memberPrint = new PrintWriter(memberWrite);
            FileReader frCustomer = new FileReader("customer.txt");
            BufferedReader brCustomer = new BufferedReader(frCustomer);

            while ((currentLine = brCustomer.readLine()) != null) {
                if (!currentLine.contains(lineToDelete)) {    //delete if the currentliine matches with the user's input
                    tempPrint.println(currentLine);
                } else {
                    char memType = '\u0000';

                    //prompt member membership type
                    do {
                        System.out.println("\n   Available Member Types");      //since originally is a customer, prompt more details for member
                        System.out.println("----------------------------");
                        System.out.println("1. Long Term Membership (L)");
                        System.out.println("2. Short Term Membership (S)");
                        System.out.println("----------------------------");
                        System.out.print("Please select a single type of membership (L / S)");
                        try {
                            memType = scan.next().charAt(0);
                            memType = Character.toUpperCase(memType);
                            if (memType != 'L' && memType != 'S') {
                                System.out.println("Please enter L or S only!");
                            } 
                        } catch (Exception e) {
                            System.out.println("Please enter single character alphabet only!");
                            scan.nextLine();
                        }
                    } while (memType != 'L' && memType != 'S');

                    String[] parts = currentLine.split(":");
                    String year = parts[3];
                    int extractedYear = Integer.parseInt(year);
                    //prompt member activation and set expiry year
                    int memberActivate = 0;
                    int memExpiry = 0;
                    do {
                        System.out.print("Enter Membership Activation Year > ");
                        try {
                            memberActivate = scan.nextInt();
                            if (memberActivate < extractedYear + 18) {
                                System.out.println("Invalid Member Activation Year! Please reenter!");
                            } else {
                                if (memType == 'L') {
                                    memExpiry = memberActivate + 10;
                                } else {
                                    memExpiry = memberActivate + 3;
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("Please enter digits only!");
                            scan.nextLine();
                        }
                    } while (memberActivate < extractedYear + 18);
                    memberPrint.println(currentLine + ':' + memType + ':' + memberActivate + ':' + memExpiry);
                }
            }
            memberWrite.close();
            memberPrint.close();
            tempWrite.close();
            tempPrint.close();
            frCustomer.close();
            brCustomer.close();
            oldFile.delete();           //delete the old customer file entirely
            File dump = new File("customer.txt");   //create the name of customer file
            newFile.renameTo(dump); //rename the temp file with updated / deleted record as the latest customer file
        } catch (IOException e) {
            System.out.println("Error occurred while reading the file!");
            e.printStackTrace();
        }
    }

    public static char getMemberDiscountType(String name) {         //return how much discount on the orders a member can get based on their membership period
        char paymentMemberType = '\u0000';

        try {
            String currentLine = null;
            FileReader frMember = new FileReader("member.txt");
            BufferedReader brMember = new BufferedReader(frMember);

            while ((currentLine = brMember.readLine()) != null) {
                if (currentLine.contains(name)) {
                    String[] parts = currentLine.split(":");
                    String memberType = parts[7];
                    paymentMemberType = memberType.charAt(0);
                }
            }
        } catch (IOException e) {
            System.out.println("Error orccured while reading the file!");
        }
        return paymentMemberType;
    }

    @Override
    public String toString() {
        return "Welcome! Member " + super.getName() + "!";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false; // Object is null or of a different class, they are not equal
        }
        Member member = (Member) obj;
        return true;
    }

}
