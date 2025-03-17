package com.ruhlan.otpcomponentcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ruhlan.otpcomponentcompose.ui.theme.OTPComponentComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OTPComponentComposeTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        OTPComponentItem(
                            text = remember {
                                mutableStateOf("")
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun OTPComponentItem(
    modifier: Modifier = Modifier,
    text: MutableState<String>
) {
    BasicTextField(
        value = text.value,
        onValueChange = {
            if (it.length <= 4) {
                text.value = it
            }
        }
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            repeat(times = 4) { index ->
                val number = when {
                    index >= text.value.length -> ""
                    else -> text.value[index]
                }

                val color = if (number.toString().isEmpty()) Color.Blue else Color.Black
                Column(
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = number.toString(),
                        style = MaterialTheme.typography.titleLarge
                    )

                    Box(
                        modifier = modifier
                            .width(60.dp)
                            .height(2.dp)
                            .background(color)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun OTP() {
    OTPComponentComposeTheme {
        OTPComponentItem(
            text = remember {
                mutableStateOf("")
            }
        )
    }
}