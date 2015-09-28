package by.gurianchyck.webapp.storage;

import by.gurianchyck.webapp.WebappException;
import by.gurianchyck.webapp.model.Resume;


import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Alexey Gurianchyck
 * 10.09.2015.
 */
public class ArrayStorage extends AbstractStorage<Integer> {
    private static final int LIMIT = 100;
    //    protected Logger logger = Logger.getLogger(getClass().getName());
//    private static Logger LOGGER = Logger.getLogger(ArrayStorage.class.getName());
    private Resume[] resumes;
    private int size;

    public ArrayStorage() {
        this.resumes = new Resume[LIMIT];
    }

    @Override
    protected boolean exist(Integer idx) {
        return idx != -1;
    }

    @Override
    protected void doClear() {
        Arrays.fill(resumes, null);
        size = 0;
    }

    @Override
    protected void doSave(Integer ctx, Resume resume) {
        resumes[size++] = resume;
    }

    @Override
    protected void doUpdate(Integer idx, Resume resume) {
        resumes[idx] = resume;
    }

    @Override
    protected Resume doLoad(Integer idx) {
        return resumes[idx];
    }

    @Override
    protected void doDelete(Integer idx) {
        int numMoved = size - idx - 1;
        if (numMoved > 0)
            System.arraycopy(resumes, idx + 1, resumes, idx,
                    numMoved);
        resumes[--size] = null; // clear to let GC do its work
    }

//    @Override
//    public Collection<Resume> getAllSorted() {
//        logger.info("getAllSorted");
//        Arrays.sort(resumes, 0, size);
//        return Arrays.asList(Arrays.copyOf(resumes, size));
//    }

    @Override
    protected List<Resume> doGetAll() {
        return Arrays.asList(Arrays.copyOf(resumes, size));
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    protected Integer getContext(String uuid) {
        for (int i = 0; i < LIMIT; i++) {
            if (resumes[i] != null) {
                if (resumes[i].getUuid().equals(uuid)) {
                    return i;
                }
            }
        }
        return -1;
    }
}
