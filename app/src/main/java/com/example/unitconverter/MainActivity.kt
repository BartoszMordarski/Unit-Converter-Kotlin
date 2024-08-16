package com.example.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter() {

    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var inputExpanded by remember { mutableStateOf(false) }
    var outputExpanded by remember { mutableStateOf(false) }
    val conversionFactor = remember { mutableStateOf(1.00) }
    val oConversionFactor = remember { mutableStateOf(1.00) }

    fun convertUnits(){
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * conversionFactor.value * 100.0 / oConversionFactor.value).roundToInt() / 100.0
        outputValue = result.toString()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Unit Converter",
            style = MaterialTheme.typography.headlineLarge,
            )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = inputValue,
            onValueChange = { inputValue = it
                            convertUnits()},
            label = {Text("Enter Value")})


        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Box {
                Button(onClick = { inputExpanded = true },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB2FF59), contentColor = Color(0xFF2F4F4F)
                    )){
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = inputExpanded, onDismissRequest = { inputExpanded = false }) {
                    DropdownMenuItem(
                        text = { Text("Centimeters") },
                        onClick = {
                            inputExpanded = false
                            inputUnit = "Centimeters"
                            conversionFactor.value = 0.01
                            convertUnits()
                        }
                    )


                    DropdownMenuItem(
                        text = { Text("Meters") },
                        onClick = {
                            inputExpanded = false
                            inputUnit = "Meters"
                            conversionFactor.value = 1.0
                            convertUnits()
                        }
                    )


                    DropdownMenuItem(
                        text = { Text("Feet") },
                        onClick = {
                            inputExpanded = false
                            inputUnit = "Feet"
                            conversionFactor.value = 0.3048
                            convertUnits()
                        }
                    )


                    DropdownMenuItem(
                        text = { Text("Milimeters") },
                        onClick = {
                            inputExpanded = false
                            inputUnit = "Milimeters"
                            conversionFactor.value = 0.001
                            convertUnits()
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "➡️", modifier = Modifier.align(Alignment.CenterVertically))
            Spacer(modifier = Modifier.width(8.dp))

            Box {
                Button(onClick = { outputExpanded = true },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB2FF59), contentColor = Color(0xFF2F4F4F)
                    )) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = outputExpanded, onDismissRequest = { outputExpanded = false }) {
                    DropdownMenuItem(
                        text = { Text("Centimeters") },
                        onClick = {
                            outputExpanded = false
                            outputUnit = "Centimeters"
                            oConversionFactor.value = 0.01
                            convertUnits()
                        }
                    )


                    DropdownMenuItem(
                        text = { Text("Meters") },
                        onClick = {
                            outputExpanded = false
                            outputUnit = "Meters"
                            oConversionFactor.value = 1.00
                            convertUnits()
                        }
                    )


                    DropdownMenuItem(
                        text = { Text("Feet") },
                        onClick = {
                            outputExpanded = false
                            outputUnit = "Feet"
                            oConversionFactor.value = 0.3048
                            convertUnits()
                        }
                    )


                    DropdownMenuItem(
                        text = { Text("Milimeters") },
                        onClick = {
                            outputExpanded = false
                            outputUnit = "Milimeters"
                            oConversionFactor.value = 0.001
                            convertUnits()
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Result: $outputValue $outputUnit",
            style = MaterialTheme.typography.headlineMedium)
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverter()
}