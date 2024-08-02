/**
 * Copyright (c) Imre Tabur
 * All rights reserved.
 *
 * @author Imre Tabur
 */
package ee.pub.platform.lib;

import java.util.Locale;
import java.util.ResourceBundle;

public class LangLoader {

    private String resourceBase;

    public LangLoader() {
    }

    public LangLoader(String baseName) {
        this.resourceBase = baseName;
    }

    public String getString(String key, Locale locale) {
        if (locale == null) {
            locale = Locale.getDefault();
        }
        if (this.resourceBase != null) {
            ResourceBundle resources = ResourceBundle.getBundle(resourceBase, locale);
            if (key != null) {
                String ret = resources.getString(key);
                if (ret != null) {
                    return ret;
                }
            }
        }
        return "";
    }

    /**
     * @return the resourceBase
     */
    public String getResourceBase() {
        return resourceBase;
    }

    /**
     * @param resourceBase the resourceBase to set
     */
    public void setResourceBase(String resourceBase) {
        this.resourceBase = resourceBase;
    }
}
