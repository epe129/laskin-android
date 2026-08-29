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
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LaskinTheme {
                Scaffold(modifier = Modifier.fillMaxSize(), containerColor = Color(0xFF15171A)) { innerPadding ->
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
    var lasku = remember { mutableListOf<String>() }
    var numerot  by remember { mutableStateOf("") }
    var vas  by remember { mutableIntStateOf(0) }
    var merkkiindex = 0
    var merkkiindex_vas = 0

    if ("" in lasku) {
        val tyhjandex = lasku.indexOf("")
        lasku.removeAt(tyhjandex)
    }
    while (lasku.size > 1) {
        if ("=" in lasku) {
            val yhtindex = lasku.indexOf("=")
            lasku.removeAt(yhtindex)
            if ("*" in lasku) {
                merkkiindex = lasku.indexOf("*")
                val vas_k = lasku[merkkiindex - 1].toInt() * lasku[merkkiindex + 1].toInt()
                lasku.removeAt(merkkiindex + 1)
                lasku.removeAt(merkkiindex - 1)
                if (vas.toString() !in lasku) {
                    merkkiindex_vas = lasku.indexOf(vas.toString())
                    if (merkkiindex_vas == -1) {
                        lasku.add(merkkiindex,vas_k.toString())
                    } else {
                        lasku[merkkiindex_vas] = vas_k.toString()
                    }
                }
                laskuTeksti = vas_k.toString()
                vas = vas_k
            }
            if ("/" in lasku) {
                merkkiindex = lasku.indexOf("/")
                vas = lasku[merkkiindex - 1].toInt() / lasku[merkkiindex + 1].toInt()
                lasku.subList(merkkiindex - 1, merkkiindex + 1).clear()
                if (vas.toString() !in lasku) {
                    lasku.add(vas.toString())
                }
                laskuTeksti = vas.toString()
            }
            if ("+" in lasku) {
                merkkiindex = lasku.indexOf("+")
                val vas_p = lasku[merkkiindex - 1].toInt() + lasku[merkkiindex + 1].toInt()
                println(lasku)
                lasku.removeAt(merkkiindex + 1)
                lasku.removeAt(merkkiindex - 1)
                if (vas.toString() !in lasku) {
                    merkkiindex_vas = lasku.indexOf(vas.toString())
                    println(merkkiindex_vas)
                    println(vas)
                    if (merkkiindex_vas == -1) {
                        lasku.add(merkkiindex,vas_p.toString())
                    } else {
                        lasku[merkkiindex_vas] = vas_p.toString()
                    }
                }
                laskuTeksti = vas_p.toString()
                vas = vas_p
            }
            if ("-" in lasku){
                merkkiindex = lasku.indexOf("-")
                vas = lasku[merkkiindex - 1].toInt() - lasku[merkkiindex + 1].toInt()
                lasku.subList(merkkiindex - 1, merkkiindex + 1).clear()
                if (vas.toString() !in lasku) {
                    lasku.add(vas.toString())
                    //lasku.add(merkkiindex , vas.toString())
                }
                println(lasku)
                laskuTeksti = vas.toString()
            }
            println(lasku)
        }
        if (lasku.size == 1) {
            break
        }
    }


    if ("=" in lasku && "+" !in lasku && "-" !in lasku && "/" !in lasku && "*" !in lasku) {
        val yhtindex = lasku.indexOf("=")
        lasku.removeAt(yhtindex)
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .width(360.dp)
                .clip(RoundedCornerShape(32.dp))
                .background(Color(0xFF202328))
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            // DISPLAY
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color(0xFF0B0D0F))
                    .padding(horizontal = 20.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Text(
                    text = if (laskuTeksti.isEmpty()) "0" else laskuTeksti,
                    color = Color.White,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Light,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // C / /
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = {
                        laskuTeksti = ""
                        lasku.clear()
                        numerot = ""
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(70.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF3A3F46)
                    )
                ) {
                    Text(
                        "C",
                        color = Color.White,
                        fontSize = 23.sp
                    )
                }

                Button(
                    onClick = {
                        laskuTeksti += "/"
                        lasku.add("/")
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(70.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF292D33)
                    )
                ) {
                    Text(
                        "/",
                        color = Color.White,
                        fontSize = 23.sp
                    )
                }

                Spacer(modifier = Modifier.weight(2f))
            }

            // 7 8 9 *
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = {
                        laskuTeksti += "7"
                        numerot += "7"
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(70.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF292D33)
                    )
                ) {
                    Text("7", color = Color.White, fontSize = 25.sp)
                }

                Button(
                    onClick = {
                        laskuTeksti += "8"
                        numerot += "8"
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(70.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF292D33)
                    )
                ) {
                    Text("8", color = Color.White, fontSize = 25.sp)
                }

                Button(
                    onClick = {
                        laskuTeksti += "9"
                        numerot += "9"
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(70.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF292D33)
                    )
                ) {
                    Text("9", color = Color.White, fontSize = 25.sp)
                }

                Button(
                    onClick = {
                        laskuTeksti += "*"
                        lasku.add(numerot)
                        numerot = ""
                        lasku.add("*")
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(70.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF8A00)
                    )
                ) {
                    Text("*", color = Color.White, fontSize = 25.sp)
                }
            }

            // 4 5 6 -
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = {
                        laskuTeksti += "4"
                        numerot += "4"
                    },
                    modifier = Modifier.weight(1f).height(70.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF292D33)
                    )
                ) {
                    Text("4", color = Color.White, fontSize = 25.sp)
                }

                Button(
                    onClick = {
                        laskuTeksti += "5"
                        numerot += "5"
                    },
                    modifier = Modifier.weight(1f).height(70.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF292D33)
                    )
                ) {
                    Text("5", color = Color.White, fontSize = 25.sp)
                }

                Button(
                    onClick = {
                        laskuTeksti += "6"
                        numerot += "6"
                    },
                    modifier = Modifier.weight(1f).height(70.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF292D33)
                    )
                ) {
                    Text("6", color = Color.White, fontSize = 25.sp)
                }

                Button(
                    onClick = {
                        laskuTeksti += "-"
                        lasku.add(numerot)
                        numerot = ""
                        lasku.add("-")
                    },
                    modifier = Modifier.weight(1f).height(70.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF8A00)
                    )
                ) {
                    Text("-", color = Color.White, fontSize = 25.sp)
                }
            }

            // 1 2 3 +
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = {
                        laskuTeksti += "1"
                        numerot += "1"
                    },
                    modifier = Modifier.weight(1f).height(70.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF292D33)
                    )
                ) {
                    Text("1", color = Color.White, fontSize = 25.sp)
                }

                Button(
                    onClick = {
                        laskuTeksti += "2"
                        numerot += "2"
                    },
                    modifier = Modifier.weight(1f).height(70.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF292D33)
                    )
                ) {
                    Text("2", color = Color.White, fontSize = 25.sp)
                }

                Button(
                    onClick = {
                        laskuTeksti += "3"
                        numerot += "3"
                    },
                    modifier = Modifier.weight(1f).height(70.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF292D33)
                    )
                ) {
                    Text("3", color = Color.White, fontSize = 25.sp)
                }

                Button(
                    onClick = {
                        laskuTeksti += "+"
                        lasku.add(numerot)
                        numerot = ""
                        lasku.add("+")
                    },
                    modifier = Modifier.weight(1f).height(70.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF8A00)
                    )
                ) {
                    Text("+", color = Color.White, fontSize = 25.sp)
                }
            }

            // , 0 and =
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = {
                        laskuTeksti += ","
                        lasku.add(",")
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(70.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF292D33)
                    )
                ) {
                    Text(",", color = Color.White, fontSize = 25.sp)
                }
                Button(
                    onClick = {
                        laskuTeksti += "0"
                        numerot += "0"
                    },
                    modifier = Modifier.weight(1f).height(70.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF292D33)
                    )
                ) {
                    Text("0", color = Color.White, fontSize = 25.sp)
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
                    modifier = Modifier
                        .weight(3f)
                        .height(70.dp),
                    shape = RoundedCornerShape(40.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF8A00)
                    )
                ) {
                    Text(
                        "=",
                        color = Color.White,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Light
                    )
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