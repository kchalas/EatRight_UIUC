package com.example.kchal_000.eatright_uiuc;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import information.CalorieCategory;
import information.MenuItem;

/**
 * Created by kchal_000 on 3/18/2015.
 * Adapter for top level of ExpandableListView
 */
public class FirstLevelAdapter extends BaseExpandableListAdapter{
    Context context;
    HashMap<CalorieCategory, HashMap<MenuItem, List<String>>> allData;
    private Object[] listGroup;  //a set of calorie categories

    public FirstLevelAdapter(Context context, HashMap<CalorieCategory, HashMap<MenuItem, List<String>>> data){
        this.context = context;
        this.allData = data;
        listGroup = allData.keySet().toArray();
    }
    @Override
    public int getGroupCount() {
        return listGroup.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return allData.get(listGroup[groupPosition]).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listGroup[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        HashMap<MenuItem, List<String>> chP = allData.get(listGroup[groupPosition]);
        Object[] keys = chP.keySet().toArray(); //a set of MenuItems
        return keys[childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    /**
     * Hardest portion of the checkbox and listview code is the adapters. Took a
     * very long time to understand the details. The getGroupView defines how
     * a checkbox is going to be displayed at the first level, so the CalorieCategories.
     * @param groupPosition
     * @param isExpanded
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        //get group item at this position and find its name
        CalorieCategory categ = (CalorieCategory)getGroup(groupPosition);
        String str = categ.getName();

        if(convertView == null){
            //Inflate layout to include more than just text
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.meals_list,null);
        }
        //fill text box and checkbox correctly, check for changes with listener
        TextView tv = (TextView) convertView.findViewById(R.id.parentname);
        tv.setText(str);
        CheckBox boxP = (CheckBox)convertView.findViewById(R.id.checkbox);
        boxP.setOnCheckedChangeListener((MenuOfRestaurant)this.context);
        boxP.setChecked(categ.isSelected());
        boxP.setTag(categ);
        return convertView;
    }

    /**
     * Each individual child in the list gets its own adapter for the next level. Also, the
     * customized view needs to be used for it to appear correctly.
     * @param groupPosition
     * @param childPosition
     * @param isLastChild
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        //custom expandable list view
        CustExpListView nxtList = new CustExpListView(this.context);
        //need list to be the keySet, and to send along the hash
        HashMap<MenuItem, List<String>> chP = allData.get(listGroup[groupPosition]);
        Object[] keys = chP.keySet().toArray();
        nxtList.setAdapter(new SecondLevelAdapter(this.context,  childPosition, chP));
        convertView = nxtList;
        notifyDataSetChanged();
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
