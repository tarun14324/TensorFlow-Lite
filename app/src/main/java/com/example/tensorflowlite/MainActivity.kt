package com.example.tensorflowlite

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tensorflowlite.ui.theme.TensorFlowLiteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TensorFlowLiteTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    InferenceScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun InferenceScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var result by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            try {
                val interpreter = loadModel(context)

                val input = Array(1) { Array(224) { Array(224) { FloatArray(3) { 1.0f } } } }

                val output = Array(1) { FloatArray(1001) }

                interpreter.run(input, output)

                val maxIndex = output[0].indices.maxByOrNull { output[0][it] } ?: -1
                val confidence = output[0][maxIndex]

                result = "Prediction Index: $maxIndex\nConfidence: $confidence"


            } catch (e: Exception) {
                result = "Error: ${e.message}"
                Log.e("TFLite", "Error running model", e)
            }
        }) {
            Text("Run Model")
        }

        Spacer(modifier = Modifier.height(16.dp))

        result?.let {
            Text(it, fontSize = 18.sp)
        }
    }
}