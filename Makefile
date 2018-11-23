project ?= ""

start: 
	@ make package/all
	@ make start/containers

start/conteiners:
	@ docker-compose up -d

package/all:
	@ make package project=user

package:
	@ echo "Packaging $(project) service"
	@ mvn -f $(project)/pom.xml -q  clean install	