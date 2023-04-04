all: jib-build run

#Build
build:
	mvn -s maven_settings.xml clean install

build-image-only:
  mvn -s maven_settings.xml clean compile jib:dockerBuild

#Build image and push to registry
build-image:
	mvn -s maven_settings.xml clean compile jib:build
	
#Run application
run:
	docker compose up -d

#Stop application
stop:
	docker compose down