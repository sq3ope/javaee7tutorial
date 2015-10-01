#!/bin/bash

basedir=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )
. "$basedir/common.sh"

echo starting derby database
$asadmin start-database

echo starting glassfish
$asadmin start-domain
