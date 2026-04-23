package com.example.blogapp.presentation.screens.profile_edit.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.example.blogapp.R
import com.example.blogapp.presentation.components.DefaultButton
import com.example.blogapp.presentation.components.DefaulttextField
import com.example.blogapp.presentation.components.DialogCapturePicture
import com.example.blogapp.presentation.screens.profile_edit.ProfileEditViewModel
import com.example.blogapp.presentation.ui.theme.DarkGray500
import com.example.blogapp.presentation.ui.theme.Red500

@Composable
fun ProfileEditContent(navController: NavHostController, viewModel: ProfileEditViewModel = hiltViewModel()){

    val state = viewModel.state
    viewModel.resultingActivityHandler.handle()

    var dialogState =  remember{ mutableStateOf(false) }

    DialogCapturePicture(
        status = dialogState,
        takePhoto = { viewModel.takePhoto() },
        pickImage = { viewModel.pickImage() }
    )


    Box(
        modifier = Modifier
            .fillMaxWidth(),
        ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(290.dp)
                .background(Red500)
        ){
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Spacer(modifier = Modifier.height(30.dp))
                if(viewModel.state.image != ""){
                    AsyncImage(
                        modifier = Modifier
                            .padding(top = 80.dp)
                            .height(100.dp)
                            .width(100.dp)
                            .clip(CircleShape)
                            .clickable {
                                dialogState.value = true
                            },
                        model = viewModel.state.image,
                        contentDescription = "image selected",
                        contentScale = ContentScale.Crop
                        )
                }else{
                    Image(
                        modifier = Modifier
                            .padding(top = 80.dp)
                            .height(120.dp)
                            .clickable {
                                dialogState.value = true
                        },
                        painter = painterResource(id = R.drawable.user),
                        contentDescription = "imagen usuario"
                    )
                }
            }
        }

        Card(modifier = Modifier
            .padding(start = 40.dp, end =40.dp, top = 250.dp)
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
                    text ="Actualizacion",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier
                    .height(10.dp))
                Text(
                    text ="Por favor ingresa tus datos para continuar",
                    fontSize = 12.sp,
                    color = Color.Gray,

                    )
                DefaulttextField(
                    modifier = Modifier.padding(top = 25.dp),
                    value = state.username,
                    onValueChanged = { viewModel.onUsernameInput(it)},
                    label = "Nombre de Usuario",
                    icon = Icons.Default.Person,
                    errorMsg = viewModel.usernameErrorMsg,
                    validateField = {
                        viewModel.validateUsername()
                    }
                )


                DefaultButton(
                    modifier = Modifier.fillMaxWidth()
                        .padding(top = 20.dp, bottom = 40.dp),
                    text = "Actualizar Datos",
                    onClick = {
                        viewModel.saveImage()
                    }
                )

            }
        }
    }
}