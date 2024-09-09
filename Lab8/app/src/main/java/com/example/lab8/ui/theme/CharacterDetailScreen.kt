import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lab8.model.Character
import com.example.lab8.ui.theme.data.CharacterDb


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailsScreen(navController: NavController, characterId: Int) {
    val character = getCharacterById(characterId)
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Character details") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Text(text = "Species: ${character.species}")
            Text(text = "Status: ${character.status}")
            Text(text = "Gender: ${character.gender}")
        }
    }
}
private val characterDb = CharacterDb()

fun getCharacterById(id: Int): Character {
    return characterDb.getCharacterById(id)
}

