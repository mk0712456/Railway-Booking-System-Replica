package org.example;

import java.util.*;

public class TicketBooker {
    public static int availableLowerBirthTickets = 1;
    public static int availableMiddleBirthTickets = 1;
    public static int availableUpperBirthTickets = 1 ;
    public static int availableRACTickets = 1;
    public static int availableWaitingListTickets =1;

    // Queues to maintain the order and List to store the bookedtickets
    public static Queue<Integer> waitingList = new LinkedList<>(); //to maintain ids of WL in specific oreder
    public static Queue<Integer> RACList = new LinkedList<>(); // to maintain ids of RAC passengers in specific order
    public static List<Integer> bookedTicketList = new ArrayList<>();// to maintain ids of booked passengers

    //creating actual instances of seats
    public static List<Integer> lowerBirthPositions = new ArrayList<>(Arrays.asList(1));
    public static List<Integer> upperBirthPositions = new ArrayList<>(Arrays.asList(1));
    public static List<Integer> middleBirthPositions = new ArrayList<>(Arrays.asList(1));
    public static List<Integer> RACPositions = new ArrayList<>(Arrays.asList(1));
    public static List<Integer> waitingListPositions = new ArrayList<>(Arrays.asList(1));

    //mapping id with passenger
    public static Map<Integer, Passenger> passenger_id_map = new HashMap<>();

    //function is to set seat_number and berth allocated to passenger
    public void bookTicket(Passenger p, int birth_position, String alloted_birth){
        //assigning seat_number and type off birth
        p.seat_number = birth_position;
        p.berth_alloted = alloted_birth;
        //map passenger id to passenger abject
        passenger_id_map.put(p.passenger_id, p);
        // add id to bookedticketlist
        bookedTicketList.add(p.passenger_id);
        System.out.println("_________________Booked ticket successfully_________________");
    }
    //RAC
    public void addToRAC(Passenger p, int RAC_info, String berth_info){
        p.seat_number = RAC_info;
        p.berth_alloted = berth_info;
        passenger_id_map.put(p.passenger_id, p);
        RACList.add(p.passenger_id);
        bookedTicketList.add(p.passenger_id);
        System.out.println("_______________RAC successfull_______");
    }
    public void addToWL(Passenger p, int WL_info, String berth_info){
        p.seat_number = WL_info;
        p.berth_alloted = berth_info;
        passenger_id_map.put(p.passenger_id, p);
        waitingList.add(p.passenger_id);
        bookedTicketList.add(p.passenger_id);
        System.out.println("_______________RAC successfull_______");
    }
    public void cancelTicket(int id){
        Passenger passenger = passenger_id_map.get(id);
        bookedTicketList.remove(id);
        passenger_id_map.remove(id);
        String birth = passenger.berth_alloted;
        int position = passenger.seat_number;
        if(birth.equals("L")){
            availableLowerBirthTickets--;
            lowerBirthPositions.remove(position);
        }
        else if(birth.equals("M")){
            availableMiddleBirthTickets--;
            middleBirthPositions.remove(position);
        }
        else if(birth.equals("U")){
            availableUpperBirthTickets--;
            upperBirthPositions.remove(position);
        }
        //remove rac passenger from queue and add to relevent birth
        if(RACList.size()>0){
            Passenger passengerfromRAC = passenger_id_map.get(RACList.poll());
            int position_RAC = passengerfromRAC.seat_number;
            RACPositions.add(position_RAC);
            RACList.remove(passengerfromRAC.passenger_id);
            availableRACTickets++;
            //remove a person from WL and add to RAC
            if(waitingList.size()>0){
                Passenger passengerfromWL = passenger_id_map.get(waitingList.poll());
                int position_WL = passengerfromWL.seat_number;
                waitingListPositions.add(position_WL);
                waitingList.remove(passengerfromWL.passenger_id);
                availableWaitingListTickets++;
                //moving removed passenger from WL to RAC

                RACList.add(passengerfromWL.passenger_id);
                passengerfromRAC.seat_number = RACPositions.get(0);
                RACPositions.remove(0);
                passengerfromRAC.berth_alloted = "RAC";
                availableWaitingListTickets++;
                availableWaitingListTickets--;
            }
            //we have an passenger from RAC for whom we can book tickets
            Main.bookTicket(passengerfromRAC);
        }
    }
    //print all available seats
    public static void printAvailableTickets()
    {
        System.out.println("Available Lower Berths "  + availableLowerBirthTickets);
        System.out.println("Available Middle Berths "  + availableMiddleBirthTickets);
        System.out.println("Available Upper Berths "  + availableUpperBirthTickets);
        System.out.println("Availabel RACs " + availableRACTickets);
        System.out.println("Available Waiting List " + availableWaitingListTickets);
        System.out.println("--------------------------");
    }

    //print all occupied passengers from all types including WL
    public static void printPassengers()
    {
        if(passenger_id_map.size() == 0)
        {
            System.out.println("No details of passengers");
            return;
        }
        for(Passenger p : passenger_id_map.values())
        {
            System.out.println("PASSENGER ID " + p.passenger_id );
            System.out.println(" Name " + p.name );
            System.out.println(" Age " + p.age );
            System.out.println(" Status " + p.seat_number + p.berth_alloted);
            System.out.println("--------------------------");
        }
    }
}
