#!/bin/sh

# Executed by installer at install step end.
ln -f -s /opt/setmy.info/etc/profile.d/setmy-info.sh /etc/profile.d/setmy-info.sh
ln -f -s /opt/setmy.info/bin/smi-binary /opt/setmy.info/bin/smi-test

exit ${?}
