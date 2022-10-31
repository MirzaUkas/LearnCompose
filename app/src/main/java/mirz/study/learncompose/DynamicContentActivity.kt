package mirz.study.learncompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import mirz.study.learncompose.ui.theme.LearnComposeTheme

class DynamicContentActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainGreetingScreen()
        }
    }
}

@Composable
fun MainGreetingScreen(viewModel: MainViewModel = MainViewModel()) {
    val newNameState = viewModel.textFieldState.observeAsState("")
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GreetingList(newNameState.value) { newName ->
            Log.e("Here", newName)
            viewModel.onTextChanged(newName)
        }
    }
}

@Composable
fun GreetingList(
    textFieldValue: String,
    textChange: (newName: String) -> Unit
) {
    TextField(value = textFieldValue, onValueChange = textChange)
    Button(onClick = {}) {
        Text(textFieldValue)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewDynamic() {
    LearnComposeTheme {
        MainGreetingScreen()
    }
}