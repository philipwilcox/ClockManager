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
        val options = appWidgetManager.getAppWidgetOptions(appWidgetId)
        // TODO: basing on these values may break on anything that isn't a Pixel w/ default launcher like the emulator
        val min_width = options.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_WIDTH)
        // TODO: this implementation is probably pixel-default-launcher fragile and specific:
        // one column = 70, two columns = 156, three columns = 242...
        val columns = if (min_width == 70) 1 else (1 + (min_width-70)/86)
        // TODO This still feels a bit weird in terms of views:appWidgetIds mapping, need to better understand
        val remoteViews = RemoteViews(context.packageName, R.layout.widget_main)
        val intent = Intent(context, TimerListWidgetService::class.java)
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
        intent.putExtra(AppWidgetManager.EXTRA_CUSTOM_INFO, columns) // store the min_width as custom info
        intent.data = Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)) // seems to not affect anything, cargo-culted in examples?
        remoteViews.setRemoteAdapter(R.id.widgetList, intent)
        remoteViews.setEmptyView(R.id.widgetList, R.id.widgetEmptyText) // in case no items
        appWidgetManager.updateAppWidget(appWidgetId, remoteViews)
        Log.e("RapidTimer", "updated data for widget id $appWidgetId (width $min_width - cols $columns): (context: $context, manager: $appWidgetManager, intent: $intent)")
    }
}