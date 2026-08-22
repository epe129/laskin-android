package com.example.laskin

import android.R.attr.onClick
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.laskin.ui.theme.LaskinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LaskinTheme {
                Scaffold(modifier = Modifier.fillMaxSize(), containerColor = Color.Gray) { innerPadding ->
                    Laskin(
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}

@Composable
fun Laskin(modifier: Modifier = Modifier) {
    var laskuTeksti by remember { mutableStateOf("") }
    val lasku = remember { mutableListOf<String>() }
    var numerot  by remember { mutableStateOf("") }
    var vas  by remember { mutableStateOf(0) }

    if ("=" in lasku) {
        println(lasku)
        //for ((index, value) in lasku.withIndex()) {
            if ("*" in lasku){
                var merkkiindex = lasku.indexOf("*")
                vas = lasku[merkkiindex - 1].toInt() * lasku[merkkiindex + 1].toInt()
                laskuTeksti = vas.toString()
            }
            if ("/" in lasku){
                var merkkiindex = lasku.indexOf("/")
                vas = lasku[merkkiindex - 1].toInt() / lasku[merkkiindex + 1].toInt()
                laskuTeksti = vas.toString()
            }
            if ("+" in lasku){
                var merkkiindex = lasku.indexOfFirst { it.startsWith("+") }
                vas = lasku[merkkiindex - 1].toInt() + lasku[merkkiindex + 1].toInt()
                lasku.subList(merkkiindex - 1, merkkiindex + 1).clear()
                if (vas.toString() !in lasku) {
                    lasku.add(merkkiindex , vas.toString())
                }
                println(lasku)
                laskuTeksti = vas.toString()
            }
            if ("-" in lasku){
                var merkkiindex = lasku.indexOf("-")
                vas = lasku[merkkiindex - 1].toInt() - lasku[merkkiindex + 1].toInt()
                laskuTeksti = vas.toString()
            }
        //}
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.height(600.dp)
        ) {
            Box(
                modifier = Modifier
                    .background(Color.Black)
                    .width(300.dp)
                    .height(50.dp)
                    .padding(10.dp)
            ) {
                Text(laskuTeksti,
                    style = TextStyle(Color.White, fontSize = 24.sp),
                    )
            }
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                maxLines = 4
            ) {
                Button(
                    onClick = {
                        laskuTeksti = ""
                        lasku.clear()
                              },
                    colors = ButtonColors(contentColor = Color.Black, containerColor = Color.Black, disabledContentColor = Color.White, disabledContainerColor = Color.White),
                    shape = CircleShape,
                ) {
                    Text("c", color = Color.White)
                }
                Button(
                    onClick = {
                        laskuTeksti += "/"
                        lasku.add("/")
                              },
                    colors = ButtonColors(contentColor = Color.Black, containerColor = Color.Black, disabledContentColor = Color.White, disabledContainerColor = Color.White),
                    shape = CircleShape,
                ) {
                    Text("/", color = Color.White)
                }
            }
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                maxLines = 4
            ) {
                Button(
                    onClick = {
                        laskuTeksti += 7
                        numerot += 7
                        },
                    colors = ButtonColors(contentColor = Color.Black, containerColor = Color.Black, disabledContentColor = Color.White, disabledContainerColor = Color.White),
                    shape = CircleShape,
                ) {
                    Text("7", color = Color.White)
                }
                Button(
                    onClick = {
                        laskuTeksti += 8
                        numerot += 8
                              },
                    colors = ButtonColors(contentColor = Color.Black, containerColor = Color.Black, disabledContentColor = Color.White, disabledContainerColor = Color.White),
                    shape = CircleShape,
                ) {
                    Text("8", color = Color.White)
                }
                Button(
                    onClick = {
                        laskuTeksti += 9
                        numerot += 9
                              },
                    colors = ButtonColors(contentColor = Color.Black, containerColor = Color.Black, disabledContentColor = Color.White, disabledContainerColor = Color.White),
                    shape = CircleShape,
                ) {
                    Text("9", color = Color.White)
                }
                Button(
                    onClick = {
                        laskuTeksti += "*"
                        lasku.add(numerot)
                        numerot = ""
                        lasku.add("*")
                              },
                    colors = ButtonColors(contentColor = Color.Black, containerColor = Color.Black, disabledContentColor = Color.White, disabledContainerColor = Color.White),
                    shape = CircleShape,
                ) {
                    Text("*", color = Color.White)
                }
            }
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                maxLines = 4
            ) {
                Button(
                    onClick = {
                        laskuTeksti += 4
                        numerot += 4

                              },
                    colors = ButtonColors(contentColor = Color.Black, containerColor = Color.Black, disabledContentColor = Color.White, disabledContainerColor = Color.White),
                    shape = CircleShape,
                ) {
                    Text("4", color = Color.White)
                }
                Button(
                    onClick = {
                        laskuTeksti += 5
                        numerot += 5

                              },
                    colors = ButtonColors(contentColor = Color.Black, containerColor = Color.Black, disabledContentColor = Color.White, disabledContainerColor = Color.White),
                    shape = CircleShape,
                ) {
                    Text("5", color = Color.White)
                }
                Button(
                    onClick = {
                        laskuTeksti += 6
                        numerot += 6
                              },
                    colors = ButtonColors(contentColor = Color.Black, containerColor = Color.Black, disabledContentColor = Color.White, disabledContainerColor = Color.White),
                    shape = CircleShape,
                ) {
                    Text("6", color = Color.White)
                }
                Button(
                    onClick = {
                        laskuTeksti += "-"
                        lasku.add(numerot)
                        numerot = ""
                        lasku.add("-")
                              },
                    colors = ButtonColors(contentColor = Color.Black, containerColor = Color.Black, disabledContentColor = Color.White, disabledContainerColor = Color.White),
                    shape = CircleShape,
                ) {
                    Text("-", color = Color.White)
                }
            }
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                maxLines = 4
            ) {
                Button(
                    onClick = {
                        laskuTeksti += 1
                        numerot += 1
                              },
                    colors = ButtonColors(contentColor = Color.Black, containerColor = Color.Black, disabledContentColor = Color.White, disabledContainerColor = Color.White),
                    shape = CircleShape,
                ) {
                    Text("1", color = Color.White)
                }
                Button(
                    onClick = {
                        laskuTeksti += 2
                        numerot += 2
                              },
                    colors = ButtonColors(contentColor = Color.Black, containerColor = Color.Black, disabledContentColor = Color.White, disabledContainerColor = Color.White),
                    shape = CircleShape,
                ) {
                    Text("2", color = Color.White)
                }
                Button(
                    onClick = {
                        laskuTeksti += 3
                        numerot += 3
                              },
                    colors = ButtonColors(contentColor = Color.Black, containerColor = Color.Black, disabledContentColor = Color.White, disabledContainerColor = Color.White),
                    shape = CircleShape,
                ) {
                    Text("3", color = Color.White)
                }
                Button(
                    onClick = {
                        laskuTeksti += "+"
                        lasku.add(numerot)
                        numerot = ""
                        lasku.add("+")
                              },
                    colors = ButtonColors(contentColor = Color.Black, containerColor = Color.Black, disabledContentColor = Color.White, disabledContainerColor = Color.White),
                    shape = CircleShape,
                ) {
                    Text("+", color = Color.White)
                }
            }
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                maxLines = 4
            ) {
                Button(
                    onClick = {
                        laskuTeksti += ","
                        lasku.add(",")
                              },
                    colors = ButtonColors(contentColor = Color.Black, containerColor = Color.Black, disabledContentColor = Color.White, disabledContainerColor = Color.White),
                    shape = CircleShape,
                ) {
                    Text(",", color = Color.White)
                }
                Button(
                    onClick = {
                        if ("=" in laskuTeksti || "=" in lasku) {
                            return@Button
                        }
                        laskuTeksti += "="
                        lasku.add(numerot)
                        numerot = ""
                        lasku.add("=")
                              },
                    colors = ButtonColors(contentColor = Color.Black, containerColor = Color.Black, disabledContentColor = Color.White, disabledContainerColor = Color.White),
                    shape = CircleShape,
                ) {
                    Text("=", color = Color.White)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LaskinTheme {
        Laskin()
    }
}