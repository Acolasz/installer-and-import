# Commands

## curl

```shell
curl --insecure -i -d "brand=5000&clientId=22052"  http://artifactory.kukutyin.hu/restapi/clientlist/search
curl -X GET "http://artifactory.kukutyin.hu/actuator" -H "Authorization: Basic <base64_encode>"
curl -X POST http://artifactory.kukutyin.hu/ihs-adapter/restapi/clientlist/search?brand=5000&clientId=22052
curl -o file.html http://artifactory.kukutyin.hu/restapi/file.html
```

## wget

```shell
wget --no-cookies --no-check-certificate --header="X-JFrog-Art-Api: <api-key>" https://artifactory.kukutyin.hu/artifactory/docker-installer/oracle/jdk/jdk-${JAVA_VERSION}-linux-x64.tar.gz -O jdk-${JAVA_VERSION}-linux-x64.tar.gz
wget --header="X-JFrog-Art-Api: <api-key>" http://artifactory.kukutyin.hu/artifactory/devops-installer/oracle/jdk/jdk-8u261-linux-x64.tar.gz
wget --header="Authorization: Basic <base64_encode>" http://artifactory.kukutyin.hu/artifactory/devops-installer/oracle/jdk/jdk-8u261-linux-x64.tar.gz
wget -O- --header="Authorization: Basic <base64_encode>" --header='Accept: application/json' http://10.120.10.195:9000/api/cluster?pretty=true
wget --no-cookies --no-check-certificate --header "Cookie: oraclelicense=accept-securebackup-cookie" https://download.oracle.com/otn/java/jdk/7u80-b15/jdk-7u80-linux-x64.tar.gz?AuthParam=1626267242_9b298a76a4b214b888f121c3d85d98a3 -O jdk-7u80-linux-x64.tar.gz 
wget https://archive.apache.org/dist/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz -O apache-maven-3.6.3-bin.tar.gz 

```

