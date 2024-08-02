/**
 * Copyright (c) Imre Tabur
 * All rights reserved.
 *
 * @author Imre Tabur
 */
package ee.pub.platform.web.app.struts;

import com.opensymphony.xwork2.ActionSupport;
import ee.pub.platform.lib.stateless.SysContextRemote;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.log4j.Logger;

public class BaseAction extends ActionSupport {

    private InitialContext initialContext = null;
    @EJB(name = "ejb/SysContextBeanJNDI")
    protected SysContextRemote bean;

    public BaseAction() {
        try {
            initialContext = new InitialContext();
            if (bean == null) {
                bean = (SysContextRemote) initialContext.lookup("ejb/SysContextBeanJNDI");
            }

            if (bean == null) {
		logger.error("Bean still Null");
            }
        } catch (NamingException ex) {
	    logger.error("Bean still Null");
        }
    }
    private static Logger logger = Logger.getLogger(BaseAction.class);
    //initialContext.lookup("ejb/SysContextBeanJNDI");

    public String getClassName() {
        String canName = this.getClass().getCanonicalName();
        return canName;
    }

    public void logInfo(String info) {
        logger.info(this.getClassName() + " : " + info);
    }

    public void logError(String error) {
        logger.error(this.getClassName() + " : " + error);
    }
}
