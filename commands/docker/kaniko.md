# Usage

1. [kaniko - Build Images In Kubernetes][kaniko_readme]
1. CI
   ```shell
   variables:
     TESTING_DIR: "VALAMI"
     TESTING_DIR_VERSION: "v0.0.0"
   stage: build
   needs:
     - "valami-dockerfile-lint"
   extends: .kaniko-config
   image:
     name: gcr.io/kaniko-project/executor:v1.20.1-debug
     entrypoint: [""]
   script:
     - echo "{\"auths\":{\"${DOCKERHUB_URL}\":{\"auth\":\"${DOCKER_AUTH}\"}}}" > /kaniko/.docker/config.json
     - /kaniko/executor
       --context "${CI_PROJECT_DIR}/${TESTING_DIR}"
       --dockerfile "${CI_PROJECT_DIR}/${TESTING_DIR}/${TESTING_DIR}${TESTING_DIR_VERSION}.Dockerfile"
       --destination "${DOCKERHUB_URL}/${TESTING_DIR}:${CI_COMMIT_TAG}${TESTING_DIR_VERSION}"
       --build-arg robot_framework=${TESTING_DIR}
    ```


[kaniko_readme]:<https://github.com/GoogleContainerTools/kaniko?tab=readme-ov-file>
