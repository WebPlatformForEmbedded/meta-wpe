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

lint() {
    local local_list="$1"
    echo "$oelint_args $local_list" | xargs oelint-adv 2>>"$tmpfile" &
}

lint_in_foreground () {
    local filelist="$1"
    local filelist_len=$(echo "$filelist" | wc -l | tr -d " " )

    local iter=1
    for f in $filelist;
    do
        echo "Linting [$iter/$filelist_len] $f [oelint-adv $oelint_args $f]"
        echo "$oelint_args $f" | xargs oelint-adv 2>>"$tmpfile"
        ((iter++))
    done
}

lint_in_background () {
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
            lint "$local_list"
            local_list=""
        fi
        ((iter++))
    done
    # make sure to include the rest of the files if any
    if [[ "$local_list" != "" ]];
    then
        lint "$local_list"
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


COLOR_OUTPUT=0
FOREGROUND=0
FILELIST="git"
FILES_TO_LINT=""

tmpfile="$(dirname $0)/linter.out.tmp"
outputfile="$(dirname $0)/linter.out"
oelint_args="--rulefile $(dirname $0)/rulefile.json --quiet --noinfo"

git_topdir=$(git rev-parse --show-toplevel)

usage () {
    echo "$0 [--color] [--allfiles]"
    echo -e "\t" "--help" "\t" "show help message"
    echo -e "\t" "--color" "\t" "enable color output"
    echo -e "\t" "--filelist type [filename]" "\t" "filelist type to use:"
    echo -e "\t\t" "git - git indexex files"
    echo -e "\t\t" "all - all files matching pattern"
    echo -e "\t\t" "single - single file mode"
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
            COLOR_OUTPUT=1
            if [[ $COLOR_OUTPUT -eq 1 ]];
            then
                oelint_args="$oelint_args --color"
            fi
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
        *)
            echo "Unknown paramter $1"
            usage
            shift
            exit 1
            ;;
    esac
done

echo "Starting $0..."

# Make sure to kill running processes when stopping the script during runtime
trap 'kill -SIGKILL -$$' SIGINT

check_dependencies

if [[ "$FILELIST" == "git" ]];
then
    echo "Using 'git' as filelist..."
    FILES_TO_LINT=$(create_filelist_git)
elif [[ "$FILELIST" == "all" ]];
then
    echo "Using 'all' as filelist..."
    FILES_TO_LINT=$(create_filelist_all)
elif [[ "$FILELIST" == "single" ]];
then
    echo "Using '$FILES_TO_LINT' as filelist..."
else
    echo "Unknown filelist type!"
    exit 1;
fi

if [[ "$FOREGROUND" -eq 1 ]];
then
    echo "Linting in foreground."
    lint_in_foreground "$FILES_TO_LINT"
else
    echo "Linting in background."
    lint_in_background "$FILES_TO_LINT"
fi

echo "Preparing the output..."
prepare_outputfile

# remove the trap
trap - SIGINT
echo "Done!"
