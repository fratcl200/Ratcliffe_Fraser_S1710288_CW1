package com.example.Ratcliffe_Fraser_S1710288.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.Ratcliffe_Fraser_S1710288.PullRssFeeds;
import com.example.Ratcliffe_Fraser_S1710288.Classes.RssFeedClass;
import com.example.myapplication.R;

//Each fragment corresponds to each RSS feed.
//Fragment approach has been chosen as it works well with the bottom navigation bar
public class CurrentIncidents extends Fragment {

    ExpandableListAdapter listAdapter;
    ExpandableListView expandableListView;
    List<String> itemTitles;
    HashMap<String, List<String>> itemDetails;
    private ArrayList<RssFeedClass> currentIncidentList = new ArrayList<>();
    String currentURL = "https://trafficscotland.org/rss/feeds/currentincidents.aspx";
    SearchView searchView;

    public CurrentIncidents(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
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
            currentIncidentList = pullRssFeeds.itemArrayList();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        itemTitles = new ArrayList<String>();
        itemDetails = new HashMap<String, List<String>>();
        expandableListView = (ExpandableListView) view.findViewById(R.id.ExpandableList);
        itemTitles.clear();
        itemDetails.clear();

        for (int i = 0; i < currentIncidentList.size(); i++) {
            List<String> listDetailsChild = new ArrayList<String>();
            itemTitles.add(currentIncidentList.get(i).getTitle());
            listDetailsChild.add(currentIncidentList.get(i).getDescription());
            itemDetails.put(itemTitles.get(i), listDetailsChild);
        }
        listAdapter = new com.example.Ratcliffe_Fraser_S1710288.Adapters.ExpandableListAdapter(getContext(), itemTitles, itemDetails);
        expandableListView.setAdapter(listAdapter);
    }
}

//Ratcliffe-Cree_Fraser_S1710288