package org.example.services;

import org.example.common.ErrorMessage;
import org.example.common.VehicleType;
import org.example.exceptions.SlotNotAvailaibleException;
import org.example.exceptions.TicketExpires;
import org.example.models.Slots;
import org.example.models.Ticket;
import org.example.models.TicketsDb;

import java.time.LocalTime;

public class TicketService {



    ParkingLotService parkingLotService;
    TicketsDb ticketsDb;
    public TicketService(){

        parkingLotService = ParkingLotService.getInstance();
        ticketsDb = TicketsDb.getInstance();
    }

    public void bookTicket(VehicleType type,int number,String color){

        if(VehicleType.BIKE==type){

                bookTicketForBike(number,color);

        }
        else if(VehicleType.CAR==type){

                bookTicketForCar(number,color);

        }
        else if(VehicleType.TRUCK==type){

            bookTicketForTruck(number,color);

        }

    }

    private void bookTicketForTruck(int number,String color){

        Slots availSlot = parkingLotService.bookSlotForTruck(number,color);

        try{

            if(availSlot==null) throw new SlotNotAvailaibleException(ErrorMessage.SLOT_NOT_AVAILABLE);

            Ticket ticket = new Ticket();

            String ticketId = String.valueOf(availSlot.getSlotId())+ String.valueOf(availSlot.getVehicle().getVehicleNumber());

            ticket.setTicketId(ticketId);
            ticket.setEntryTime(LocalTime.now());
            ticket.setSlots(availSlot);

            ticketsDb.addTicket(ticket);

            System.out.println("Slot booked successfully ! ");

        }catch(SlotNotAvailaibleException se){

            System.out.println(se.getMessage());
        }
    }

    private void bookTicketForCar(int number,String color){

        Slots availSlot = parkingLotService.bookSlotForCar(number,color);

        try{

            if(availSlot==null) throw new SlotNotAvailaibleException(ErrorMessage.SLOT_NOT_AVAILABLE);

            Ticket ticket = new Ticket();

            String ticketId = String.valueOf(availSlot.getSlotId())+ String.valueOf(availSlot.getVehicle().getVehicleNumber());

            ticket.setTicketId(ticketId);
            ticket.setEntryTime(LocalTime.now());
            ticket.setSlots(availSlot);

            ticketsDb.addTicket(ticket);

            System.out.println("Slot booked successfully ! ");

        }catch(SlotNotAvailaibleException se){

            System.out.println(se.getMessage());
        }
    }

    private void bookTicketForBike(int number,String color){

            Slots availSlot = parkingLotService.bookSlotForBike(number,color);

            try{

                if(availSlot==null) throw new SlotNotAvailaibleException(ErrorMessage.SLOT_NOT_AVAILABLE);

                Ticket ticket = new Ticket();

                String ticketId = String.valueOf(availSlot.getSlotId())+ String.valueOf(availSlot.getVehicle().getVehicleNumber());

                ticket.setTicketId(ticketId);
                ticket.setEntryTime(LocalTime.now());
                ticket.setSlots(availSlot);

                ticketsDb.addTicket(ticket);

                System.out.println("Slot booked successfully ! ");

            }catch(SlotNotAvailaibleException se){

                System.out.println(se.getMessage());
            }

    }

    public void unBookTicket(String ticketId){

        Ticket ticket = ticketsDb.getTicket(ticketId);
        try {

            if (ticket == null) throw new TicketExpires(ErrorMessage.TICKET_NOT_AVAILABLE);

            boolean status = parkingLotService.unParkVehicle(ticket.getSlots());

            if(status) {

                PaymentService ps = new PaymentService();
                ps.checkOut(ticket);

            }
            else {
               throw new SlotNotAvailaibleException(ErrorMessage.INVALID_SLOT);
            }

        }catch(TicketExpires te){

            System.out.println(te.getMessage());

        }catch(SlotNotAvailaibleException se){

            System.out.println(se.getMessage());

        }
    }


    public void displayBookedTickets(){

        ticketsDb.displayAllTickets();

    }


}
