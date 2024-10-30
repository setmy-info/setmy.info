from fastapi import FastAPI
import os
from datetime import datetime

LOG_DIR_PATH = "/mnt/data"
LOG_FILE_PATH = os.path.join(LOG_DIR_PATH, "request_log.txt")

app = FastAPI()


@app.get("/")
def read_root():
    k8s_variables = {key: value for key, value in os.environ.items() if key.startswith("K8S")}

    timestamp = datetime.now().isoformat()
    pod_name = os.environ.get("K8S_POD_NAME")
    log_file_name = f"{pod_name}_request_log.txt" if pod_name else "default_request_log.txt"
    log_file_path = os.path.join(LOG_DIR_PATH, log_file_name)
    if os.path.isdir(LOG_DIR_PATH):
        with open(log_file_path, "a") as log_file:
            log_file.write(f"{timestamp} - {k8s_variables}\n")

    return k8s_variables
