package org.example.services;

import org.example.common.ErrorMessage;
import org.example.common.VehicleType;
import org.example.exceptions.SlotAlreadyEmpty;
import org.example.models.Slots;
import org.example.models.Vehicle;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class ParkingLotService {

    TreeMap<Integer, ArrayList<Slots>> parkingLot = new TreeMap<>();

    static ParkingLotService ps = null;

    private ParkingLotService(){


    }

    public static ParkingLotService  getInstance(){

        if(ps==null){

            ps = new ParkingLotService();

        }

        return ps;

    }

    /// create Lot

    public void createParkingLot(int numberOfFloors , int numberOfSlots){

        for(int itr=1; itr<=numberOfFloors; itr++){

            ArrayList<Slots> slots = new ArrayList<>();
            for(int jtr=1; jtr<=numberOfSlots; jtr++){

                Slots s = new Slots();

                s.setSlotId(jtr);
                s.setVehicle(null);
                s.setEmpty(true);
                if(jtr==1)
                    s.setSlotType(VehicleType.TRUCK);
                if(jtr==2||jtr==3)
                    s.setSlotType(VehicleType.BIKE);
                else
                    s.setSlotType(VehicleType.CAR);

                slots.add(s);
            }

            parkingLot.put(itr,slots);

        }

    }


   public  void displayFreeSpots(VehicleType type){

        if(type==VehicleType.BIKE){
            displayFreeSpotForBike();
        }

        else if (type==VehicleType.CAR){
            displayFreeSpotForCar();
        }

        else if(type==VehicleType.TRUCK){

            displayFreeSpotForTruck();
        }

    }

    public void displayFreeSpot(){

        displayFreeSpotForBike();
        displayFreeSpotForCar();
        displayFreeSpotForTruck();

    }

    void displayFreeSpotForBike(){

        System.out.println("Free spot for Bikes");

        for(Map.Entry<Integer,ArrayList<Slots>> entry : parkingLot.entrySet()){

            System.out.println("Floor : "+entry.getKey());

            ArrayList<Slots> bikeSlots = entry.getValue();

            for(int itr=1; itr<=2; itr++){

                Slots s = bikeSlots.get(itr);

                if(s.isEmpty() && s.getSlotType()==VehicleType.BIKE){

                    System.out.println(s.getSlotId());
                }
            }

        }

    }

    void displayFreeSpotForTruck(){
        System.out.println("Free spot for Tucks");

        for(Map.Entry<Integer,ArrayList<Slots>> entry : parkingLot.entrySet()){

            System.out.println("Floor : "+entry.getKey());


                Slots s = entry.getValue().get(0);

                if(s.isEmpty() && s.getSlotType()==VehicleType.TRUCK) {

                    System.out.println(s.getSlotId());
                }

        }
    }

    void displayFreeSpotForCar(){

        System.out.println("Free spot for Cars");

        for(Map.Entry<Integer,ArrayList<Slots>> entry : parkingLot.entrySet()){

            System.out.println("Floor : "+entry.getKey());

            ArrayList<Slots> carsSlots = entry.getValue();

            for(int itr=3; itr<carsSlots.size(); itr++){

                Slots s = carsSlots.get(itr);

                if(s.isEmpty() && s.getSlotType()==VehicleType.CAR){

                    System.out.println(s.getSlotId());
                }
            }

        }

    }

    public Slots bookSlotForCar(int number,String color){

        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleType(VehicleType.CAR);
        vehicle.setVehicleNumber(number);
        vehicle.setColor(color);

        for(Map.Entry<Integer,ArrayList<Slots>> entry : parkingLot.entrySet()){

            System.out.println("Floor : "+entry.getKey());

            ArrayList<Slots> carsSlots = entry.getValue();

            for(int itr=3; itr<carsSlots.size(); itr++){

                Slots s = carsSlots.get(itr);

                if(s.isEmpty() && s.getSlotType()==VehicleType.CAR){

                   s.setVehicle(vehicle);
                   s.setEmpty(false);

                   return s;

                }
            }

        }

        return null;
    }

    public Slots bookSlotForBike(int number,String color){

        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleType(VehicleType.BIKE);
        vehicle.setVehicleNumber(number);
        vehicle.setColor(color);

        for(Map.Entry<Integer,ArrayList<Slots>> entry : parkingLot.entrySet()){

            System.out.println("Floor : "+entry.getKey());

            ArrayList<Slots> bikeSlots = entry.getValue();

            for(int itr=1; itr<=2; itr++){

                Slots s = bikeSlots.get(itr);

                if(s.isEmpty()){

                    System.out.println("Slot : "+s.getSlotId());
                    s.setEmpty(false);
                    s.setSlotType(VehicleType.BIKE);
                    s.setVehicle(vehicle);

                    return s;

                }
            }

        }

        return null;
    }

    public Slots bookSlotForTruck(int number,String color){

        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleType(VehicleType.TRUCK);
        vehicle.setVehicleNumber(number);
        vehicle.setColor(color);

        for(Map.Entry<Integer,ArrayList<Slots>> entry : parkingLot.entrySet()){

            System.out.println("Floor : "+entry.getKey());

            ArrayList<Slots> carsSlots = entry.getValue();



                Slots s = carsSlots.get(0);

                if(s.isEmpty() && s.getSlotType()==VehicleType.TRUCK){

                    s.setVehicle(vehicle);
                    s.setEmpty(false);

                    return s;

                }


        }

        return null;

    }

    public boolean unParkVehicle(Slots slot){

        for(Map.Entry<Integer,ArrayList<Slots>> entry : parkingLot.entrySet()){

            System.out.println("Floor : "+entry.getKey());

            ArrayList<Slots> slots = entry.getValue();

            for(int itr=0; itr<=slots.size(); itr++){

                Slots s = slots.get(itr);

                if(s.getSlotId()==slot.getSlotId()){

                    try {

                        if (s.isEmpty()) throw new SlotAlreadyEmpty(ErrorMessage.SLOT_ALREADY_EMPTY);

                        s.setVehicle(null);
                        s.setEmpty(true);

                        return true;

                    }catch(SlotAlreadyEmpty se){

                        System.out.println(se.getMessage());
                        return false;
                    }

                }

            }

        }
        return false;
    }



}
