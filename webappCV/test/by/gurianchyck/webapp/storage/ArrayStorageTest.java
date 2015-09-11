package by.gurianchyck.webapp.storage;

import by.gurianchyck.webapp.model.Contact;
import by.gurianchyck.webapp.model.ContactType;
import by.gurianchyck.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by Alexey Gurianchyck
 * 10.09.2015.
 */
public class ArrayStorageTest {
    private static Resume R1, R2, R3;
    private ArrayStorage storage = new ArrayStorage();
    static {
        R1 = new Resume("full name 1", "location 1");
        R1.addContact(new Contact(ContactType.MAIL, "mail@ya.ru"));
        R1.addContact(new Contact(ContactType.PHONE, "11111"));

        R2 = new Resume("full name 2", "location 2");
        R2.addContact(new Contact(ContactType.SKYPE, "skype2"));
        R2.addContact(new Contact(ContactType.PHONE, "11111"));

        R3 = new Resume("full name 3", "location 3");
        R3.addContact(new Contact(ContactType.MAIL, "mail@ya.ru"));
        R3.addContact(new Contact(ContactType.PHONE, "11111"));
    }
    @BeforeClass
    public static void beforeClass(){
        // the same as static
    }
    @Before
    public void before(){
        storage.clear();
        storage.save(R1);
        storage.save(R2);
        storage.save(R3);
    }
    @Test
    public void testClear() throws Exception {
        storage.clear();
        Assert.assertEquals(0, storage.size());

    }

    @Test
    public void testSave() throws Exception {
        Assert.assertEquals(3, storage.size());

    }

    @Test
    public void testUpdate() throws Exception {
        R1.addContact(new Contact(ContactType.ICQ, "icq"));
        storage.update(R1);
        Assert.assertEquals(R1, storage.load(R1.getUuid()));

    }

    @Test
    public void testLoad() throws Exception {
        R1.getUuid();
        Assert.assertEquals(storage.load(R1.getUuid()), R1);
    }

    @Test
    public void testDelete() throws Exception {
        storage.delete(R1.getUuid());
        Assert.assertEquals(2, storage.size());
//        Assert.assertEquals(null, storage.load(R1.getUuid()));
    }

    @Test
    public void testGetAllSorted() throws Exception {


    }

    @Test
    public void testSize() throws Exception {
        Assert.assertEquals(3, storage.size());
    }
}