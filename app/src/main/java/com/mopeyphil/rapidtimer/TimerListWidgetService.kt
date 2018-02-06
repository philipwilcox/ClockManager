package com.mopeyphil.rapidtimer

import android.content.Intent
import android.util.Log
import android.widget.RemoteViewsService


class TimerListWidgetService : RemoteViewsService() {

    override fun onGetViewFactory(intent: Intent) : TimerListWidgetFactory {
        Log.e("RapidTimer", "Context in TimerLIstWidgetService is ${this.applicationContext}")
        return TimerListWidgetFactory(this.applicationContext, intent)
    }
}