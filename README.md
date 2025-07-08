# 🧠 Android TensorFlow Lite Inference (Jetpack Compose)

This is a sample Android app built using **Jetpack Compose** that demonstrates how to integrate and run a **TensorFlow Lite (`.tflite`) model** on-device for ML inference.

---

## 📱 Features

- Runs a `model.tflite` model using the TensorFlow Lite Interpreter
- Takes dummy input data (can be extended to image input)
- Outputs prediction results with confidence score
- Built entirely using **Jetpack Compose UI
---

## 📦 Project Structure
├── app/
│ └── src/
│ └── main/
│ ├── java/
│ │ └── com.example.tensorflowlite/
│ │ ├── MainActivity.kt
│ │ ├── InferenceScreen.kt
│ │ └── ModelLoader.kt
│ └── assets/
│ ├── model.tflite
