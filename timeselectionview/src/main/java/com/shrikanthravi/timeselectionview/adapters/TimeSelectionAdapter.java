package com.shrikanthravi.timeselectionview.adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.shrikanthravi.timeselectionview.R;
import com.shrikanthravi.timeselectionview.data.MovieTime;

import java.util.List;

/**
 * Created by shrikanthravi on 01/03/18.
 */


public class TimeSelectionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<MovieTime> movieTimeList;
    Context context;
    public static int selectedpos=-1;
    public static ProgressBar selectedprogressBar = null;


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
        System.out.println("color Testing "+((float) percent*100));

        movieTimeHolder.progressBar.setProgress(movieTime.getTotalSeats()-movieTime.getAvailableSeats());
        if(movieTime.isSelected()){
            movieTimeHolder.progressBar.setProgressTintList(ColorStateList.valueOf(interpolateColor(context.getResources().getColor(R.color.red),context.getResources().getColor(R.color.green),percent)));

            movieTimeHolder.movieTimeTV.setTextColor(interpolateColor(context.getResources().getColor(R.color.red),context.getResources().getColor(R.color.green),percent));
            //movieTimeHolder.movieTimeTV.setTextSize(movieTimeHolder.movieTimeTV.getTextSize()+10);
        }
        else{
            movieTimeHolder.progressBar.setProgressTintList(ColorStateList.valueOf(interpolateColor(context.getResources().getColor(R.color.red),context.getResources().getColor(R.color.green),percent)));

            //movieTimeHolder.movieTimeTV.setTextColor(interpolateColor(context.getResources().getColor(R.color.red),context.getResources().getColor(R.color.green),percent));
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
                        movieTimeList.get(position).setSelected(true);
                        movieTimeList.get(selectedpos).setSelected(false);

                        notifyItemChanged(position);
                        notifyItemChanged(selectedpos);
                        selectedpos = position;
                    }
                    else{
                        movieTimeList.get(position).setSelected(true);
                        notifyItemChanged(position);
                        selectedpos = position;
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

