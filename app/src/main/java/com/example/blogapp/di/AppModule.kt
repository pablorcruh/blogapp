package com.example.blogapp.di

import com.example.blogapp.core.Constants.USERS
import com.example.blogapp.data.repository.AuthRepositoryImpl
import com.example.blogapp.data.repository.UserRepositoryImpl
import com.example.blogapp.domain.repository.AuthRepository
import com.example.blogapp.domain.repository.UsersRepository
import com.example.blogapp.domain.usecases.auth.AuthUseCases
import com.example.blogapp.domain.usecases.auth.GetCurrentUserUseCase
import com.example.blogapp.domain.usecases.auth.LoginUseCase
import com.example.blogapp.domain.usecases.auth.LogoutUseCase
import com.example.blogapp.domain.usecases.auth.SignupUseCase
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

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore = Firebase.firestore

    @Provides
    fun provideFirebaseStorage(): FirebaseStorage = FirebaseStorage.getInstance()

    @Provides
    fun provideStorageUsersRef(storage: FirebaseStorage): StorageReference = storage.reference.child(USERS)

    @Provides
    fun provideUsersRef(db: FirebaseFirestore): CollectionReference = db.collection(USERS)

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl

    @Provides
    fun provideUsersRepository(impl: UserRepositoryImpl): UsersRepository = impl

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

}