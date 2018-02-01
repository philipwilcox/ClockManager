package com.mopeyphil.clockmanager

import android.app.Activity
import android.os.Bundle
import android.app.AlarmManager
import android.content.Context
import android.text.format.DateUtils
import android.util.Log

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var alarmManager = applicationContext.getSystemService(Context.ALARM_SERVICE);
        var nextAlarm = (alarmManager as AlarmManager).nextAlarmClock
        Log.e("AlarmManager: ", "$nextAlarm")
        nextAlarm?.let {
            var alarmString = DateUtils.formatDateTime(applicationContext, it.triggerTime,
                    DateUtils.FORMAT_SHOW_DATE or DateUtils.FORMAT_SHOW_TIME)
            Log.e("AlarmManager", "Next alarm will trigger at $alarmString")
        }

    }
}
