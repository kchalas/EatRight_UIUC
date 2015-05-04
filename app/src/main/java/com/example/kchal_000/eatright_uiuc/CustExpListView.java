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

    /**
     * Custom view for the adapter at the second level. Without it
     * it would not appear at all. This is a bit of magic with the onMeasure.
     * It's strange that it cannot simply use the regular defaults and caused
     * many debugging issues back in the beginning.
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(2000, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

}
