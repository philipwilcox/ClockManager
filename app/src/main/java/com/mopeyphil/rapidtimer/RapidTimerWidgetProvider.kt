package com.mopeyphil.rapidtimer

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.util.Log
import android.widget.ListView
import android.widget.RemoteViews



class RapidTimerWidgetProvider : AppWidgetProvider() {

    override fun onEnabled(context: Context) {
        super.onEnabled(context)
        // Don't think I need to do anything custom here since onUpdate is called immediately after.
        Log.e("RapidTimer", "widget enabled!")
    }

    override fun onUpdate(context: Context, appWidgetManager : AppWidgetManager, appWidgetIds: IntArray) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        val remoteViews = RemoteViews(context.packageName, R.layout.widget_main)
        // TODO: hit another layer of indirection here, https://developer.android.com/reference/android/widget/RemoteViews.html#setRemoteAdapter(int,%20android.content.Intent)
        // I need an Intent to point to a service for the adapter
        //remoteViews.setRemoteAdapter(R.id.widgetList, null)
        Log.e("RapidTimer", "widget updated!")
    }
}