package com.mopeyphil.rapidtimer

import android.icu.util.Calendar
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito

/**
 * Created by philip on 2/6/18.
 */
class TimerUnitTest {
    val calendar = Mockito.mock(Calendar::class.java)

    @Test
    fun start_a_new_timer() {
        val timer = Timer(5000, "", calendar)
        Mockito.`when`(calendar.timeInMillis).thenReturn(10000)
        timer.start()
        assertEquals(timer.duration, 5000)
        val remaining = timer.currentRemainingMillis()
        assertEquals(remaining, 5000) // Remaining is the important part of the contract to check
        assertEquals(timer.state, Timer.TimerState.RUNNING)
    }

    @Test
    fun pause_a_running_timer() {
        val timer = Timer(5000, "", calendar)
        Mockito.`when`(calendar.timeInMillis).thenReturn(10000)
        timer.start()
        // Pretend 2.5s went by
        Mockito.`when`(calendar.timeInMillis).thenReturn(12500)
        timer.pause()
        assertEquals(timer.duration, 5000)
        // Let some time pass while paused
        Mockito.`when`(calendar.timeInMillis).thenReturn(14500)
        val remaining = timer.currentRemainingMillis()
        assertEquals(remaining, 2500)
        assertEquals(timer.state, Timer.TimerState.PAUSED)
    }

    @Test
    fun remaining_for_running_timer() {
        val timer = Timer(5000, "", calendar)
        Mockito.`when`(calendar.timeInMillis).thenReturn(10000)
        timer.start()
        // Pretend 3.5s went by
        Mockito.`when`(calendar.timeInMillis).thenReturn(13500)
        val remaining = timer.currentRemainingMillis()
        assertEquals(timer.duration, 5000)
        assertEquals(timer.state, Timer.TimerState.RUNNING)
        assertEquals(remaining, 1500)
    }

    @Test
    fun remaining_for_paused_timer() {
        val timer = Timer(5000, "", calendar)
        Mockito.`when`(calendar.timeInMillis).thenReturn(10000)
        timer.start()
        // Pretend 2.5s went by
        Mockito.`when`(calendar.timeInMillis).thenReturn(12500)
        timer.pause()
        assertEquals(timer.duration, 5000)
        assertEquals(timer.startedAt, 10000)
        val remaining = timer.currentRemainingMillis()
        assertEquals(remaining, 2500) // check remaining at point when paused
        assertEquals(timer.state, Timer.TimerState.PAUSED)
        // Set current time a bit further in the future to make sure remaining ignores this in paused state
        Mockito.`when`(calendar.timeInMillis).thenReturn(15000)
        val remaining2 = timer.currentRemainingMillis()
        assertEquals(remaining2, 2500)
    }

    @Test
    fun start_a_paused_timer() {
        val timer = Timer(5000, "", calendar)
        Mockito.`when`(calendar.timeInMillis).thenReturn(10000)
        timer.start()
        // Pretend 3.5s went by
        Mockito.`when`(calendar.timeInMillis).thenReturn(13500)
        timer.pause()
        assertEquals(timer.state, Timer.TimerState.PAUSED)
        // Set current time a bit further in the future to make sure remaining ignores this in paused state
        Mockito.`when`(calendar.timeInMillis).thenReturn(20000)
        timer.start()
        // Now let one more second elapse on the timer
        Mockito.`when`(calendar.timeInMillis).thenReturn(21000)
        assertEquals(timer.duration, 5000)
        val remaining = timer.currentRemainingMillis()
        assertEquals(500, remaining)
    }

    @Test
    fun reset_a_timer() {
        val timer = Timer(5000, "", calendar)
        Mockito.`when`(calendar.timeInMillis).thenReturn(10000)
        timer.start()
        // Pretend 3.5s went by
        Mockito.`when`(calendar.timeInMillis).thenReturn(13500)
        timer.pause()
        // Set current time a bit further in the future to make sure remaining ignores this in paused state
        Mockito.`when`(calendar.timeInMillis).thenReturn(20000)
        timer.reset()
        // Now let one more second go by clock
        Mockito.`when`(calendar.timeInMillis).thenReturn(21000)
        assertEquals(timer.duration, 5000)
        val remaining = timer.currentRemainingMillis()
        assertEquals(remaining, 5000)
        assertEquals(timer.state, Timer.TimerState.STOPPED)
    }

    @Test
    fun start_a_reset_timer() {
        val timer = Timer(5000, "", calendar)
        Mockito.`when`(calendar.timeInMillis).thenReturn(10000)
        timer.start()
        // Pretend 3.5s went by
        Mockito.`when`(calendar.timeInMillis).thenReturn(13500)
        timer.reset()
        timer.start()
        // Set current time a bit further after resetting
        Mockito.`when`(calendar.timeInMillis).thenReturn(16500)
        val remaining = timer.currentRemainingMillis()
        assertEquals(timer.duration, 5000)
        assertEquals(remaining, 2000)
        assertEquals(timer.state, Timer.TimerState.RUNNING)

    }


}