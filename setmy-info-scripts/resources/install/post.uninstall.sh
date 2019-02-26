#!/bin/sh

# De-install script, executed at uninstall end.
rm -f /etc/profile.d/setmy-info.sh
rm -f /opt/setmy.info/bin/smi-test

exit ${?}
