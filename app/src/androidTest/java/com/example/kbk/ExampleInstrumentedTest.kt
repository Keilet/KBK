package com.example.kbk

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val sharedPreferences = appContext.getSharedPreferences("test", 0)
        sharedPreferences.edit().clear().commit();
        val valueHelper:ClassValuesHelper=ClassValuesHelper(sharedPreferences)

     //   valueHelper.saveValues()

        val readValues:Values= valueHelper.readValues()!!
        val value:Values= Values()
        value.setFirstOperand("kea")
        value.setSecondOperand(144)
        assertTrue(value.equalsToValues(readValues))
    }
}