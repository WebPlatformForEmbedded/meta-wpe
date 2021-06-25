#!/usr/bin/env bash

git_topdir=$(git rev-parse --show-toplevel)
hooks_dir="$git_topdir/.git/hooks"

if [ -f "$hooks_dir/pre-commit" -o -L "$hooks_dir/pre-commit" ];
then
    echo "Pre-commit already exists. Do you want to overwrite [y/n]?"
    read overwrite

    [[ "$overwrite" != "y" ]] && echo "Not installing hooks. Exiting." && exit 0
fi

echo "Installing symbolic link for pre-commmit in $hooks_dir"
ln -sf "$(realpath $(dirname $0)/pre-commit)" "$hooks_dir/pre-commit" 
