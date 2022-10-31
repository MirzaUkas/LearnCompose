package mirz.study.learncompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import mirz.study.learncompose.ui.theme.LearnComposeTheme

val namesList: ArrayList<String> = arrayListOf("Tono", "Asep", "Udin")

class DynamicContentActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Greeting(namesList)
        }
    }
}

@Composable
fun Greeting(names: List<String>) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for (name in names)
            Text(text = "Hello $name!")

        Button(onClick = { namesList.add("Surya") }) {
            Text("Add Name")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewDynamic() {
    LearnComposeTheme {
        Greeting(namesList)
    }
}