package com.example.neonatal;

import java.util.ArrayList;
import java.util.Scanner;

import weka.classifiers.Classifier;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

public class NeonatalMenuPredictor {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        
        // Load trained model
        String modelPath = "neonatal_model_rf.bin";
        Classifier model = (Classifier) weka.core.SerializationHelper.read(modelPath);
        System.out.println("Model loaded successfully!\n");

        // Build structure of dataset (must match training attributes)
        ArrayList<Attribute> attributes = new ArrayList<>();

        attributes.add(new Attribute("age"));          // numeric
        attributes.add(new Attribute("weight"));       // numeric
        attributes.add(new Attribute("temperature"));  // numeric
        attributes.add(new Attribute("heart_rate"));   // numeric

        // Define class attribute (for prediction)
        ArrayList<String> classValues = new ArrayList<>();
        classValues.add("healthy");
        classValues.add("sick");
        attributes.add(new Attribute("outcome", classValues));

        Instances data = new Instances("TestInstances", attributes, 0);
        data.setClassIndex(data.numAttributes() - 1);

        while (true) {
            System.out.println("=== Neonatal Health Prediction ===");
            System.out.println("1. Predict new case");
            System.out.println("2. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();

            if (choice == 2) {
                System.out.println("Exiting program...");
                break;
            }

            // Take user input
            System.out.print("Enter Age (in days): ");
            double age = sc.nextDouble();
            System.out.print("Enter Weight (in kg): ");
            double weight = sc.nextDouble();
            System.out.print("Enter Temperature (°C): ");
            double temp = sc.nextDouble();
            System.out.print("Enter Heart Rate (bpm): ");
            double hr = sc.nextDouble();

            // Create a single instance
            double[] values = new double[data.numAttributes()];
            values[0] = age;
            values[1] = weight;
            values[2] = temp;
            values[3] = hr;
            values[4] = weka.core.Utils.missingValue(); // class value (unknown)
            Instance inst = new DenseInstance(1.0, values);

            data.add(inst);

            // Predict
            double pred = model.classifyInstance(data.lastInstance());
            String predictedClass = data.classAttribute().value((int) pred);

            System.out.println("\nPredicted Outcome: " + predictedClass.toUpperCase() + "\n");
        }

        sc.close();
    }
}
