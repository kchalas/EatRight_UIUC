package com.example.kchal_000.eatright_uiuc;

import android.content.Context;
import android.widget.ExpandableListView;

/**
 * Created by kchal_000 on 3/19/2015.
 */
public class CustExpListView extends ExpandableListView {


    public CustExpListView(Context context) {
        super(context);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(2000, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

}
