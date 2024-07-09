package org.example;

import java.util.Scanner;

public class Main {
    public static void bookTicket(Passenger passenger) {
        TicketBooker ticketBooker = new TicketBooker();
        if (TicketBooker.availableWaitingListTickets == 0) {
            System.out.println("Tickets closed");
            return;
        }
        //code for preferred tickets
        if (passenger.berth_preference.equals("L") && TicketBooker.availableLowerBirthTickets > 0 ||
                passenger.berth_preference.equals("U") && TicketBooker.availableUpperBirthTickets > 0 ||
                passenger.berth_preference.equals("M") && TicketBooker.availableMiddleBirthTickets > 0) {
            System.out.println("Preferred birth available");
            if (passenger.berth_preference.equals("L")) {
                System.out.println("Lower birth given");
                // no matter on which position given to passenger
                // thats why .get(0)
                ticketBooker.bookTicket(passenger,( TicketBooker.lowerBirthPositions.get(0)), "L");
                // remove the particular seat from the lowerBirthPositions
                TicketBooker.lowerBirthPositions.remove(0);
                //decrement the count of lowerberth seats available
                TicketBooker.availableLowerBirthTickets--;
            }
            else if (passenger.berth_preference.equals("U")) {
                System.out.println("Upper birth given");
                // no matter on which position given to passenger
                // thats why .get(0)
                ticketBooker.bookTicket(passenger,( TicketBooker.upperBirthPositions.get(0)), "L");
                // remove the particular seat from the lowerBirthPositions
                TicketBooker.upperBirthPositions.remove(0);
                //decrement the count of lowerberth seats available
                TicketBooker.availableUpperBirthTickets--;
            }
            else if (passenger.berth_preference.equals("M")) {
                System.out.println("Middle birth given");
                // no matter on which position given to passenger
                // thats why .get(0)
                ticketBooker.bookTicket(passenger,( TicketBooker.middleBirthPositions.get(0)), "L");
                // remove the particular seat from the lowerBirthPositions
                TicketBooker.middleBirthPositions.remove(0);
                //decrement the count of lowerberth seats available
                TicketBooker.availableMiddleBirthTickets--;
            }
        }
        //code for not preferred tickets
        if(TicketBooker.availableLowerBirthTickets>0){
            System.out.println("Lower birth given");
            ticketBooker.bookTicket(passenger, TicketBooker.lowerBirthPositions.get(0), "L");
            TicketBooker.lowerBirthPositions.remove(0);
            TicketBooker.availableLowerBirthTickets--;
        } else if (TicketBooker.availableMiddleBirthTickets>0) {
            System.out.println("Middle birth given");
            ticketBooker.bookTicket(passenger, TicketBooker.middleBirthPositions.get(0), "M");
            TicketBooker.middleBirthPositions.remove(0);
            TicketBooker.availableMiddleBirthTickets--;
        }
        else if(TicketBooker.availableUpperBirthTickets>0){
            System.out.println("Upper birth given");
            ticketBooker.bookTicket(passenger, TicketBooker.upperBirthPositions.get(0), "U");
            TicketBooker.upperBirthPositions.remove(0);
            TicketBooker.availableUpperBirthTickets--;
        }
        else if(TicketBooker.availableRACTickets>0){
            System.out.println("RAC available");
            ticketBooker.addToRAC(passenger, TicketBooker.RACPositions.get(0), "RAC");
            TicketBooker.RACPositions.remove(0);
            TicketBooker.availableRACTickets--;
        }
        else if(TicketBooker.availableWaitingListTickets>0){
            System.out.println("WaitingList available");
            ticketBooker.addToWL(passenger, TicketBooker.waitingListPositions.get(0), "WL");
            TicketBooker.waitingListPositions.remove(0);
            TicketBooker.availableWaitingListTickets--;
        }
    }
    //cancel ticket
    public static void cancelTicket(int id){
        TicketBooker ticketBooker = new TicketBooker();
        if( ! TicketBooker.passenger_id_map.containsKey(id)){
            System.out.println("Invalid passenger id");
            return;
        }
        else{
            ticketBooker.cancelTicket(id);
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        boolean loop = true;
        while (loop){
            System.out.println("1.Book tickets /n 2.Cancel Tickets /n 3. Available tickets /n 4.Booked Tickets 5.Exit");
            int choice = sc.nextInt();
            switch (choice){
                //book tickets
                case 1:
                {
                    System.out.println("Enter name, age and birth preferences");
                    String name = sc.next();
                    int age = sc.nextInt();
                    String berth_preference = sc.next();
                    Passenger passenger = new Passenger(name, age, berth_preference);
                    bookTicket(passenger);   // to be wittern
                }
                break;
                // cancel tickets
                case 2:
                {
                    //cancellation based on id
                    System.out.println("Enter the passenger_id to cancel");
                    int passenger_id = sc.nextInt();
                    cancelTicket(passenger_id);
                }
                break;
                //print the available tickets
                case 3:
                {
                    TicketBooker booker = new TicketBooker();
                    TicketBooker.printAvailableTickets();
                }
                break;
                //Booked tickets
                case 4:
                {
                    TicketBooker booker = new TicketBooker();
                    TicketBooker.printPassengers();
                }
                break;
                //exit from loop
                case 5:
                {
                    loop = false;
                }
                break;
                default:
                    break;
            }
        }
    }
}