import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.navigation.NavController
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.LaunchedEffect
import androidx.activity.compose.BackHandler
import androidx.compose.material3.ExperimentalMaterial3Api
import com.example.lab8.model.Character

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharactersScreen(navController: NavController, characters: List<Character>) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Characters") })
        }
    ) { innerPadding ->
        LazyColumn(
            contentPadding = innerPadding,
        ) {
            items(characters) { character ->
                CharacterItem(character) {
                    navController.navigate("characterDetails/${character.id}")
                }
            }
        }
    }
}



