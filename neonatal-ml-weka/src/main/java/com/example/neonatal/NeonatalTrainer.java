package com.example.neonatal;

import java.io.File;
import java.util.Random;

import weka.classifiers.Evaluation;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;
import weka.core.converters.CSVLoader;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.ReplaceMissingValues;
import weka.filters.unsupervised.attribute.Standardize;

public class NeonatalTrainer {

    public static void main(String[] args) throws Exception {
        // 🔹 1. Path to your dataset
        String csvPath = "C:\\Users\\ppp\\Downloads\\neonatal_disease_dataset_v2.csv";
        System.out.println("📂 Loading dataset from: " + csvPath);

        // 🔹 2. Load CSV
        CSVLoader loader = new CSVLoader();
        loader.setSource(new File(csvPath));
        Instances data = loader.getDataSet();

        // 🔹 3. Set class attribute (last column = disease label)
        data.setClassIndex(data.numAttributes() - 1);
        System.out.println("✅ Dataset loaded: " + data.numInstances() + " samples, " + data.numAttributes() + " attributes");

        // 🔹 4. Handle missing values
        ReplaceMissingValues rmv = new ReplaceMissingValues();
        rmv.setInputFormat(data);
        data = Filter.useFilter(data, rmv);

        // 🔹 5. Standardize features
        Standardize std = new Standardize();
        std.setInputFormat(data);
        data = Filter.useFilter(data, std);

        // 🔹 6. Split 80% train / 20% test
        data.randomize(new Random(1));
        int trainSize = (int) Math.round(data.numInstances() * 0.8);
        int testSize = data.numInstances() - trainSize;
        Instances train = new Instances(data, 0, trainSize);
        Instances test = new Instances(data, trainSize, testSize);

        // 🔹 7. Train Random Forest model
        RandomForest rf = new RandomForest();
        rf.setNumIterations(100); // ✅ use this instead of setNumTrees()
        rf.buildClassifier(train);
        System.out.println("🌲 Model training complete!");

        // 🔹 8. Evaluate on test data
        Evaluation eval = new Evaluation(train);
        eval.evaluateModel(rf, test);

        System.out.println("\n=== Evaluation Results ===");
        System.out.println(eval.toSummaryString());
        System.out.println("✅ Accuracy: " + String.format("%.2f", (1 - eval.errorRate()) * 100) + "%");

        // 🔹 9. Save trained model
        weka.core.SerializationHelper.write("neonatal_model_rf.bin", rf);
        System.out.println("💾 Model saved as neonatal_model_rf.bin");
    }
}
//cd "C:\Users\ppp\OneDrive\Documents\neonatal-ml-weka"
//mvn clean install

//mvn exec:java -Dexec.mainClass="com.example.neonatal.NeonatalTrainer"
