#!/bin/sh

WHAT=${1}
FILE=${2}
sed -i -e "/${WHAT}/d" ${FILE}

exit 0