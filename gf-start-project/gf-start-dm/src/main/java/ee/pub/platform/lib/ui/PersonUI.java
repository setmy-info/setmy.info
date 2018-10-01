/**
 * Copyright (c) Imre Tabur
 * All rights reserved.
 *
 * @author Imre Tabur
 */
package ee.pub.platform.lib.ui;

import ee.pub.platform.lib.Person;

public class PersonUI {

    private Person person;
    private String firstNameLabel;
    private String lastNameLabel;

    /**
     * @return the person
     */
    public Person getPerson() {
        return person;
    }

    /**
     * @param person the person to set
     */
    public void setPerson(Person person) {
        this.person = person;
    }

    /**
     * @return the firstNameLabel
     */
    public String getFirstNameLabel() {
        return firstNameLabel;
    }

    /**
     * @param firstNameLabel the firstNameLabel to set
     */
    public void setFirstNameLabel(String firstNameLabel) {
        this.firstNameLabel = firstNameLabel;
    }

    /**
     * @return the lastNameLabel
     */
    public String getLastNameLabel() {
        return lastNameLabel;
    }

    /**
     * @param lastNameLabel the lastNameLabel to set
     */
    public void setLastNameLabel(String lastNameLabel) {
        this.lastNameLabel = lastNameLabel;
    }
}
