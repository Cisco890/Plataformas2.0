import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Alignment
import com.example.lab8.model.Character

@Composable
fun CharacterItem(character: Character, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Círculo coloreado
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .background(Color.Gray, shape = CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = character.name, style = MaterialTheme.typography.titleMedium)
                Text(text = "Species: ${character.species}", style = MaterialTheme.typography.bodySmall)
                Text(text = "Status: ${character.status}", style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

