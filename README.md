MAINTEX – AI-Driven Household Utility Maintenance Cost Predictor
------------------------------------------------------------------------------------------------------------------------------------
📌 Overview
------------------------------------------------------------------------------------------------------------------------------------

MAINTEX is an AI-powered predictive maintenance and cost management system designed for households.
It helps homeowners predict, monitor, and optimize utility maintenance costs across electricity, plumbing, water, gas, and internet systems.

Unlike traditional utility tracking apps, MAINTEX provides:

🔮 Predictive maintenance cost forecasting (based on AI/ML models).

📊 Interactive dashboard with charts for cost trends & utility breakdown.

🛠️ Rule-based recommendations for cost savings & preventive maintenance.

🧾 Personalized budget planning for household utilities.

🔔 Smart reminders for inspections, servicing, and utility upkeep.

🚀 Features
------------------------------------------------------------------------------------------------------------------------------------

AI/ML Prediction Engine – Estimates future maintenance costs using household-specific data.

User-Friendly Android App – Simple form inputs with cost prediction displayed instantly.

Visualization Dashboard – Line chart & bar chart for monthly/utility breakdown.

Rule-Based Recommendations – Actionable insights to reduce costs.

Profile & Settings Pages – Manage household data and preferences.

Secure Flask Backend – Model API hosted with Flask & CORS support.

⚙️ Tech Stack
------------------------------------------------------------------------------------------------------------------------------------
🔹 Frontend (Mobile App)

Android (Kotlin + XML)

MPAndroidChart (for graphs & visualizations)

Volley (API communication)

🔹 Backend

Python Flask API

Scikit-learn / Joblib (ML model serving)

Pandas / Numpy (data preprocessing)


🧪 How It Works
------------------------------------------------------------------------------------------------------------------------------------
User inputs household details (pipe age, water usage, appliances, etc.) in the Android app.

Data is sent to the Flask API.

Model + encoder process the input and return a predicted cost.

Android app displays the predicted cost & updates charts.

The recommendations engine provides rule-based tips for reducing expenses.

📊 Performance
------------------------------------------------------------------------------------------------------------------------------------

Accuracy: ~89.8% (tested on 300 synthetic household data points).

Average Cost Savings: 18.7% (compared to baseline).

Outperforms traditional utility apps by focusing on maintenance cost prediction rather than just consumption tracking.

📷 Screenshots
------------------------------------------------------------------------------------------------------------------------------------
D:\utility-cost-predictor\UTILITYCOSTPROOF\Screenshot_20250830_153720.png
