package com.example.blogapp.data.repository

import android.net.Uri
import com.example.blogapp.core.Constants.POSTS
import com.example.blogapp.domain.model.Post
import com.example.blogapp.domain.model.Response
import com.example.blogapp.domain.repository.PostRepository
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.tasks.await
import java.io.File
import javax.inject.Inject
import javax.inject.Named

class PostRepositoryImpl @Inject constructor(
    @Named(POSTS) private val postRef: CollectionReference,
    @Named(POSTS) private val storagePostRef: StorageReference
): PostRepository {
    override suspend fun create(
        post: Post,
        file: File
    ): Response<Boolean> {
       return try {
            var fromFile = Uri.fromFile(file)
            val ref =  storagePostRef.child(file.name)
            val uploadTask = ref.putFile(fromFile).await()
            val url = ref.downloadUrl.await()
            post.image = url.toString()
            postRef.add(post).await()
            Response.Success(true)
        } catch (e: Exception) {
            e.printStackTrace()
            Response.Failure(e)
        }
    }
}
