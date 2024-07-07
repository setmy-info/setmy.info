/**
 * Copyright (c) Imre Tabur
 * All rights reserved.
 *
 * @author Imre Tabur
 */
package ee.pub.platform.lib;

public class SysException extends Exception {

    public SysException() {
        super();
    }

    public SysException(String msg) {
        super(msg);
    }

    public SysException(Exception ex) {
        super(ex);
    }
}
