cmake_minimum_required (VERSION 3.12.1)

PROJECT (setmy-info-scripts)

SET (PROJECT_VERSION                    5)
SET (PROJECT_VERSION_MAJOR              1)
SET (PROJECT_VERSION_MINOR              0)
SET (PROJECT_VERSION_PATCH              0)
SET (PROJECT_VERSION_TWEAK              0)

SET (PROJECT_DESCRIPTION_SUMMARY        "setmy.info scripts")
SET (PROJECT_DESCRIPTION                "setmy.info scripts Installation Example")
SET (PROJECT_VENDOR                     "Hear And See Systems LLC")
SET (PROJECT_MAINTAINER                 "Imre Tabur <imre.tabur@eesti.ee>")
SET (PROJECT_WWW                        "https://setmy.info")
SET (PROJECT_LICENSE                    "MIT")

INCLUDE(./resources/cmake/variables.txt)

ADD_CUSTOM_TARGET(serviceScripts        cp ${RESOURCES_PATH}/in/*.service ${BINARY_OUTPUT_PATH})
ADD_CUSTOM_TARGET(setprofiles           cp -R ${RESOURCES_PATH}/profiles/* ${LIBRARY_OUTPUT_PATH}/profiles)
ADD_DEPENDENCIES(build setprofiles serviceScripts)
ADD_DEPENDENCIES(setprofiles dirs)
ADD_DEPENDENCIES(serviceScripts dirs)

CONFIGURE_FILE("${RESOURCES_PATH}/in/smi-location.in"   "${BINARY_OUTPUT_PATH}/smi-location")

INSTALL(PROGRAMS  "${BINARY_OUTPUT_PATH}/term"          DESTINATION bin)
INSTALL(PROGRAMS  "${BINARY_OUTPUT_PATH}/smi-location"  DESTINATION bin)
INSTALL(DIRECTORY "${LIBRARY_OUTPUT_PATH}/profiles"     DESTINATION lib)

INCLUDE(./resources/cmake/install.txt)
INCLUDE(./resources/cmake/packaging.txt)
