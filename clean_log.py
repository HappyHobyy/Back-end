import subprocess
from datetime import datetime
import boto3
import os

def process_container_logs(container, bucket_name, s3_client):
    log_path = subprocess.check_output(['docker', 'inspect', '--format={{.LogPath}}', container]).decode().strip()
    print(f"Processing logs for container {container}...")

    # Upload and truncate logs
    upload_and_truncate(log_path, bucket_name, s3_client)

    # Clear container logs
    clear_container_logs(container)

def clear_container_logs(container):
    log_path = subprocess.check_output(['docker', 'inspect', '--format={{.LogPath}}', container]).decode().strip()
    print("Clearing logs for container:", container)
    with open(log_path, 'w') as f:
        f.truncate()
    print("Logs cleared for container:", container)

def upload_and_truncate(log_file, bucket_name, s3_client):
    timestamp = datetime.now().strftime('%Y%m%d%H%M%S')
    log_name = os.path.basename(log_file)
    s3_key = f"logs/{log_name}_{timestamp}.log.gz"

    # Compress and upload
    with open(log_file, 'rb') as f:
        compressed_data = subprocess.run(['gzip', '-c'], input=f.read(), stdout=subprocess.PIPE).stdout
        s3_client.put_object(Bucket=bucket_name, Key=s3_key, Body=compressed_data)
    print("Logs uploaded to S3:", s3_key)

def main():
    bucket_name = "hobbyhobby"
    s3_client = boto3.client('s3')

    containers = ["user", "gateway", "community", "photocontent"]

    for container in containers:
        process_container_logs(container, bucket_name, s3_client)

    print("Execution completed successfully.")

if __name__ == "__main__":
    main()
