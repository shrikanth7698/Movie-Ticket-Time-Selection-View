![alt text](https://drive.google.com/uc?id=1kc4jGVGjTneM88VqbnRfOn9fGpQhLd3I)

# Movie Time Selection View

### Movie Time Selection View Library for Android

Hassle free way to select movie time at your own place and pace. Designed with different color variations to show the number of seats occupied at any moment.

  ### Version
[![](https://jitpack.io/v/shrikanth7698/Movie-Ticket-Time-Selection-View.svg)](https://jitpack.io/#shrikanth7698/Movie-Ticket-Time-Selection-View)

### Installation

* **Gradle**

	Add it in your root build.gradle at the end of repositories:
	```gradle
  allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
	```

	Add the dependency in your app build.gradle
	```gradle
  dependencies {
	        implementation 'com.github.shrikanth7698:Movie-Ticket-Time-Selection-View:v0.0.1'
	}
	```

* **Maven**

	Add the JitPack repository to your build file
	```gradle
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
	```

	Add the dependency
	```gradle
  	<dependency>
        <groupId>com.github.shrikanth7698</groupId>
        <artifactId>Movie-Ticket-Time-Selection-View</artifactId>
        <version>v0.0.1</version>
	  </dependency>
	```
  
### Usage

Drop the Custom Navigation Drawer in your XML layout as is shown below:
```xml
    <com.shrikanthravi.timeselectionview.TimeSelectionView
        android:layout_width="match_parent"
        android:id="@+id/timeSelectionView"
        android:layout_centerVertical="true"
        android:layout_height="wrap_content"
        android:layout_margin="15dp">
        
    </com.shrikanthravi.timeselectionview.TimeSelectionView>
```
And then in your Activity or fragment
```java
        
        //Inside onCreate()
        
        //Create a list of MovieTime
        final List<MovieTime> movieTimeList = new ArrayList<>();

        TimeSelectionView timeSelectionView = findViewById(R.id.timeSelectionView);
        
        //Add movie times 
        //MovieTime movieTime = new MovieTime("Time",totalNumberOfSeats,availableSeats)
        movieTimeList.add(new MovieTime("7:00 am",300,250));
        movieTimeList.add(new MovieTime("10:00 am",300,120));
        movieTimeList.add(new MovieTime("12:00 pm",300,60));
        movieTimeList.add(new MovieTime("1:45 pm",300,50));
        movieTimeList.add(new MovieTime("3:00 pm",300,170));
        movieTimeList.add(new MovieTime("6:00 pm",300,0));
        movieTimeList.add(new MovieTime("8:00 pm",300,120));
        movieTimeList.add(new MovieTime("10:30 pm",300,60));

        //set the movie list to time selection view
        timeSelectionView.setMovieTimeList(movieTimeList);

        //Listener to get the movie time selected by the user
        // it returns the position of the selected movie time which is related to the list you created earlier
        timeSelectionView.setOnMovieTimeClickListener(new TimeSelectionView.OnMovieTimeClickListener() {
            @Override
            public void onMovieTimeClicked(int position) {
                Toast.makeText(MainActivity.this,"Selected Movie Time   "+movieTimeList.get(position).getTime(),Toast.LENGTH_SHORT).show();
            }
        });
```

## License

```
MIT License

Copyright (c) 2018 Shrikanth Ravi

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
