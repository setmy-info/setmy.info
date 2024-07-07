package ee.pub.platform.lib.stateless;

import ee.pub.platform.lib.SysException;
import ee.pub.platform.lib.ui.PersonUI;
import javax.ejb.Remote;
import javax.inject.Named;

@Named
@Remote
public interface SysContextRemote {

    public PersonUI getPersonUI() throws SysException;
}
