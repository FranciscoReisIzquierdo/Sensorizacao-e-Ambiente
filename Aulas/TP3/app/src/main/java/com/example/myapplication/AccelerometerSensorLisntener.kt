package com.example.myapplication


import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log


class AccelerometerSensorlistener: SensorEventListener {
    companion object {
        private const val TAG: String = "AccelerometerSensorlistener"
    }

    private lateinit var sensorManager: SensorManager
    private lateinit var ourAccelerometerViewModel: AccelerometerViewModel

    fun setSensorManager(sensorMan: SensorManager, aViewModel: AccelerometerViewModel) {
        sensorManager = sensorMan
        ourAccelerometerViewModel = aViewModel
    }

    override fun onSensorChanged(event: SensorEvent) {
        AccelerometerData.valueX = event.values[0]
        AccelerometerData.valueY = event.values[1]
        AccelerometerData.valueZ = event.values[2]
        AccelerometerData.accuracy = event.accuracy
        ourAccelerometerViewModel.currentAccelerometerData.value = AccelerometerData
        //sensorManager.unregisterListener(this)
        Log.d(
            TAG,
            "[SENSOR] - X=${AccelerometerData.valueX}. Y=${AccelerometerData.valueY}, Z=${AccelerometerData.valueZ}"
        )
    }


    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

}
