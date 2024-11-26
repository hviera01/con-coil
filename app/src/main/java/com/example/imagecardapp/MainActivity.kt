package com.example.imagecardapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.imagecardapp.ui.theme.ImageCardAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImageCardAppTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    Scaffold(
        topBar = { CustomAppBar() },
        content = { padding ->
            PostListScreen(Modifier.padding(padding))
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAppBar() {
    TopAppBar(
        title = {
            Text(
                text = "ImageCardApp",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimary
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color(0xFF2196F3)
        )
    )
}

@Composable
fun PostListScreen(modifier: Modifier = Modifier) {
    val posts = listOf(
        Post(
            username = "naturelover",
            profileImageRes = R.drawable.loguito,
            postImageRes = R.drawable.card_ejemplo,
            description = "Ofrecemos rica comida"

        ),
        Post(
            username = "Antojitos la sorda",
            profileImageRes = R.drawable.logo2,
            postImageRes = R.drawable.card_ejemplo1,
            description = "Escuchamos pedidos."
        )
    )

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(posts) { post ->
            RedesignedPostCard(post = post)
        }
    }
}

data class Post(
    val username: String,
    val profileImageRes: Int,
    val postImageRes: Int,
    val description: String
)

@Composable
fun RedesignedPostCard(post: Post) {
    ElevatedCard(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color(0xFFFFF59D) // Amarillo pastel
        ),
        elevation = CardDefaults.elevatedCardElevation(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            HeaderSection(username = post.username, profileImageRes = post.profileImageRes)
            PostImage(imageRes = post.postImageRes)
            InteractionRow()
            DescriptionSection(description = post.description)
        }
    }
}

@Composable
fun HeaderSection(username: String, profileImageRes: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(painter = painterResource(id = profileImageRes), contentDescription = null, modifier = Modifier
                .size(50.dp)
                .clip(CircleShape))
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = username,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
fun PostImage(imageRes: Int) {
    Image(
        painter = painterResource(id = imageRes),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun InteractionRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            IconButton(onClick = { /* Like */ }) {
                Icon(Icons.Outlined.FavoriteBorder, contentDescription = "Like")
            }
            IconButton(onClick = { /* Comment */ }) {
                Icon(Icons.Outlined.Add, contentDescription = "Comment")
            }
            IconButton(onClick = { /* Share */ }) {
                Icon(Icons.Outlined.Share, contentDescription = "Share")
            }
        }
        IconButton(onClick = { /* Save */ }) {
            Icon(Icons.Outlined.Check, contentDescription = "Save")
        }
    }
}

@Composable
fun DescriptionSection(description: String) {
    Text(
        text = description,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(8.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    ImageCardAppTheme {
        MainScreen()
    }
}