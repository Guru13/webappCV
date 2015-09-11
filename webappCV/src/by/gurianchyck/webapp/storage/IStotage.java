package by.gurianchyck.webapp.storage;

import by.gurianchyck.webapp.WebappException;
import by.gurianchyck.webapp.model.Resume;

import java.util.Collection;

/**
 * Created by Alexey Gurianchyck
 * 10.09.2015.
 */
public interface IStotage {
    void clear();
    void save(Resume resume) throws WebappException;
    void update(Resume resume);
    Resume load(String uuid);
    void delete(String uuid);
    Collection<Resume> getAllSorted();
    int size();
}
