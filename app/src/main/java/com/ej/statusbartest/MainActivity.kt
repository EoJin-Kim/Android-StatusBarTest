package com.ej.statusbartest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.ej.statusbartest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.lifecycleOwner = this

        val transition = supportFragmentManager.beginTransaction()
        val fragment = BlankFragment.newInstance()
        transition.replace(R.id.fragmentContainerView, fragment)
        transition.commit()
    }
}