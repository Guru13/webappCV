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

    protected void write(File file, Resume resume) {
        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(resume);
        } catch (IOException e) {
            throw new WebappException("Couldn't create new file  " + file.getAbsolutePath(), e, resume);
        }
    }

    protected Resume read(File file) {
        Resume resume = new Resume();
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            resume = (Resume) ois.readObject();
        } catch (IOException e) {
            throw new WebappException("Couldn't create new file  " + file.getAbsolutePath(), e, resume);
        } catch (ClassNotFoundException e) {
            throw new WebappException("Erroe read resume  " + e);
        }
        return resume;
    }


}
