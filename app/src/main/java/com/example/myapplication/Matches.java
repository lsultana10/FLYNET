package com.example.myapplication;


//model classes for adding user matches in

public class Matches {

    private String uid;
    private String email;
    private String fullname;
    private String job;
    private String dob;
    private String image;
    private String flightNo;
    private String category;


    public Matches(){}

    public Matches(String uid, String email,String fullname,String job, String dob, String image, String category,String flightNo){

        this.uid = uid;
        this.email = email;
        this.fullname = fullname;
        this.dob = dob;
        this.job = job;
        this.image = image;
        this.category = category;
        this.flightNo = flightNo;

    }

    public void setUid(String iUid){

        uid = iUid;
    }

    public String getUid(){

        return uid;
    }


    public void setEmail(String iEmail){

        email = iEmail;
    }

    public void setFullname(String iFullname){

        fullname = iFullname;
    }

    public void setJob(String iJob){

        job = iJob;
    }


    public void setCategory(String iCategory){

        category = iCategory;
    }

    public String getImage(){

        return image;
    }

    public void setDob(String iDob){

        dob = iDob;
    }

    public String getDob(){

        return dob;
    }


    public void setFlightNo(String iFlightno){

        flightNo = iFlightno;
    }

    public String getFlightNo(){

        return flightNo;
    }

    public void setImage(String iImage){

        image = iImage;
    }

    public String getCategory(){

        return category;
    }

    public String getEmail(){

        return email;
    }

    public String getFullname(){

        return fullname;
    }

    public String getJob(){

        return job;
    }
}
