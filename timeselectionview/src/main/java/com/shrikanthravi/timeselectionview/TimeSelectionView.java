package com.shrikanthravi.timeselectionview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.shrikanthravi.timeselectionview.adapters.TimeSelectionAdapter;
import com.shrikanthravi.timeselectionview.data.MovieTime;
import com.shrikanthravi.timeselectionview.listeners.MyRecyclerItemClickListener;

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
    //private int timeTextColor = android.R.color.tab_indicator_text;
    //private int seatProgressTintColor = R.color.colorPrimary;

    //Listeners
    private OnMovieTimeClickListener onMovieTimeClickListener;

    public TimeSelectionView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
        /*TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.TimeSelectionView,
                0, 0);
        setAttributes(a);
        a.recycle();*/
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
        addOnItemTouchListener(
                new MyRecyclerItemClickListener(context, new MyRecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        if(movieTimeList.get(position).getAvailableSeats()!=0) {
                            MovieTimeClicked(position);
                        }
                    }
                })
        );
    }

    protected void setAttributes(TypedArray attrs){

    }

    public List<MovieTime> getMovieTimeList() {
        return movieTimeList;
    }

    public void setMovieTimeList(List<MovieTime> movieTimeList1) {
        movieTimeList.clear();
        movieTimeList.addAll(movieTimeList1);
        timeSelectionAdapter.notifyDataSetChanged();
    }

    public interface OnMovieTimeClickListener{
        public void onMovieTimeClicked(int position);
    }


    public OnMovieTimeClickListener getOnMovieTimeClickListener() {
        return onMovieTimeClickListener;
    }

    public void setOnMovieTimeClickListener(OnMovieTimeClickListener onMovieTimeClickListener) {
        this.onMovieTimeClickListener = onMovieTimeClickListener;
    }

    protected void MovieTimeClicked(int position){
        if(onMovieTimeClickListener!=null){
            onMovieTimeClickListener.onMovieTimeClicked(position);
        }
    }
}
