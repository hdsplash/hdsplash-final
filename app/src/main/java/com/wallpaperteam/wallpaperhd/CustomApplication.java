package com.wallpaperteam.wallpaperhd;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.multidex.MultiDex;

import java.util.Calendar;

public class CustomApplication extends Application {

    private static Context context;
    private AlarmManager alarmManager;
    private PendingIntent alarmIntent;
    public static final String MyPREFERENCES = "LayoutPrefs" ;
    public static SharedPreferences sharedPreferences;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        sharedPreferences= getSharedPreferences(MyPREFERENCES,MODE_PRIVATE);
        context = getApplicationContext();
        alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 12);
        calendar.set(Calendar.MINUTE, 0);

        alarmManager.setRepeating(AlarmManager.RTC, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, alarmIntent);

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    protected void attachBaseContext(Context newBase){
        super.attachBaseContext(newBase);
        MultiDex.install(this);
    }
}
