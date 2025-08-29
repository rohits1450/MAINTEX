# generate_data.py

import pandas as pd
import numpy as np
import random
import os

# Ensure output folder exists
os.makedirs("data", exist_ok=True)

# Step 1.1: Generate base feature columns
n = 300
speed_plans = ['Basic', 'Standard', 'Premium']

data = {
    "pipe_age": np.random.randint(1, 30, n),
    "water_usage": np.round(np.random.uniform(50, 500, n), 2),
    "rainfall": np.round(np.random.uniform(0, 300, n), 2),
    "appliance_count": np.random.randint(1, 20, n),
    "daily_usage": np.round(np.random.uniform(1, 24, n), 1),
    "wiring_age": np.random.randint(1, 40, n),
    "gas_appliances": np.random.randint(0, 10, n),
    "pipe_condition": np.random.randint(1, 11, n),
    "internet_devices": np.random.randint(1, 15, n),
    "speed_plan": [random.choice(speed_plans) for _ in range(n)],
    "household_size": np.random.randint(1, 8, n),
    "recycling_score": np.random.randint(1, 11, n),
}

# Step 1.2: Calculate maintenance cost using synthetic formula
speed_map = {'Basic': 1, 'Standard': 2, 'Premium': 3}
speed_numeric = [speed_map[sp] for sp in data['speed_plan']]

cost = (
    3 * data["pipe_age"]
    + 0.2 * data["water_usage"]
    - 0.1 * data["rainfall"]
    + 2 * data["appliance_count"]
    + 1.5 * data["daily_usage"]
    + 2 * data["wiring_age"]
    + 3 * data["gas_appliances"]
    - 2 * data["pipe_condition"]
    + 1.5 * data["internet_devices"]
    + 5 * np.array(speed_numeric)
    + 4 * data["household_size"]
    - 3 * data["recycling_score"]
    + np.random.normal(0, 25, n)
)

data["maintenance_cost"] = np.round(cost, 2)

# Step 1.3: Save the final DataFrame to CSV
df = pd.DataFrame(data)
df.to_csv("data/synthetic_data.csv", index=False)
print(" Synthetic dataset saved to data/synthetic_data.csv")
