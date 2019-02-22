#!/bin/sh

# De-install script, executed at uninstall end.
rm -f /etc/profile.d/example.sh

exit ${?}
