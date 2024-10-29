from fastapi import FastAPI
import os

app = FastAPI()


@app.get("/")
def read_root():
    k8s_variables = {key: value for key, value in os.environ.items() if key.startswith("K8S")}
    return k8s_variables
