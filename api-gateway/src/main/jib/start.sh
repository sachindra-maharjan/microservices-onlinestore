#!/bin/sh

echo "Pooling the config server"

sh ./app/wait-for-it.sh -u http://config-server -p 9091 -e /actuator -t 300 -s

retval=$?
echo $retval

if [ $retval -eq 0 ]; then
  echo "Starting application"
  java $JAVA_OPTS -cp @/app/jib-classpath-file com.yeti.store.apigateway.ApiGatewayApplication
  echo "Application started"
fi
