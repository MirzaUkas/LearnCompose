package mirz.study.learncompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import mirz.study.learncompose.ui.theme.LearnComposeTheme

val namesList: ArrayList<String> = arrayListOf("Asep", "Udin")

class DynamicContentActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainGreetingScreen()
        }
    }
}

@Composable
fun MainGreetingScreen() {
    val greetingListState = remember {
        mutableStateListOf<String>("Tono", "Tono")
    }
    val newNameState = remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GreetingList(greetingListState, newNameState.value, {
            greetingListState.add(newNameState.value)
        }) {
            newNameState.value = it
        }
    }
}

@Composable
fun GreetingList(
    names: List<String>,
    textFieldValue: String,
    onClick: () -> Unit,
    textChange: (newName: String) -> Unit
) {

    for (name in names)
        Text(text = "Hello $name!", style = MaterialTheme.typography.h3)
    TextField(value = textFieldValue, onValueChange = textChange)
    Button(onClick = onClick) {
        Text("Add Name")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewDynamic() {
    LearnComposeTheme {
        MainGreetingScreen()
    }
}