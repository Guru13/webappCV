package by.gurianchyck.webapp.storage;

import by.gurianchyck.webapp.WebappException;
import by.gurianchyck.webapp.model.ContactType;
import by.gurianchyck.webapp.model.Resume;
import by.gurianchyck.webapp.model.Section;
import by.gurianchyck.webapp.model.SectionType;

import java.io.*;
import java.util.Map;

/**
 * Created by Alexey Gurianchyck
 * 23.09.2015.
 */
public class SerializeFileStorage extends FileStorage {

    public SerializeFileStorage(String path) {
        super(path);
    }

    @Override
    protected void write(OutputStream os, Resume resume) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(os)) {
            oos.writeObject(resume);
        }
    }

    @Override
    protected Resume read(InputStream is) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(is)) {
           return (Resume) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new WebappException("Erroe read resume  " + e);
        }
    }


}
