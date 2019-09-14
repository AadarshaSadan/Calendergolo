package com.aadarsha.myapplication;

import android.view.View;
import android.widget.TextView;

import com.aadarsha.myapplication.R;


public class DayViewContainer extends ViewContainer {
     TextView textView;
    public DayViewContainer(View view) {
        super(view);
        textView = view.findViewById(R.id.calendarDayText);
    }


}
