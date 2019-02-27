#!/bin/sh

# Executed by installer at install step end.
SMI_PROVIDER=setmy.info
ln -f -s /opt/${SMI_PROVIDER}/etc/profile.d/setmy-info.sh /etc/profile.d/setmy-info.sh
ln -f -s /opt/${SMI_PROVIDER}/bin/smi-binary /opt/${SMI_PROVIDER}/bin/smi-test

exit ${?}
