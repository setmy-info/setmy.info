cmake_minimum_required (VERSION 3.13.2)

PROJECT (setmy-info-scripts)

SET (PROJECT_VERSION                    5)
SET (PROJECT_VERSION_MAJOR              1)
SET (PROJECT_VERSION_MINOR              2)
SET (PROJECT_VERSION_PATCH              3)
SET (PROJECT_VERSION_TWEAK              4)

SET (PROJECT_DESCRIPTION_SUMMARY        "setmy.info scripts")
SET (PROJECT_DESCRIPTION                "setmy.info scripts Installation Example")
SET (PROJECT_VENDOR                     "Hear And See Systems LLC")
SET (PROJECT_MAINTAINER                 "Imre Tabur <imre.tabur@eesti.ee>")
SET (PROJECT_WWW                        "https://setmy.info")
SET (PROJECT_LICENSE                    "MIT")

INCLUDE(./resources/cmake/variables.txt)

ADD_CUSTOM_TARGET(setprofiles cp -R ${RESOURCES_PATH}/profiles/* ${LIBRARY_OUTPUT_PATH}/profiles)
ADD_DEPENDENCIES(build setprofiles)
ADD_DEPENDENCIES(setprofiles dirs)

CONFIGURE_FILE("${SOURCES_PATH}/example" "${BINARY_OUTPUT_PATH}/example")

INSTALL(PROGRAMS  "${BINARY_OUTPUT_PATH}/term"          DESTINATION bin)
INSTALL(PROGRAMS  "${BINARY_OUTPUT_PATH}/example"       DESTINATION bin)
INSTALL(DIRECTORY "${LIBRARY_OUTPUT_PATH}/profiles"     DESTINATION lib)
IF(CMAKE_SYSTEM_NAME STREQUAL "Linux")
    INSTALL(PROGRAMS "${BINARY_OUTPUT_PATH}/example.service"    DESTINATION bin)
ENDIF()

INCLUDE(./resources/cmake/install.txt)
INCLUDE(./resources/cmake/packaging.txt)
