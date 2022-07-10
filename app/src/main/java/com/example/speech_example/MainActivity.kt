package com.example.speech_example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.speech_example.ui.theme.Speech_ExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Speech_ExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Screen()
                }
            }
        }
    }
}

@Composable
fun Screen(
    viewModel: TextViewModel = viewModel()
) {
    val state = viewModel.state.value
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        OutlinedTextField(
            value = state.text,
            onValueChange = {
            viewModel.onTextFieldValueChange(it)}
        )
        Spacer(
            modifier = Modifier.height(12.dp)
        )
        Button(
            onClick = {
                viewModel.textToSpeech(context)},
                enabled = state.isButtonEnabled
        ) {
            Text(text = "speak")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Speech_ExampleTheme {
        Screen()
    }
}