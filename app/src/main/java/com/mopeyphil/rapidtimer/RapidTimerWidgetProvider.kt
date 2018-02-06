package com.mopeyphil.rapidtimer

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
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
        // TODO eventually handle appWidgetIds to support multiple instances of the widget?
        for (i in appWidgetIds) {
            dataUpdater(context, appWidgetManager, i)
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        Log.e("RapidTimer", "widget updated!")
    }

    override fun onAppWidgetOptionsChanged(context: Context?, appWidgetManager: AppWidgetManager?, appWidgetId: Int, newOptions: Bundle?) {
        // TODO why does this get optionals for everything an onUpdate got non-nulls?
        // TODO the !! is what I want?
        dataUpdater(context!!, appWidgetManager!!, appWidgetId)
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions)
        Log.e("RapidTimer", "update done with options $newOptions")
    }

    private fun dataUpdater(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {

        // TODO This still feels a bit weird in terms of views:appWidgetIds mapping, need to better understand
        val remoteViews = RemoteViews(context.packageName, R.layout.widget_main)

        // Let's see if this has effect, at least:
        remoteViews.setTextColor(R.id.widgetText, Color.RED)

        val intent = Intent(context, TimerListWidgetService::class.java)
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
        intent.data = Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)) // seems to not affect anything, cargo-culted in examples?
        remoteViews.setRemoteAdapter(R.id.widgetList, intent)
        remoteViews.setEmptyView(R.id.widgetList, R.id.widgetEmptyText) // in case no items
        appWidgetManager.updateAppWidget(appWidgetId, remoteViews)
        Log.e("RapidTimer", "updated data for widget id $appWidgetId (context: $context, manager: $appWidgetManager, intent: $intent)")

    }
}