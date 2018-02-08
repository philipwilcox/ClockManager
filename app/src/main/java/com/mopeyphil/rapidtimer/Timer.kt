package com.mopeyphil.rapidtimer

import android.icu.util.Calendar

/**
 * This represents a timer, either running or not. It's more than a data class since interactions
 * with it will gneerally be using the start/pause/reset methods.
 */
class Timer(var duration: Long, var name: String = "", private val calendar: Calendar = Calendar.getInstance()) {
    var state = TimerState.STOPPED
    internal var startedAt : Long = 0
    internal var elapsed : Long = 0

    /**
     * STOPPED is a timer which will run for `duration` milliseconds after being started.
     * RUNNING is a timer currently running.
     * PAUSED is a timer that has already run for `elapsed` milliseconds but is not currently running.
     */
    enum class TimerState {
        STOPPED, RUNNING, PAUSED
    }

    fun start() {
        when (state) {
            TimerState.STOPPED -> startedAt = calendar.timeInMillis
            // fudge a new faked startedAt if paused based on how much time had elapsed before
            TimerState.PAUSED ->  startedAt = (calendar.timeInMillis - elapsed)
            // startedAt should end up 20000 - 2500 => 17500
            else -> throw Exception("Shouldn't be able to start a timer that's not in STOPPED or PAUSED state")
        }
        state = TimerState.RUNNING
    }

    fun pause() {
        elapsed = calendar.timeInMillis - startedAt
        state = TimerState.PAUSED
    }

    fun reset() {
        state = TimerState.STOPPED
    }

    fun currentRemainingMillis() : Long {
        return when (state) {
            TimerState.PAUSED -> duration - elapsed
            TimerState.RUNNING -> duration - (calendar.timeInMillis - startedAt)
            else -> duration
        }
    }
}