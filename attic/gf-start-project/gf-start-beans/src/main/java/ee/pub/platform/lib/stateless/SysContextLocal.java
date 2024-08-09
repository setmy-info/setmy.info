package ee.pub.platform.lib.stateless;

import ee.pub.platform.lib.SysException;
import ee.pub.platform.lib.ui.PersonUI;
import javax.ejb.Local;

@Local
public interface SysContextLocal {

    public PersonUI getPersonUI() throws SysException;
}
