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
import android.view.WindowManager
import android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
import com.zhyea.bamboo.general.ContentController
import com.zhyea.bamboo.general.IRequestHandler
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.max
import kotlin.math.min


class BambooActivity : Activity() {


    /**
     * b
     */
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
    private var keyboardBrightness: Float = -1.0F

    /**
     * k
     */
    private var screenBrightness: Float = -1.0F

    /**
     * p
     */
    private var screenTimeout: Int = 0;

    /**
     * j
     */
    private var screenBrightnessMode: BrightnessMode = BrightnessMode.SYSTEM

    /**
     * l
     */
    private var keyboardBrightnessMode: BrightnessMode = BrightnessMode.SYSTEM


    private fun adjustKeyboardBrightness() {
        val layoutParams: WindowManager.LayoutParams = window.attributes;
        var buttonBrightness: Float = layoutParams.buttonBrightness

        while (true) {
            if (layoutParams.buttonBrightness.compareTo(buttonBrightness) != 0) {
                layoutParams.buttonBrightness = buttonBrightness
                window.attributes = layoutParams
                return
            }
            buttonBrightness = -1.0F;
            buttonBrightness = max(0.0F, min(this.keyboardBrightness, 1.0F))
        }
    }


    private fun adjustScreenBrightness() {
        val layoutParams: WindowManager.LayoutParams = window.attributes;
        var buttonBrightness: Float = layoutParams.screenBrightness

        while (true) {
            if (layoutParams.screenBrightness.compareTo(buttonBrightness) != 0) {
                layoutParams.screenBrightness = buttonBrightness
                window.attributes = layoutParams
                return
            }
            buttonBrightness = -1.0F;
            buttonBrightness = max(0.02F, min(this.screenBrightness, 1.0F));
        }
    }


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

    fun getKeyboardBrightness(): Float {
        return this.keyboardBrightness
    }


    private fun getSensorManager(): SensorManager? {
        if (null == this.sensorManager) {
            this.sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        }
        return this.sensorManager
    }


    fun setContentController(controller: ContentController) {
        this.contentController = controller
        this.contentController?.setParent(this.listener)
    }

    fun setKeyboardBrightness(brightness: Float) {
        this.keyboardBrightness = brightness
        adjustKeyboardBrightness()
    }

    fun setKeyboardBrightnessMode(mode: BrightnessMode) {
        this.keyboardBrightnessMode = mode
        adjustKeyboardBrightness()
    }

    fun setScreenBrightness(screenBrightness: Float) {
        this.screenBrightness = screenBrightness
        adjustScreenBrightness()
    }

    fun setScreenBrightnessMode(mode: BrightnessMode) {
        this.screenBrightnessMode = mode
        adjustScreenBrightness()
    }

    fun setScreenTimeout(timeout: Int) {
        this.screenTimeout = timeout
        lockScreen()
        resetScreenTimeout()
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