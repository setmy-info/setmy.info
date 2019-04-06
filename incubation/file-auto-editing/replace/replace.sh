#!/bin/sh

WHAT=${1}
WITH=${2}
FILE=${3}
sed -i -e "s/${WHAT}/${WITH}/g" ${FILE}

exit 0