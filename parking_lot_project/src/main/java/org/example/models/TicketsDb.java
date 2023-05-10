package org.example.models;

import java.util.HashMap;
import java.util.Map;

public class TicketsDb {

    HashMap<String,Ticket> ticketDb = new HashMap<>();

    static  TicketsDb ticketsDb=null;

    private TicketsDb(){

    }

    public static TicketsDb getInstance(){

        if(ticketsDb==null) ticketsDb = new TicketsDb();

        return ticketsDb;
    }

    public void addTicket(Ticket ticket){

        ticketDb.put(ticket.getTicketId(),ticket);

    }

    public void removeTicket(String ticketId){

        ticketDb.remove(ticketId);
    }

    public Ticket getTicket(String ticketId){

        Ticket ticket = null;

        if(ticketDb.containsKey(ticketId))
          ticket = ticketDb.get(ticketId);

        return ticket;
    }

    public  void displayAllTickets(){

        System.out.println(" ***** Booked Tickets ***** ");

        for(Map.Entry<String,Ticket> entry: ticketDb.entrySet()){

            System.out.println("Ticket Id : "+entry.getKey());

        }

    }

}
