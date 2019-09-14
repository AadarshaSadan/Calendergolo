package com.aadarsha.myapplication;

import android.view.View;
import android.widget.TextView;

;import com.aadarsha.myapplication.R;

public class MonthViewContainer extends ViewContainer {
    TextView textView;

    public MonthViewContainer(View view) {
        super(view);
        textView = view.findViewById(R.id.calendarMonthText);
    }
}
