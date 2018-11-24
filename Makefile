# PHONY = up container/start container/restart stop/container 

project ?= "all"

up: 	
	@ make container/start

container/start: _build-docker-image
	@ make _container/execute command=up args=-d service=$(project)

container/restart:
	@ make _container/execute command=restart args="" service=$(project)

container/stop:
	@ make _container/execute command=down args=-v service=$(project)

_container/execute:
ifeq ($(project), "all")
	@ docker-compose $(command) $(args)
else
	@ docker-compose $(command) $(args) $(service)
endif

_build-docker-image:	
ifeq ($(project), "all")
	@ make _package service=user
else
	@ make _package service=$(project)
endif

_package:
	@ echo "Packaging $(service) service"
	@ cp -f Dockerfile $(service)/
	@ cd $(service) && mvn -q clean package -DskipTests
	@ rm -f $(service)/Dockerfile
