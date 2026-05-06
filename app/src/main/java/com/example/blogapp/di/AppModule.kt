package com.example.blogapp.di

import com.example.blogapp.core.Constants.POSTS
import com.example.blogapp.core.Constants.USERS
import com.example.blogapp.data.repository.AuthRepositoryImpl
import com.example.blogapp.data.repository.PostRepositoryImpl
import com.example.blogapp.data.repository.UserRepositoryImpl
import com.example.blogapp.domain.repository.AuthRepository
import com.example.blogapp.domain.repository.PostRepository
import com.example.blogapp.domain.repository.UsersRepository
import com.example.blogapp.domain.usecases.auth.AuthUseCases
import com.example.blogapp.domain.usecases.auth.GetCurrentUserUseCase
import com.example.blogapp.domain.usecases.auth.LoginUseCase
import com.example.blogapp.domain.usecases.auth.LogoutUseCase
import com.example.blogapp.domain.usecases.auth.SignupUseCase
import com.example.blogapp.domain.usecases.posts.CreatePostUseCase
import com.example.blogapp.domain.usecases.posts.DeletePostUseCase
import com.example.blogapp.domain.usecases.posts.GetPostsByUserIdUseCase
import com.example.blogapp.domain.usecases.posts.GetPostsUseCase
import com.example.blogapp.domain.usecases.posts.PostsUseCase
import com.example.blogapp.domain.usecases.users.CreateUserUseCase
import com.example.blogapp.domain.usecases.users.GetUserByIdUseCase
import com.example.blogapp.domain.usecases.users.SaveImageUseCase
import com.example.blogapp.domain.usecases.users.UpdateUserUseCase
import com.example.blogapp.domain.usecases.users.UsersUseCase
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore = Firebase.firestore

    @Provides
    fun provideFirebaseStorage(): FirebaseStorage = FirebaseStorage.getInstance()

    @Provides
    @Named(USERS)
    fun provideStorageUsersRef(storage: FirebaseStorage): StorageReference = storage.reference.child(USERS)

    @Provides
    @Named(USERS)
    fun provideUsersRef(db: FirebaseFirestore): CollectionReference = db.collection(USERS)

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl

    @Provides
    fun provideUsersRepository(impl: UserRepositoryImpl): UsersRepository = impl


    @Provides
    @Named(POSTS)
    fun provideStoragePostsRef(storage: FirebaseStorage): StorageReference= storage.reference.child(POSTS)

    @Provides
    @Named(POSTS)
    fun providePostRef(db: FirebaseFirestore): CollectionReference = db.collection(POSTS)

    @Provides
    fun providePostRepository(impl: PostRepositoryImpl): PostRepository = impl

    @Provides
    fun provideAuthUseCases(repository: AuthRepository) = AuthUseCases(
        getCurrentUser = GetCurrentUserUseCase(repository),
        login = LoginUseCase(repository),
        logout = LogoutUseCase(repository),
        signup = SignupUseCase(repository)
    )

    @Provides
    fun provideUsersUseCase(repository: UsersRepository) = UsersUseCase(
        createUser = CreateUserUseCase(repository),
        getUserById = GetUserByIdUseCase(repository),
        updateUser = UpdateUserUseCase(repository),
        saveImage = SaveImageUseCase(repository)
    )

    @Provides
    fun providePostUseCase(repository: PostRepository) = PostsUseCase(
        createPost = CreatePostUseCase(repository),
        getPosts = GetPostsUseCase(repository),
        getPostByUserId = GetPostsByUserIdUseCase(repository),
        deletePostUseCase = DeletePostUseCase(repository)
    )

}