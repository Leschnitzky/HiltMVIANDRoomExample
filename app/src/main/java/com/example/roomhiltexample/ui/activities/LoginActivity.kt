package com.example.roomhiltexample.ui.activities

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity

import androidx.activity.viewModels

import com.example.roomhiltexample.R
import com.example.roomhiltexample.ui.viewmodels.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

    }
}