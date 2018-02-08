package com.mopeyphil.rapidtimer

import android.content.Context
import android.util.Log
import android.widget.RemoteViews
import android.widget.RemoteViewsService

class TimerListWidgetFactory(val context: Context, val columns: Int) : RemoteViewsService.RemoteViewsFactory {
    // TODO: eventually this will call out to (a) stopped timers living in stored data service that
    // can be updated through app - some sort of UpdateDataService - or running timers living in
    // another service or such?

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
        val timer = DummyTimerData.getTimer(position)
        val remoteViews = RemoteViews(context.packageName, R.layout.widget_timer_item)
        remoteViews.setTextViewText(R.id.timerText, "${timer.name}")

        // TODO Make a helper for this with minute formatting, somewhere - inside Timer, probably
        remoteViews.setTextViewText(R.id.timerDuration, "${timer.duration}")
        return remoteViews
    }

    override fun getCount(): Int {
        Log.e("RapidTimer", "getCount")
        val numTimers = DummyTimerData.getCount()
        if (columns < numTimers) {
            return columns
        } else {
            return numTimers
        }
    }

    override fun getViewTypeCount(): Int {
        Log.e("RapidTimer", "getViewTypeCount")
        return 1;
    }

    override fun onDestroy() {
        Log.e("RapidTimer", "onDestroy")
    }
}