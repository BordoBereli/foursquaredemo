package com.kutluoglu.foursquaredemo.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.kutluoglu.foursquaredemo.R
import com.kutluoglu.foursquaredemo.base.BaseActivity
import com.kutluoglu.foursquaredemo.main.features.venueList.ItemListActivity
import com.kutluoglu.presentation.models.login.LoggedInUser
import com.kutluoglu.presentation.resource.ResourceState
import com.kutluoglu.presentation.viewModels.login.LoginViewModel
import dagger.android.AndroidInjection

class LoginActivity : BaseActivity() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)
        AndroidInjection.inject(this)

        val username = findViewById<EditText>(R.id.username)
        val login = findViewById<Button>(R.id.login)

        loginViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(LoginViewModel::class.java)

        observeLoginLiveData()
        login.isEnabled = true

        username.apply {
            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.login(
                                username.text.toString()
                        )
                }
                false
            }

            login.setOnClickListener {
                loginViewModel.login(username.text.toString())
            }
        }

    }

    private fun observeLoginLiveData() {
        loginViewModel.getLoginLiveData().observe(this, Observer { resource ->
            if(resource != null) {
                when (resource.status) {
                    ResourceState.LOADING -> {
                        showSpinner()
                    }

                    ResourceState.SUCCESS -> {
                        dismissSpinner()
                        if (resource.data != null) {
                            Log.e("Data", resource.data!!.toString())
                            updateUiWithUser(resource.data)
                            loginViewModel.loginSuccess()
                            goToVenueListActivity()
                        }
                    }

                    ResourceState.ERROR -> {
                        dismissSpinner()
                        resource.message?.let { showLoginFailed(R.string.error_login_message) }
                    }
                }
            }
        })

//        loginViewModel.login("543332126")
    }

    private fun goToVenueListActivity() {
        startActivity(Intent(this, ItemListActivity::class.java))
        finish()
    }

    private fun updateUiWithUser(model: LoggedInUser?) {
        val welcome = getString(R.string.welcome)
        val displayName = model?.displayName
        Toast.makeText(
                applicationContext,
                "$welcome $displayName",
                Toast.LENGTH_LONG
        ).show()
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}
