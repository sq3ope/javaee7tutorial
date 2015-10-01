#!/bin/bash

projectdir=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )
basedir=$projectdir/..
. "$basedir/common.sh"

echo building
cd "$projectdir"
mvn clean install

echo deploying to glassfish
$asadmin deploy --force=true "$(convert_path "$projectdir/dukes-age/target/dukes-age.war")"
$asadmin deploy --force=true "$(convert_path "$projectdir/firstcup/target/firstcup.war")"
