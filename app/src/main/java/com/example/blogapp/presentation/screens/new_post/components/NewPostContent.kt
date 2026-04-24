package com.example.blogapp.presentation.screens.new_post.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blogapp.R
import com.example.blogapp.presentation.components.DefaulttextField
import com.example.blogapp.presentation.ui.theme.Red500


data class CategoryRadioButton(
    var category: String,
    var image : Int
)


@Composable
fun NewPostContent(){

    var radioOptions = listOf(
        CategoryRadioButton("PC", R.drawable.icon_pc),
        CategoryRadioButton("PS4", R.drawable.icon_ps4),
        CategoryRadioButton("XBOX", R.drawable.icon_xbox),
        CategoryRadioButton("NINTENDO", R.drawable.icon_nintendo)
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
                    Image(
                        modifier = Modifier
                            .height(120.dp)
                            .padding(top = 20.dp),
                        painter = painterResource(id = R.drawable.add_image),
                        contentDescription = "Control Xbox"
                    )
                    Text(
                        text = "SELECCIONA UNA IMAGEN",
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        DefaulttextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 25.dp, start = 20.dp, end = 20.dp),
            value = "",
            onValueChanged = {},
            label = "Nombre del Juego",
            icon = Icons.Default.Face,
            errorMsg = "",
            validateField = {}
        )

        DefaulttextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 0.dp, start = 20.dp, end = 20.dp),
            value = "",
            onValueChanged = {},
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
        radioOptions.forEach { option ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .selectable(
                        selected =false,
                        onClick = {}
                    ),
                verticalAlignment = Alignment.CenterVertically
            ){
                RadioButton(
                    selected = false,
                    onClick = {}
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