package com.shrikanthravi.movietimeselection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.shrikanthravi.timeselectionview.TimeSelectionView;
import com.shrikanthravi.timeselectionview.data.MovieTime;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final List<MovieTime> movieTimeList = new ArrayList<>();

        TimeSelectionView timeSelectionView = findViewById(R.id.timeSelectionView);


        movieTimeList.add(new MovieTime("7:00 am",300,250));
        movieTimeList.add(new MovieTime("10:00 am",300,120));
        movieTimeList.add(new MovieTime("12:00 pm",300,60));
        movieTimeList.add(new MovieTime("1:45 pm",300,50));
        movieTimeList.add(new MovieTime("3:00 pm",300,170));
        movieTimeList.add(new MovieTime("6:00 pm",300,0));
        movieTimeList.add(new MovieTime("8:00 pm",300,120));
        movieTimeList.add(new MovieTime("10:30 pm",300,60));

        timeSelectionView.setMovieTimeList(movieTimeList);

        timeSelectionView.setOnMovieTimeClickListener(new TimeSelectionView.OnMovieTimeClickListener() {
            @Override
            public void onMovieTimeClicked(int position) {
                Toast.makeText(MainActivity.this,"Selected Movie Time "+movieTimeList.get(position).getTime(),Toast.LENGTH_SHORT).show();
            }
        });

    }
}
