es 环境搭建
sysctl -w vm.max_map_count=262144
docker pull elasticsearch:7.6.2
docker run -d -e ES_JAVA_POTS="-Xms256m -Xmx256m" -e "discovery.type=single-node" \
-p 9200:9200 -p 9300:9300 --name search elasticsearch:7.6.2
docker cp e210fa11d632:/usr/share/elasticsearch/config/ /usr/local/iya/base/elasticsearch/
docker cp e210fa11d632:/usr/share/elasticsearch/data/ /usr/local/iya/base/elasticsearch/
docker cp e210fa11d632:/usr/share/elasticsearch/logs/ /usr/local/iya/base/elasticsearch/
docker cp e210fa11d632:/usr/share/elasticsearch/plugins/ /usr/local/iya/base/elasticsearch/
docker rm -f  search

docker run -e ES_JAVA_OPTS="-Xms256m -Xmx256m" -d --name elasticsearch -p 9200:9200 -p 9300:9300  \
-v /usr/local/iya/base/elasticsearch/config:/usr/share/elasticsearch/config \
-v /usr/local/iya/base/elasticsearch/data:/usr/share/elasticsearch/data \
-v /usr/local/iya/base/elasticsearch/logs:/usr/share/elasticsearch/logs \
-v /usr/local/iya/base/elasticsearch/plugins:/usr/share/elasticsearch/plugins \
-e "discovery.type=single-node" elasticsearch:7.6.2

docker exec -it elasticsearch /bin/bash
cd /usr/share/elasticsearch/config
vim elasticsearch.yml
http.cors.enabled: true
http.cors.allow-origin: "*"

cd /usr/share/elasticsearch/plugins/
mkdir ik
cd ik
curl https://github.com/medcl/elasticsearch-analysis-ik/releases/download/v7.6.2/elasticsearch-analysis-ik-7.6.2.zip
unzip elasticsearch-analysis-ik-7.6.2.zip
rm -rf  elasticsearch-analysis-ik-7.6.2.zip
exit
docker restart elasticsearch

docker run -d --name kibana -e "I18N_LOCALE=zh-CN" --link=elasticsearch:elasticsearch -p 5601:5601 kibana:7.6.2
docker run -d --name elasticsearch-head -p 9100:9100 mobz/elasticsearch-head:5
