package com.example.blogapp.presentation.screens.update_post.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.example.blogapp.R
import com.example.blogapp.presentation.components.DefaulttextField
import com.example.blogapp.presentation.components.DialogCapturePicture
import com.example.blogapp.presentation.screens.update_post.UpdatePostViewModel
import com.example.blogapp.presentation.ui.theme.Red500

@Composable
fun UpdatePostContent(viewModel: UpdatePostViewModel = hiltViewModel()){
    val state = viewModel.state
    viewModel.resultingActivityHandler.handle()

    var dialogState =  remember{ mutableStateOf(false) }

    DialogCapturePicture(
        status = dialogState,
        takePhoto = { viewModel.takePhoto() },
        pickImage = { viewModel.pickImage() }
    )


    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(),

            ) {
            Box(
                modifier = Modifier
                    .padding(top = 80.dp)
                    .fillMaxWidth()
                    .height(170.dp)
                    .background(Red500)

            ){
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    if(viewModel.state.image != ""){
                        AsyncImage(
                            modifier = Modifier
                                .padding(top = 80.dp)
                                .fillMaxWidth()
                                .height(170.dp)
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
                            painter = painterResource(id = R.drawable.add_image),
                            contentDescription = "imagen usuario"
                        )
                        Text(
                            text = "SELECCIONA UNA IMAGEN",
                            fontSize = 19.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        DefaulttextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 25.dp, start = 20.dp, end = 20.dp),
            value = state.name,
            onValueChanged = {
                viewModel.onNameInput(it)
            },
            label = "Nombre del Juego",
            icon = Icons.Default.Face,
            errorMsg = "",
            validateField = {}
        )

        DefaulttextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 0.dp, start = 20.dp, end = 20.dp),
            value = state.description,
            onValueChanged = {
                viewModel.onDescriptionInput(it)
            },
            label = "Descripcion",
            icon = Icons.Default.List,
            errorMsg = "",
            validateField = {}
        )
        Text(
            modifier = Modifier.padding(vertical = 15.dp),
            text = "CATEGORIAS",
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
        )
        viewModel.radioOptions.forEach { option ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .selectable(
                        selected =(option.category == state.category),
                        onClick = {
                            viewModel.onCategoryInput(option.category)
                        }
                    ),
                verticalAlignment = Alignment.CenterVertically
            ){
                RadioButton(
                    selected = (option.category == state.category),
                    onClick = {
                        viewModel.onCategoryInput(option.category)
                    }
                )
                Row() {
                    Text(
                        modifier = Modifier.width(110.dp).padding(12.dp),
                        text = option.category
                    )
                    Image(
                        modifier = Modifier.height(50.dp).padding(8.dp),
                        painter = painterResource(id = option.image),
                        contentDescription = ""
                    )
                }
            }
        }
    }
}