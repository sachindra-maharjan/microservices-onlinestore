#!/bin/sh

echo "Pooling the config server"

sh ./app/wait-for-it.sh -u $CONFIG_SERVER_URI -p $CONFIG_SERVER_PORT -e /actuator -t $CONFIG_SERVER_TIMEOUT -s

retval=$?
echo $retval

if [ $retval -eq 0 ]; then
  echo "Starting application"
  java $JAVA_OPTS -cp @/app/jib-classpath-file $ENTRYPOINT
  started=$?
  if [[ $started == 0 ]]; then
    echo "Application started"
  else 
    echo "Application failed to start."
  fi
fi
