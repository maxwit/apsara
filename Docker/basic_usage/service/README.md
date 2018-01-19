### Run your new load-balanced app
* Init swarm manager
`docker swarm init`
<br/>
* Run the app
`docker stack deploy -c docker-compose.yml getstartedlab`
<br/>
* Get the service ID
`docker service ls`
<br/>
* List tasks for the service
`docker service ps getstartedlab_web`
<br/>
* List all containers on the system
`docker container ls -q`
### Scale the app
* Change `replicas` value in docker-compose.yml, and run command:
`docker stack deploy -c docker-compose.yml getstartedlab`
### Take down the app and the swarm
* Take down the app
`docker stack rm getstartedlab`
<br/>
* Take down the swarm
`docker swarn leave --force`
