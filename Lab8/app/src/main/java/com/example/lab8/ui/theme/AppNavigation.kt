package com.example.lab8.ui.theme

import CharacterDetailsScreen
import CharactersScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.lab8.ui.theme.data.CharacterDb
import com.example.lab8.model.Character

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "login") {
        composable("login") { LoginScreen(navController) }
        composable("characters") {
            CharactersScreen(navController, characters = getCharacterList())
        }
        composable(
            "characterDetails/{characterId}",
            arguments = listOf(navArgument("characterId") { type = NavType.IntType })
        ) { backStackEntry ->
            val characterId = backStackEntry.arguments?.getInt("characterId") ?: 0
            CharacterDetailsScreen(navController, characterId)
        }
    }
}
private val characterDb = CharacterDb()

fun getCharacterList(): List<Character> {
    return characterDb.getAllCharacters()
}
