#!/bin/bash

projectdir=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )
basedir=$projectdir/../../..
. "$basedir/common.sh"

echo building
cd "$projectdir"
mvn clean install

echo deploying to glassfish
$asadmin deploy --force=true "$(convert_path "$projectdir/target/dukeetf.war")"
