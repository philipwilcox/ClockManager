package com.mopeyphil.rapidtimer

import java.util.*

/**
 * Created by philip on 2/8/18.
 */
object DummyTimerData {
    val random = Random()

    val myTimers = Array<Timer>(3, {
        Timer(random.nextInt(5).toLong() + 1, "# $it")
    })

    fun getTimer(i: Int) : Timer {
        return myTimers.get(i)
    }

    fun getCount() : Int {
        return myTimers.size
    }
}