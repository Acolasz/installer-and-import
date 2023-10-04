# Usage

## Install

> [Install][install_java_maven] both tools java and maven
> 
> [Install][install_jad] JAD

## Commands

### JAD

#### CLI
```shell
jad -o -r -sjava -dsrc ./**/*.class
```

#### GUI

> We are working with [GUI][jad_usage_with_gui].

### JAVA

#### keytool
```shell
# List
keytool -v -list -keystore "d:\Portable\Java\jdk1.8.0_172\jre\lib\security\cacerts"
keytool -list -keystore "d:\Portable\Java\jdk1.8.0_172\jre\lib\security\cacerts" -alias kukutyin.hu
# Print
keytool -printcert -file "d:\workspace\repos\kukutyin\tmp\kukutyinca.cer"
# Import
keytool -importcert -file "d:\workspace\repos\kukutyin\tmp\kukutyinca.cer" -alias kukutyin.hu -keystore "d:\Portable\Java\jdk1.8.0_172\jre\lib\security\cacerts" -storepass changeit
# Delete
keytool -delete -alias kukutyin.hu -keystore "d:\Portable\Java\jdk1.8.0_172\jre\lib\security\cacerts" -storepass changeit
```

#### WSImport

> WSImport WSDL to Java Objects

```shell
d:\Portable\Java\jdk1.6.0_24\bin\wsimport.exe -p hu.kukutyin.web.sample https://kukutyin_ws:12345678@kukutyin.hu:476/kukutyinws/KukutyinV1?wsdl
d:\Portable\Java\jdk1.6.0_24\bin\wsimport.exe https://kukutyin_ws:12345678@kukutyin.hu:476/kukutyinws/KukutyinV1?wsdl
```

[install_java_maven]:<https://www.digitalocean.com/community/tutorials/install-maven-mac-os>

[install_jad]:<http://www.javadecompilers.com/jad>

[jad_usage_with_gui]:<https://github-releases.githubusercontent.com/32844456/f5daf300-272d-11ea-93dc-b7f9005f21d0?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAIWNJYAX4CSVEH53A%2F20210818%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210818T072439Z&X-Amz-Expires=300&X-Amz-Signature=21bd02bef81dd528a548cc7f03c20a2a3c47546673cc804e6e1a51cab82ffb0f&X-Amz-SignedHeaders=host&actor_id=53154839&key_id=0&repo_id=32844456&response-content-disposition=attachment%3B%20filename%3Djd-gui-windows-1.6.6.zip&response-content-type=application%2Foctet-stream>
