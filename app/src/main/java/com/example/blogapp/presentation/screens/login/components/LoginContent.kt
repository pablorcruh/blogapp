package com.example.blogapp.presentation.screens.login.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.blogapp.R
import com.example.blogapp.presentation.components.DefaultButton
import com.example.blogapp.presentation.components.DefaulttextField
import com.example.blogapp.presentation.screens.login.LoginViewModel
import com.example.blogapp.presentation.ui.theme.DarkGray500
import com.example.blogapp.presentation.ui.theme.Red500

@Composable
fun LoginContent (paddingValues: PaddingValues, navController: NavHostController,viewModel: LoginViewModel= hiltViewModel()){

    var state = viewModel.state

    Box(
        modifier = Modifier
            .padding(paddingValues = paddingValues)
            .fillMaxWidth(),

    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
                .background(Red500)

        ){
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Image(
                    modifier = Modifier.height(130.dp),
                    painter = painterResource(id = R.drawable.control),
                    contentDescription = "Control Xbox"
                )
                Text(
                    text = "FIREBASE MVVM"
                )
            }
        }
        Card(modifier = Modifier
            .padding(start = 40.dp, end =40.dp, top = 200.dp)
            .background(DarkGray500)
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 20.dp)
            ){
                Text(
                    modifier = Modifier.padding(
                        top = 40.dp,
                        bottom = 0.dp,
                        start = 0.dp,
                        end = 0.dp),
                    text ="Login",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier
                    .height(10.dp))
                Text(
                    text ="Por favor inicia Sesion para continuar",
                    fontSize = 12.sp,
                    color = Color.Gray,

                    )
                DefaulttextField(
                    modifier = Modifier.padding(top = 25.dp),
                    value = state.email,
                    onValueChanged = { viewModel.onEmailInput(it)},
                    label = "Email",
                    icon = Icons.Default.Email,
                    keyboardType = KeyboardType.Email,
                    errorMsg = viewModel.emailErrorMsg,
                    validateField = { viewModel.validateEmail()}
                )
                DefaulttextField(
                    modifier = Modifier.padding(top = 0.dp),
                    value = state.password,
                    onValueChanged = { viewModel.onPasswordInput(it)},
                    label = "Password",
                    icon = Icons.Default.Lock,
                    hideText = true,
                    errorMsg = viewModel.passwordErrorMsg,
                    validateField = { viewModel.validatePassword()}
                )

                DefaultButton(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 40.dp),
                    text = "Iniciar Sesion",
                    onClick = {
                       viewModel.login()

                    },
                    enabled = viewModel.isEnabledLoginButton
                )

            }
        }
    }

}
