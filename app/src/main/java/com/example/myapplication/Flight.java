package com.example.myapplication;

import java.io.Serializable;


//Model class for JSON file/API

public class Flight {


    String destination;
    String flightNo;
    String date;
    String departure;



    public Flight(){
    }


    public Flight(String flightNo, String destination, String departure, String date){
        this.date = date;
        this.destination = destination;
        this.flightNo = flightNo;
        this.departure =departure;
    }

    public void setDate(String iDate){

        date = iDate;
    }

    public void setDestination(String iDestination){

        destination = iDestination;
    }

    public void setFlightNo(String iFlightNo){

        flightNo = iFlightNo;
    }

    public void setDeparture(String iDeparture){

        departure = iDeparture;
    }



    public String getDeparture(){

        return departure;
    }


    public String getDate(){

        return date;
    }

    public String getDestination(){

        return destination;
    }


    public String getFlightNo(){

        return flightNo;
    }

}
