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
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore


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
    var userPassword by remember { mutableStateOf("")}
    var msg by remember { mutableStateOf("訊息")}
    val db = Firebase.firestore
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
        TextField(
            value = userPassword,
            onValueChange = { newText ->
                userPassword = newText
            },
            label = { Text("密碼") },
            placeholder = { Text(text = "請輸入您的密碼") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions
                (keyboardType = KeyboardType.Password)
        )


        Text("您輸入的姓名是：$userName\n出生體重為：$userWeight 公克"+ "\n密碼：$userPassword")

        Row {
            Button(onClick = {
                val user = Person(userName, userWeight, userPassword)
                db.collection("users")
                    //.add(user)
                    .document(userName)
                    .set(user)
                    .addOnSuccessListener { documentReference ->
                        msg = "新增/異動資料成功"
                    }
                    .addOnFailureListener { e ->
                        msg = "新增/異動資料失敗：" + e.toString()
                    }

            }) {
                Text("新增/修改資料")
            }
            Button(onClick = {  }) {
                Text("查詢資料")
            }
            Button(onClick = {  }) {
                Text("刪除資料")
            }
        }
        Text(text = msg)

    }


}


data class Person(
    var userName: String,
    var userWeight: Int,
    var userPassword: String
)
