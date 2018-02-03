package com.mopeyphil.clockmanager

import android.app.Activity
import android.os.Bundle
import android.app.AlarmManager
import android.content.Context
import android.content.Intent
import android.provider.AlarmClock
import android.text.format.DateUtils
import android.util.Log

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
        showTimers()
    }

    fun showTimers() {
        val intent = Intent(AlarmClock.ACTION_SHOW_TIMERS)
        startActivityForResult(intent, 3)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // Check which request we're responding to
        if (requestCode == 3) {
            // Make sure the request was successful
            if (resultCode == Activity.RESULT_OK) {
                Log.e("QuickTimer", "$data")
            }
        }
    }
}
