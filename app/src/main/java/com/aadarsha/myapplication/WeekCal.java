package com.aadarsha.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.aadarsha.myapplication.R;




import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.YearMonth;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Week CalendarView library added
 * https://github.com/kizitonwose/CalendarView
 */

public class WeekCal extends AppCompatActivity {
    private CalendarView calendarView;
    ////private List<Integer> l = new ArrayList<>();
    private List<String> markList = new ArrayList<>();
    private TextView monthtext;

    private  int getvalue=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_calander);
        calendarView = findViewById(R.id.calendarView);
        monthtext=findViewById(R.id.month);




        calendarView.setDayBinder(new DayBinder<DayViewContainer>() {

            @Override
            public DayViewContainer create(View view) {
                return new DayViewContainer(view);
            }

            @Override
            public void bind(DayViewContainer viewContainer, final CalendarDay calendarDay) {
                viewContainer.textView.setText(calendarDay.getDate().getDayOfMonth() + "");
                if (calendarDay.getDate().getDayOfWeek() == DayOfWeek.FRIDAY || calendarDay.getDate().getDayOfWeek() == DayOfWeek.SUNDAY)//every sunday and saturday
                  {
              //  if(calendarDay.getDate(Calendar.DATEß
                    viewContainer.textView.setTextColor(Color.RED);
                     viewContainer.textView.setBackgroundResource(0);
                }

                for (int i = 0; i < markList.size(); i++) {
                    //.d("MAR",markList.get(i));

                    //Log.d("SALAY",""+calendarDay.getDate().toString());
                    if (calendarDay.getDate().toString().equalsIgnoreCase(markList.get(i))) {
                        Log.d("date>>", "" + calendarDay.getDate());
                        viewContainer.textView.setBackground(getResources().getDrawable(R.drawable.circular_tv));
                    }
                }

                viewContainer.textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        doGolo(calendarDay.getDate().getDayOfWeek().getValue()-2);

                        Log.d("Demo",String.valueOf(calendarDay.getDate().getDayOfWeek().getValue() - 1));
                    }
                });
            }
        });

        calendarView.setMonthHeaderBinder(new MonthHeaderFooterBinder<MonthViewContainer>() {
            @Override
            public MonthViewContainer create(View view) {
                return new MonthViewContainer(view);
            }

            @Override
            public void bind(MonthViewContainer viewContainer, CalendarMonth calendarDay) {
                viewContainer.textView.setText("Month - " + Getintmonth(calendarDay.getMonth()));
                getvalue=calendarDay.getMonth();
                Log.d("TAG",String.valueOf(calendarDay.getMonth()));
                //monthtext.setText(Getintmonth(calendarDay.getMonth()));
             }
        });

        monthtext.setText(Getintmonth(getvalue));

        calendarView.setInDateStyle(InDateStyle.ALL_MONTHS);
        calendarView.setOutDateStyle(OutDateStyle.END_OF_ROW);
        calendarView.setScrollMode(ScrollMode.PAGED);
        calendarView.setOrientation(RecyclerView.HORIZONTAL);

        YearMonth ym = YearMonth.now();
        calendarView.setMaxRowCount(6);
        calendarView.setHasBoundaries(true);
        calendarView.setup(ym.minusMonths(10), ym.plusMonths(10), WeekFields.of(Locale.getDefault()).getFirstDayOfWeek());
        calendarView.scrollToMonth(ym);
        calendarView.notifyCalendarChanged();



    }

    //lagyo 4 ota golo 9-13-2019
    public void doGolo(int golovalue) {
        markList = getIntervalDates(new Date(),2);
        calendarView.notifyCalendarChanged();

    }

    private  String  Getintmonth(int month_)
    {
        switch(month_)
        {
            case 1:
                monthtext.setText("January");
          return  "January";
            case 2:
                return "February";

            case 3:
                return  "March";

            case 4:
                return "April";

            case 5:
                return  "May";

            case 6:
                return "June";

            case 7:
                return  "July";

            case 8:
                return "Augest";

            case 9:
                return  "September";

            case 10:
                monthtext.setText("October");
                return "October";

            case  11:
                return  "November";

            case  12:
                return  "Decemeber";

            default:
                throw new IllegalStateException("Unexpected value: " + month_);
        }


    }

    //today 9-13-2019 interval 10ya
    public ArrayList<String> getIntervalDates(Date refdate, int interval)
    {

        Calendar cal = Calendar.getInstance();
        cal.setTime(refdate);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        ArrayList<String> intervals = new ArrayList<>();
        int MARK_INTERVAL = 4 ;
        int LOOPS = 5;
        for(int l=0;l<LOOPS;l++) {
            for (int i = 0; i < MARK_INTERVAL; i++) {
                if(i!=0)
                    cal.add(Calendar.DAY_OF_MONTH, 1);
                intervals.add(format.format(cal.getTime()));
            }
            cal.add(Calendar.DAY_OF_MONTH,interval);
        }
        return  intervals;
    }
}