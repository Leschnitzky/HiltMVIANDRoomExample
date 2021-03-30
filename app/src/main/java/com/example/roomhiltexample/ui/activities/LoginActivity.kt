package com.example.roomhiltexample.ui.activities

import android.os.Bundle
import android.view.View
import android.widget.*

import androidx.appcompat.app.AppCompatActivity

import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.roomhiltexample.R
import com.example.roomhiltexample.model.User
import com.example.roomhiltexample.ui.adapters.MyAdapter
import com.example.roomhiltexample.ui.viewmodels.LoginViewModel
import com.example.roomhiltexample.ui.viewmodels.MainIntent
import com.example.roomhiltexample.util.DataState
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private val viewModel: LoginViewModel by viewModels()
    private val errorText: TextView by lazy { findViewById(R.id.errorText) }
    private val userEditText : EditText by lazy { findViewById(R.id.username) }
    private val passEditText : EditText by lazy { findViewById(R.id.password) }
    private val loginButton: Button by lazy { findViewById(R.id.login) }
    private val recyclerView: RecyclerView by lazy { findViewById(R.id.recyclerView)}
    private val progressBar: ProgressBar by lazy { findViewById(R.id.loading)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)
        subscribeObservers()
        loginButton.setOnClickListener {
            val user = User(userEditText.text.toString(),passEditText.text.toString())
            viewModel.emitIntentWithUser(MainIntent.AddUser, user)
        }
        viewModel.emitIntent(MainIntent.GetUserEvents)
    }


    private fun subscribeObservers() {
        viewModel.totalUserDataState.observe(this, Observer { dataState ->
            when (dataState) {
                is DataState.Success<List<User>> -> {
                    progressBar.visibility = View.GONE
                    errorText.visibility = View.GONE
                    recyclerView.layoutManager = LinearLayoutManager(this)
                    recyclerView.adapter = MyAdapter(dataState.data.toTypedArray())
                }
                
                is DataState.Error -> {
                    progressBar.visibility = View.GONE
                    errorText.text = dataState.exception.message
                    errorText.visibility = View.VISIBLE
                }

                is DataState.Loading -> {
                    progressBar.visibility = View.VISIBLE
                    errorText.visibility = View.GONE
                }
            }
        })
        
        viewModel.addedToDb.observe(this, Observer { dataState ->
            when (dataState) {
                is DataState.Success<Long> -> {
                    progressBar.visibility = View.GONE
                    errorText.visibility = View.GONE
                    Toast.makeText(this, "Added user!", Toast.LENGTH_SHORT).show()
                    viewModel.emitIntent(MainIntent.GetUserEvents)
                }

                is DataState.Error -> {
                    progressBar.visibility = View.GONE
                    errorText.text = dataState.exception.message
                    errorText.visibility = View.VISIBLE
                }

                is DataState.Loading -> {
                    progressBar.visibility = View.VISIBLE
                    errorText.visibility = View.GONE
                }
            }
        })
    }

    private fun displayError(message: String){}

    companion object {
        private const val TAG = "LoginActivity"
    }

}