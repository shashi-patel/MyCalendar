MyCalendar
===========
[![](https://jitpack.io/v/shashi180493/MyCalendar.svg)](https://jitpack.io/#shashi180493/MyCalendar)

Android fully customizable calendar 

<p align="center">
 <img height=393 width=200 src="https://github.com/shashi180493/MyCalendar/blob/master/snp_calendar.png"/>
</p>

I would appreciate any kind of help to improve this library. Thanks

Gradle
------
```
allprojects{
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}

dependencies {
    ...
    implementation 'com.github.shashi180493:MyCalendar:1.0'
}
```
Maven
-----
```
<repositories>
	<repository>
	    <id>jitpack.io</id>
	    <url>https://jitpack.io</url>
	</repository>
</repositories>

<dependency>
    <groupId>com.github.shashi180493</groupId>
    <artifactId>MyCalendar</artifactId>
    <version>1.0</version>
</dependency>
```

Usage
-----

You must declare the following view in your xml layout:

```xml
<com.shashikant.calendar.SNPCalendarView
        android:id="@+id/snpcv_demo"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"/>
```
implement following method in your class:

```java
snpcv_demo.setOnCalendarViewListener(new onSNPCalendarViewListener() {
      @Override
      public void onDateChanged(String date) {
          Log.d(TAG, "date->" + date);
      }

      @Override
      public void onDisplayedMonthChanged(int month, int year, String monthStr) {
      }
});

```

Developed By
--------------------

Shashikant Patel - <shashi180493@gmail.com>

<a href="https://www.facebook.com/imshashikantpatel">
  <img alt="Follow me on Facebook"
       height=50 width=50
       src="https://github.com/shashi180493/MyCalendar/blob/master/facebook.png" />
</a>
<a href="https://plus.google.com/u/0/+ShashikantPatelsurvivor">
  <img alt="Follow me on Google+"
       height=50 width=50
       src="https://github.com/shashi180493/MyCalendar/blob/master/google-plus.png" />
</a>
<a href="https://www.linkedin.com/in/shashikant-patel-01597180/">
  <img alt="Follow me on LinkedIn"
       height=50 width=50
       src="https://github.com/shashi180493/MyCalendar/blob/master/linkedin.png" />
