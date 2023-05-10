package org.example.models;

import org.example.common.VehicleType;

import java.time.LocalTime;

public class Ticket {

    String ticketId;

    Slots slots;

    LocalTime entryTime;

    public Slots getSlots() {
        return slots;
    }

    public void setSlots(Slots slots) {
        this.slots = slots;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }



    public LocalTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalTime entryTime) {
        this.entryTime = entryTime;
    }
}
