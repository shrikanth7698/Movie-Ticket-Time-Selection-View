package com.shrikanthravi.timeselectionview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.shrikanthravi.timeselectionview.adapters.TimeSelectionAdapter;
import com.shrikanthravi.timeselectionview.data.MovieTime;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class TimeSelectionView extends RecyclerView{
    public TimeSelectionView(Context context) {
        super(context);
    }

    //data
    protected List<MovieTime> movieTimeList;
    protected TimeSelectionAdapter timeSelectionAdapter;

    //Customization Variables
    private int timeTextColor = android.R.color.tab_indicator_text;
    private int seatProgressTintColor = R.color.colorPrimary;

    public TimeSelectionView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.TimeSelectionView,
                0, 0);
        setAttributes(a);
        a.recycle();
    }

    public TimeSelectionView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    protected void init(Context context){
        setHorizontalFadingEdgeEnabled(true);
        setOverScrollMode(OVER_SCROLL_NEVER);
        movieTimeList = new ArrayList<>();
        timeSelectionAdapter = new TimeSelectionAdapter(movieTimeList,context);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
        setLayoutManager(layoutManager);
        setAdapter(timeSelectionAdapter);
        movieTimeList.add(new MovieTime("7:00 am",300,250));
        movieTimeList.add(new MovieTime("11:00 am",300,120));
        movieTimeList.add(new MovieTime("3:00 pm",300,60));
        movieTimeList.add(new MovieTime("6:45 pm",300,50));
        movieTimeList.add(new MovieTime("10:00 pm",300,170));
        movieTimeList.add(new MovieTime("7:00 am",300,250));
        movieTimeList.add(new MovieTime("11:00 am",300,120));
        movieTimeList.add(new MovieTime("3:00 pm",300,60));
        movieTimeList.add(new MovieTime("6:45 pm",300,50));
        movieTimeList.add(new MovieTime("10:00 pm",300,170));
        movieTimeList.add(new MovieTime("7:00 am",300,250));
        movieTimeList.add(new MovieTime("11:00 am",300,120));
        movieTimeList.add(new MovieTime("3:00 pm",300,60));
        movieTimeList.add(new MovieTime("6:45 pm",300,50));
        movieTimeList.add(new MovieTime("10:00 pm",300,170));
        timeSelectionAdapter.notifyDataSetChanged();

    }

    protected void setAttributes(TypedArray attrs){

    }



}
