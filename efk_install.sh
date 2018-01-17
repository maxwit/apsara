#!/bin/sh

###
#
# elasticSearch and kibana the same version
# i.e. ElasticSearch and kibana both version 6.1.1
#
# consult: 
# https://techstricks.com/elasticsearch-fluentd-kibana-efk-setup-guide/
#
###

# install elastic searcho
# finally curl http://localhost:9200/
echo "cd to /home"
cd ~

if [ ! -d ~/EFK ];
then
	mkdir ~/EFK
fi

echo "cd to EFK"
cd EFK

echo "wget elasticSearch zip"
### 不登录 vpn 反而更快
echo "wget- from url"
wget https://artifacts.elastic.co/downloads/elasticsearch/elasticsearch-6.1.1.zip
unzip elasticsearch-6.1.1.zip
#cd elasticsearch-6.1.1
#./bin/elasticsearch


### kibana
#cd ..
curl -O https://artifacts.elastic.co/downloads/kibana/kibana-6.1.1-darwin-x86_64.tar.gz
tar xvf kibana-6.1.1-darwin-x86_64.tar.gz
#cd kibana-6.1.1-darwin-x86_64
#./bin/kibana


### fluentd
# 
# reference: https://docs.fluentd.org/v1.0/articles/java
# requirement : ruby, gem (ruby package manager, bundle)
#
###
#cd ..
gem install bundler
 
git clone https://github.com/fluent/fluentd.git
cd fluentd

bundle install
bundle exec rake build

gem pkg/fluentd-1.0.2.gem

#fluentd --setup ./fluent
#fluentd -c ./fluent/fluent.conf -vv

# for test
# echo '{"json":"message"}' | fluent-cat debug.test
