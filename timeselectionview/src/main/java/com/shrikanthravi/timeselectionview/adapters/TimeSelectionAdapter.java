package com.shrikanthravi.timeselectionview.adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.shrikanthravi.timeselectionview.R;
import com.shrikanthravi.timeselectionview.data.MovieTime;
import com.shrikanthravi.timeselectionview.view.VerticalProgressbar;

import java.util.List;
import java.util.logging.Handler;

/**
 * Created by shrikanthravi on 01/03/18.
 */


public class TimeSelectionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static List<MovieTime> movieTimeList;
    Context context;
    public static int selectedpos=-1;
    public static ProgressBar selectedprogressBar = null;
    public static float normalSize = 15;
    public static float bigSize = 18;

    public  class MovieTimeHolder extends RecyclerView.ViewHolder {

        public TextView movieTimeTV;
        public ProgressBar progressBar;
        public MovieTimeHolder(View view) {
            super(view);

            movieTimeTV = view.findViewById(R.id.movieTimeTV);
            progressBar = view.findViewById(R.id.seatsProgress);

        }

    }

    public TimeSelectionAdapter(List<MovieTime> verticalList, Context context) {
        this.movieTimeList = verticalList;
        this.context = context;
    }




    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.time_column_item, parent, false);
        return new MovieTimeHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {


        final MovieTime movieTime = movieTimeList.get(position);
        final MovieTimeHolder movieTimeHolder = ((MovieTimeHolder) holder);
        movieTimeHolder.movieTimeTV.setText(movieTime.getTime());
        movieTimeHolder.progressBar.setMax(movieTime.getTotalSeats());
        float percent = ((float)(movieTime.getAvailableSeats()))/(float) movieTime.getTotalSeats();
        System.out.println("color Testing "+movieTimeHolder.movieTimeTV.getTextSize());


        movieTimeHolder.progressBar.setProgress(movieTime.getTotalSeats()-movieTime.getAvailableSeats());
        if(movieTime.isSelected()){
            movieTimeHolder.progressBar.setProgressTintList(ColorStateList.valueOf(interpolateColor(context.getResources().getColor(R.color.red),context.getResources().getColor(R.color.green),percent)));
            movieTimeHolder.movieTimeTV.setTextColor(context.getResources().getColor(android.R.color.black));
            movieTimeHolder.progressBar.setBackground(context.getDrawable(R.drawable.progress_bg_selected));
            //movieTimeHolder.movieTimeTV.setTextSize(TypedValue.COMPLEX_UNIT_SP,bigSize);
        }
        else{
            movieTimeHolder.progressBar.setProgressTintList(ColorStateList.valueOf(interpolateColor(context.getResources().getColor(R.color.red),context.getResources().getColor(R.color.green),percent)));
            movieTimeHolder.movieTimeTV.setTextColor(context.getResources().getColor(android.R.color.tab_indicator_text));
            movieTimeHolder.progressBar.setBackground(context.getDrawable(R.drawable.progress_bg));
            //movieTimeHolder.movieTimeTV.setTextSize(TypedValue.COMPLEX_UNIT_SP,normalSize);
        }
        movieTimeHolder.progressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedpos == position){
                    movieTimeList.get(position).setSelected(false);
                    notifyItemChanged(position);
                    selectedpos = -1;
                }
                else{
                    if(selectedpos>=0) {
                        if(movieTime.getAvailableSeats()!=0){
                            movieTimeList.get(position).setSelected(true);
                            movieTimeList.get(selectedpos).setSelected(false);
                            notifyItemChanged(position);
                            notifyItemChanged(selectedpos);
                            selectedpos = position;
                        }
                        else{
                            final PopupWindow mPopupwindow;
                            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
                            View mView = mInflater.inflate(R.layout.full_layout,null,false);
                            mPopupwindow = new PopupWindow(mView, 300, 300, true);
                            mPopupwindow.showAsDropDown(movieTimeHolder.movieTimeTV,movieTimeHolder.progressBar.getWidth()/2,movieTimeHolder.progressBar.getHeight()+10);
                            android.os.Handler handler = new android.os.Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mPopupwindow.dismiss();
                                }
                            },3000);
                        }
                    }
                    else{
                        if(movieTime.getAvailableSeats()!=0) {
                            movieTimeList.get(position).setSelected(true);
                            notifyItemChanged(position);
                            selectedpos = position;
                        }
                        else{
                            final PopupWindow mPopupwindow;
                            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
                            View mView = mInflater.inflate(R.layout.full_layout,null,false);
                            mPopupwindow = new PopupWindow(mView, mView.getWidth(), mView.getHeight(), true);
                            mPopupwindow.showAsDropDown(movieTimeHolder.movieTimeTV,movieTimeHolder.progressBar.getWidth()/2,movieTimeHolder.progressBar.getHeight()+10);
                            android.os.Handler handler = new android.os.Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mPopupwindow.dismiss();
                                }
                            },3000);
                        }
                    }
                }

            }
        });



    }

    @Override
    public int getItemCount() {
        return movieTimeList.size();
    }

    private float interpolate(final float a, final float b,
                              final float proportion) {
        return (a + ((b - a) * proportion));
    }

    /**
     * Returns an interpolated color, between <code>a</code> and <code>b</code>
     * proportion = 0, results in color a
     * proportion = 1, results in color b
     */
    private int interpolateColor(int a, int b, float proportion) {

        if (proportion > 1 || proportion < 0) {
            throw new IllegalArgumentException("proportion must be [0 - 1]");
        }
        float[] hsva = new float[3];
        float[] hsvb = new float[3];
        float[] hsv_output = new float[3];

        Color.colorToHSV(a, hsva);
        Color.colorToHSV(b, hsvb);
        for (int i = 0; i < 3; i++) {
            hsv_output[i] = interpolate(hsva[i], hsvb[i], proportion);
        }

        int alpha_a = Color.alpha(a);
        int alpha_b = Color.alpha(b);
        float alpha_output = interpolate(alpha_a, alpha_b, proportion);

        return Color.HSVToColor((int) alpha_output, hsv_output);
    }
}

