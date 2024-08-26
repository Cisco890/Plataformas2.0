package com.example.lab7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.lab7.ui.theme.Lab7Theme
import com.example.lab7.ui.theme.PaleGreenColor
import com.example.lab7.ui.theme.TopGreenColor
import com.example.lab7.ui.theme.selectedColor
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random

enum class NotificationType {
    GENERAL,
    NEW_POST,
    NEW_MESSAGE,
    NEW_LIKE
}

data class Notification(
    val id: Int,
    val title: String,
    val body: String,
    val sendAt: Date,
    val type: NotificationType
)

fun generateRandomDate(): Date {
    val calendar = Calendar.getInstance()
    val now = Calendar.getInstance()
    calendar.timeInMillis = now.timeInMillis - Random.nextLong(0, 10 * 24 * 60 * 60 * 1000L) // Entre 0 y 10 días atrás
    calendar.set(Calendar.HOUR_OF_DAY, Random.nextInt(0, 24))
    calendar.set(Calendar.MINUTE, Random.nextInt(0, 60))
    calendar.set(Calendar.SECOND, Random.nextInt(0, 60))
    return calendar.time
}

fun generateFakeNotifications(): List<Notification> {
    val notifications = mutableListOf<Notification>()
    val titles = listOf(
        "Nueva versión disponible",
        "Nuevo post de Juan",
        "Mensaje de Maria",
        "Te ha gustado una publicación"
    )
    val bodies = listOf(
        "La aplicación ha sido actualizada a v1.0.2. Ve a la PlayStore y actualízala!",
        "Te han etiquetado en un nuevo post. ¡Míralo ahora!",
        "No te olvides de asistir a esta capacitación mañana, a las 6pm, en el Intecap.",
        "A Juan le ha gustado tu publicación. ¡Revisa tu perfil!"
    )
    val types = NotificationType.entries.toTypedArray()

    for (i in 1..50) {
        notifications.add(
            Notification(
                id = i,
                title = titles.random(),
                body = bodies.random(),
                sendAt = generateRandomDate(),
                type = types.random()
            )
        )
    }
    return notifications
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationAppBar(onBackClick: () -> Unit) {
    TopAppBar(
        title = { Text(text = "Notificaciones", color = Color.White) },
        navigationIcon = {
            IconButton(onClick = { onBackClick() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = TopGreenColor)
    )
}

@Composable
fun NotificationFilter(
    selectedFilter: NotificationType,
    onFilterSelected: (NotificationType) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Tipos de notificaciones",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleMedium,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(listOf(
                "General" to NotificationType.GENERAL,
                "Nueva publicación" to NotificationType.NEW_POST,
                "Mensajes" to NotificationType.NEW_MESSAGE,
                "Likes" to NotificationType.NEW_LIKE
            )) { (text, type) ->
                FilterChip(
                    selected = type == selectedFilter,
                    onClick = { onFilterSelected(type) },
                    label = { Text(text = text, color = Color.Black) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = if (isSystemInDarkTheme()) {
                            Color.White
                        } else {
                            selectedColor
                        },
                    ),
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}

@Composable
fun NotificationItem(notification: Notification) {
    val backgroundColor = if (isSystemInDarkTheme()) {
        Color(0xFF2D2D2D)
    } else {
        PaleGreenColor
    }
    val iconBackgroundColor = when (notification.type) {
        NotificationType.GENERAL -> Color(0xFFFFF9C4)
        NotificationType.NEW_POST -> Color(0xFFBBDEFB)
        NotificationType.NEW_MESSAGE -> Color(0xFFB2EBF2)
        NotificationType.NEW_LIKE -> Color(0xFFE1BEE7)
    }
    val icon = when (notification.type) {
        NotificationType.GENERAL -> Icons.Default.Notifications
        NotificationType.NEW_POST -> Icons.Default.Info
        NotificationType.NEW_MESSAGE -> Icons.Default.Email
        NotificationType.NEW_LIKE -> Icons.Default.ThumbUp
    }

    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val formattedDate = dateFormat.format(notification.sendAt)

    Card(
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 0.dp)
    ) {
        Box(modifier = Modifier.padding(16.dp)) {
            Text(
                text = formattedDate,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(bottom = 8.dp)
            )
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .padding(end = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier
                            .size(40.dp)
                            .background(color = iconBackgroundColor, shape = CircleShape)
                            .padding(8.dp)
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Column {
                    Text(
                        text = notification.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = notification.body,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

@Composable
fun NotificationList(
    notifications: List<Notification>,
    selectedFilter: NotificationType
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(notifications.filter { it.type == selectedFilter }) { notification ->
            NotificationItem(notification)
        }
    }
}

@Composable
fun NotificationScreen(innerPadding: PaddingValues, onBackClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PaleGreenColor)
            .padding(innerPadding)
    ) {
        NotificationAppBar(onBackClick = onBackClick)
        var selectedFilter by remember { mutableStateOf(NotificationType.GENERAL) }
        val notifications = generateFakeNotifications()

        NotificationFilter(
            selectedFilter = selectedFilter,
            onFilterSelected = { filter -> selectedFilter = filter }
        )
        NotificationList(
            notifications = notifications,
            selectedFilter = selectedFilter
        )
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab7Theme {
                Scaffold { innerPadding ->
                    NotificationScreen(
                        innerPadding = innerPadding,
                        onBackClick = { /* Handle back navigation */ }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotificationScreenPreview() {
    Lab7Theme {
        NotificationScreen(
            innerPadding = PaddingValues(),
            onBackClick = {}
        )
    }
}
