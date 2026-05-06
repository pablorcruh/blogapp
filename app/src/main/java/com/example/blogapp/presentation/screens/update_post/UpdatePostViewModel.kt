package com.example.blogapp.presentation.screens.update_post

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blogapp.R
import com.example.blogapp.domain.model.Post
import com.example.blogapp.domain.model.Response
import com.example.blogapp.domain.usecases.auth.AuthUseCases
import com.example.blogapp.domain.usecases.posts.PostsUseCase
import com.example.blogapp.presentation.utils.ComposeFileProvider
import com.example.blogapp.presentation.utils.ResultingActivityHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class UpdatePostViewModel @Inject constructor(
    private val postUseCase: PostsUseCase,
    private val authUseCase: AuthUseCases,
    private val savedStateHandle: SavedStateHandle,
    @ApplicationContext private val context: Context
) : ViewModel(){

    val data = savedStateHandle.get<String>("post")
    val post = Post.fromJson(data!!)

    var state by mutableStateOf(UpdatePostState())
        private set

    var updatePostResponse by mutableStateOf<Response<Boolean>?>(null)

    var file: File? = null

    val resultingActivityHandler = ResultingActivityHandler()

    val user = authUseCase.getCurrentUser()

    init{
        state = state.copy(
            name = post.name,
            description = post.description,
            image = post.image,
            category = post.category
        )
    }

    fun pickImage() = viewModelScope.launch {
        val result = resultingActivityHandler.getContent("image/*")
        if(result != null){
            file = ComposeFileProvider.createFileFromUri(context, result)
            state = state.copy(image = result.toString())
        }
    }

    fun takePhoto() = viewModelScope.launch {
        val result = resultingActivityHandler.takePicturePreview()
        if(result !=null){
            state= state.copy(image = ComposeFileProvider.getPathFromBitmap(context, result))
            file = File(state.image)
        }
    }

    var radioOptions = listOf(
        CategoryRadioButton("PC", R.drawable.icon_pc),
        CategoryRadioButton("PS4", R.drawable.icon_ps4),
        CategoryRadioButton("XBOX", R.drawable.icon_xbox),
        CategoryRadioButton("NINTENDO", R.drawable.icon_nintendo)
    )

    fun UpdatePost(post: Post) = viewModelScope.launch {
        updatePostResponse = Response.Loading
        val result = postUseCase.updatePostUseCase.invoke(post, file)
        updatePostResponse = result
    }

    fun onUpdatePost(){
        val post = Post(
            id = post.id,
            name = state.name,
            description = state.description,
            image = post.image,
            category = state.category,
            idUser = user?.uid ?: ""
        )
        UpdatePost(post)
    }


    fun clearForm(){
        /*state = state.copy(
            name = "",
            category = "",
            description = "",
            image = ""
        )*/

        updatePostResponse = null
    }

    fun onNameInput(name: String){
        state = state.copy(name = name)
    }

    fun onImageInput(image: String){
        state = state.copy(image = image)
    }

    fun onDescriptionInput(description: String){
        state = state.copy(description = description)
    }

    fun onCategoryInput(category: String){
        state = state.copy(category = category)
    }
}

data class CategoryRadioButton(
    var category: String,
    var image : Int
)