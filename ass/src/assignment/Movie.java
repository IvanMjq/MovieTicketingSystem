/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

/**
 *
 * @author ivanmjq
 */
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public abstract class Movie {

    private String movieID, movieTitle, releaseDate;
    private int movieDuration;
    private double rating;
    private static int currentYear = 2023;

    public Movie(String movieID, String movieTitle, String releaseDate, int movieDuration, double rating) {
        this.movieID = movieID;
        this.movieTitle = movieTitle;
        this.releaseDate = releaseDate;
        this.movieDuration = movieDuration;
        this.rating = rating;
    }

    public String getMovieID() {
        return movieID;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public int getMovieDuration() {
        return movieDuration;
    }

    public double getRating() {
        return rating;
    }

    public void setMovieID(String movieID) {
        this.movieID = movieID;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setMovieDuration(int movieDuration) {
        this.movieDuration = movieDuration;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public static int getCurrentYear() {
        return currentYear;
    }

    public static void setCurrentYear(int currentYear) {
        Movie.currentYear = currentYear;
    }

    @Override
    public String toString() {
        return String.format("Movie Title: %s\nRelease Date: %s\nMovie Duration: %d Minutes\nRating: %.2f\n", movieTitle, releaseDate, movieDuration, rating);
    }

    private static List<Movie> movies = getAllMovies();
    private static List<char[]> tickets = getAllMovieTicket();

    public static int displayList() {
        Scanner sn = new Scanner(System.in);
        boolean validInput = false; //initialize for try-catch method
        int ipt = 0; //user input

        do {
            try {
                System.out.println("    \nMovie Orders");
                System.out.println("======================");
                System.out.println("1 - Movies List");
                System.out.println("2 - Buy Movie Ticket");
                System.out.println("3 - Exit");
                System.out.println("======================");
                System.out.print("Enter your choice > ");
                ipt = sn.nextInt();
                validInput = true;
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                sn.nextLine(); // Clear the input buffer
            }
        } while (!validInput);
        return ipt;
    }

    public static void display() { //let user input to choose to see the details info of the movies
        int userChoice;
        displayMovies();
        userChoice = getUserMovieInput();
        Movie showMovie = movies.get(userChoice - 1);
        System.out.println(showMovie.toString());

    }

    public static void displayMovies() {
        int i = 1;
        System.out.println("");
        for (Movie m : movies) {
            System.out.print(i);
            System.out.println(". " + m.getMovieTitle());
            i++;
        }
    }

    public static int getUserMovieInput() {
        Scanner sn1 = new Scanner(System.in);
        int noOfMovie = movies.size();
        int ipt = 0;
        boolean validInput = false;

        do {
            validInput = false;
            do {
                try {
                    System.out.print("Enter the number of the movie in the list > ");
                    ipt = sn1.nextInt();
                    validInput = true;
                } catch (Exception e) {
                    System.out.println("Invalid input. Please enter a number.");
                    sn1.nextLine(); // Clear the input buffer
                }
                if (ipt < 1 || ipt > noOfMovie) {
                    System.out.println("Invalid Input. Please enter between 1 - " + noOfMovie + "!!");
                }
            } while (!validInput);
        } while (ipt < 1 || ipt > noOfMovie);
        return ipt;
    }

    public static void buy() {
        Scanner sn2 = new Scanner(System.in);
        boolean validInput = false;
        int userIpt;
        displayMovies();
        int i = 1, row = 0, column = 0, ticketBuffer = 0;
        userIpt = getUserMovieInput();
        char[] movieTickets = tickets.get(userIpt - 1);

        //display cinema seat
        System.out.print("+-------------+\n" + "|    Screen   |\n" + "+-------------+\n" + "  ");
        for (int j = 0; j < 30; j++) {
            System.out.print(movieTickets[j]);
            if (j == 4 || j == 14 || j == 24) {
                System.out.print(" ");
            }
            if (j == 9 || j == 19 || j == 29) {
                System.out.println(" " + i + " ");
                i++;
                System.out.print("  ");
            }
        }
        System.out.println("\n  01234 56789");
        System.out.println("+-------------+\n");

        do {
            do {
                validInput = false;
                do {
                    try {
                        System.out.print("Enter the number of row    > ");
                        row = sn2.nextInt();
                        validInput = true;
                    } catch (Exception e) {
                        System.out.println("Invalid input. Please enter a number.");
                        sn2.nextLine(); // Clear the input buffer
                    }
                } while (!validInput);
                if (row < 1 || row > 3) {
                    System.out.println("Invalid input, Please enter between 1 - 3!!");
                }
            } while (row < 1 || row > 3);
            do {
                validInput = false;
                do {
                    try {
                        System.out.print("Enter the number of column > ");
                        column = sn2.nextInt();
                        validInput = true;
                    } catch (Exception e) {
                        System.out.println("Invalid input. Please enter a number.");
                        sn2.nextLine(); // Clear the input buffer
                    }
                } while (!validInput);
                if (column < 0 || column > 9) {
                    System.out.println("Invalid input, Please enter between 1 - 9!!");
                }
            } while (column < 0 || column > 9);
            ticketBuffer = (row - 1) * 10 + column;

            if (movieTickets[ticketBuffer] == 'X') {  //error msg
                System.out.println("The seat had been sold!! Please choose again!!");
            }
        } while (movieTickets[ticketBuffer] == 'X');

        //update tickett
        movieTickets[ticketBuffer] = 'X';
        //save ticket purchased into txt file
        tickets.set(userIpt - 1,movieTickets);
        appendTicketPurchase(userIpt, row, column);
    }

    public static void overWriteTxtFile() {
        try (BufferedWriter writeTicket = new BufferedWriter(new FileWriter("seat.txt"))) {
            for (int j=0;j<tickets.size();j++) {
                char[] buffer = tickets.get(j);
                for (int i = 0; i < 30; i++) {
                    writeTicket.write(buffer[i]);
                    writeTicket.write(":");
                }
                writeTicket.newLine();
            }
            writeTicket.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void appendTicketPurchase(int userIpt, int row, int column) {
        Movie movie = movies.get(userIpt - 1);

        try (BufferedWriter writeTicketPurc = new BufferedWriter(new FileWriter("membTicket&Food.txt", true))) {
            writeTicketPurc.write(movie.getMovieTitle());
            writeTicketPurc.write(':');
            writeTicketPurc.write("Row" + row + "Column" + column);
            writeTicketPurc.write(':');

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void appendNewLine() {
        try (BufferedWriter writeNewLine = new BufferedWriter(new FileWriter("membTicket&Food.txt", true))) {
            writeNewLine.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void appendNewLine(String hmm) {
        try (BufferedWriter writeNewLine = new BufferedWriter(new FileWriter("membTicket&Food.txt"))) {
            writeNewLine.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //read movies from text file
    public static List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("movie.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] splt = line.split(":");
                String movieID = splt[0];
                String movieTitle = splt[1];
                String releaseDate = splt[2];
                int movieDuration = Integer.parseInt(splt[3]);
                double rating = Double.parseDouble(splt[4]);
                String genre = splt[5];
                String movieDirector = splt[6];
                String mainCast = splt[7];
                String plotSummary = splt[8];

                Movie movie = null;

                if (genre.equals("Adventure") == true) {
                    movie = new ActionMovie(movieID, movieTitle, releaseDate, movieDuration, rating, movieDirector, mainCast, plotSummary);
                } else if (genre.equals("Crime") == true) {
                    movie = new CrimeMovie(movieID, movieTitle, releaseDate, movieDuration, rating, movieDirector, mainCast, plotSummary);
                } else {
                    movie = new SciFiMovie(movieID, movieTitle, releaseDate, movieDuration, rating, movieDirector, mainCast, plotSummary);
                }

                movies.add(movie); // Add the movie to the ArrayList

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return movies;
    }

    //read seat from text file
    public static List<char[]> getAllMovieTicket() {
        List<char[]> tickett = new ArrayList<>();
        int i = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("seat.txt"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                char[] buffer = new char[30];
                String[] splt = line.split(":");
                for (int j = 0; j < 30; j++) {
                    buffer[j] = splt[j].charAt(0);
                }
                tickett.add(buffer);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return tickett;
    }

    public static void addMovie() {
        Scanner sc2 = new Scanner(System.in);
        String movieID, movieTitle, releaseDate, movieDirector, mainCast, plotSummary;
        int movieDuration = 0, chooseGenre = 0;
        double movieRating = 0.0;
        boolean validInput = false;
        char userCfm = 'n';
        int date = 0, month = 0, year = 0;
        String genre;

        do {
            System.out.print("Enter the new Movie ID (Mx)  > ");
            movieID = sc2.nextLine();
            if (movieID.charAt(0) != 'M') {
                System.out.println("Invalid Movie ID format, Please enter again!!");
            }
            if (movieID.length() > 3) {
                System.out.println("Invsalid Movie ID format, Only Can Contains maximun 3 Chararcter");
            }
        } while (movieID.charAt(0) != 'M' || movieID.length() > 3);

        do {
            System.out.print("Enter the new movie Title > ");
            movieTitle = sc2.nextLine();
            do {
                System.out.print("Are you sure with this Movie Title (y/n) > ");
                userCfm = sc2.next().charAt(0);
                if (userCfm != 'y' && userCfm != 'n') {
                    System.out.println("Please enter 'y' or 'n' only!!");
                }
                sc2.nextLine();
            } while (userCfm != 'y' && userCfm != 'n');
        } while (userCfm != 'y');

        do {
            validInput = false;
            System.out.print("Enter the new Movie Release Date(dd-mm-yyyy) > ");
            releaseDate = sc2.next();
            String[] splt = releaseDate.split("-");
            date = Integer.parseInt(splt[0]);
            month = Integer.parseInt(splt[1]);
            year = Integer.parseInt(splt[2]);
            if (date < 1 || releaseDate.length() != 10) {
                System.out.println("Incorrect format!! Please enter a valid date!!");
                continue;
            }
            if (year < currentYear) {
                System.out.println("Incorrect format!! Year should be or exceed " + currentYear);
                continue;
            }
            if (month > 12) {
                System.out.println("One year only have 12 months!!");
                continue;
            }
            if (month == 2) {
                if (date > 29 && year%4 == 0) {
                    System.out.println("This year is a leap year and this month only have 29 days!!");
                    continue;
                }
                else if (date > 28 && year%4 != 0) {
                    System.out.println("This year is a non-leap year and this month only have 28 days!!");
                    continue;
                }
            }
            if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
                if (date > 31) {
                    System.out.println("This month only have 31 days!!");
                    continue;
                }
            }
            if (month == 4 || month == 6 || month == 9 || month == 11) {
                if (date > 30) {
                    System.out.println("This month only have 30 days!!");
                    continue;
                }
            }

            validInput = true;
        } while (!validInput);

        do {
            validInput = false;
            do {
                try {
                    System.out.print("Enter the new Movie Duration > ");
                    movieDuration = sc2.nextInt();
                    validInput = true;
                } catch (Exception e) {
                    System.out.println("Invalid input. Please enter a number!!");
                    sc2.nextLine(); // Clear the input buffer
                }
            } while (!validInput);
            if (movieDuration <= 50) {
                System.out.println("Invalid movie duration, should be exceed 50!!");
            }
        } while (movieDuration <= 50);

        do {
            validInput = false;
            do {
                try {
                    System.out.print("Enter the new Movie Rating > ");
                    movieRating = sc2.nextDouble();
                    validInput = true;
                } catch (Exception e) {
                    System.out.println("Invalid input. Please enter a number!!");
                    sc2.nextLine(); // Clear the input buffer
                }
            } while (!validInput);
            if (movieRating < 0.1 || movieRating > 5.0) {
                System.out.println("Invalid rating, the rating should between 0.1 - 5.0");
            }
            sc2.nextLine();
        } while (movieRating < 0.1 || movieRating > 5.0);

        do {
            System.out.print("Enter the new Movie Director > ");
            movieDirector = sc2.nextLine();
            do {
                System.out.print("Are you sure with this Movie Director (y/n) > ");
                userCfm = sc2.next().charAt(0);
                if (userCfm != 'y' && userCfm != 'n') {
                    System.out.println("Please enter 'y' or 'n' only!!");
                }
                sc2.nextLine();
            } while (userCfm != 'y' && userCfm != 'n');
        } while (userCfm != 'y');

        do {
            System.out.print("Enter the new Movie Main Cast > ");
            mainCast = sc2.nextLine();
            do {
                System.out.print("Are you sure with this Movie MainCast (y/n) > ");
                userCfm = sc2.next().charAt(0);
                if (userCfm != 'y' && userCfm != 'n') {
                    System.out.println("Please enter 'y' or 'n' only!!");
                }
                sc2.nextLine();
            } while (userCfm != 'y' && userCfm != 'n');
        } while (userCfm != 'y');

        do {
            System.out.print("Enter the new Movie Plot Summary > ");
            plotSummary = sc2.nextLine();
            do {
                System.out.print("Are you sure with this Movie Plot Summary (y/n) > ");
                userCfm = sc2.next().charAt(0);
                if (userCfm != 'y' && userCfm != 'n') {
                    System.out.println("Please enter 'y' or 'n' only!!");
                }
                sc2.nextLine();
            } while (userCfm != 'y' && userCfm != 'n');
        } while (userCfm != 'y');

        do {
            do {
                try {
                    System.out.println("Select the genre of the new movie:");
                    System.out.println("1 - Action Movie");
                    System.out.println("2 - Crime Movie");
                    System.out.println("3 - Science Fiction Movie");
                    System.out.print("Enter your choice > ");
                    chooseGenre = sc2.nextInt();
                    validInput = true;
                } catch (Exception e) {
                    System.out.println("Invalid input. Please enter a number.");
                    sc2.nextLine(); // Clear the input buffer
                }
            } while (!validInput);
        } while (chooseGenre < 1 || chooseGenre > 3);

        switch (chooseGenre) {
            case 1:
                genre = "Action";
                break;
            case 2:
                genre = "Crime";
                break;
            default:
                genre = "Sci-Fi";
                break;
        }

        do {
            System.out.print("Are you sure want to add this new movies?(y/n) > ");
            userCfm = sc2.next().charAt(0);
            if (userCfm != 'y' && userCfm != 'n') {
                System.out.println("Invalid Input, Please enter y or n only!!");
            }
        } while (userCfm != 'y' && userCfm != 'n');

        if (userCfm == 'n') {
            return;
        }

        try (BufferedWriter writeMovie = new BufferedWriter(new FileWriter("movie.txt", true))) {
            writeMovie.write(movieID + ":" + movieTitle + ":" + releaseDate + ":" + movieDuration + ":" + movieRating + ":"
                    + genre + ":" + movieDirector + ":" + mainCast + ":" + plotSummary);
            writeMovie.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Movie movie = null;

        if (genre.equals("Adventure") == true) {
            movie = new ActionMovie(movieID, movieTitle, releaseDate, movieDuration, movieRating, movieDirector, mainCast, plotSummary);
        } else if (genre.equals("Crime") == true) {
            movie = new CrimeMovie(movieID, movieTitle, releaseDate, movieDuration, movieRating, movieDirector, mainCast, plotSummary);
        } else {
            movie = new SciFiMovie(movieID, movieTitle, releaseDate, movieDuration, movieRating, movieDirector, mainCast, plotSummary);
        }
        
        

        movies.add(movie); // Add the movie to the ArrayList
        char[] fillEmptySeat = new char[30];
        for (int i=0;i<30; i++) {
            fillEmptySeat[i] = 'O';
        }
        tickets.add(fillEmptySeat);
        overWriteTxtFile();

    }

    public static void deleteMovie() {
        int userIpt;
        char userCfm;
        Scanner sc3 = new Scanner(System.in);

        do {
            Movie.displayMovies();
            userIpt = Movie.getUserMovieInput();
            System.out.print("Are you sure want to delete this movies?(y/n) > ");
            userCfm = sc3.next().charAt(0);
            if (userCfm != 'y' && userCfm != 'n') {
                System.out.println("Invalid Input, Please enter y or n only!!");
            }
        } while (userCfm != 'y' && userCfm != 'n');

        if (userCfm == 'n') {
            return;
        }

        movies.remove(userIpt - 1);
        tickets.remove(userIpt - 1);
        overWriteTxtFile(); 

        try (BufferedWriter overWriteMovie = new BufferedWriter(new FileWriter("movie.txt"))) {
            for (Movie m : movies) {
                if (m instanceof ActionMovie) {
                    ActionMovie buf1 = (ActionMovie) m;
                    overWriteMovie.write(buf1.delete() + "\n");
                } else if (m instanceof SciFiMovie) {
                    SciFiMovie buf1 = (SciFiMovie) m;
                    overWriteMovie.write(buf1.delete() + "\n");
                } else if (m instanceof CrimeMovie) {
                    CrimeMovie buf1 = (CrimeMovie) m;
                    overWriteMovie.write(buf1.delete() + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
