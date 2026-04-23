package com.example.blogapp.presentation.components

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button // Assuming you might want to use Material3 Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogCapturePicture(
    status: MutableState<Boolean>,
    takePhoto: () -> Unit,
    pickImage: () -> Unit
){

    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current

    val pickMediaLauncher: ActivityResultLauncher<PickVisualMediaRequest> =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia()) { uri: Uri? ->
            if (uri != null) {
                // User successfully picked an image
                Log.d("ImagePicker", "Image URI: $uri")
                selectedImageUri = uri
                // Now you can use this URI to load the image, upload it, etc.
            } else {
                // User cancelled the picker or an error occurred
                Log.d("ImagePicker", "No media selected (RESULT_CANCELED or error)")
                // Optionally, show a message to the user, or simply do nothing.
                // Toast.makeText(context, "No image selected", Toast.LENGTH_SHORT).show()
            }
        }

    if (status.value) {
        AlertDialog(
            onDismissRequest = { status.value = false },
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            properties = DialogProperties(
                dismissOnBackPress = true, // Default is true
                dismissOnClickOutside = true // Default is true
            ),
            content = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 30.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        modifier = Modifier.width(130.dp),
                        onClick = {
                            status.value = false
                            pickMediaLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                            pickImage()
                        }
                    ) {
                        Text(text = "Galeria")
                    }
                    Button(
                        modifier = Modifier.width(130.dp),
                        onClick = {
                            status.value = false
                            takePhoto()
                        }
                    ) {
                        Text(text = "Camera")
                    }
                }
            },
        )
    }
}
