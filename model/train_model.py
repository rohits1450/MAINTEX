import pandas as pd
import numpy as np
import os
from sklearn.model_selection import train_test_split
from sklearn.ensemble import RandomForestRegressor
from sklearn.preprocessing import OneHotEncoder
from sklearn.metrics import mean_squared_error, r2_score
import joblib

# Load data from the CSV file.
try:
    df = pd.read_csv("data/synthetic_data.csv")
except FileNotFoundError:
    print("Error: 'data/synthetic_data.csv' not found. Please ensure the file exists in the correct directory.")
    exit()

# Fit the OneHotEncoder on the categorical feature
encoder = OneHotEncoder(sparse_output=False, handle_unknown='ignore')
encoded_speed = encoder.fit_transform(df[['speed_plan']])
encoded_cols = encoder.get_feature_names_out(['speed_plan'])

# Create a DataFrame with the encoded features and concatenate with the original data
encoded_df = pd.DataFrame(encoded_speed, columns=encoded_cols)
df_encoded = pd.concat([df.drop('speed_plan', axis=1), encoded_df], axis=1) 

# Define features (X) and target (y)
X = df_encoded.drop("maintenance_cost", axis=1)
y = df_encoded["maintenance_cost"]

# Split the data into training and testing sets.
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# Initialize and train the RandomForestRegressor model.
model = RandomForestRegressor(n_estimators=100, random_state=42)
model.fit(X_train, y_train)

# Make predictions on the test set and evaluate the model.
y_pred = model.predict(X_test)
rmse = np.sqrt(mean_squared_error(y_test, y_pred))
r2 = r2_score(y_test, y_pred)

print(f"RMSE: {rmse:.2f}")
print(f"R² Score: {r2:.2f}")

# Create a directory to save the model and encoder.
os.makedirs("model", exist_ok=True)

# Save the trained model and the encoder to disk.
joblib.dump(model, "model/model.pkl")
joblib.dump(encoder, "model/encoder.pkl") # This is the crucial line that was added.

print("Model saved as model/model.pkl")
print("Encoder saved as model/encoder.pkl")