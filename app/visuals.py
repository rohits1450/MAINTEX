import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
import os

df = pd.read_csv("data/synthetic_data.csv")
os.makedirs("visuals/feature_graphs", exist_ok=True)

def plot_pipe_age_vs_cost():
    plt.figure(figsize=(8, 5))
    sns.scatterplot(data=df, x="pipe_age", y="maintenance_cost")
    plt.title("Pipe Age vs Maintenance Cost")
    plt.savefig("visuals/feature_graphs/pipe_age_vs_cost.png")
    plt.close()

def plot_appliances_vs_cost():
    plt.figure(figsize=(8, 5))
    sns.scatterplot(data=df, x="appliance_count", y="maintenance_cost")
    plt.title("Appliance Count vs Maintenance Cost")
    plt.savefig("visuals/feature_graphs/appliances_vs_cost.png")
    plt.close()

def plot_cost_distribution():
    plt.figure(figsize=(8, 5))
    sns.histplot(df["maintenance_cost"], bins=25, kde=True)
    plt.title("Maintenance Cost Distribution")
    plt.savefig("visuals/feature_graphs/cost_distribution.png")
    plt.close()

def plot_correlation_heatmap():
    plt.figure(figsize=(12, 8))
    numeric_df = df.select_dtypes(include=['number'])
    sns.heatmap(numeric_df.corr(), annot=True, cmap="coolwarm", fmt=".2f")
    plt.title("Feature Correlation Heatmap")
    plt.savefig("visuals/feature_graphs/correlation_heatmap.png")
    plt.close()


def plot_feature_importance():
    import joblib
    from sklearn.preprocessing import OneHotEncoder

    model = joblib.load("model/model.pkl")

    encoder = OneHotEncoder(sparse_output=False, handle_unknown='ignore')
    speed_encoded = encoder.fit_transform(df[['speed_plan']])
    encoded_cols = encoder.get_feature_names_out(['speed_plan'])
    encoded_df = pd.DataFrame(speed_encoded, columns=encoded_cols)

    df_encoded = pd.concat([df.drop(['speed_plan', 'maintenance_cost'], axis=1), encoded_df], axis=1)
    feature_names = df_encoded.columns
    importances = model.feature_importances_

    plt.figure(figsize=(10, 6))
    sns.barplot(x=importances, y=feature_names)
    plt.title("Feature Importance (Random Forest)")
    plt.tight_layout()
    plt.savefig("visuals/feature_graphs/feature_importance.png")
    plt.close()

def generate_all_plots():
    plot_pipe_age_vs_cost()
    plot_appliances_vs_cost()
    plot_cost_distribution()
    plot_correlation_heatmap()
    plot_feature_importance()
    print(" All plots saved in visuals/feature_graphs/")

if __name__ == "__main__":
    generate_all_plots()
