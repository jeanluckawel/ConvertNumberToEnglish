package com.example.convert

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.convert.ui.theme.ConvertTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConvertTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
					ConvertNumberToWords()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConvertNumberToWords() {
    var inputNumber by remember { mutableStateOf("") }
    var outputWords by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Text(
            text = "Convertir un nombre en anglais",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = inputNumber,
            onValueChange = { inputNumber = it },
            label = { Text("Entrez un nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { outputWords = convertNumberToWords(inputNumber) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Convertir")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = outputWords,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
            )
        )
    }
}
fun convertNumberToWords(number: String): String {
    val units = arrayOf(
        "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
        "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"
    )

    val tens = arrayOf("", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety")

    val num = number.toIntOrNull()
    if (num == null) {
        return "Invalid input"
    } else if (num < 0) {
        return "Negative numbers not supported"
    } else if (num == 0) {
        return "zero"
    } else if (num < 20) {
        return units[num]
    } else if (num < 100) {
        return "${tens[num / 10]} ${units[num % 10]}"
    } else if (num < 1000) {
        return "${units[num / 100]} hundred ${convertNumberToWords((num % 100).toString())}"
    } else if (num < 1000000) {
        return "${convertNumberToWords((num / 1000).toString())} thousand ${convertNumberToWords((num % 1000).toString())}"
    } else {
        return "Number too large"
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ConvertTheme {
        ConvertNumberToWords()
    }
}