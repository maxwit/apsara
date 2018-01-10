#!/bin/sh

# Remove old versions
yum remove docker docker-common docker-selinux docker-engine

# Set up the repository
yum install -y yum-utils device-mapper-persistent-data lvm2

yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo

#yum-config-manager --enable docker-ce-edge

#yum-config-manager --enable docker-ce-test

#yum-config-manager --disable docker-ce-edge

#yum-config-manager --disable docker-ce-test

# Install latest Docker CE
yum install -y docker-ce

# Install a specific version of Docker CE
# yum list docker-ce --showduplicates | sort -r
# yum install <FULLY-QUALIFIED-PACKAGE-NAME>

# Start Docker
systemctl start docker

# Verify
docker run hello-world
