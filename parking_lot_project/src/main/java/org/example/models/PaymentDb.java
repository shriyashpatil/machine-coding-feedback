package org.example.models;

import java.util.HashMap;

public class PaymentDb {

    HashMap<String,Payment> payments = new HashMap<>();

    static PaymentDb paymentDb = null;

    private PaymentDb(){}


    public static PaymentDb getInstance(){

        if(paymentDb==null){

            paymentDb = new PaymentDb();

        }

        return paymentDb;

    }

    public void addPayment(Payment payment){

        payments.put(payment.getPaymentId(),payment);

    }

    public Payment getPayment(String paymentId){

        Payment p = payments.get(paymentId);

        return p;
    }

}
