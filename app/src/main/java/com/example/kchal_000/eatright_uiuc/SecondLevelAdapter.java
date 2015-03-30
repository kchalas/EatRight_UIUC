package com.example.kchal_000.eatright_uiuc;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
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
        GroupViewHolder holder;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.second_level_meals_list,null);
            //holder = new GroupViewHolder();
            //holder.tv = (TextView) convertView.findViewById(R.id.parentname);
            //holder.check = (CheckBox) convertView.findViewById(R.id.checkbox);
            //convertView.setTag(holder);
        }//else{holder = (GroupViewHolder) convertView.getTag();}


        String str = this.meal;
        //holder.tv.setText(str);
        TextView tvTitle = (TextView)convertView.findViewById(R.id.mealname);
        //TextView tv = new TextView(this.context);
        //tv.setPadding(70, 0, 0, 0);
        //Log.i("setting txt", str);
        tvTitle.setText(str);
       // tv.setText(str);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String str = (String)getChild(groupPosition, childPosition);
        TextView tv = new TextView(this.context);
        tv.setText(str);
        tv.setPadding(130, 0, 0, 0);
        return tv;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    class GroupViewHolder{
        TextView tv;
        CheckBox check;
    }
}
