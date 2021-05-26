package com.livingcode.test.pinblock

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.livingcode.test.pinblock.ui.main.BlockFragment

class BlockActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.block_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, BlockFragment.newInstance())
                .commitNow()
        }
    }
}