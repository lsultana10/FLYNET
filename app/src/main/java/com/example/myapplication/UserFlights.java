package com.example.myapplication;


//model class for flights added by user

public class UserFlights {


    String userID;
    String destination;
    String departure;
    String flightNo;
    String date;
    String key;


    public UserFlights(){
    }


    public UserFlights(String userID,String flightNo, String destination,String departure, String date, String key){
        this.userID = userID;
        this.date = date;
        this.destination = destination;
        this.flightNo = flightNo;
        this.key = key;
        this.departure = departure;
    }

    public void setUserID(String iUserID){

        userID = iUserID;
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

    public void setKey(String iKey){

        key = iKey;
    }


    public void setDeparture(String iDeparture){

        departure = iDeparture;
    }



    public String getDeparture(){

        return departure;
    }


    public String getKey(){

        return key;
    }


    public String getUserID(){

        return userID;
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
