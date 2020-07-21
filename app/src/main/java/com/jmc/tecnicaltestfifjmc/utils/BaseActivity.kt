package com.jmc.tecnicaltestfifjmc.utils

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity



abstract class BaseActivity : AppCompatActivity() {

    protected lateinit var baseViewModel: BaseViewModel

    protected abstract fun initializeViewModel()
    abstract fun observeViewModel()
//    protected abstract fun initViewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        initializeViewModel()
        observeViewModel()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
