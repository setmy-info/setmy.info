/**
 * Copyright (c) Imre Tabur
 * All rights reserved.
 *
 * @author Imre Tabur
 */
package ee.pub.platform.lib.stateless;

import ee.pub.platform.lib.Person;
import ee.pub.platform.lib.SysException;
import ee.pub.platform.lib.ui.PersonUI;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.Lock;
import javax.ejb.Singleton;
import javax.inject.Named;

@Named
@ConcurrencyManagement(javax.ejb.ConcurrencyManagementType.CONTAINER)
@Singleton(name = "SysContextBean", mappedName = "ejb/SysContextBeanJNDI")
public class SysContextBean implements SysContextRemote {

    @Override
    @Lock(javax.ejb.LockType.READ)
    public PersonUI getPersonUI() throws SysException {
        PersonUI personUI = new PersonUI();
        Person person = new Person();
        personUI.setPerson(person);
        person.setFirstName("Bond");
        person.setLastName("James");
        return personUI;
    }
}
