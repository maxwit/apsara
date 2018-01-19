### Add a new service and redeploy
* Add new service `visualizer` to docker-compose.yml
* Make sure the shell is configured to talk to myvm1
1. On MAC or Linux
`eval $(docker-machine env myvm1)`
1. On Windows
`& "C:\Program Files\Docker\Docker\Resources\bin\docker-machine.exe" env myvm1 | Invoke-Expression`
* Rerun command on manager
`docker stack deploy -c docker-compose.yml getstartedlab`
* Take a look at the visualizer. Get the IP address of one of your nodes by `running docker-machine ls` and open browser

### Persist the data
* Add a `redis` service to docker-compose.yml
* Create a ./data directory on the manager:
`docker-machine ssh myvm1 "mkdir ./data"`
<br/>
* Make sure the shell is configured to talk to myvm1
* Run docker stack deploy one more time
`docker stack deploy -c docker-compose.yml getstartedlab`
<br/>
* Verify that the three services are running as expected
`docker service ls`
<br/>
* Check the web page at one of your nodes