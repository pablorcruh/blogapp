package com.example.blogapp.data.repository

import android.net.Uri
import com.example.blogapp.core.Constants.POSTS
import com.example.blogapp.core.Constants.USERS
import com.example.blogapp.domain.model.Post
import com.example.blogapp.domain.model.Response
import com.example.blogapp.domain.model.User
import com.example.blogapp.domain.repository.PostRepository
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.io.File
import javax.inject.Inject
import javax.inject.Named

class PostRepositoryImpl @Inject constructor(
    @Named(USERS)private val usersRef: CollectionReference,
    @Named(POSTS) private val postRef: CollectionReference,
    @Named(POSTS) private val storagePostRef: StorageReference
): PostRepository {
    override fun getPosts(): Flow<Response<List<Post>>> = callbackFlow{
        val snapshotListener = postRef.addSnapshotListener { snapshots, exception ->
            GlobalScope.launch(Dispatchers.IO) {
                val postResponse = if(snapshots !=null){
                    val posts = snapshots.toObjects(Post::class.java)
                    val idUserArray = ArrayList<String>()
                    posts.forEach { post ->
                        idUserArray.add(post.idUser)
                    }
                    val idUserList = idUserArray.toSet().toList()

                    idUserList.map{idUser ->
                        async{
                            val user = usersRef.document(idUser).get().await().toObject(User::class.java)!!
                            posts.forEach { post ->
                                if(post.idUser == idUser){
                                    post.user = user
                                }
                            }
                        }
                    }.forEach {
                        it.await()
                    }
                    Response.Success(posts)
                }else{
                    Response.Failure(exception)
                }
                trySend(postResponse)
            }
        }
        awaitClose{
            snapshotListener.remove()
        }
    }

    override fun getPostsByUserId(idUser: String): Flow<Response<List<Post>>> = callbackFlow {
        val snapshotListener = postRef.whereEqualTo("idUser", idUser).addSnapshotListener { snapshot, exception ->
            val postResponse = if(snapshot != null){
                val posts = snapshot.toObjects(Post::class.java)
                Response.Success(posts)
            }else {
                Response.Failure(exception)
            }
            trySend(postResponse)
        }
        awaitClose{
            snapshotListener.remove()
        }
    }

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
