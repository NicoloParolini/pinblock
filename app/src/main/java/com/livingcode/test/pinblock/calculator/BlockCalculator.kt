package com.livingcode.test.pinblock.calculator

import java.lang.NumberFormatException
import kotlin.experimental.xor
import kotlin.random.Random

// ISO 9564 Format 3 - https://en.wikipedia.org/wiki/ISO_9564
class BlockCalculator {
    private val PIN_MIN = 4
    private val PIN_MAX = 12
    private val PIN_PAD_MIN = 10
    private val PIN_PAD_MAX = 15
    private val BLOCK_SIZE = 16

    fun calculate(pin: String, pan: String): String? {
        if (pin.length in PIN_MIN..PIN_MAX && pan.length == BLOCK_SIZE) {
            return try {
                buildBlock(
                    buildPinField(pin.map { it.toString().toByte() }),
                    buildPanField(pan.map { it.toString().toByte() })
                )
            } catch (ex: NumberFormatException) {
                null
            }
        }
        return null
    }

    private fun buildPinField(pin: List<Byte>): ByteArray {
        return ByteArray(BLOCK_SIZE) { position ->
            when (position) {
                0 -> 3
                1 -> pin.size.toByte()
                in 2..pin.size + 1 -> pin[position - 2]
                else -> Random.Default.nextInt(PIN_PAD_MIN, PIN_PAD_MAX).toByte()
            }
        }
    }

    private fun buildPanField(pan: List<Byte>): ByteArray {
        return ByteArray(BLOCK_SIZE) { position ->
            when (position) {
                in 0..3 -> 0
                else -> pan[position - 1]
            }
        }
    }

    private fun buildBlock(pinField: ByteArray, panField: ByteArray): String {
        return ByteArray(BLOCK_SIZE) { position ->
            pinField[position] xor panField[position]
        }.joinToString("") { byte ->
            byte
                .toString(16)
                .uppercase()
        }
    }

}