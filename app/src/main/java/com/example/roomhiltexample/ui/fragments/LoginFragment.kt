package com.example.roomhiltexample.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
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
class LoginFragment : Fragment() {

    val viewModel: LoginViewModel by viewModels()
    private lateinit var fragmentView : View
    private val errorText: TextView by lazy { fragmentView.findViewById(R.id.errorText)}
    private val userEditText : EditText by lazy { fragmentView.findViewById(R.id.username) }
    private val passEditText : EditText by lazy { fragmentView.findViewById(R.id.password) }
    private val loginButton: Button by lazy { fragmentView.findViewById(R.id.login) }
    private val recyclerView: RecyclerView by lazy { fragmentView.findViewById(R.id.recyclerView)}
    private val progressBar: ProgressBar by lazy { fragmentView.findViewById(R.id.loading)}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_login, container, false)
        return fragmentView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        subscribeObservers()
        loginButton.setOnClickListener {
            val user = User(userEditText.text.toString(),passEditText.text.toString())
            viewModel.emitIntentWithUser(MainIntent.AddUser, user)
        }
        viewModel.emitIntent(MainIntent.GetUserEvents)
    }


    private fun subscribeObservers() {
        viewModel.totalUserDataState.observe(viewLifecycleOwner, Observer { dataState ->
            when (dataState) {
                is DataState.Success<List<User>> -> {
                    progressBar.visibility = View.GONE
                    errorText.visibility = View.GONE
                    recyclerView.layoutManager = LinearLayoutManager(context)
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

        viewModel.addedToDb.observe(viewLifecycleOwner, Observer { dataState ->
            when (dataState) {
                is DataState.Success<Long> -> {
                    progressBar.visibility = View.GONE
                    errorText.visibility = View.GONE
                    Toast.makeText(context, "Added user!", Toast.LENGTH_SHORT).show()
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