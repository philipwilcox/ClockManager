package com.mopeyphil.rapidtimer

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.widget_timer_item.view.*
import android.R.attr.name
import android.widget.TextView
import android.view.LayoutInflater
import android.view.View.inflate



class TimerArrayAdapter(context : Context, timers : Array<Timer> ) : ArrayAdapter<Timer>(context, 0, timers) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val timer = getItem(position)
        val view =  if (convertView == null) {
            LayoutInflater.from(context).inflate(R.layout.widget_timer_item, parent, false)
        } else {
            convertView
        }
        view.timerText.text = timer.name
        // TODO Make a helper for this with minute formatting, somewhere - inside Timer, probably
        // And another for timerRemaining that shows millis if single-digit seconds...
        view.timerDuration.text = "${timer.duration}"
        return view
    }


}
