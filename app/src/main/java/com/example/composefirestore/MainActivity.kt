package com.example.composefirestore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.composefirestore.ui.theme.ComposeFireStoreTheme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.material3.Button
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.text.input.KeyboardType


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeFireStoreTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   /* Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )*/
                    Birth(m = Modifier.padding(innerPadding))
                }
            }
        }
    }
}


@Composable
fun Birth(m: Modifier){
    var userName by remember { mutableStateOf("李宥萱")}
    var userWeight by remember { mutableStateOf(3500)}
    Column {
        TextField(
            value = userName,
            onValueChange = { newText ->
                userName = newText
            },
            modifier = m ,
            label= { Text("姓名") },
            placeholder = { Text("請輸入您的姓名") }

        )
        TextField(
            value = userWeight.toString(),
            onValueChange = { newText ->
                if (newText == ""){
                    userWeight = 0
                }
                else{userWeight = newText.toInt()}
            },
            label = { Text("出生體重") },
            keyboardOptions = KeyboardOptions
                (keyboardType = KeyboardType.Number)
        )

        Text("您輸入的姓名是：$userName\n出生體重為：$userWeight 公克")

    }


}
