package by.gurianchyck.webapp.storage;

import by.gurianchyck.webapp.WebappException;
import by.gurianchyck.webapp.model.Resume;


import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Alexey Gurianchyck
 * 10.09.2015.
 */
public class ArrayStorage implements IStotage {
    private static final int LIMIT = 100;
//    protected Logger LOGGER = Logger.getLogger(getClass().getName());
    private static Logger LOGGER = Logger.getLogger(ArrayStorage.class.getName());
    private Resume[] resumes;
    private int size;

    public ArrayStorage() {
        this.resumes = new Resume[LIMIT];
    }

    @Override
    public void clear() {
//        for (int i = 0; i < LIMIT; i++) {
//            resumes[i] = null;
//        }
        LOGGER.info("Delete all resumes");
        Arrays.fill(resumes, null);
        size = 0;
    }

    @Override
    public void save(Resume resume)  {
//        for (int i = 0; i < resumes.length; i++) {
//            if (resumes[i] != null){
//                if (resumes[i].equals(resume)){
//                    throw new IllegalStateException("Already present");
//                }
//            }else {
//                resumes[i] = resume;
//                break;
//            }
//        }
        LOGGER.info("Save resume with uuid=" + resume.getUuid());
        int idx = getIndex(resume.getUuid());
        if (idx != -1){
         /*   try {
                throw new WebappException("Resume " + resume.getUuid() + " already exist", resume);
            } catch (WebappException e) {
                LOGGER.log(Level.SEVERE, e.getMessage(), e);
            }*/
            throw new WebappException("Resume " + resume.getUuid() + " already exist", resume);
        }
        resumes[size++] = resume;
    }

    @Override
    public void update(Resume resume) {
        LOGGER.info("Update resume with uuid=" + resume.getUuid());
        int idx = getIndex(resume.getUuid());
        if (idx == -1){
            throw new WebappException("Resume " + resume.getUuid() + " not exist", resume);
        }
        resumes[idx] = resume;
//        for (Resume currentResume : resumes){
//            if (currentResume != null && currentResume.equals(resume)){
//                currentResume = resume;
//            }
//        }

    }

    @Override
    public Resume load(String uuid) {
//        Resume resume = null;
//
//        for (Resume currentResume : resumes){
//            if ((currentResume != null) && currentResume.getUuid().equals(uuid)){
//                resume = currentResume;
//                break;
//            }
//        }
//        return resume;
        LOGGER.info("Load resume with uuid=" + uuid);
        int idx = getIndex(uuid);
        if (idx == -1){
            throw new WebappException("Resume " + uuid + " not exist");
        }
        return resumes[idx];
    }

    @Override
    public void delete(String uuid) {
//        for (int i = 0; i < resumes.length; i++) {
//            if (resumes[i].getUuid().equals(uuid)){
//                resumes[i] = null;
//                break;
//            }
//        }
        LOGGER.info("Delete resume with uuid=" + uuid);
        int idx = getIndex(uuid);
        if (idx == -1){
            throw new WebappException("Resume " + uuid + " not exist");
        }
        int numMoved = size - idx - 1;
        if (numMoved > 0)
            System.arraycopy(resumes, idx+1, resumes, idx,
                    numMoved);
        resumes[--size] = null; // clear to let GC do its work


    }

    @Override
    public Collection<Resume> getAllSorted() {
        Arrays.sort(resumes, 0, size);
        return Arrays.asList(Arrays.copyOf(resumes, size));
    }

    @Override
    public int size() {
        return size;
    }
    private int getIndex(String uuid){
        for (int i = 0; i < LIMIT; i++) {
            if (resumes[i] != null){
                if (resumes[i].getUuid().equals(uuid)){
                    return i;
                }
            }
        }
        return -1;
    }
}
