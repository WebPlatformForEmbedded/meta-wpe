#!/usr/bin/env bash

# This scripts should work with GNU/Linux and macOSX

check_dependencies () {
    ! command -v oelint-adv &> /dev/null && echo "Please install 'oelint-adv' using 'pip3 install oelint_adv'." && exit 1
    ! command -v git &> /dev/null && echo "Please install 'git' using your package manager." && exit 1
    ! command -v sed &> /dev/null && echo "Please install 'sed' using your package manager." && exit 1
    ! command -v sort &> /dev/null && echo "Please install 'sort' using your package manager." && exit 1
}

create_filelist_all () {
    find .. -name "*.bb" -or -name "*.bbappend"
}

create_filelist_git () {
    local files=$(git ls-tree --full-tree -r --name-only HEAD | grep -E ".bb$|$.bbappend$")
    echo "$files" | while read -r line; do echo "$git_topdir/$line"; done
}

create_filelist_diff () {
    local files=$(git diff --name-only | grep -E ".bb$|$.bbappend$")
    echo "$files" | while read -r line; do echo "$git_topdir/$line"; done
}

lint_files() {
    local local_list="$1"

    if [[ "$TO_STDOUT" -eq 1 ]];
    then
        echo "Linting $local_list"
        echo "$oelint_args $local_list" | xargs oelint-adv
    else
        if [[ "$FOREGROUND" -eq 1 ]];
        then
            echo "Linting $local_list"
            echo "$oelint_args $local_list" | xargs oelint-adv 2>>"$tmpfile"
        else
            echo "$oelint_args $local_list" | xargs oelint-adv 2>>"$tmpfile" &
        fi
    fi
}

lint () {
    local filelist="$1"
    local files_per_lint="${2:-1}"

    local filelist_len=$(echo "$filelist" | wc -l | tr -d " " )
    local iter=1
    local local_list=""

    echo "Starting linter for $filelist_len file(s) ($files_per_lint file(s) per process)..."
    for f in $filelist;
    do
        local_list="$local_list $f"
        if [[ $((iter % files_per_lint)) -eq 0 ]];
        then
            lint_files "$local_list"
            local_list=""
        fi
        ((iter++))
    done
    # make sure to include the rest of the files if any
    if [[ "$local_list" != "" ]];
    then
        lint_files "$local_list"
    fi

    echo "Waiting for liniting to complete..." 
    wait
    echo "Finished linting!"
}

prepare_outputfile () {
    # delete user specific data
    sed "s#$git_topdir#..#g" "$tmpfile" > sed.tmp && mv sed.tmp "$tmpfile"

    # delete potential duplicates
    sort -u "$tmpfile" -o "$tmpfile"

    # delete empty lines
    sed "/^[[:space:]]*$/d" "$tmpfile" > sed.tmp && mv sed.tmp "$tmpfile"

    # delete the tempfile
    mv "$tmpfile" "$outputfile"
}

enable_color () {
    oelint_args="$oelint_args --color"
}

FOREGROUND=0
TO_STDOUT=0
FILELIST="git"
FILES_TO_LINT=""

tmpfile="$(dirname $0)/linter.out.tmp"
outputfile="$(dirname $0)/linter.out"
oelint_args="--rulefile $(dirname $0)/rulefile.json --quiet --noinfo --constantfile $(dirname $0)/constantfile.json"

git_topdir=$(git rev-parse --show-toplevel)

usage () {
    echo "$0 [--help] [--color] [--filelist type] [--foreground] [--stdout]"
    echo -e "\t" "--help" "\t" "show help message"
    echo -e "\t" "--color" "\t" "enable color output"
    echo -e "\t" "--filelist type [filename]" "\t" "filelist type to use:"
    echo -e "\t\t" "git - git indexex files"
    echo -e "\t\t" "all - all relevant"
    echo -e "\t\t" "diff - git diff of unstaged files"
    echo -e "\t\t" "single - single file mode"
    echo -e "\t" "--foreground" "\t" "Use linter in foreground (lint file by file)"
    echo -e "\t" "--stdout" "\t" "Print results to stdout"
}

while [[ "$#" -ge 1 ]];
do
    case "$1" in
        --help)
            usage
            check_dependencies
            exit 1
            ;;
        --color)
            enable_color
            shift
            ;;
        --filelist)
            FILELIST="$2"
            if [[ "$FILELIST" == "single" ]];
            then
                FILES_TO_LINT="$3"
                shift
            fi
            shift
            shift
            ;;
        --foreground)
            FOREGROUND=1
            shift
            ;;
        --stdout)
            TO_STDOUT=1
            shift
            ;;
        *)
            echo "Unknown paramter $1"
            usage
            shift
            exit 1
            ;;
    esac
done

echo "Starting $0 with PID: $$..."

# Make sure to kill all running processes (including backgrounded) on SIGINT
trap 'kill -SIGKILL -$(ps -o "pgid" $$ | tail -n1)' SIGINT

check_dependencies

if [[ "$FILELIST" == "git" ]];
then
    echo "Using 'git' as filelist..."
    FILES_TO_LINT=$(create_filelist_git)
elif [[ "$FILELIST" == "all" ]];
then
    echo "Using 'all' as filelist..."
    FILES_TO_LINT=$(create_filelist_all)
elif [[ "$FILELIST" == "diff" ]];
then
    echo "Using 'diff' as filelist..."
    FILES_TO_LINT=$(create_filelist_diff)
    TO_STDOUT=1
    enable_color
elif [[ "$FILELIST" == "single" ]];
then
    echo "Using '$FILES_TO_LINT' as filelist..."
    TO_STDOUT=1
    enable_color
else
    echo "Unknown filelist type!"
    exit 1;
fi

lint "$FILES_TO_LINT"

if [[ "$TO_STDOUT" -eq 0 ]];
then
    echo "Preparing the output..."
    prepare_outputfile
fi

# remove the trap
trap - SIGINT
echo "Done!"
