#!/bin/bash

# Define default values for the arguments
URL="http://localhost"
PORT=80
TIMEOUT=15
STRICT=false

# Parse the command line arguments
while [[ $# -gt 0 ]]
do
key="$1"

case $key in
    -u|--url)
    URL="$2"
    shift # past argument
    shift # past value
    ;;
    -p|--port)
    PORT="$2"
    shift # past argument
    shift # past value
    ;;
    -t|--timeout)
    TIMEOUT="$2"
    shift # past argument
    shift # past value
    ;;
    -s|--strict)
    STRICT=true
    shift # past argument
    ;;
    *)    # unknown option
    shift # past argument
    ;;
esac
done

echo "Waiting for $URL:$PORT..."

# Try to curl the service until it becomes available or a timeout is reached
for i in $(seq 1 $TIMEOUT)
do
    if curl -s "$URL:$PORT" >/dev/null; then
        break
    fi
    echo -n "."
    sleep 1
done

# Check if the service became available or not
if [[ $i -eq $TIMEOUT ]]; then
    echo "Failed to connect to $URL:$PORT within $TIMEOUT seconds."
    if [[ "$STRICT" = true ]]; then
        exit 1
    fi
else
    echo "$URL:$PORT is available!"
fi
