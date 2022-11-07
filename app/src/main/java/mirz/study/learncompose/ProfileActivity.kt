package mirz.study.learncompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import mirz.study.learncompose.ui.theme.LearnComposeTheme

class ProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearnComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainProfileScreen(userProfileList)
                }
            }
        }
    }
}

@Composable
fun UserProfileDetailScreen(user: UserProfile = userProfileList[0]) {
    Scaffold(topBar = { AppBar() }) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                ProfilePicture(user.pictureUrl, user.status, 240.dp)
                ProfileContent(user.name, user.status, Alignment.CenterHorizontally)
            }
        }
    }
}

@Composable
fun MainProfileScreen(users: List<UserProfile>) {
    Scaffold(topBar = { AppBar() }) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            LazyColumn {
                items(users) { user ->
                    ProfileCard(userProfile = user)
                }
            }
        }
    }
}

@Composable
fun AppBar() {
    TopAppBar(
        navigationIcon = { Icon(Icons.Default.Home, "") },
        title = { Text("Message Application") })
}

@Composable
fun ProfileCard(userProfile: UserProfile) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.Top),
        elevation = 8.dp,
        backgroundColor = Color.White
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            ProfilePicture(userProfile.pictureUrl, userProfile.status)
            ProfileContent(userProfile.name, userProfile.status, Alignment.Start)
        }
    }
}

@Composable
fun ProfileContent(name: String, status: Boolean, alignment: Alignment.Horizontal) {
    Column(
        modifier = Modifier
            .padding(8.dp),
        horizontalAlignment = alignment
    ) {
        CompositionLocalProvider(LocalContentAlpha provides (if (status) 1f else ContentAlpha.medium)) {
            Text(name, style = MaterialTheme.typography.h5)

        }
        CompositionLocalProvider(LocalContentAlpha provides (ContentAlpha.medium)) {
            Text(
                text = if (status) "Active now"
                else "Offline",
                style = MaterialTheme.typography.body2
            )
        }
    }

}

@Composable
fun ProfilePicture(pictureUrl: String, status: Boolean, imageSize: Dp = 72.dp) {
    Card(
        shape = CircleShape,
        border = BorderStroke(width = 2.dp, color = if (status) Color.Green else Color.Red),
        modifier = Modifier.padding(16.dp),
        elevation = 4.dp
    ) {
        Image(
            painter = rememberImagePainter(data = pictureUrl, builder = {
                transformations(CircleCropTransformation())
            }),
            contentDescription = "",
            modifier = Modifier.size(imageSize)
        )
    }

}


@Preview(showBackground = true)
@Composable
fun UserListPreview() {
    LearnComposeTheme {
        MainProfileScreen(userProfileList)
    }
}

@Preview(showBackground = true)
@Composable
fun UserProfileDetailPreview() {
    LearnComposeTheme {
        UserProfileDetailScreen()
    }
}