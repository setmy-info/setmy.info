#!/bin/sh

CUR_DIR=`pwd`

# diff -Naur /usr/src/openvpn-2.3.2 /usr/src/openvpn-2.3.4 > openvpn.patch
# patch -p3 < /root/openvpn.patch

# 1. Create temporary folder structure
CHECKOUT_DIR=./checkout
if [ ! -d ${CHECKOUT_DIR} ]; then
    mkdir -p ${CHECKOUT_DIR}
fi

# 2. Include config
PROJECTS_NAMES=`ls ./repos | sort`
for PROJECT_NAME in ${PROJECTS_NAMES}; do
    . ./repos/${PROJECT_NAME}
    if [ ! -d "${CHECKOUT_DIR}/${PROJECT_NAME}" ]; then
	${REPO_TYPE} clone ${REPO_URL} ${CHECKOUT_DIR}/${PROJECT_NAME}
	cd ${CHECKOUT_DIR}/${PROJECT_NAME}
	${REPO_TYPE} checkout ${REPO_BRANCH}
	${REPO_TYPE} branch
	cd ${CUR_DIR}
    fi
done

# 3. Apply projects
for PROJECT_NAME in ${PROJECTS_NAMES}; do
    # Coping
    cp -R ${CHECKOUT_DIR}/${PROJECT_NAME}/* ./
    # Post copy
    if [ "$(ls ./patch/*${PROJECT_NAME}.post.copy | wc -l)" -ge "1" ]; then
        PATCH_NAMES=`ls ./patch/*${PROJECT_NAME}.post.copy | sort`
	for PATCH_NAME in ${PATCH_NAMES}; do
	    if [ -f ${PATCH_NAME} ]; then
		. ${PATCH_NAME}
	    fi
	done
    else
	echo "No post copy files";
    fi;
    # Patching
    if [ "$(ls ./patch/*${PROJECT_NAME}.patch | wc -l)" -ge "1" ]; then
        PATCH_NAMES=`ls ./patch/*${PROJECT_NAME}.patch | sort`
	for PATCH_NAME in ${PATCH_NAMES}; do
    	if [ -f ${PATCH_NAME} ]; then
    	    mkdir -p ${CHECKOUT_DIR}
    	    patch -f -s -p1 < ${PATCH_NAME}
    	fi
        done
    else
	echo "No patch files";
    fi;
    # Post (after) Patch
    if [ "$(ls ./patch/*${PROJECT_NAME}.post.patch | wc -l)" -ge "1" ]; then
        PATCH_NAMES=`ls ./patch/*${PROJECT_NAME}.post.patch | sort`
	for PATCH_NAME in ${PATCH_NAMES}; do
	    if [ -f ${PATCH_NAME} ]; then
		. ${PATCH_NAME}
	    fi
	done
    else
	echo "No post/after patch files";
    fi;
    # Change
    if [ "$(ls ./patch/*${PROJECT_NAME}.change | wc -l)" -ge "1" ]; then
        PATCH_NAMES=`ls ./patch/*${PROJECT_NAME}.change | sort`
	for PATCH_NAME in ${PATCH_NAMES}; do
	    if [ -f ${PATCH_NAME} ]; then
		. ${PATCH_NAME}
	    fi
	done
    else
	echo "No change files";
    fi;
done

cd ${CUR_DIR}

exit 0