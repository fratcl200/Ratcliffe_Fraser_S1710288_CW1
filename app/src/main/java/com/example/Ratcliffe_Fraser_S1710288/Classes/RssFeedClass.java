package com.example.Ratcliffe_Fraser_S1710288.Classes;

public class RssFeedClass
{
    private String title;
    private String description;
    private Float geoLat;
    private Float geoLong;

    //Class to load important data from RSS feed
    public RssFeedClass()
    {
        title = "";
        description = "";
        geoLat= 0.0f;
        geoLong= 0.0f;
    }

    public RssFeedClass(String atitle, String adescription, Float ageoLat, Float ageoLong)
    {
        title = atitle;
        description = adescription;
        geoLat = ageoLat;
        geoLong = ageoLong;

    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String atitle)
    {
        title = atitle;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String adescription)
    {
        description = adescription;
    }

    public Float getGeoLat()
    {
        return geoLat;
    }

    public void setGeoLat(Float ageoLat)
    {
        geoLat = ageoLat;
    }

    public Float getGeoLong()
    {
        return geoLong;
    }

    public void setGeoLong(Float ageoLong)
    {
        geoLong = ageoLong;
    }

    public String toString()
    {
        String temp;

        temp = title + " " + description + " " + geoLat + "" + geoLong;

        return temp;
    }
}

//Ratcliffe-Cree_Fraser_S1710288
