package com.zhyea.bamboo.reader

import android.app.Activity
import android.hardware.Sensor
import android.hardware.Sensor.TYPE_ACCELEROMETER
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.os.PowerManager
import android.os.PowerManager.WakeLock
import android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
import com.zhyea.bamboo.general.ContentController
import com.zhyea.bamboo.general.IRequestHandler
import java.util.*
import java.util.concurrent.TimeUnit


class BambooActivity : Activity() {


    private val listener: BambooActivityListener = BambooActivityListener(this)

    /**
     * n
     */
    private var wakeLock: WakeLock? = null

    /**
     * o
     */
    private var handler: Handler? = null

    /**
     * h
     */
    private var sensorManager: SensorManager? = null

    /**
     * c
     */
    private val sensorListeners: LinkedList<SensorListenerInfo> = LinkedList()

    /**
     * d
     */
    private val windowRotationChangeListeners: LinkedList<OnWindowRotationChangeListener> = LinkedList()

    /**
     * p
     */
    private val delayMillis = 0;

    /**
     * i
     */
    private var contentController: ContentController? = null

    /**
     * m
     */
    private val keyboardBrightness: Double = -1.0


    private fun lockScreen() {
        getScreenLock().acquire(TimeUnit.MINUTES.toMillis(1))
    }

    private fun unlockScreen() {
        getScreenLock().release()
    }

    private fun resetScreenTimeout() {
        if (null == this.handler) {
            this.handler = Handler(Looper.getMainLooper(), BambooActivityCallback(this))
        }
        this.handler!!.removeCallbacksAndMessages(null);
        if (0 == delayMillis) {
            unlockScreen();
        }
        if ((0 != this.delayMillis) && (this.delayMillis != Int.MAX_VALUE)) {
            this.handler!!.sendEmptyMessageDelayed(0, this.delayMillis.toLong())
        }
    }

    private fun getScreenLock(): WakeLock {
        if (this.wakeLock == null) {
            val powerManager: PowerManager = getSystemService(POWER_SERVICE) as PowerManager
            this.wakeLock = powerManager.newWakeLock(FLAG_KEEP_SCREEN_ON, "actTag:")
            this.wakeLock!!.setReferenceCounted(false)
        }
        return this.wakeLock!!
    }

    private fun closeScreenTimeout() {
        this.handler?.removeCallbacksAndMessages(null)
    }


    private fun notifyWindowRotationChange(eventId: Int) {
        for (listener in windowRotationChangeListeners) {
            listener.onWindowRotationChange(eventId)
        }
    }


    fun addOnScreenRotationChangeListener(listener: OnWindowRotationChangeListener) {
        this.windowRotationChangeListeners.add(listener)
    }

    fun addSensorListener(sensor: Sensor, sensorEventListener: SensorEventListener, paramInt: Int) {
        getSensorManager()?.registerListener(sensorEventListener, sensor, paramInt)
        this.sensorListeners.add(SensorListenerInfo(sensor, sensorEventListener, paramInt))
    }


    fun getAccelerometerSensor(): Sensor? {
        return getSensorManager()?.getDefaultSensor(TYPE_ACCELEROMETER)
    }

    fun getContentController(): ContentController? {
        return this.contentController
    }

    fun getKeyboardBrightness(): Double {
        return this.keyboardBrightness
    }


    fun getSensorManager(): SensorManager? {
        if (null == this.sensorManager) {
            this.sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        }
        return this.sensorManager
    }


    /**
     * -------------------------------------------------------------------------
     */

    class BambooActivityTask : TimerTask() {
        override fun run() {
            TODO("Not yet implemented")
        }

    }


    class BambooActivityListener(private val activity: BambooActivity) : IRequestHandler {

        override fun requestDeactive(request: ContentController?): Boolean {
            return false
        }

        override fun requestHideMenu() {
        }

        override fun requestShowMenu() {
            TODO("Not yet implemented")
        }


        fun getSoftInputMode(handler: ContentController): Int {
            return this.activity.window.attributes.softInputMode
        }

    }


    interface OnWindowRotationChangeListener {
        fun onWindowRotationChange(eventId: Int)
    }

    class BambooActivityCallback(val bambooActivity: BambooActivity) : Handler.Callback {
        override fun handleMessage(msg: Message): Boolean {
            TODO("Not yet implemented")
            return true
        }
    }


    class SensorListenerInfo(val sensor: Sensor, val eventListener: SensorEventListener, val eventCode: Int) {

    }

}