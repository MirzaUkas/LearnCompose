package mirz.study.learncompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mirz.study.learncompose.ui.theme.LearnComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    Surface(color = Color.DarkGray, modifier = Modifier.fillMaxSize()) {

        Row {
            Surface(
                color = Color.Red,
                modifier = Modifier
                    .height(500.dp)
                    .width(50.dp)

            ) {}
            Surface(
                color = Color.Blue,
                modifier = Modifier
                    .height(500.dp)
                    .width(50.dp)

            ) {}
            Column {
                Surface(
                    color = Color.Yellow,
                    modifier = Modifier
                        .height(100.dp)
                        .width(100.dp)

                ) {}
                Surface(
                    color = Color.Green,
                    modifier = Modifier
                        .height(100.dp)
                        .width(100.dp)

                ) {}
            }
        }

    }
}

@Composable
fun GreetingText(name: String) {
    Text(
        text = "Hello $name!", modifier = Modifier
            .height(100.dp)
            .width(200.dp)
            .padding(8.dp)
            .clickable { },
        style = MaterialTheme.typography.h5,
        fontWeight = FontWeight.ExtraBold
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LearnComposeTheme {
        MainScreen()
    }
}