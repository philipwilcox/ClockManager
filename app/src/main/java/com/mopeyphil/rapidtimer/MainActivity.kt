package com.mopeyphil.rapidtimer

import android.app.Activity
import android.os.Bundle
import android.app.AlarmManager
import android.content.Context
import android.text.format.DateUtils
import android.util.Log
import kotlinx.android.synthetic.main.widget_main.*

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TODO add neater error handling if this is null somehow
        val alarmManager = (applicationContext.getSystemService(Context.ALARM_SERVICE)) as AlarmManager
        var nextAlarm = alarmManager.nextAlarmClock
        Log.e("AlarmManager: ", "$nextAlarm")
        nextAlarm?.let {
            var alarmString = DateUtils.formatDateTime(applicationContext, it.triggerTime,
                    DateUtils.FORMAT_SHOW_DATE or DateUtils.FORMAT_SHOW_TIME)
            Log.e("AlarmManager", "Next alarm will trigger at $alarmString")
        }

        val columns = 3
        widgetList.adapter = TimerArrayAdapter(applicationContext, DummyTimerData.myTimers)
    }
}
