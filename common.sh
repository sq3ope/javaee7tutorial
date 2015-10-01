#!/bin/bash

glassfish_version=4.1

target_dir=$basedir/target
glassfish_root=$target_dir/glassfish4

if [ "$OSTYPE" == "cygwin" ]; then
	asadmin="cmd /c $(cygpath -aw "$glassfish_root/bin/asadmin.bat")"
else
	asadmin="$glassfish_root/bin/asadmin"
fi

if [ ! -d "$target_dir" ]; then
    echo creating target dir
    mkdir -p "$target_dir"
fi

function convert_path {
    if [ "$OSTYPE" == "cygwin" ]; then
        echo "$(cygpath -aw "$1")"
    else
        echo "$1"
    fi
}
