/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package assignment;

import java.util.Scanner;
import java.io.File;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Acer
 */
class Event {

    private String eventTitle;
    private int eventCapacity;
    private String eventOrganizer;
    private String date;
    private String startingTime;
    private String endingTime;
    private String eventVenue;
    private String eventRegisterDuration;

    public Event() {
    }
    Scanner scanner = new Scanner(System.in);

    public Event(String eventTitle, int eventCapacity, String eventOrganizer, String eventVenue, String date, String startingTime,
            String endingTime, String eventRegisterDuration) {
        this.eventTitle = eventTitle;
        this.eventCapacity = eventCapacity;
        this.eventOrganizer = eventOrganizer;
        this.eventVenue = eventVenue;
        this.date = date;
        this.startingTime = startingTime;
        this.endingTime = endingTime;
        this.eventRegisterDuration = eventRegisterDuration;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public void setEventCapacity(int eventCapacity) {
        this.eventCapacity = eventCapacity;
    }

    public void setEventOrganizer(String eventOrganizer) {
        this.eventOrganizer = eventOrganizer;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setStartingTime(String startingTime) {
        this.startingTime = startingTime;
    }

    public void setEndingTime(String endingTime) {
        this.endingTime = endingTime;
    }

    public void setEventVenue(String eventVenue) {
        this.eventVenue = eventVenue;
    }

    public void setEventRegisterDuration(String eventRegisterDuration) {
        this.eventRegisterDuration = eventRegisterDuration;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public int getEventCapacity() {
        return eventCapacity;
    }

    public String getEventOrganizer() {
        return eventOrganizer;
    }

    public String getEventVenue() {
        return eventVenue;
    }

    public String getDate() {
        return date;
    }

    public String getStartingTime() {
        return startingTime;
    }

    public String getEndingTime() {
        return endingTime;
    }

    public String getEventRegisterDuration() {
        return eventRegisterDuration;
    }

    public static void displayEventMenu(int lineCount) {
        Scanner scanner = new Scanner(System.in);
        int eventNumber = 0;
        int i = 0;
        String currentLine = null;
        do {
            System.out.println("Event Selection Menu:");
            try {
                FileReader fileReadEvents = new FileReader("events.txt");
                BufferedReader bufferedReadEvents = new BufferedReader(fileReadEvents);
                i = 0;
                while ((currentLine = bufferedReadEvents.readLine()) != null) {
                    i++;
                    if (i != 1) {
                        String[] eventDetails = currentLine.split(":");
                        System.out.println(i - 1 + ". " + eventDetails[0]);
                    }
                }
                System.out.print("Select an event details to view: ");
                try {
                    eventNumber = scanner.nextInt();
                    if (eventNumber < 1 || eventNumber >= i) {
                        System.out.println("Please enter selection between 1 to " + (i - 1) + '!');
                    }
                } catch (Exception ex) {
                    System.out.println("Please enter digits only");
                    scanner.nextLine();
                }
            } catch (IOException e) {
                System.out.println("Error occured while reading file!");
            }
        } while (eventNumber < 1 || eventNumber > i - 1);

        String extractedLine = null;
        int lineRead = 0;
        currentLine = null;
        try {
            FileReader fileReadEvents = new FileReader("events.txt");
            BufferedReader bufferedEvents = new BufferedReader(fileReadEvents);
            while ((currentLine = bufferedEvents.readLine()) != null) {
                lineRead++;
                if (lineRead != 0) {
                    if (lineRead == (eventNumber + 1)) {
                        extractedLine = currentLine;
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println("Error occured while reading the file!");
        }
        Event.displayEventsDetails(extractedLine);
    }

    public static void displayEventsDetails(String line) {
        try {
            String currentLine = null;
            FileReader fileReadEvents = new FileReader("events.txt");
            BufferedReader bufferedRead = new BufferedReader(fileReadEvents);
            String[] eventDetails = line.split(":");
            System.out.println("----------------------------------------------");
            System.out.println("                   " + eventDetails[0] + "                     ");
            System.out.println("----------------------------------------------");
            System.out.println("  Event Capacity    --> " + eventDetails[1] + " people");
            System.out.println("  Event Organizer   --> " + eventDetails[2]);
            System.out.println("  Event Venue       --> " + eventDetails[3]);
            System.out.println("  Event Date        --> " + eventDetails[4]);
            System.out.println("  Starting Time     --> " + eventDetails[5]);
            System.out.println("  Ending Time       --> " + eventDetails[6]);
            System.out.println("  Register Duration --> " + eventDetails[7]);
            System.out.print("\n");
            fileReadEvents.close();
            bufferedRead.close();
        } catch (IOException e) {
            System.out.println("Error occured while reading the file!");
        }
    }

    public static int eventLineCount() {
        int lineCount = 0;
        String currentLine = null;
        try {
            FileReader eventReader = new FileReader("events.txt");
            BufferedReader bufferedReadEvent = new BufferedReader(eventReader);
            while ((currentLine = bufferedReadEvent.readLine()) != null) {
                lineCount++;
            }
            eventReader.close();
            bufferedReadEvent.close();
        } catch (IOException e) {
            System.out.println("Error occured while reading the file!");
        }
        return lineCount;
    }

    public static void printEventsToFile(Event events) {
        try {
            FileWriter eventFileWrite = new FileWriter("events.txt", true);
            PrintWriter eventPrintWrite = new PrintWriter(eventFileWrite, true);
            eventPrintWrite.println(events.getEventTitle() + ":" + events.getEventCapacity() + ":" + events.getEventOrganizer() + ":" + events.getEventVenue() + ":" + events.getDate() + ":" + events.getStartingTime() + ":" + events.getEndingTime() + ":" + events.getEventRegisterDuration());
            eventFileWrite.close();
            eventPrintWrite.close();
        } catch (IOException e) {
            System.out.println("Error occured while reading file!");
        }
    }

    public void addEventDetails() {
        Scanner scanner = new Scanner(System.in);
        boolean hasDigits = false;
        String eventTitle = null;
        Event events = new Event();
        do {
            System.out.print("Enter Event Title: ");
            eventTitle = scanner.nextLine(); // Use nextLine to read the entire line
            events.setEventTitle(eventTitle);
            hasDigits = false;
            for (int i = 0; i < eventTitle.length(); i++) {
                if (Character.isDigit(eventTitle.charAt(i)) == true) {
                    System.out.println("Alphebet only!");
                    hasDigits = true;
                    break;
                }
            }
        } while (hasDigits == true);

        boolean validInput = false;

        do {
            System.out.print("Enter Capacity (pax): ");
            try {
                events.setEventCapacity(scanner.nextInt());
                validInput = true;
            } catch (Exception ex) {
                System.out.println("Please enter digits only!");
                scanner.nextLine(); // Consume the newline character
            }
        } while (!validInput);
        scanner.nextLine();
        do {
            System.out.print("Enter Event Organizer: ");
            eventOrganizer = scanner.nextLine(); // Use nextLine to read the entire line
            events.setEventOrganizer(eventOrganizer);
            hasDigits = false;
            for (int i = 0; i < eventOrganizer.length(); i++) {
                if (Character.isDigit(eventOrganizer.charAt(i)) == true) {
                    System.out.println("Alphebet only!");
                    hasDigits = true;
                    break;
                }
            }
        } while (hasDigits == true);

        do {
            System.out.print("Enter Event Venue: ");
            eventVenue = scanner.nextLine(); // Use nextLine to read the entire line
            events.setEventVenue(eventVenue);
            hasDigits = false;
            for (int i = 0; i < eventVenue.length(); i++) {
                if (Character.isDigit(eventVenue.charAt(i)) == true) {
                    System.out.println("Alphebet only!");
                    hasDigits = true;
                    break;
                }
            }
        } while (hasDigits == true);

        int day = 0, month = 0, year = 0;
        boolean valid = false;
        do {
            String eventDate = null;
            //scanner.nextLine();
            System.out.print("Enter Event Date (DD/MM/YYYY): ");
            eventDate = scanner.nextLine();
            String[] dateParts = eventDate.split("/");

            if (eventDate.length() != 10) {
                System.out.println("Invalid input format. Please use DD/MM/YYYY.");
                valid = false;
            } else {
                try {
                    day = Integer.parseInt(dateParts[0]);
                    month = Integer.parseInt(dateParts[1]);
                    year = Integer.parseInt(dateParts[2]);

                    if (day < 1 || month < 1 || month > 12 || year < 2022 || year > 2025) {
                        System.out.println("Invalid date. Year should be in the range of 2022-2025, and day/month values should be valid.");
                        valid = false;
                    } else {
                        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                            if (month == 2) {
                                if (day > 29) {
                                    System.out.println("This is a leap year. February can have 29 days.");
                                    continue;
                                }
                            }
                        } else {
                            if (month == 2) {
                                if (day > 28) {
                                    System.out.println("February can have 28 days in non-leap years.");
                                    continue;
                                }
                            }
                        }
                        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
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
                        valid = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter numeric values for day, month, and year.");
                }
            }
        } while (!valid);

        events.setDate(day + "/" + month + "/" + year);

        valid = false;
        int hours = 0, minutes = 0;
        String startingTime = null;
        while (!valid) {

            do {
                System.out.print("Enter the Staring time (HH:mm): ");
                startingTime = scanner.nextLine();
                valid = true;

                if (startingTime.length() != 5) {
                    System.out.println("Invalid input format. Please use HH:mm.");
                }
            } while (startingTime.length() != 5);

            String[] timeParts = startingTime.split(":");
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
            events.setStartingTime(hours + "." + minutes);
        }

        int endHours = 0, endMinutes = 0;
        String endingTime = null;
        valid = false;

        while (!valid) {
            do {
                System.out.print("Enter the Ending time (HH:mm): ");
                endingTime = scanner.nextLine();
                valid = true;
                if (endingTime.length() != 5) {
                    System.out.println("Invalid input format. Please use HH:mm.");
                }
            } while (endingTime.length() != 5);

            String[] endParts = endingTime.split(":");
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
            events.setEndingTime(endHours + "." + endMinutes);
        }

        int rDay = 0, rMonth = 0, rYear = 0;
        String endRegister = null;
        do {
            System.out.print("Enter Register Deadline (DD/MM/YYYY): ");
            endRegister = scanner.nextLine();
            String[] dateParts = endRegister.split("/");

            if (endRegister.length() != 10) {
                System.out.println("Invalid input format. Please use DD/MM/YYYY.");
                valid = false;
            } else {
                try {
                    rDay = Integer.parseInt(dateParts[0]);
                    rMonth = Integer.parseInt(dateParts[1]);
                    rYear = Integer.parseInt(dateParts[2]);

                    if (day < 1 || rDay > 31 || rMonth < 1 || rMonth > 12 || rYear < 2022 || rYear > 2025) {
                        System.out.println("Invalid date.Range of Year 2023-2024. Please try again.");
                        valid = false;
                    } else {
                        valid = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter numeric values for day, month, and year.");
                }
            }
        } while (!valid);
        events.setEventRegisterDuration(rDay + "/" + rMonth + "/" + rYear);
        printEventsToFile(events);
    }

    public static void deleteEventDetails() {
        Scanner scanner = new Scanner(System.in);
        int eventNumber = 0;
        int i = 0;
        String currentLine = null;
        do {
            System.out.println("Event Selection Menu:");
            try {
                FileReader fileReadEvents = new FileReader("events.txt");
                BufferedReader bufferedReadEvents = new BufferedReader(fileReadEvents);
                while ((currentLine = bufferedReadEvents.readLine()) != null) {
                    i++;
                    if (i != 1) {
                        String[] eventDetails = currentLine.split(":");
                        System.out.println(i - 1 + ". " + eventDetails[0]);
                    }
                }
                fileReadEvents.close();
                bufferedReadEvents.close();
            } catch (IOException e) {
                System.out.println("Error occured while reading file!");
            }
            System.out.print("Select an event details to delete: ");
            try {
                eventNumber = scanner.nextInt();
                if (eventNumber < 1 || eventNumber >= i) {
                    System.out.println("Please enter selection between 1 to " + i + '!');
                }
            } catch (Exception ex) {
                System.out.println("Please enter digits only");
                scanner.nextLine();
            }
        } while (eventNumber < 1 || eventNumber > i);

        String extractedLine = null;
        int lineRead = 0;
        currentLine = null;
        try {
            FileReader fileReadEvents = new FileReader("events.txt");
            BufferedReader bufferedEvents = new BufferedReader(fileReadEvents);
            while ((currentLine = bufferedEvents.readLine()) != null) {
                lineRead++;
                if (lineRead != 0) {
                    if (lineRead == (eventNumber + 1)) {
                        extractedLine = currentLine;
                    }
                }
            }
            fileReadEvents.close();
            bufferedEvents.close();
        } catch (IOException ex) {
            System.out.println("Error occured while reading the file!");
        }

        String tempFile = "temp.txt";
        File oldFile = new File("events.txt");
        File newFile = new File(tempFile);
        try {
            String deleteLine = null;
            FileWriter fileWrite = new FileWriter(tempFile, true);
            BufferedWriter bufferedWrite = new BufferedWriter(fileWrite);
            PrintWriter printWrite = new PrintWriter(bufferedWrite);

            FileReader fileRead = new FileReader("events.txt");
            BufferedReader bufferedRead = new BufferedReader(fileRead);
            while ((deleteLine = bufferedRead.readLine()) != null) {
                if (!(deleteLine.contains(extractedLine))) {
                    printWrite.println(deleteLine);
                }
            }
            printWrite.flush();
            printWrite.close();
            fileRead.close();
            bufferedRead.close();
            bufferedWrite.close();
            fileWrite.close();

            oldFile.delete();
            File replace = new File("events.txt");
            newFile.renameTo(replace);
        } catch (IOException ex) {
            System.out.println("Error occured while reading the file!");
        }
    }

}
