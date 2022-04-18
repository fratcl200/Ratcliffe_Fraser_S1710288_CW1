package com.example.Ratcliffe_Fraser_S1710288.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import com.example.myapplication.R;
import java.util.HashMap;
import java.util.List;

//Expandable list adapted from - https://www.youtube.com/watch?v=Kla5CQRoh8Y
//Expandable list allows for data to be displayed on click for each item
//Also provides functionality to collapse to previous view state.
public class ExpandableListAdapter extends BaseExpandableListAdapter {

        private Context context;
        private HashMap<String, List<String>> itemDescription;
        private List<String> itemGroups;

        public ExpandableListAdapter(Context context, List<String> itemGroups, HashMap<String, List<String>> itemDescription) {
        this.context = context;
        this.itemDescription = itemDescription;
        this.itemGroups = itemGroups;
        }

    @Override
    public int getGroupCount() {
        return itemDescription.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return itemDescription.get(itemGroups.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return itemGroups.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return itemDescription.get(itemGroups.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        String tempItem = getGroup(i).toString();
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.group_item, null);

        }
        TextView item = view.findViewById(R.id.mobile);
        item.setTypeface(null, Typeface.BOLD);
        item.setText(tempItem);
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        final String tempDescription = (String) getChild(i, i1);
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.child_item, null);
        }
        TextView item = view.findViewById(R.id.model);
        item.setText(tempDescription);
            return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}

//Ratcliffe-Cree_Fraser_S1710288