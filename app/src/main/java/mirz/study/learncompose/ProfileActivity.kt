package mirz.study.learncompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
                    MainProfileScreen(users)
                }
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
            Column {
                for (user in users)
                    ProfileCard(user)

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
            ProfilePicture(userProfile.drawableId, userProfile.status)
            ProfileContent(userProfile.name, userProfile.status)
        }
    }
}

@Composable
fun ProfileContent(name: String, status: Boolean) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
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
fun ProfilePicture(drawableId: Int, status: Boolean) {
    Card(
        shape = CircleShape,
        border = BorderStroke(width = 2.dp, color = if (status) Color.Green else Color.Red),
        modifier = Modifier.padding(16.dp),
        elevation = 4.dp
    ) {
        Image(
            painter = painterResource(id = drawableId),
            contentDescription = "",
            modifier = Modifier.size(72.dp),
            contentScale = ContentScale.Crop
        )
    }

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    LearnComposeTheme {
        MainProfileScreen(users)
    }
}