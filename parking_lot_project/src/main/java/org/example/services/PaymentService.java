package org.example.services;

import org.example.common.SuccessMessage;
import org.example.models.Payment;
import org.example.models.PaymentDb;
import org.example.models.Slots;
import org.example.models.Ticket;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class PaymentService {

    PaymentDb paymentDb;

    int pricePerHour = 10;

    public PaymentService(){

        paymentDb = PaymentDb.getInstance();

    }

    public void checkOut(Ticket ticket){

        LocalTime currentTime = LocalTime.now();

        LocalTime entryTime = ticket.getEntryTime();

        long hours = Duration.between(currentTime,entryTime).toHours();

        long price = hours*this.pricePerHour;

        Payment payment = new Payment();
        String paymentId = ticket.getTicketId()+String.valueOf(price);
        payment.setPaymentId(paymentId);
        payment.setCredit(price);
        payment.setTimestamp(LocalDateTime.now());

        paymentDb.addPayment(payment);

        System.out.println(SuccessMessage.PAYMENT_SUCCESSFULL);

    }


}
