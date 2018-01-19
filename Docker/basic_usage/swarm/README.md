### Set up the swarm
#### Create a cluster
* Create VMs
`docker-machine create --driver virtualbox myvm1`
`docker-machine create --driver virtualbox myvm2`
<br/>
* List the VMs and get their IP addresses
`docker-machine ls`
<br/>
* Initialize the swarm and add nodes
`docker-machine ssh myvm1 "docker swarm init --advertise-addr <myvm1 ip>"`
`docker-machine ssh myvm2 "docker swarm join --token <token> <ip>:2377"`
<br/>
* Run docker node ls on the manager to view the nodes in this swarm:
`docker-machine ssh myvm1 "docker node ls"`

### Deploy the app to the swarm cluster
#### Configure a docker-machine shell to the swarm manager
* Get docker machine shell environment on MAC or Linux
```
$ docker-machine env myvm1
export DOCKER_TLS_VERIFY="1"
export DOCKER_HOST="tcp://192.168.99.100:2376"
export DOCKER_CERT_PATH="/Users/sam/.docker/machine/machines/myvm1"
export DOCKER_MACHINE_NAME="myvm1"
# Run this command to configure your shell:
# eval $(docker-machine env myvm1)
```
`eval $(docker-machine env myvm1)`

* Verify that myvm1 is the active machine
`docker-machine ls`

#### Deploy the app to swarm manager
* Run the command on myvm1
`docker stack deploy -c docker-compose.yml getstartedlab`
<br/>
* List the services
`docker stack ps getstartedlab`
#### Accessing the cluster
* Run get VMs’ IP addresses 
`docker-machine ls`
* Visit either of them on a browser. You’ll see five possible container IDs all cycling by randomly, demonstrating the load-balancing.

#### Iterating and scaling your app
1. Scale the app by changing the `docker-compose.yml` file.
1. Change the app behavior by editing code, then rebuild, and push the new image. 
1. Join any machine to the swarm

In either case, simply run `docker stack deploy` again to deploy these changes.

#### Cleanup and reboot
* Tear down the stack
`docker stack rm gtestartedlab`
<br/>
* Unsetting docker-machine shell variable settings
`eval $(docker-machine env -u)`
<br/>
* Restarting Docker machines. To restart a machine that’s stopped, run:
`docker-machine start <machine-name>`