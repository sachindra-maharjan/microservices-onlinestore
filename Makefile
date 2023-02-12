all: jib-build run

#Build
build:
	mvn clean install

#Build image and push to registry
build-image:
	mvn clean compile jib:build

#Run application
run:
	docker compose up -d

#Stop application
stop:
	docker compose down