#!/bin/sh

# Executed by installer at install step end.
ln -s /opt/setmy.info/etc/profile.d/example.sh /etc/profile.d/example.sh

exit ${?}
