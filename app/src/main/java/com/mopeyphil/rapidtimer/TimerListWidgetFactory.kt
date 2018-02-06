package com.mopeyphil.rapidtimer

import android.content.Context
import android.icu.util.Calendar
import android.util.Log
import android.widget.RemoteViews
import android.widget.RemoteViewsService

class TimerListWidgetFactory(val context: Context) : RemoteViewsService.RemoteViewsFactory {
    // TODO: eventually this will call out to (a) stopped timers living in stored data service that
    // can be updated through app - some sort of UpdateDataService - or running timers living in
    // another service or such?

    init {
        Log.e("RapidTimer", "initializing TimerListWidgetFactory")
    }

    override fun onCreate() {
        Log.e("RapidTimer", "onCreate, Context is $context")
    }

    override fun getLoadingView(): RemoteViews? {
        Log.e("RapidTimer", "getLoadingView")
        return null;
    }

    override fun getItemId(position: Int): Long {
        Log.e("RapidTimer", "getItemId")
        return 1;
    }

    override fun onDataSetChanged() {
        Log.e("RapidTimer", "onDataSetChanged")
    }

    override fun hasStableIds(): Boolean {
        Log.e("RapidTimer", "hasStableIds")
        return true;
    }

    override fun getViewAt(position: Int): RemoteViews {
        Log.e("RapidTimer", "getViewAt")
        val remoteViews = RemoteViews(context.packageName, R.layout.widget_timer_item)

        val calendar = Calendar.getInstance()
        remoteViews.setTextViewText(R.id.timerText, "${position} - ${calendar.timeInMillis % 100}")
        return remoteViews
    }

    override fun getCount(): Int {
        Log.e("RapidTimer", "getCount")
        return 2;
    }

    override fun getViewTypeCount(): Int {
        Log.e("RapidTimer", "getViewTypeCount")
        return 1;
    }

    override fun onDestroy() {
        Log.e("RapidTimer", "onDestroy")
    }
}