from flask import Flask, request, jsonify
from flask_cors import CORS
import joblib
import pandas as pd
import os

app = Flask(__name__)
CORS(app)

# --- Model path ---
BASE_DIR = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
MODEL_PATH = os.path.join(BASE_DIR, "model", "model.pkl")

try:
    model = joblib.load(MODEL_PATH)
    print("Model loaded successfully.")
except Exception as e:
    print(f"Error loading model: {e}")
    raise

# Define the features your app sends
FEATURES = [
    "pipeAge",
    "waterUsage",
    "rainfall",
    "applianceCount",
    "dailyUsage",
    "wiringAge",
    "gasAppliances",
    "pipeCondition",
    "devices",
    "householdSize",
    "recyclingScore",
    "plan"   # categorical spinner
]

NUMERIC = [f for f in FEATURES if f != "plan"]

@app.get("/health")
def health():
    return jsonify(status="ok")

@app.post("/predict")
def predict():
    try:
        data = request.get_json(silent=True)
        if not data or not isinstance(data, dict):
            return jsonify(error="Invalid JSON body"), 400

        # Missing keys?
        missing = [k for k in FEATURES if k not in data]
        if missing:
            return jsonify(error=f"Missing keys: {missing}"), 400

        # Build DataFrame
        row = {k: data[k] for k in FEATURES}
        df = pd.DataFrame([row])

        # Force numeric types
        for col in NUMERIC:
            try:
                df[col] = pd.to_numeric(df[col])
            except Exception:
                return jsonify(error=f"Field '{col}' must be numeric"), 400

        # Encode plan manually (Basic=0, Standard=1, Premium=2) — adjust if your model expects differently
        df["plan"] = df["plan"].map({"Basic": 0, "Standard": 1, "Premium": 2})

        # Predict
        y = model.predict(df)[0]
        return jsonify(prediction=float(y))
    except Exception as e:
        return jsonify(error=str(e)), 500

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000, debug=True)
