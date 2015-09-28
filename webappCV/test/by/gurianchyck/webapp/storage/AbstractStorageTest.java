package by.gurianchyck.webapp.storage;

import by.gurianchyck.webapp.WebappException;
import by.gurianchyck.webapp.model.ContactType;
import by.gurianchyck.webapp.model.Resume;
import by.gurianchyck.webapp.model.SectionType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Alexey Gurianchyck
 * 21.09.2015.
 */
public class AbstractStorageTest {

    private Resume R1, R2, R3;
    protected IStorage storage;


    @BeforeClass
    public static void beforeClass(){
        // the same as static
    }
    @Before
    public void before(){
        R1 = new Resume("full name 1", "location 1");
        R1.addContact(ContactType.MAIL, "mail@ya.ru");
        R1.addContact(ContactType.PHONE, "11111");

        R2 = new Resume("full name 2", "location 2");
        R2.addContact(ContactType.SKYPE, "skype2");
        R2.addContact(ContactType.PHONE, "11111");

        R3 = new Resume("full name 3", "location 3");
        R3.addContact(ContactType.MAIL, "mail@ya.ru");
        R3.addContact(ContactType.PHONE, "11111");
        storage.clear();
        storage.save(R3);
        storage.save(R1);
        storage.save(R2);
//        R1.addObjective("Objective1");
//        R1.addMultiTextSection(SectionType.ACHIEVEMENT,"Achievement1", "Achievement2");
//        R1.addMultiTextSection(SectionType.QUALIFICATION,"Java", "SQL");
        //TODO add  EXPERIENCE , EDUCATION

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
    @Test(expected = WebappException.class)
    public void testSaveNotFound() throws Exception {
        storage.save(R1);
        storage.save(R1);

    }

    @Test
    public void testUpdate() throws Exception {
        R1.addContact(ContactType.ICQ, "icq");
        storage.update(R1);
        Assert.assertEquals(R1, storage.load(R1.getUuid()));

        R2.setFullName("updated 2");
        storage.update(R2);
        Assert.assertEquals(R2, storage.load(R2.getUuid()));

    }
    @Test(expected = WebappException.class)
    public void testUpdateNotFound() throws Exception {
        storage.update(new Resume("name", "location"));
    }

    @Test
    public void testLoad() throws Exception {

        Assert.assertEquals(R1, storage.load(R1.getUuid()));
        Assert.assertEquals(R2, storage.load(R2.getUuid()));
        Assert.assertEquals(R3, storage.load(R3.getUuid()));
    }

    @Test(expected = WebappException.class)
    public void testLoadNotFound() throws Exception {
        storage.load("uid");
    }

    @Test(expected = WebappException.class)
    public void testDeleteNotFound() throws Exception{
        storage.load("ddfdf");
    }

    @Test
    public void testDelete() throws Exception {
        storage.delete(R1.getUuid());
        Assert.assertEquals(2, storage.size());

    }

    @Test
    public void testGetAllSorted() throws Exception {
//        Resume [] src = new Resume[]{R1,R2,R3};
//        Arrays.sort(src);
//        Assert.assertArrayEquals(src, storage.getAllSorted().toArray());
        List<Resume> list = Arrays.asList(R1, R2, R3);
//        Collections.sort(list);
        assertEquals(list, storage.getAllSorted());

    }

    @Test
    public void testSize() throws Exception {
        Assert.assertEquals(3, storage.size());
    }
}