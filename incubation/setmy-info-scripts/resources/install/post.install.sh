#!/bin/sh

# Executed by installer at install step end.
ln -f -s /opt/setmy.info/etc/profile.d/example.sh /etc/profile.d/example.sh

exit ${?}
