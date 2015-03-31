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

import information.MenuItem;

/**
 * Created by kchal_000 on 3/18/2015.
 */
public class FirstLevelAdapter extends BaseExpandableListAdapter{
    Context context;

    HashMap<String, HashMap<MenuItem, List<String>>> allData = DataProvider.getData();
    private Object[] listGroup = allData.keySet().toArray(); //a set of calorie categories
    public FirstLevelAdapter(Context context){
        this.context = context;

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

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String str = (String)getGroup(groupPosition);
        //GroupViewHolder holder;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.meals_list,null); //resource, viewgroup
            //holder = new GroupViewHolder();
            //holder.tv = (TextView) convertView.findViewById(R.id.mealname);
            //holder.check = (CheckBox) convertView.findViewById(R.id.checkboxchild);
            //convertView.setTag(holder);
        } //else{ holder = (GroupViewHolder) convertView.getTag();}
        //holder.tv.setText(str);
        TextView tv = (TextView) convertView.findViewById(R.id.parentname);
        tv.setText(str);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        /**
        String str = (String)getChild(groupPosition, childPosition);
        TextView tv = new TextView(context);
        tv.setText(str);
        tv.setPadding(50, 0, 0, 0);

        **/
        CustExpListView nxtList = new CustExpListView(this.context);
        //need list to be the keySet, and to send along the hash
        HashMap<MenuItem, List<String>> chP = allData.get(listGroup[groupPosition]);
        Object[] keys = chP.keySet().toArray();
        //String keyStr= keys[childPosition].toString();
        //Log.i("keystr", keyStr);
        //Log.i("hash size", chP.size()+"");
        nxtList.setAdapter(new SecondLevelAdapter(this.context,  childPosition, chP));
        convertView = nxtList;
        //convertView.setPadding(20, 0, 0, 0);
        return convertView;
        //return tv;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
