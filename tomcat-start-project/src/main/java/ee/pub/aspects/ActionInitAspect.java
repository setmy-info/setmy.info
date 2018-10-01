package ee.pub.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@Aspect
public class ActionInitAspect {

    private static final Logger LOG = Logger.getLogger(ActionInitAspect.class);

    @Before("execution(String ee.pub.web.actions.*Action.add(..))")
    public void initBeforeAdd(final JoinPoint joinPoint) {
        LOG.debug("==================AOP==========================");
    }
}
