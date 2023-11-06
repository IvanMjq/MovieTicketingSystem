/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

/**
 *
 * @author NgPoh
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MovieTicketingSystem {

    public static void main(String[] args) {
        List<Bookings> bookedFacilities = new ArrayList<>(); // Create a list to store booked facilities

        //create objects
        CustomerAccount[] accounts = new CustomerAccount[3];
        accounts[0] = new Staff();
        accounts[1] = new CustomerAccount();
        accounts[2] = new Member();

        //determine file destination
        String customerFile = "customer.txt";
        String memberFile = "member.txt";
        String staffFile = "staff.txt";

        //input validation variable
        int selection1 = 0;
        int selectionRegister = 0;
        int selectionLogIn = 0;

        CustomerAccount user = null; //determine user log in as which party(Staff Member or normal customer)
        String temp = null;         //this variable stores the name of user
        int validInput = 0;

        String emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern emailPat = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
        //converting classes of objects
        Member member = (Member) accounts[2];
        Staff staff = (Staff) accounts[0];
        char loopMenu = '\u0000';
        do {
            Scanner scan = new Scanner(System.in);
            do {
                System.out.println("\n           Main Menu");                   //prompt main menu
                System.out.println("================================");
                System.out.println("1. Register");
                System.out.println("2. Log In");
                System.out.println("================================");
                System.out.print("Please select an option > ");
                try {
                    selection1 = scan.nextInt();
                    if (selection1 < 1 || selection1 > 2) {
                        System.out.println("Please enter digits between 1 - 2 only!");
                    }
                } catch (Exception e) {
                    System.out.println("Please enter digits only!");
                    scan.nextLine();
                }
            } while (selection1 < 1 || selection1 > 2);

            switch (selection1) {
                case 1:
                    do {
                        System.out.println("\nAccount Types");                  //register account
                        System.out.println("=================");
                        System.out.println("1. Staff");
                        System.out.println("2. Customer");
                        System.out.println("3. Member");
                        System.out.println("=================");
                        System.out.print("Pick an account > ");
                        try {
                            selectionRegister = scan.nextInt();
                            if (selectionRegister < 1 || selectionRegister > 3) {
                                System.out.println("Please enter digits between 1 - 3 only!");
                            }
                        } catch (Exception e) {
                            System.out.println("Please enter digits only!");
                            scan.nextLine();
                        }
                    } while (selectionRegister < 1 || selectionRegister > 3);
                    break;
                case 2:
                    do {
                        System.out.println("\nAccount Types");                  //login account
                        System.out.println("=================");
                        System.out.println("1. Staff");
                        System.out.println("2. Customer");
                        System.out.println("3. Member");
                        System.out.println("=================");
                        System.out.print("Pick an account > ");
                        try {
                            selectionLogIn = scan.nextInt();
                            if (selectionLogIn < 1 || selectionLogIn > 3) {
                                System.out.println("Please enter digits between 1 - 3 only!");
                            }
                        } catch (Exception e) {
                            System.out.println("Please enter digits only!");
                            scan.nextLine();
                        }
                    } while (selectionLogIn < 1 || selectionLogIn > 3);
                    break;
            }

            switch (selectionRegister) {                                        //user register for which type of account
                case 1:
                try {
                    accounts[0].registerAccount(accounts[0], staffFile);
                } catch (IOException e) {
                    System.out.println("Error occured while reading the file!");
                    e.printStackTrace();
                }
                user = staff;
                break;
                case 2:
                try {
                    accounts[1].registerAccount(accounts[1], customerFile);
                } catch (IOException e) {
                    System.out.println("Error occured while reading the file!");
                    e.printStackTrace();
                }
                user = accounts[1];
                break;
                case 3:
                try {
                    accounts[2].registerAccount(accounts[2], memberFile);
                } catch (IOException e) {
                    System.out.println("Error occured while reading the file!");
                    e.printStackTrace();
                }
                user = member;
                break;
            }

            switch (selectionLogIn) {                                           //user log in for which type of account
                case 1:
                try {
                    temp = CustomerAccount.loginUser(staffFile);
                } catch (IOException e) {
                    System.out.println("Error occured while reading the file!");
                    e.printStackTrace();
                }
                user = staff;
                break;
                case 2:
                try {
                    temp = CustomerAccount.loginUser(customerFile);
                } catch (IOException e) {
                    System.out.println("Error occured while reading the file!");
                    e.printStackTrace();
                }
                user = accounts[1];
                break;
                case 3:
                try {
                    temp = CustomerAccount.loginUser(memberFile);
                } catch (IOException e) {
                    System.out.println("Error occured while reading the file!");
                    e.printStackTrace();
                }
                user = member;
                break;
            }

            if (staff.equals(user)) {
                int menuSelect = 0;
                do {
                    System.out.println("       \nStaff Settings");                  //if user login as staff this menu will show
                    System.out.println("============================");
                    System.out.println("1. Convert Customer Account ");
                    System.out.println("2. Modify Movie Settings");
                    System.out.println("3. Modify Events Settings");
                    System.out.println("============================");
                    System.out.print("Pick an Option > ");
                    try {
                        menuSelect = scan.nextInt();
                        if (menuSelect < 1 || menuSelect > 3) {
                            System.out.println("Please enter digits between 1 - 3 only!");
                            scan.nextLine();
                        }
                    } catch (Exception e) {
                        System.out.println("Please enter digits only!");
                        scan.nextLine();
                    }
                } while (menuSelect < 1 || menuSelect > 3);
                switch (menuSelect) {
                    case 1:
                        member.customerToMember();
                        break;
                    case 2:
                        boolean valid = false;
                        int movieIpt = 0;

                        do {
                            valid = false;
                            do {
                                try {
                                    System.out.println("   \nMovie Settings");
                                    System.out.println("====================");
                                    System.out.println("1. Add movies");
                                    System.out.println("2. Delete movies");
                                    System.out.println("3. Exit");
                                    System.out.println("====================");
                                    System.out.print("Enter your choice > ");
                                    movieIpt = scan.nextInt();
                                    valid = true;
                                } catch (Exception e) {
                                    System.out.println("Invalid input. Please enter a number.");
                                    scan.nextLine(); // Clear the input buffer
                                }
                            } while (!valid);
                            if (movieIpt == 1) {
                                Movie.addMovie();
                            } else if (movieIpt == 2) {
                                Movie.deleteMovie();
                            } else if (movieIpt > 3) {
                                System.out.println("Invalid Input, Please enter between 1 - 3!!");
                            }
                        } while (movieIpt != 3);

                        break;
                    default:
                        Event event = new Event();
                        int choice = 0;
                        do {
                            System.out.println(" \n\nEvents Settings");
                            System.out.println("=================");
                            System.out.println("1. Add Events");
                            System.out.println("2. Delete Events");
                            System.out.println("3. Exit");
                            System.out.println("=================");
                            System.out.print("Enter an option > ");
                            try {
                                choice = scan.nextInt();
                                if (choice < 1 || choice > 3) {
                                    System.out.println("Please enter digits between 1 - 3 only!");
                                }
                            } catch (Exception e) {
                                System.out.println("Please enter digits only!");
                                scan.nextLine();
                            }
                        } while (choice < 1 || choice > 3);
                        switch (choice) {
                            case 1:
                                event.addEventDetails();
                                break;
                            case 2:
                                Event.deleteEventDetails();
                                break;
                            default:
                                break;
                        }
                        break;
                }
            } else {
                int memberOption = 0;
                do {
                    System.out.println("\n      Customer Orders");                                  //if user login as customer or member, this menu will show
                    System.out.println("============================");
                    System.out.println("1. Purchase Movie Ticket");
                    System.out.println("2. Facility Booking and Events");
                    System.out.println("3. Purchase Merch");
                    System.out.println("============================");
                    System.out.print("Pick an option > ");
                    try {
                        memberOption = scan.nextInt();
                        if (memberOption < 1 || memberOption > 3) {
                            System.out.println("Please enter digits between 1 - 3 only!");
                        }
                    } catch (Exception e) {
                        System.out.println("Please enter digits only!");
                        scan.nextLine();
                    }
                } while (memberOption < 1 || memberOption > 3);
                double totalCost = 0.0;
                boolean hasBuyTicket = false;
                char orderTicket = '\u0000';
                Orders orders = new Orders(temp);
                switch (memberOption) {
                    case 1:
                        Movie.appendNewLine("hmm");
                        int userInp = 0;
                        while (userInp != 3) {
                            userInp = Movie.displayList();
                            switch (userInp) {
                                case 1:
                                    Movie.display();
                                    break;
                                case 2:
                                    Movie.buy();
                                    totalCost += 13;
                                    hasBuyTicket = true;
                                    break;
                                case 3:
                                    Movie.overWriteTxtFile();
                                    Movie.appendNewLine();
                                    orderTicket = FoodCombo.foodInput();
                                    break;
                                default:
                                    System.out.println("Invalid input, please enter digit betwen 1 - 3!");
                                    break;
                            }
                        }
                        if (orderTicket == 'y' || hasBuyTicket == true) {
                            Orders order = new Orders(temp);

                            System.out.println("\n--------Movie Purchase Summary----------");
                            System.out.println("----------------------------------------");
                            System.out.println("Order ID: " + orders.getOrderID());
                            System.out.println("Username: " + temp);
                            System.out.println("----------------------------------------");
                            System.out.printf("%-31sPrice(RM)\n", "Movie Purchased");
                            System.out.println("----------------------------------------");

                            try (BufferedReader reader = new BufferedReader(new FileReader("membTicket&Food.txt"))) {
                                String line;
                                int lineCount = 0;
                                double moviePrice = 13.00;
                                int count = 1;

                                while ((line = reader.readLine()) != null) {
                                    String[] splt = line.split(":");
                                    lineCount++;

                                    if (lineCount == 2) {
                                        System.out.println("Movie:");
                                        for (int i = 0; i < splt.length; i++) {
                                            if (hasBuyTicket) {
                                                System.out.printf("%-4s%-31s%.2f\n", count, splt[i], moviePrice);
                                                count++;
                                                i++;
                                            } else {
                                                System.out.printf("%-31s\n", "-");
                                            }

                                        }
                                    } else if (lineCount == 3) {
                                        count = 1;
                                        System.out.println("Food:");
                                        for (int i = 0; i < splt.length; i++) {
                                            double itemPrice = Double.parseDouble(splt[i + 1]);
                                            System.out.printf("%-4s%-31s%s0\n", count, splt[i], splt[i + 1]);
                                            count++;
                                            totalCost += itemPrice;
                                            i++;
                                        }
                                    }
                                }
                                System.out.println("----------------------------------------");

                                System.out.printf("Total Cost\t\t\t   %.2f\n", totalCost);
                                char memberPeriod = '\u0000';
                                double membershipDiscount = 0.0;
                                if (member.equals(user)) {
                                    memberPeriod = Member.getMemberDiscountType(temp);
                                    if (memberPeriod == 'L') {
                                        totalCost *= Member.getDiscountLong();
                                    } else {
                                        totalCost *= Member.getDiscountShort();
                                    }
                                    System.out.printf("After discount:\t\t\t  %.2f\n", totalCost);
                                }
                                System.out.println("----------------------------------------");
                                order.setTotalPrice(totalCost);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            //double latestTotal = 0.0;
                            char memberPeriod = '\u0000';
                            int paymentChoice;
                            //double testTotal = 0.0;
                            do {
                                System.out.print("\nAre you sure you want to make order?(Y/N)->");
                                String orderConfirmation = scan.next();
                                if (orderConfirmation.equalsIgnoreCase("Y")) {
                                    System.out.println("\nPayment Methods:");
                                    System.out.println("1. Cash");
                                    System.out.println("2. Debit Card/Credit Card");
                                    System.out.println("3. QR Payment");
                                    do {
                                        System.out.print("Select a payment method: ");
                                        paymentChoice = scan.nextInt();
                                        if (paymentChoice != 1 && paymentChoice != 2 && paymentChoice != 3) {
                                            System.out.println("Error input!");
                                        }
                                    } while (paymentChoice != 1 && paymentChoice != 2 && paymentChoice != 3);
                                    String paymentMethod = "";

                                    switch (paymentChoice) {
                                        case 1:
                                            paymentMethod = "Cash";
                                            break;
                                        case 2:
                                            paymentMethod = "Debit Card/Credit Card";
                                            break;
                                        case 3:
                                            paymentMethod = "QR Payment";
                                            break;
                                    }
                                    Payment payment = new Payment(order.getOtherTotalPrice(), paymentMethod);
                                    //membershipDiscount = payment.getTotalPaid();
                                    Payment.payment(order, scan, temp, paymentChoice, totalCost);

                                    System.out.printf("Total Amount: RM%.2f", totalCost);
                                    System.out.println("\n------------------------------------");
                                    Payment.writePaymentDetailsToFile(order, payment, temp, totalCost);
                                    Orders.saveNextOrderID();
                                    break;

                                } else if (orderConfirmation.equalsIgnoreCase("N")) {
                                    System.out.println("\nOrder canceled. Thank you!");
                                    break;

                                } else {
                                    System.out.println("Invalid input. Please enter Y to confirm or N to cancel.");
                                }
                            } while (true);
                        }
                        break;
                    case 2:
                        Bookings bookings = new Bookings();
                        Event event = new Event();
                        Scanner scanner = new Scanner(System.in);
                        int choice = 0;
                        do {
                            System.out.println(" \nEvents and Facilities");
                            System.out.println("======================");
                            System.out.println("1. View Event");
                            System.out.println("2. Booking Facility");
                            System.out.println("3. Exit");
                            System.out.println("======================");
                            System.out.print("Select an option (1-3): ");
                            try {
                                choice = 0;
                                choice = scanner.nextInt();
                                if (choice < 1 || choice > 3) {
                                    System.out.println("Invalid Selection! Please try again!");
                                }
                            } catch (Exception e) {
                                System.out.println("Please enter digits only!");
                                scan.nextLine();
                            }
                            switch (choice) {
                                case 1:
                                    int lineCount = Event.eventLineCount();
                                    Event.displayEventMenu(lineCount);
                                    break;
                                case 2:
                                    Bookings.bookFacility(bookings, scanner);
                                    bookedFacilities.add(bookings);
                                    break;
                                case 3:
                                    break; // Exit the program
                            }
                        } while (choice < 1 || choice > 3 || choice == 1);
                        if (choice != 3) {
                            if (!bookedFacilities.isEmpty()) {
                                System.out.println("\n------------Booking summary-------------");
                                System.out.println("Order ID: " + orders.getOrderID());
                                System.out.println("Username: " + temp);
                                System.out.println("----------------------------------------");
                                System.out.printf("%-31sPrice(RM)\n", "Booking venue");
                                System.out.println("----------------------------------------");
                                double totalVenuePrice = 0.0;
                                for (Bookings booking : bookedFacilities) {
                                    String venueName = booking.getVenueName();
                                    double venuePrice = booking.getVenuePrice();
                                    System.out.printf("%-31s %.2f\n", venueName, venuePrice);
                                    totalVenuePrice += venuePrice;
                                }
                                System.out.println("----------------------------------------");
                                System.out.printf("Total Cost\t\t\t%.2f\n", totalVenuePrice);
                                System.out.println("----------------------------------------");
                                Orders order = new Orders(temp);
                                order.setTotalPrice(totalVenuePrice);
                                char memberPeriod = '\u0000';
                                double membershipDiscount = 0.0;
                                int paymentChoice;
                                System.out.print("\nAre you sure you want to make order?(Y/N)->");
                                String orderConfirmation = scan.next();

                                if (orderConfirmation.equalsIgnoreCase("Y")) {
                                    System.out.println("\nPayment Methods:");
                                    System.out.println("1. Cash");
                                    System.out.println("2. Debit Card/Credit Card");
                                    System.out.println("3. QR Payment");
                                    do {
                                        System.out.print("Select a payment method: ");
                                        paymentChoice = scan.nextInt();
                                        if (paymentChoice != 1 && paymentChoice != 2 && paymentChoice != 3) {
                                            System.out.println("Error input!");
                                        }
                                    } while (paymentChoice != 1 && paymentChoice != 2 && paymentChoice != 3);
                                    String paymentMethod = "";

                                    switch (paymentChoice) {
                                        case 1:
                                            paymentMethod = "Cash";
                                            break;
                                        case 2:
                                            paymentMethod = "Debit Card/Credit Card";
                                            break;
                                        case 3:
                                            paymentMethod = "QR Payment";
                                            break;
                                        default:
                                            System.out.println("Invalid payment method choice.");
                                            break;
                                    }

                                    Payment payment = new Payment(order.getOtherTotalPrice(), paymentMethod);

                                    membershipDiscount = Payment.payment(order, scan, temp, paymentChoice, totalVenuePrice);
                                    if (member.equals(user)) {
                                        memberPeriod = Member.getMemberDiscountType(temp);
                                        if (memberPeriod == 'L') {
                                            membershipDiscount = totalCost * Member.getDiscountLong();
                                        } else {
                                            membershipDiscount = totalCost * Member.getDiscountShort();
                                        }
                                    }
                                    System.out.printf("Total Paid: RM%.2f\n", membershipDiscount);
                                    System.out.println("----------------------------------------");
                                    Payment.writePaymentDetailsToFile(order, payment, temp, membershipDiscount);
                                    Orders.saveNextOrderID();
                                    break;

                                } else if (orderConfirmation.equalsIgnoreCase("N")) {
                                    System.out.println("\nOrder canceled. Thank you!");
                                    break;

                                } else {
                                    System.out.println("Invalid input. Please enter Y to confirm or N to cancel.");
                                }

                            }

                            break;
                        }
                        break;
                    case 3:
                        boolean continueInput = true;
                        int merchOrderTicket = 0;

                        Merch.displayMerchItems();//display merch item available

                        do {
                            int merchItemID;

                            do {
                                System.out.print("Enter the MerchID you want to buy(0 to end): ");
                                try {
                                    merchItemID = scan.nextInt();
                                    if (merchItemID == 0) {
                                        merchOrderTicket = 1;
                                        continueInput = false;
                                        break; // Exit the inner loop when 0 is entered
                                    } else if (merchItemID == Merch.tshirt.getMerchID() || merchItemID == Merch.poster.getMerchID()
                                            || merchItemID == Merch.badge.getMerchID() || merchItemID == Merch.mug.getMerchID()
                                            || merchItemID == Merch.hat.getMerchID()) {
                                        MerchOrders merchOrder = new MerchOrders(orders.getOrderID(), merchItemID);
                                        orders.addMerchandiseOrder(merchOrder);
                                        System.out.println("Item added to the order.");
                                        String merchItemName = Merch.getMerchItemName(merchItemID);
                                        double merchItemPrice = Merch.getMerchItemPrice(merchItemID);
                                    } else {
                                        System.out.println("Invalid Merchandise Item ID. Please try again.");
                                    }
                                } catch (Exception ex) {
                                    System.out.println("Please enter digits only!");
                                    scan.nextLine();
                                }
                            } while (true); // Inner loop for merchandise ID input

                        } while (continueInput); // Outer loop to continue ordering

                        if (merchOrderTicket == 1) {
                            System.out.println("\n----------Order Summary----------");
                            System.out.println("Order ID: " + orders.getOrderID());
                            System.out.println("Username: " + temp);
                            System.out.println("---------------------------------");
                            System.out.printf("Item\t\t\tPrice(RM)\n");
                            System.out.println("---------------------------------");

                            //this part is to display what you have order
                            for (MerchOrders merchOrder : orders.getMerchandiseOrders()) {
                                int merchItemID = merchOrder.getMerchItemID();
                                String merchItemName = Merch.getMerchItemName(merchItemID);
                                double merchItemPrice = Merch.getMerchItemPrice(merchItemID);
                                System.out.printf("%s\t\t  %.2f\n", merchItemName, merchItemPrice);
                            }
                            System.out.println("---------------------------------");
                            double totalPrice = orders.getTotalPrice();
                            char membershipPeriod = '\u0000';
                            if (member.equals(user)) {
                                membershipPeriod = Member.getMemberDiscountType(temp);
                                if (membershipPeriod == 'L') {
                                    totalPrice *= Member.getDiscountLong();
                                } else {
                                    totalPrice *= Member.getDiscountShort();
                                }
                                System.out.printf("Total Price:\t\t  %.2f\n", orders.getTotalPrice());
                                System.out.printf("After discount:\t\t  %.2f\n", totalPrice);
                            } else {
                                System.out.printf("Total Price:\t\t  %.2f\n", totalPrice);
                            }

                            System.out.println("---------------------------------");
                            int paymentChoice;

                            do {
                                System.out.print("\nAre you sure you want to make order?(Y/N)->");
                                String orderConfirmation = scan.next();
                                if (orderConfirmation.equalsIgnoreCase("Y")) {
                                    System.out.println("\nPayment Methods:");
                                    System.out.println("1. Cash");
                                    System.out.println("2. Debit Card/Credit Card");
                                    System.out.println("3. QR Payment");
                                    do {
                                        System.out.print("Select a payment method: ");
                                        paymentChoice = scan.nextInt();
                                        if (paymentChoice != 1 && paymentChoice != 2 && paymentChoice != 3) {
                                            System.out.println("Error input!");
                                        }
                                    } while (paymentChoice != 1 && paymentChoice != 2 && paymentChoice != 3);
                                    String paymentMethod = "";

                                    switch (paymentChoice) {
                                        case 1:
                                            paymentMethod = "Cash";
                                            break;
                                        case 2:
                                            paymentMethod = "Debit Card/Credit Card";
                                            break;
                                        case 3:
                                            paymentMethod = "QR Payment";
                                            break;
                                        default:
                                            System.out.println("Invalid payment method choice.");
                                            break;
                                    }

                                    Payment payment = new Payment(totalPrice, paymentMethod);
                                    Payment.payment(orders, scan, temp, paymentChoice, totalPrice);
                                    System.out.printf("Total Amount: RM%.2f", totalPrice);
                                    System.out.println("\n---------------------------------");
                                    Payment.writePaymentDetailsToFile(orders, payment, temp, totalPrice);
                                    Orders.saveNextOrderID();

                                    break;
                                } else if (orderConfirmation.equalsIgnoreCase("N")) {
                                    System.out.println("\nOrder canceled. Thank you!");
                                    break;
                                } else {
                                    System.out.println("Invalid input. Please enter Y to confirm or N to cancel.");
                                }
                            } while (true);
                            break;
                        }
                }

            }
            do {
                System.out.print("Do you want to return to main menu? (y/n) > ");
                try {
                    loopMenu = scan.next().charAt(0);
                    loopMenu = Character.toUpperCase(loopMenu);
                    if (loopMenu != 'Y' && loopMenu != 'N') {
                        System.out.println("Please enter between y or n only!");
                    }
                } catch (Exception ex) {
                    System.out.println("Please enter characters only!");
                    scan.nextLine();
                }
            } while (loopMenu != 'Y' && loopMenu != 'N');
        } while (loopMenu == 'Y');
    }
}
