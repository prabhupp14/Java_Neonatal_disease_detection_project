# Java_Neonatal_disease_detection_project
This project is a menu-driven Java application that predicts neonatal (newborn) health conditions using a machine learning model trained in Weka. It provides an interactive console-based interface where users input clinical parameters, and the system classifies the newborn’s health status
🧠 Neonatal Health Prediction using Weka

This project is a menu-driven Java application that predicts neonatal (newborn) health conditions using a machine learning model trained in Weka. It provides an interactive console-based interface where users input clinical parameters, and the system classifies the newborn’s health status (e.g., “Normal”, “Suspect”, or “Pathological”) based on the trained model.

🚀 Features

Menu-driven interface for easy interaction

Uses Weka’s machine learning library for model training and prediction

Supports multiple neonatal health parameters as input

Can load pre-trained .model files for predictions

Written entirely in Java, compatible with Weka 3.8+

🧩 Technologies Used

Java 8+

Weka 3.8.6 (Machine Learning Library)

Maven (for dependency management)

📂 Project Structure
neonatal-ml-weka/
│
├── src/
│   └── main/
│       └── java/
│           └── com/example/neonatal/
│               └── NeonatalMenuPredictor.java
├── pom.xml
└── neonatal_model.model

⚙️ How to Run

Install Weka 3.8.6 or use Maven dependency:

<dependency>
  <groupId>nz.ac.waikato.cms.weka</groupId>
  <artifactId>weka-stable</artifactId>
  <version>3.8.6</version>
</dependency>


Compile:

javac -cp ".;C:\Program Files\Weka-3-8\weka.jar" NeonatalMenuPredictor.java


Run:

java -cp ".;C:\Program Files\Weka-3-8\weka.jar" NeonatalMenuPredictor

🧪 Example Usage
=== Neonatal Health Prediction ===
Enter birth weight: 2.5
Enter temperature: 36.7
Enter heart rate: 120
Predicted Health Condition: NORMAL

📊 Future Enhancements

GUI interface using JavaFX or Swing

Integration with real hospital neonatal datasets

Model optimization for higher accuracy
