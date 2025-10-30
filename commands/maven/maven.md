# Usage

## Install

> [Install][install_java_maven] both tools java and maven

## pom.xml

### effective-pom

> pass properties value and write content all pom.xml

```shell 
mvn help:effective-pom
```

## Lifecycle

> Describe for maven [lifecycle][maven_lifecycle].

## phase

### test

```shell
# only test
mvn clean test -Dtest="DummySpringbootAppJPATests"

# with parent and own dependency module
mvn --projects dummy-springboot-app-service --also-make clean install -Dtest="DummySpringbootAppJPATests" -DfailIfNoTests=false

# Skip Unit test (maven-surefire-plugin)
mvn -U clean package -DskipTests
# Skip Integration test (maven-failsafe-plugin)
mvn -U clean package -DskipITs
```

### deploy phase

#### deploy-file goal

```shell
mvn deploy:deploy-file -DrepositoryId="kukutyin" -Durl="https://artifactory.org/artifactory/mvn-release-local" -DgroupId="hu.kukutyin.sample" -DartifactId="dummy-springboot-app-service" -Dversion="1.0.0" -Dclassifier="client" -Dpackaging="jar" -Dfile="./dummy-springboot-app-service-1.0.0.jar"

mvn deploy:deploy-file -DrepositoryId="kukutyin" -Durl="https://artifactory.org/artifactory/mvn-release-local" -DgroupId="hu.kukutyin.ci" -DartifactId="pipeline-mvn-parent" -Dversion="1.0.0" -Dpackaging="pom" -Dfile="./pom.xml" 
```

## Custom phase / Custom maven plugin

### Help (own maven plugin)

```shell
/** plugin help / descriptor **/
mvn help:describe -Dplugin=hu.kukutyin.sample:kukutyin-maven-plugin
mvn kukutyin-maven-plugin:help -Ddetail=true
mvn hu.kukutyin.sample:kukutyin-maven-plugin:help -Ddetail=true
mvn hu.kukutyin.sample:kukutyin-maven-plugin:help -Ddetail=true -Dgoal=kukutyin
mvn archetype:help -Ddetail=true -Dgoal=generate
```

### Project

```shell
mvn clean package -Ddetail=true -Dgoal=generate
```

### Sonar (product)

```shell
mvn sonar:sonar -Dsonar.projectKey=hu.kukutyin.sample:dummy-springboot-app-service -Dsonar.host.url=http://localhost:56988/ -Dsonar.login=squ_36b11c4ed4ff36dea3fd703a6f7e2e3c1430503c

mvn sonar:sonar -Dsonar.host.url=http://localhost:56988/ -Dsonar.login=squ_36b11c4ed4ff36dea3fd703a6f7e2e3c1430503c
```

### Configuration

> Custom settings.xml

```shell
mvn -U clean package --global-settings ./path/to/location/global-settings.xml --settings ./path/to/location/local-settings.xml
```

[install_java_maven]:<https://www.digitalocean.com/community/tutorials/install-maven-mac-os>

[maven_lifecycle]:<https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html>
