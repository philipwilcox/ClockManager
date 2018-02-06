package com.mopeyphil.rapidtimer

import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.util.Log
import android.widget.RemoteViews
import android.widget.RemoteViewsService

class TimerListWidgetFactory(val context: Context, val intent: Intent) : RemoteViewsService.RemoteViewsFactory {
    init {
        Log.e("RapidTimer", "initializing TimerListWidgetFactory")
    }

    override fun onCreate() {
        Log.e("RapidTimer", "onCreate, Context is $context")

        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLoadingView(): RemoteViews {
        Log.e("RapidTimer", "getLoadingView")
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
        remoteViews.setTextViewText(R.id.timerText, "${calendar.timeInMillis}")
        return remoteViews
    }

    override fun getCount(): Int {
        Log.e("RapidTimer", "getCount")
        return 1;
    }

    override fun getViewTypeCount(): Int {
        Log.e("RapidTimer", "getViewTypeCount")
        return 1;
    }

    override fun onDestroy() {
        Log.e("RapidTimer", "onDestroy")
    }
}