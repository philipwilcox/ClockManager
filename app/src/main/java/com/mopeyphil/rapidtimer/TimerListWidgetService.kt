package com.mopeyphil.rapidtimer

import android.appwidget.AppWidgetManager
import android.content.Intent
import android.util.Log
import android.widget.RemoteViewsService


class TimerListWidgetService : RemoteViewsService() {
    override fun onGetViewFactory(intent: Intent) : RemoteViewsFactory {
        Log.e("RapidTimer", "Context in TimerLIstWidgetService is ${this.applicationContext}")
        // Get width if we were invoked from an intent that has it as CUSTOM_INFO, otherwise use 0
        val columns = intent?.extras?.getInt(AppWidgetManager.EXTRA_CUSTOM_INFO) ?: 0
        return TimerListWidgetFactory(this.applicationContext, columns)
    }
}