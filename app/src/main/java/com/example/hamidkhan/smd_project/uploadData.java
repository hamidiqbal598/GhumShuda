package com.example.hamidkhan.smd_project;

public class uploadData
{
    private String name;
    private String cnic;
    private String fathername;
    private String phoneno;
    private String lostlocation;
    private String lostdata;
    private String age;
    private String height;
    private String color;
    private String imageurl;

    public uploadData()
    {

    }
    public uploadData(String _name,String _Url)
    {
        name=_name;
        imageurl=_Url;
        cnic=fathername=phoneno=lostlocation=lostdata=age=height=color="Dummy";
    }

    public uploadData(String name, String cnic, String fathername, String phoneno, String lostlocation, String lostdata, String age, String height, String color, String imageurl) {
        if(name.trim().equals(""))
        {
            name="No name";
        }
        this.name = name;
        this.cnic = cnic;
        this.fathername = fathername;
        this.phoneno = phoneno;
        this.lostlocation = lostlocation;
        this.lostdata = lostdata;
        this.age = age;
        this.height = height;
        this.color = color;
        this.imageurl = imageurl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getFathername() {
        return fathername;
    }

    public void setFathername(String fathername) {
        this.fathername = fathername;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getLostlocation() {
        return lostlocation;
    }

    public void setLostlocation(String lostlocation) {
        this.lostlocation = lostlocation;
    }

    public String getLostdata() {
        return lostdata;
    }

    public void setLostdata(String lostdata) {
        this.lostdata = lostdata;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
