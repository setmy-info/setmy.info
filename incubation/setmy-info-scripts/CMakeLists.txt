cmake_minimum_required (VERSION 3.13.4)

# TODO
# 1. Makefile build without C/C++ check, add bash check instead
# 2. Add FreeBSD package build.
# 3. pre scripts to add for install and uninstall
# 4. Add Changelog file. Add some other fields too for RPM information.
# 5. clean target
# 6. etc profile.d
# 7. Try .term then in installation location profiles

PROJECT (setmy-info-scripts)

IF(CMAKE_SYSTEM_NAME STREQUAL "Linux")
    SET (CMAKE_INSTALL_PREFIX  "/opt/setmy.info")
ENDIF()
IF(CMAKE_SYSTEM_NAME STREQUAL "FreeBSD")
    SET (CMAKE_INSTALL_PREFIX  "/usr/local")
ENDIF()

SET (PROJECT_MODULE_EPOCH_NUMBER  0)
SET (PROJECT_MODULE_VERSION_MAJOR 1)
SET (PROJECT_MODULE_VERSION_MINOR 0)
SET (PROJECT_MODULE_VERSION_BUILD 0)
SET (PROJECT_MODULE_VERSION_PATCH 0)
SET (PROJECT_MODULE_VERSION_REVISION 0)

SET (BUILD_PATH                ${PROJECT_BINARY_DIR}/build)
SET (RESOURCES_PATH            ${PROJECT_BINARY_DIR}/resources)
SET (SOURCES_PATH              ${PROJECT_BINARY_DIR}/src)
SET (EXECUTABLE_OUTPUT_PATH    ${BUILD_PATH}/bin)
SET (BINARY_OUTPUT_PATH        ${BUILD_PATH}/bin)
SET (LIBRARY_OUTPUT_PATH       ${BUILD_PATH}/lib)
SET (MAN_OUTPUT_PATH           ${BUILD_PATH}/man)
SET (DOC_OUTPUT_PATH           ${BUILD_PATH}/doc)

SET(CMAKE_ALLOW_LOOSE_LOOP_CONSTRUCTS true)

ADD_CUSTOM_TARGET(build       ALL)
ADD_CUSTOM_TARGET(cleaning    )
ADD_CUSTOM_TARGET(dirs        mkdir -p ${BUILD_PATH} && mkdir -p ${MAN_OUTPUT_PATH}/man1 && mkdir -p ${LIBRARY_OUTPUT_PATH}/profiles)
ADD_CUSTOM_TARGET(gzipman     cp ${RESOURCES_PATH}/man/*.1 ${MAN_OUTPUT_PATH}/man1 && gzip -f ${MAN_OUTPUT_PATH}/man1/*.1)
ADD_CUSTOM_TARGET(setprofiles cp -R ${RESOURCES_PATH}/profiles/* ${LIBRARY_OUTPUT_PATH}/profiles)
ADD_CUSTOM_TARGET(buildClean  rm -rf ./build)
ADD_CUSTOM_TARGET(pkgClean    rm -rf ./${PROJECT_NAME}*.tar.gz ./${PROJECT_NAME}*.rpm ${PROJECT_NAME}*.sh)
ADD_CUSTOM_TARGET(cmakeClean  rm -rf ./*.cmake ./_CPack_Packages install_manifest.txt CMakeCache.txt)

ADD_DEPENDENCIES(build gzipman setprofiles)
ADD_DEPENDENCIES(gzipman dirs)
ADD_DEPENDENCIES(setprofiles dirs)
ADD_DEPENDENCIES(cleaning cmakeClean buildClean pkgClean)

INSTALL(PROGRAMS ${PROJECT_BINARY_DIR}/src/term DESTINATION bin)
INSTALL(PROGRAMS ${BINARY_OUTPUT_PATH}/example  DESTINATION bin)
INSTALL(DIRECTORY "${LIBRARY_OUTPUT_PATH}/profiles" DESTINATION lib)
INSTALL(FILES "${MAN_OUTPUT_PATH}/man1/example.1.gz" DESTINATION man/man1)

CONFIGURE_FILE("${SOURCES_PATH}/example" "${BINARY_OUTPUT_PATH}/example")

#https://cmake.org/cmake/help/v3.14/manual/cpack-generators.7.html

#https://cmake.org/cmake/help/v3.14/cpack_gen/rpm.html#cpack_gen:CPack%20RPM%20Generator
SET(CPACK_RPM_PACKAGE_DEBUG               true)
SET(CPACK_PACKAGE_NAME                    "${PROJECT_NAME}")
SET(CPACK_PACKAGE_VENDOR                  "Hear And See Systems LLC")
SET(CPACK_PACKAGE_DESCRIPTION             "setmy.info scripts")
SET(CPACK_PACKAGE_DESCRIPTION_SUMMARY     "setmy.info scripts Installation Example")
SET(CPACK_PACKAGE_VERSION                 "${PROJECT_MODULE_VERSION_MAJOR}.${PROJECT_MODULE_VERSION_MINOR}.${PROJECT_MODULE_VERSION_PATCH}")
SET(CPACK_PACKAGE_FILE_NAME               "${CPACK_PACKAGE_NAME}-${PROJECT_MODULE_VERSION_MAJOR}.${PROJECT_MODULE_VERSION_MINOR}.${PROJECT_MODULE_VERSION_PATCH}.noarch")
SET(CPACK_PACKAGE_VERSION_MAJOR           "${PROJECT_MODULE_VERSION_MAJOR}")
SET(CPACK_PACKAGE_VERSION_MINOR           "${PROJECT_MODULE_VERSION_MINOR}")
SET(CPACK_PACKAGE_VERSION_PATCH           "${PROJECT_MODULE_VERSION_PATCH}")
SET(CPACK_PACKAGE_INSTALL_DIRECTORY       "CPack Component Example")
SET(CPACK_RPM_PRE_INSTALL_SCRIPT_FILE     "${PROJECT_SOURCE_DIR}/src/main/bash/pre.install.sh")
SET(CPACK_RPM_PRE_UNINSTALL_SCRIPT_FILE   "${PROJECT_SOURCE_DIR}/src/main/bash/pre.uninstall.sh")
SET(CPACK_RPM_POST_INSTALL_SCRIPT_FILE    "${PROJECT_SOURCE_DIR}/src/main/bash/post.install.sh")
SET(CPACK_RPM_POST_UNINSTALL_SCRIPT_FILE  "${PROJECT_SOURCE_DIR}/src/main/bash/post.uninstall.sh")
SET(CPACK_RPM_PACKAGE_SUMMARY             "Summary")
SET(CPACK_RPM_PACKAGE_NAME                ${PROJECT_NAME})
SET(CPACK_GENERATOR                       "TGZ;RPM;STGZ")
SET(CPACK_PACKAGING_INSTALL_PREFIX        ${CMAKE_INSTALL_PREFIX})
SET(CPACK_PACKAGE_RELOCATABLE             TRUE)
# SET(CPACK_RPM_PACKAGE_REQUIRES            "some-else >= 1.0.0")

# https://cmake.org/cmake/help/v3.14/cpack_gen/deb.html
# http://www.debian.org/doc/debian-policy/ch-controlfields.html
# https://wiki.ubuntu.com/PackagingGuide/Basic?action=show&redirect=HowToBuildDebianPackagesFromScratch/
SET(CPACK_DEBIAN_PACKAGE_NAME             "${PROJECT_NAME}")
SET(CPACK_DEBIAN_PACKAGE_VERSION          "${CPACK_PACKAGE_VERSION}")
SET(CPACK_DEBIAN_PACKAGE_MAINTAINER       "Imre Tabur <imre.tabur@eesti.ee>")
SET(CPACK_DEBIAN_PACKAGE_DESCRIPTION      "Example cmake")
#SET(CPACK_DEBIAN_PACKAGE_DEPENDS          "some-else (>= 1.0.0)")
SET(CPACK_DEBIAN_PACKAGE_PRIORITY         "optional")
SET(CPACK_DEBIAN_PACKAGE_SECTION          "libs")
SET(CPACK_DEBIAN_ARCHITECTURE             "all")

# https://cmake.org/cmake/help/v3.14/cpack_gen/freebsd.html

include(CPack)
