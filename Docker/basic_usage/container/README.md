### Build the app
    docker build -t friendlyhello .
### Run the app
* Run in frontend
`docker run -p 4000:80 friendlyhello`
* Run in backround
`docker run -d -p 4000:80 friendlyhello`
* End the process
`docker container stop <Container ID>`
### Share the image
* Login in with Docker ID
`docker  login`
* Tag the image
`docker tag image_name username/repository:tag`
* Publish the image
`docker push username/repository:tag`
* Pull and run the image from the remote repository
`docker run -p 4000:80 username/repository:tag`
