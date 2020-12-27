package entities;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Console {
    public static void main(String[] args) throws IOException {
        PremiereLeagueManager premiereLeagueManager = new PremiereLeagueManager();

        premiereLeagueManager.restorePreviousState("data.ser");

        System.out.println("Welcome to the Football Premier League Manager!");
        System.out.println("-----------------------------------------------");
        while (true) {
            displayMenu(premiereLeagueManager);
        }
    }

    public static void displayMenu(PremiereLeagueManager premiereLeagueManager) throws IOException {
        Scanner scanner;
        int menuOption;

        System.out.println("Menu:");
        System.out.println("-----");
        System.out.println("1: Create new club and add to Premiere League");
        System.out.println("2: Delete existing Club");
        System.out.println("3: Display statistics for a selected Club");
        System.out.println("4: Display the Premiere League Table");
        System.out.println("5: Add match to Premiere League");
        System.out.println("6: Open GUI");
        System.out.println("7: Save & Exit");
        System.out.print("Select an option to continue: ");

        while (true) {
            scanner = new Scanner(System.in);
            try {
                menuOption = scanner.nextInt();
                scanner.nextLine();
                if (menuOption > 0 && menuOption < 8) {
                    break;
                } else {
                    System.out.println("Invalid input. Please select an option from the menu");
                    System.out.print("Select an option to continue: ");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please select an option from the menu");
                System.out.print("Select an option to continue: ");
            }
        }

        switch (menuOption) {
            case 1:
                premiereLeagueManager.restorePreviousState("data.ser");
                // Club Name
                System.out.print("Enter Club Name: ");
                String clubName = scanner.nextLine();
                if (clubExists(premiereLeagueManager, clubName)) {
                    System.out.println("A club with that name already exists!\n");
                    break;
                }

                // Club Location
                System.out.print("Enter location of the club: ");
                String clubLocation = scanner.nextLine();

                // Date Founded
                System.out.println("Enter date founded:");
                Date dateFounded = getDateInput();

                // Head Coach
                System.out.print("Enter name of the head coach: ");
                String headCoach = scanner.nextLine();

                premiereLeagueManager.addNewClub(clubName, clubLocation, dateFounded, headCoach);
                System.out.println("\nClub added to the database!\n");
                premiereLeagueManager.saveState("data.ser");
                break;

            case 2:
                premiereLeagueManager.restorePreviousState("data.ser");
                if (premiereLeagueManager.getClubs().isEmpty()) {
                    System.out.println("There are 0 clubs in the database.\n");
                    break;
                }

                System.out.print("Enter the name of the club that needs to be deleted: ");
                String clubToBeDeleted = scanner.nextLine();
                System.out.println(premiereLeagueManager.deleteClub(clubToBeDeleted));
                premiereLeagueManager.saveState("data.ser");
                break;

            case 3:
                premiereLeagueManager.restorePreviousState("data.ser");
                System.out.print("Enter the name of the club to view the stats: ");
                String club = scanner.nextLine();
                System.out.println(premiereLeagueManager.displayStatsForClub(club));
                break;

            case 4:
                premiereLeagueManager.restorePreviousState("data.ser");
                System.out.println(premiereLeagueManager.displayPremierLeagueTable());
                break;

            case 5:
                premiereLeagueManager.restorePreviousState("data.ser");
                if (premiereLeagueManager.getClubs().isEmpty()) {
                    System.out.println("There aren't any clubs in the database. First add at least two clubs\n" +
                            "to add a match to the Premiere League.\n");
                    break;
                } else if (premiereLeagueManager.getClubs().size() == 1) {
                    System.out.println("There is only 1 club in the database. First add another to add a match\n" +
                            "to the Premiere League.\n");
                    break;
                }

                System.out.println("----------------------------");
                System.out.println("Add match to Premiere League");
                System.out.println("----------------------------");

                // Date Played
                System.out.println("Enter date played:");
                Date datePlayed = getDateInput();

                // Team 1 Details
                System.out.print("Enter name of Team 1: ");
                String team1Name = scanner.nextLine();
                System.out.print("Enter score for Team 1: ");
                int team1Score = scanner.nextInt(); scanner.nextLine();

                // Team 2 Details
                System.out.print("Enter name of Team 2: ");
                String team2Name = scanner.nextLine();
                System.out.print("Enter score for Team 2: ");
                int team2Score = scanner.nextInt(); scanner.nextLine();

                premiereLeagueManager.addMatchToPremierLeague(datePlayed, team1Name, team1Score, team2Name, team2Score);
                System.out.println("\nMatch added to Premiere League!\n");
                premiereLeagueManager.saveState("data.ser");
                break;

            case 6:
                premiereLeagueManager.restorePreviousState("data.ser");
                System.exit(0);
                break;

            case 7:
                premiereLeagueManager.restorePreviousState("data.ser");
                premiereLeagueManager.saveState("data.ser");
                System.out.println("Exiting Program");
                System.exit(0);
                break;
        }
    }

    public static Date getDateInput() {
        Date date = new Date();
        Scanner scanner;

        // Get year input
        while (true) {
            scanner = new Scanner(System.in);
            try {
                System.out.print("Year: ");
                int year = scanner.nextInt();
                date.setYear(year);
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. Please enter a valid year.");
            }
        }

        // Get month input
        while (true) {
            scanner = new Scanner(System.in);
            try {
                System.out.print("Month: ");
                int month = scanner.nextInt();
                date.setMonth(month);
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. " + e.getMessage());
            }
        }

        // Get day input
        while (true) {
            scanner = new Scanner(System.in);
            try {
                System.out.print("Day: ");
                int day = scanner.nextInt();
                date.setDay(day);
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. " + e.getMessage());
            }
        }

        return date;
    }

    public static boolean clubExists(PremiereLeagueManager premiereLeagueManager, String clubName) {
        for (FootballClub club : premiereLeagueManager.getClubs()) {
            if (club.getClubName().equalsIgnoreCase(clubName)) {
                return true;
            }
        }
        return false;
    }

}
