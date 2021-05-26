package com.livingcode.test.pinblock.calculator

import org.junit.Assert.*
import org.junit.Test

class BlockCalculatorTest{
    private val calculator = BlockCalculator()
    private val validPan = "1111222233334444"
    private val validPin = "1234"

    @Test
    fun calculateShortPin() {
        assertNull(calculator.calculate("1", validPan))
    }

    @Test
    fun calculateLongPin() {
        assertNull(calculator.calculate("12345678912345", validPan))
    }

    @Test
    fun calculateNonNumericPin() {
        assertNull(calculator.calculate("1abcde3", validPan))
    }

    @Test
    fun calculateValidPinAndValidPan() {
        val result = calculator.calculate(validPin, validPan)
        assertNotNull(result)
        assertEquals(16, result!!.length)
        assertEquals('3', result[0])
        assertEquals(validPin.length.toString(16), result[1].toString())
        assertEquals(validPin[0], result[2])
        assertEquals(validPin[1], result[3])
    }

    @Test
    fun calculateShortPan() {
        assertNull(calculator.calculate(validPin, "1"))
    }

    @Test
    fun calculateLongPan() {
        assertNull(calculator.calculate(validPin, "12345678900987654321"))
    }

    @Test
    fun calculateNonNumericPan() {
        assertNull(calculator.calculate(validPin, "sdsdg23456789012"))
    }
}