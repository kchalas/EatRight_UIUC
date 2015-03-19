package com.example.kchal_000.eatright_uiuc;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by kchal_000 on 3/18/2015.
 */
public class SecondLevelAdapter extends BaseExpandableListAdapter{
    Context context;
    int chosen;
    String meal;
    List<String> details;


    public SecondLevelAdapter(Context context, int chosen, HashMap<String, List<String>> matchMealToDets){
        this.context = context;
        this.chosen = chosen;
        Object[] meals =  matchMealToDets.keySet().toArray();
        this.meal = meals[chosen].toString();
        this.details = matchMealToDets.get(meal);

    }
    @Override
    public int getGroupCount() {
        return 1;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.details.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        //Log.i("meals - getGroup", meals[groupPosition].toString());
        return this.meal;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        Log.i("chosen", chosen+" "+ getGroup(chosen));
        //Log.i("list size", ""+list.size());
        return this.details.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return chosen;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String str = this.meal;
        TextView tv = new TextView(this.context);
        tv.setPadding(50, 0, 0, 0);
        //Log.i("setting txt", str);

        tv.setText(str);
        return tv;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String str = (String)getChild(groupPosition, childPosition);
        TextView tv = new TextView(this.context);
        tv.setText(str);
        tv.setPadding(100, 0, 0, 0);
        return tv;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
