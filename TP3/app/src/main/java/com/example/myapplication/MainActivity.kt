package com.example.myapplication

import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Context
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer


class MainActivity : AppCompatActivity() {

    lateinit var sensorManager: SensorManager
    lateinit var accelerometerSensorListener: AccelerometerSensorlistener
    private val model: AccelerometerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.register_button).setOnClickListener {
            Log.d("BUTTONS", "User clicked button with id BUTTON_ID.")
            //Get the sensor manager
            sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
            //Get the accelerometer sensor
            val mAcccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
            //If the smartphone has this sensor
            if (mAcccelerometer != null) {
                accelerometerSensorListener = AccelerometerSensorlistener()
                accelerometerSensorListener.setSensorManager(sensorManager, model)
                sensorManager.registerListener(
                    accelerometerSensorListener,
                    mAcccelerometer,
                    SensorManager.SENSOR_DELAY_NORMAL
                )
            }
        }
        findViewById<Button>(R.id.stop_button).setOnClickListener {
            Log.d("BUTTONS", "User clicked button with id BUTTON_ID.")
            sensorManager.unregisterListener(accelerometerSensorListener)
        }
        val accelerometerObserver = Observer<AccelerometerData> { newSample ->
            //ir buscar textviews do x y e z
            findViewById<TextView>(R.id.value_for_x).text = newSample.valueX.toString()
            findViewById<TextView>(R.id.value_for_y).text = newSample.valueY.toString()
            findViewById<TextView>(R.id.value_for_z).text = newSample.valueZ.toString()
        }
        model.currentAccelerometerData.observe(this, accelerometerObserver)
    }



}
