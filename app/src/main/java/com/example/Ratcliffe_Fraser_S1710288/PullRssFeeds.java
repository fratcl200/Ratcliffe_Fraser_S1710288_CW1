package com.example.Ratcliffe_Fraser_S1710288;

import android.util.Log;

import com.example.Ratcliffe_Fraser_S1710288.Classes.RssFeedClass;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

//Could also use "implements runnable"
public class PullRssFeeds extends Thread {
    String tempTitle;
    String tempDescription;
    String tempGeoLocation;
    Float geoLat;
    Float geoLong;
    String rssURLInputThread;
    ArrayList<RssFeedClass> itemsList = new ArrayList<>();

    //Called to get full list of rss items
    public PullRssFeeds(String inputURL) {
        rssURLInputThread = inputURL;
    }

    //Complete list of rss items used in run() method
    public ArrayList<RssFeedClass> itemArrayList() {return itemsList;}

    //When PullRssFeeds is called, run method executes due to "extends Thread"
    @Override
    public void run() {
        try {
            //Setup connection to RSS feed via HTTPUrlConnection
            URL url = new URL(rssURLInputThread);
            // Adapted from https://developer.android.com/reference/java/net/HttpURLConnection
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoInput(true);
            con.connect();
            InputStream is = con.getInputStream();

            //XMLPullParser Implements httpsURLConnection to parse RSS feed
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(is, null);
            int eventType = xpp.getEventType();
            //Loops through RSS feed until it reaches end of document
            while (eventType != XmlPullParser.END_DOCUMENT) {
                eventType = xpp.next();
                //Looks for item in Rss feed, where all details are stored
                if (eventType == XmlPullParser.START_TAG && xpp.getName().equalsIgnoreCase("item")) {
                    eventType = xpp.next();

                    while (eventType != XmlPullParser.END_TAG && xpp.getName() != "item") {
                        eventType = xpp.next();

                        if (eventType == XmlPullParser.START_TAG) {
                            if (xpp.getName().equalsIgnoreCase("title")) {
                                tempTitle = xpp.nextText().trim();
                            } else if (xpp.getName().equalsIgnoreCase("description")) {
                                tempDescription = xpp.nextText().trim();
                            } else if (xpp.getName().equalsIgnoreCase("georss:point")) {
                                tempGeoLocation = xpp.nextText().trim();
                                String[] separateGeoLocation = tempGeoLocation.split(" ");
                                geoLat = Float.parseFloat(separateGeoLocation[0]);
                                geoLong = Float.parseFloat(separateGeoLocation[1]);
                                //Geo Lat + long could be used in journey planner
                            }
                        }
                    }
                    //Add title, desc, lat, long to Array list for each item
                    itemsList.add(new RssFeedClass(tempTitle, tempDescription, geoLat, geoLong));
                }
            }
            //Catch + log errors
        } catch (XmlPullParserException | IOException e) {
            Log.e("tag", e.toString());
            e.printStackTrace();
        }
    }
}

//Ratcliffe-Cree_Fraser_S1710288