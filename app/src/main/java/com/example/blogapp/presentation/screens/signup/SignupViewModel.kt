package com.example.blogapp.presentation.screens.signup

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blogapp.domain.model.Response
import com.example.blogapp.domain.model.User
import com.example.blogapp.domain.usecases.auth.AuthUseCases
import com.example.blogapp.domain.usecases.users.UsersUseCase
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val authUsecase: AuthUseCases,
    private val usersUseCase: UsersUseCase
): ViewModel() {

    // state form
    var state by mutableStateOf(SignupState())

    var isValidUsername by mutableStateOf(false)
        private set

    var usernameErrorMsg by mutableStateOf("")
        private set

    var isValidEmail by mutableStateOf(false)
        private set

    var emailErrorMsg by mutableStateOf("")
        private set

    var isValidPassword by mutableStateOf(false)
        private set

    var passwordErrorMsg by mutableStateOf("")
        private set

    var isconfirmPassword by mutableStateOf(false)
        private set

    var confirmPasswordErrorMsg by mutableStateOf("")
        private set

    var isEnabledRegisterButton = false

    var user = User()

    fun onSignup(){
        user.username = state.username
        user.email = state.email
        user.password = state.password
        signup(user)
    }

    fun onEmailInput(email: String){
        state = state.copy(email = email)
    }

    fun onUsernameInput(username: String){
        state = state.copy(username = username)
    }

    fun onPasswordInput(password: String){
        state = state.copy(password = password)
    }

    fun onConfirmPasswordInput(confirmPassword: String){
        state = state.copy(confirmPassword= confirmPassword)
    }

    var signupResponse by mutableStateOf<Response<FirebaseUser>?>(null)
        private set

    fun createUser() = viewModelScope.launch {
        user.id = authUsecase.getCurrentUser()!!.uid
        usersUseCase.createUser(user)
    }

    fun signup(user: User) = viewModelScope.launch {
        signupResponse = Response.Loading
        val result = authUsecase.signup(user)
        signupResponse = result
    }

    fun enabledRegisterButton(){
        isEnabledRegisterButton =
                isValidEmail &&
                isValidPassword &&
                isValidUsername &&
                isconfirmPassword &&
                isValidUsername
    }

    fun validateConfirmPassword(){
        if(state.password == state.confirmPassword){
            isconfirmPassword = true
            confirmPasswordErrorMsg = ""
        }else{
            isconfirmPassword = false
            confirmPasswordErrorMsg = "confirm password not match"
        }
        enabledRegisterButton()
    }

    fun validateUsername(){
        if(state.username.length >=5){
            isValidUsername = true
            usernameErrorMsg = ""
        }else{
            isValidUsername = false
            usernameErrorMsg =  "at least 5 characters"
        }
        enabledRegisterButton()
    }


    fun validateEmail(){
        if(Patterns.EMAIL_ADDRESS.matcher(state.email).matches()){
            isValidEmail = true
            emailErrorMsg = ""
        }else {
            isValidEmail = false
            emailErrorMsg = "Email is not valid"
        }
        enabledRegisterButton()
    }


    fun validatePassword(){
        if(state.password.length >= 6){
            isValidPassword = true
            passwordErrorMsg = ""
        }else {
            isValidPassword = false
            passwordErrorMsg = "Password not long enought"
        }
        enabledRegisterButton()
    }
}