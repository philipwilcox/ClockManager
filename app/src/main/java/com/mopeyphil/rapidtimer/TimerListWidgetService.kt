package com.mopeyphil.rapidtimer

import android.content.Intent
import android.util.Log
import android.widget.RemoteViewsService


class TimerListWidgetService : RemoteViewsService() {
    init {
        Log.e("RapidTimer", "initializing TimerListWidgetService")
    }

    override fun onGetViewFactory(intent: Intent) : RemoteViewsFactory {
        Log.e("RapidTimer", "Context in TimerLIstWidgetService is ${this.applicationContext}")
        return TimerListWidgetFactory(this.applicationContext)
    }
}