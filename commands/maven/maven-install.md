# Install

* Download Maven [apache-maven][maven_download]
* ```shell
  MAVEN_VERSION=3.9.9
  tar -xvf ~/Downloads/apache-maven-${MAVEN_VERSION}-bin.tar.gz
  mkdir -p ~/portable/maven/
  mv ~/Downloads/apache-maven-${MAVEN_VERSION} ~/portable/maven/
  ```
* .zshrc
  ```shell
  MAVEN_VERSION=3.9.9
  MAVEN_HOME=~/portable/maven/apache-maven-${MAVEN_VERSION}
  export PATH="${MAVEN_HOME}/bin:${PATH}"
  ```

[maven_download]:<https://dlcdn.apache.org/maven/maven-3/3.9.9/binaries/apache-maven-3.9.9-bin.tar.gz>
