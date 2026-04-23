package com.example.blogapp.presentation.screens.login

import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blogapp.domain.model.Response
import com.example.blogapp.domain.usecases.auth.AuthUseCases
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authUseCases: AuthUseCases): ViewModel() {

    // state form
    var state by mutableStateOf(LoginState())
        private set

    var isValidEmail: Boolean by mutableStateOf(false)
        private set

    var emailErrorMsg: String by mutableStateOf("")
        private set

    var isValidPassword: Boolean by mutableStateOf(false)
        private set

    var passwordErrorMsg: String by mutableStateOf("")
        private set

    var isEnabledLoginButton = false

    // login response
    var loginResponse by mutableStateOf<Response<FirebaseUser>?>(null)
        private set


    var currentUser = authUseCases.getCurrentUser()
    init {
        if(currentUser != null){
            loginResponse = Response.Success(currentUser!!)
        }
    }


    fun onEmailInput(email: String){
        state = state.copy(email = email)
    }

    fun onPasswordInput(password: String){
        state = state.copy(password = password)
    }


    fun login() = viewModelScope.launch {
        loginResponse = Response.Loading
        val result = authUseCases.login(state.email, state.password)
        loginResponse = result
    }

    fun enabledLoginButton(){
        isEnabledLoginButton = isValidEmail && isValidPassword
    }

    fun validateEmail(){
        if(Patterns.EMAIL_ADDRESS.matcher(state.email).matches()){
            isValidEmail = true
            emailErrorMsg = ""
        }else {
            isValidEmail = false
            emailErrorMsg = "Email is not valid"
        }
        enabledLoginButton()
    }


    fun validatePassword(){
        if(state.password.length >= 6){
            isValidPassword = true
            passwordErrorMsg = ""
        }else {
            isValidPassword= false
            passwordErrorMsg= "Password not long enought"
        }
        enabledLoginButton()
    }

}