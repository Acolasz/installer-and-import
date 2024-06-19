#!/usr/bin/env bash

usage() {
  cat <<EOF
------------------------------
$0
  -ls                         ( list all )
------------------------------
EOF
}

listAll() {
  yq -r '.env.*.[].name' ./ssh-servers.yaml
}

listEnv() {
 echo 'listEnv'
}

fileCheck() {
 echo 'fileCheck'
}

OTHER_ARGUMENTS=()
SAMPLE=""
API_KEY=""

for arg in "$@"; do
  case $arg in
  -ls)
    listAll
    exit 0
    shift
    ;;
  -ls)
    list
    exit 0
    shift
    ;;
  *)
    usage
    exit 0
    shift
    ;;
  esac
done

exit 0
