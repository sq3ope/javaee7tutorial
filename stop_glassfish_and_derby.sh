#!/bin/bash

basedir=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )
. "$basedir/common.sh"

echo stopping glassfish
$asadmin stop-domain

echo stopping derby database
$asadmin stop-database
