package by.gurianchyck.webapp.storage;

import static org.junit.Assert.*;

/**
 * Created by Alexey Gurianchyck
 * 25.09.2015.
 */
public class SerializeFileStorageTest extends AbstractStorageTest{
    {
        storage = new SerializeFileStorage("./file_storage");
    }

}