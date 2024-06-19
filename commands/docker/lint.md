# Usage

1. CI
   ```shell
   variables:
     TESTING_DIR: "VALAMI"
     TESTING_DIR_VERSION: "v0.0.0"
   stage: lint
   image: hadolint/hadolint:v2.12.1-beta-debian
   script:
     - echo ./${TESTING_DIR}/${TESTING_DIR}${TESTING_DIR_VERSION}.Dockerfile
     - hadolint -t warning ./${TESTING_DIR}/${TESTING_DIR}${TESTING_DIR_VERSION}.Dockerfile
   ```