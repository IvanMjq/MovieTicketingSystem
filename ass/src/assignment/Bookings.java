/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author Acer
 */
public class Bookings {
    private int venueID;
    private int capacity;
    private String purpose;   
    private String companyName;
    private String date;
    private String bookingStartTime;
    private String bookingEndTime;
    private String venueName;
    private double venuePrice;
    private static int currentYear = 2023;
    private static double firstVenue = 2500.00;
    private static double secondVenue = 3000.00;
    private static double thirdVenue = 5000.00;
    private static double fourthVenue = 1500.00;
    private static double fifthVenue = 1200.00;

        // Function to book a facility
    public static void bookFacility(Bookings bookings, Scanner scanner) {
        int venueID = 0;
        do {
            System.out.println("Booking Details");
            System.out.println("1001. Midvalley Exhibition Centre - RM2500");
            System.out.println("1002. Pavilion Bukit Jalil Exhibition Lobby - RM3000");
            System.out.println("1003. Pavilion Bukit Bintang Exhibition Lobby - RM5000");
            System.out.println("1004. Setapak Central Mall - RM1500");
            System.out.println("1005. Tropicana Gardens Mall Convention Centre - RM1200");
            System.out.print("\n");
            System.out.print("Enter Venue ID: ");
            try {
                venueID = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                if (venueID < 1001 || venueID > 1005) {
                    System.out.println("Please enter venueID between 1001 - 1005 only!");
                }
            } catch (Exception e) {
                System.out.println("Please enter digits only!");
                scanner.nextLine();
            }
        } while (venueID < 1001 || venueID > 1005);

        boolean hasDigits = false;
        String companyName = null;
        do {
            System.out.print("Enter Company Name: ");
            companyName = scanner.nextLine(); // Use nextLine to read the entire line
            bookings.setCompanyName(companyName);
            hasDigits = false;
            for (int i = 0; i < companyName.length(); i++) {
                if (Character.isDigit(companyName.charAt(i)) == true) {
                    System.out.println("Alphebet only!");
                    hasDigits = true;
                    break;
                }
            }
        } while (hasDigits == true);

        
        int capacity = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Enter Capacity (pax): ");
    try {
        capacity = scanner.nextInt();
        validInput = true; // Set validInput to true to exit the loop if the input is valid
     } catch (Exception ex) {
        System.out.println("Please enter digits only!");
        scanner.nextLine(); // Consume the newline character
    }
}

        scanner.nextLine();
        hasDigits = false;
        String purpose = null;
        do {
            System.out.print("Enter Purpose for booking: ");
            purpose = scanner.nextLine(); // Use nextLine to read the entire line
            hasDigits = false;
            for (int i = 0; i < purpose.length(); i++) {
                if (Character.isDigit(purpose.charAt(i)) == true) {
                    System.out.println("Alphebet only!");
                    hasDigits = true;
                    break;
                }
            }
        } while (hasDigits == true);

        int day = 0, month = 0, year = 0;
        boolean valid = false;

        do {
            System.out.print("Enter booking date (DD/MM/YYYY): ");
            String input = scanner.nextLine();
            String[] dateParts = input.split("/");

            if (dateParts.length != 3) {
                System.out.println("Invalid input format. Please use DD/MM/YYYY.");
                continue;
            }

            try {
                day = Integer.parseInt(dateParts[0]);
                month = Integer.parseInt(dateParts[1]);
                year = Integer.parseInt(dateParts[2]);

                if (year < currentYear) {
                    System.out.println("Incorrect format!! Year should be or exceed " + currentYear);
                    continue;
                }
                if (month == 2) {
                    if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                        if (day > 29) {
                            System.out.println("This is a leap year. February can have 29 days.");
                            continue;
                        }
                    } else {
                        if (day > 28) {
                            System.out.println("February can have 28 days in non-leap years.");
                            continue;
                        }
                    }
                } else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
                    if (day > 31) {
                        System.out.println("This month only has 31 days!!");
                        continue;
                    }
                } else if (month == 4 || month == 6 || month == 9 || month == 11) {
                    if (day > 30) {
                        System.out.println("This month only has 30 days!!");
                        continue;
                    }
                }

                valid = true; // Set 'valid' to true if all checks pass

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter numeric values for day, month, and year.");
            }
        } while (!valid);

        valid = false;
        int hours = 0, minutes = 0;
        String startInput;
        while (!valid) {

            do {
                System.out.print("Enter the Staring time (HH:mm): ");
                startInput = scanner.nextLine();

                if (startInput.length() != 5) {
                    System.out.println("Invalid input format. Please use HH:mm.");
                }
            } while (startInput.length() != 5);

            String[] timeParts = startInput.split(":");
            hours = Integer.parseInt(timeParts[0]);
            minutes = Integer.parseInt(timeParts[1]);

            if (timeParts.length != 2) {
                System.out.println("Invalid input format. Please use HH:mm.");
                continue;
            }

            try {
                hours = Integer.parseInt(timeParts[0]);
                minutes = Integer.parseInt(timeParts[1]);

                if (hours < 0 || hours > 23 || minutes < 0 || minutes > 59) {
                    System.out.println("Invalid time. Please try again.");
                } else {
                    valid = true; // Set valid to true to exit the loop
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter numeric values for hours and minutes.");
            }
            bookings.setBookingStartTime(hours + "." + minutes);
        }

        int endHours = 0, endMinutes = 0;
        String endInput;
        valid = false;

        while (!valid) {
            do {
                System.out.print("Enter the Ending time (HH:mm): ");
                endInput = scanner.nextLine();
                if (endInput.length() != 5) {
                    System.out.println("Invalid input format. Please use HH:mm.");
                }
            } while (endInput.length() != 5);

            String[] endParts = endInput.split(":");
            if (endParts.length != 2) {
                System.out.println("Invalid input format. Please use HH:mm.");
                continue;
            }

            try {
                endHours = Integer.parseInt(endParts[0]);
                endMinutes = Integer.parseInt(endParts[1]);

                if (endHours < 0 || endHours > 23 || endMinutes < 0 || endMinutes > 59) {
                    System.out.println("Invalid time. Please try again.");
                } else {
                    valid = true; // Set validEnd to true to exit the loop
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter numeric values for hours and minutes.");
            }
            bookings.setBookingEndTime(hours + "." + minutes);
        }

        String venueName;

// Use a switch statement to map the venue ID to the venue name
        switch (venueID) {
            case 1001:
                venueName = "Midvalley Exhibition Centre";
                bookings.setVenuePrice(2500);
                bookings.setVenueName(venueName); 
                break;
            case 1002:
                venueName = "Pavilion Bukit Jalil";
                bookings.setVenuePrice(3000);
                bookings.setVenueName(venueName); 
                break;
            case 1003:
                venueName = "Pavilion KL";
                bookings.setVenuePrice(5000);
                bookings.setVenueName(venueName); 
            break;
            case 1004:
                venueName = "Setapak Central Mall";
                bookings.setVenuePrice(1500);
                bookings.setVenueName(venueName); 
            break;
            case 1005:
                venueName = "Tropicana Gardens Mall";
                bookings.setVenuePrice(1200);
                bookings.setVenueName(venueName); 
            break;
            default:
                venueName = "Error!";
                bookings.setVenuePrice(0.0); 
                bookings.setVenueName(venueName);
                break;
                }

        try {
            FileWriter fileWrite = new FileWriter("bookings.txt");
            PrintWriter printWrite = new PrintWriter(fileWrite, true);

            printWrite.println(venueName + ':' + companyName + ":" + capacity + ":" + purpose + ":" + day + ":" + month + ":" + year + ":" + hours + ":" + minutes + ":" + endHours + ":" + endMinutes + ":" + bookings.getVenuePrice());
            fileWrite.close();
            printWrite.close();
        } catch (IOException e) {
            System.out.println("Error occured while reading file");
        }
        
    }

    
    public Bookings() {
    }
       
    public Bookings(int venueID, int capacity, String purpose, String companyName, String date,String bookingStartTime,String bookingEndTime) {
        this.venueID = venueID;
        this.capacity = capacity;
        this.purpose = purpose;
        this.companyName = companyName;
        this.date = date;
        this.bookingStartTime = bookingStartTime;
        this.bookingEndTime = bookingEndTime;
    }

     public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }
    
    public int getVenueID() {
        return venueID;
    }

    public int getCapacity() {
        return capacity;
    }
 public String getPurpose() {
        return purpose;
    }

    public String getCompanyName() {
        return companyName;
    }
    
    public String getDate() {
        return date;
    }
    public String getBookStartTime() {
        return bookingStartTime;
    }
    public String getBookEndTime() {
        return bookingEndTime;
    }

    public double getVenuePrice() {
        return venuePrice;
    }

    public void setVenuePrice(double venuePrice) {
        this.venuePrice = venuePrice;
    }
    
    public void setVenueID(int venueID) {
        this.venueID = venueID;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setBookingStartTime(String bookingStartTime) {
        this.bookingStartTime = bookingStartTime;
    }

    public void setBookingEndTime(String bookingEndTime) {
        this.bookingEndTime = bookingEndTime;
    }

    public static double getFirstVenue() {
        return firstVenue;
    }

    public static double getSecondVenue() {
        return secondVenue;
    }

    public static double getThirdVenue() {
        return thirdVenue;
    }

    public static double getFourthVenue() {
        return fourthVenue;
    }

    public static double getFifthVenue() {
        return fifthVenue;
    }
    
    
    
  }
