#!/bin/bash

basedir=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )
. "$basedir/common.sh"

glassfish_zip=$target_dir/glassfish.zip

if [ ! -f "$glassfish_zip" ]; then
    echo downloading glassfish...
    wget "http://download.java.net/glassfish/${glassfish_version}/release/glassfish-${glassfish_version}-web.zip" -O "$glassfish_zip"
else
    echo glassfish already downloaded 	
fi

if [ -d "$glassfish_root" ]; then
    echo deleting old glassfish installation
    rm -rf "$glassfish_root"
fi

echo extracting glassfish
unzip "$glassfish_zip" -d "$target_dir"
