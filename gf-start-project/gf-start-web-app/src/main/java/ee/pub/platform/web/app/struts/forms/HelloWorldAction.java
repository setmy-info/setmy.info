/*
 * Copyright 2006 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ee.pub.platform.web.app.struts.forms;

import java.util.Date;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.conversion.annotations.Conversion;
import com.opensymphony.xwork2.conversion.annotations.TypeConversion;
import ee.pub.platform.lib.LangLoader;
import ee.pub.platform.web.app.struts.BaseAction;
import java.util.Locale;

@Conversion()
public class HelloWorldAction extends BaseAction {

    private Date dateNow;
    private String name;
    private static final String BASE_NAME = "person";  // person.properties
    private static final String FIRST_NAME_LABEL = "person.first.name.label";
    private static final String LAST_NAME_LABEL = "person.last.name.label";

    @TypeConversion(converter = "ee.pub.platform.web.app.struts.DateConverter")
    @RequiredFieldValidator(message = "Please enter the date")
    public void setDateNow(Date now) {
        this.dateNow = now;
    }

    public Date getDateNow() {
        return dateNow;
    }

    @RequiredStringValidator(message = "Please enter a name", trim = true)
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

    public String getLabel() {
        try {
            // TODO : BUG : Issue 9.
            LangLoader loader = new LangLoader(BASE_NAME);
            Locale loc = new Locale("et", "EE");
            String tmpFLabel = loader.getString(FIRST_NAME_LABEL, loc);
            String tmpLLabel = loader.getString(LAST_NAME_LABEL, loc);
        } catch (Exception ex) {
        }
        return "";

    }
}
