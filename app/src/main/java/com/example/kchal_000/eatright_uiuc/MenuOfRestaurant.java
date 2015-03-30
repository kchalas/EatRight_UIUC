package com.example.kchal_000.eatright_uiuc;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import api.apiInterface;
import information.RestaurantInfo;

/*
   Helped along with details from :
   http://androidcodesnips.blogspot.com/2011/09/three-level-expandable-list.html
 */

public class MenuOfRestaurant extends ActionBarActivity {
    ExpandableListView expt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent restIntent = getIntent();
        RestaurantInfo rInfo = (RestaurantInfo)restIntent.getSerializableExtra("rest");
        //need to use RestaurantInfo to create a data struct to give to contentView
        Log.i("json to parse ", apiInterface.getMenu(rInfo));
        //set up content view
        setContentView(R.layout.activity_menu_of_restaurant);
        expt = (ExpandableListView) findViewById(R.id.expandableListView);
        expt.setAdapter(new FirstLevelAdapter(this));
        //expt.setGroupIndicator(getResources().getDrawable(R.drawable.arrow_right_icon));
        //expt.setIndicatorBounds(5,5);

        Button back = (Button) findViewById(R.id.buttonBack);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), RestaurantChoices.class);
                startActivityForResult(myIntent, 0);
            }

        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu_of_restaurant, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
/**
    public class FirstLevelAdapter extends BaseExpandableListAdapter {
        private Context context;
        HashMap<String, HashMap<String, List<String>>> allData = DataProvider.getData();
        private Object[] listGroup = allData.keySet().toArray();
        private HashMap<String, List<String>> listChild;

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
            HashMap<String, List<String>> chP = allData.get(listGroup[groupPosition]);
            Object[] keys = chP.keySet().toArray();
            return chP.get(keys[childPosition]);
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
            //where the details of top level choices go...

            //LayoutInflater inflat = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //convertView = inflat.inflate(R.layout.meals_list, null);

            TextView txtView = (TextView)findViewById(R.id.parentname);
            String txt = listGroup[groupPosition].toString();
            txtView.setText(txt);


            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            TextView txtView = (TextView)findViewById(R.id.childname);
            txtView.setText("TROUBLE");
            //CustomExpandableListView nxtList = new CustomExpandableListView(MenuOfRestaurant.this);
            //nxtList.setAdapter(new SecondLevelAdapter(groupPosition));
            //nxtList.setGroupIndicator(null);
            return txtView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }

    public class CustomExpandableListView extends ExpandableListView {
        int groupPosition;
        int childPosition;
        int groupID;

        public CustomExpandableListView(Context context){
            super(context);
        }

        protected void onMeasure(int wMSpec, int hMSpec){
            wMSpec = MeasureSpec.makeMeasureSpec(960, MeasureSpec.AT_MOST);
            hMSpec = MeasureSpec.makeMeasureSpec(600, MeasureSpec.AT_MOST);
            super.onMeasure(wMSpec, hMSpec);
        }
    }

    public class SecondLevelAdapter extends BaseExpandableListAdapter {
        private Context context;
        private int group;
        HashMap<String, HashMap<String, List<String>>> allData = DataProvider.getData();
        private Object[] listGroup = allData.keySet().toArray();
        private HashMap<String, List<String>> list = allData.get(listGroup[this.group]) ;
        private Object[] keysOfMap = list.keySet().toArray();

        public SecondLevelAdapter(int group){
            this.group = group;
        }

        @Override
        public int getGroupCount() {
            return keysOfMap.length;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return list.get(keysOfMap[groupPosition]).size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return keysOfMap[groupPosition];
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            List<String> child = list.get(keysOfMap[groupPosition]);
            return child.get(childPosition);
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
            return null;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            return null;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return false;
        }
    }

*/







}
