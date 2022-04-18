package com.example.Ratcliffe_Fraser_S1710288.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.Ratcliffe_Fraser_S1710288.PullRssFeeds;
import com.example.Ratcliffe_Fraser_S1710288.Classes.RssFeedClass;
import com.example.myapplication.R;

public class CurrentRoadworks extends Fragment {

    ExpandableListAdapter listAdapter;
    ExpandableListView expandableListView;
    List<String> itemTitles;
    HashMap<String, List<String>> itemDetails;
    ArrayList<String> titlesForList = new ArrayList<String>();
    private ArrayList<RssFeedClass> roadworksList = new ArrayList<>();
    String currentURL = "https://trafficscotland.org/rss/feeds/roadworks.aspx";

    public CurrentRoadworks(){
        // require a empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onViewCreated(view, savedInstanceState);

        // Thread adapted from https://jenkov.com/tutorials/java-concurrency/creating-and-starting-threads.html
        PullRssFeeds pullRssFeeds = new PullRssFeeds(currentURL);
        Thread th = new Thread(pullRssFeeds);
        th.start();

        try {
            th.join(0);
            roadworksList = pullRssFeeds.itemArrayList();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        itemTitles = new ArrayList<String>();
        itemDetails = new HashMap<String, List<String>>();
        expandableListView = (ExpandableListView) view.findViewById(R.id.ExpandableList);
        itemTitles.clear();
        itemDetails.clear();

        for (int i = 0; i < roadworksList.size(); i++) {
            List<String> listDetailsChild = new ArrayList<String>();
            itemTitles.add(roadworksList.get(i).getTitle());
            String tempDesc = roadworksList.get(i).getDescription();
            String descFormatted = tempDesc.replace("<br />", "\n");
            listDetailsChild.add(descFormatted);
            itemDetails.put(itemTitles.get(i), listDetailsChild);
        }
        listAdapter = new com.example.Ratcliffe_Fraser_S1710288.Adapters.ExpandableListAdapter(getContext(), itemTitles, itemDetails);
        expandableListView.setAdapter(listAdapter);
    }
}

//Ratcliffe-Cree_Fraser_S1710288
