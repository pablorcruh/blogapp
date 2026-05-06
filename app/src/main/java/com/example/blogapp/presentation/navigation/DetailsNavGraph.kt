package com.example.blogapp.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.blogapp.presentation.screens.detail_post.DetailPostScreen
import com.example.blogapp.presentation.screens.new_post.NewPostScreen
import com.example.blogapp.presentation.screens.profile_edit.ProfileEditSreen
import com.example.blogapp.presentation.screens.update_post.UpdatePostScreen

fun NavGraphBuilder.detailsNavGraph(navController: NavHostController){
    navigation(
        route = Graph.DETAILS,
        startDestination = DetailsScreen.ProfileEdit.route
    ){

        composable(route = DetailsScreen.NewPost.route){
            NewPostScreen(navController)
        }

        composable(
            route = DetailsScreen.ProfileEdit.route,
            arguments = listOf(navArgument("user"){
                type = NavType.StringType
            })
        ){
            it.arguments?.getString("user")?.let{ user ->
                ProfileEditSreen(navController)
            }
        }

        composable(
            route = DetailsScreen.PostDetail.route,
            arguments = listOf(navArgument("post"){
                type = NavType.StringType
            })
        ){
            it.arguments?.getString("post")?.let {
                DetailPostScreen(navController)
            }
        }

        composable(
            route = DetailsScreen.UpdatePost.route,
            arguments = listOf(navArgument("post"){
                type = NavType.StringType
            })
        ){
            it.arguments?.getString("post")?.let {
                UpdatePostScreen(navController, post=it)
            }
        }
    }
}

sealed class DetailsScreen(val route: String){

    object NewPost: DetailsScreen("post/new")

    object ProfileEdit: DetailsScreen("profile/edit/{user}"){
        fun passUser(user: String) = "profile/edit/${user}"
    }

    object PostDetail: DetailsScreen("posts/detail/{post}"){
        fun passPost(post: String) = "posts/detail/${post}"
    }

    object UpdatePost: DetailsScreen("posts/update/{post}"){
        fun passPost(post: String) = "posts/update/${post}"
    }

}