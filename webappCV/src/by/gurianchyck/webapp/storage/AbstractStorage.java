package by.gurianchyck.webapp.storage;

import by.gurianchyck.webapp.WebappException;
import by.gurianchyck.webapp.model.Resume;
import by.gurianchyck.webapp.storage.IStorage;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Alexey Gurianchyck
 * 11.09.2015.
 */
public abstract class AbstractStorage implements IStorage {

    protected final Logger logger = Logger.getLogger(getClass().getName());

    protected abstract boolean exist(String uuid);


    @Override
    public void clear() {
        logger.info("Delete all resumes");
        doClear();
    }

    protected abstract void doClear();

    @Override
    public void save(Resume resume) {
        logger.info("Save resume with uuid=" + resume.getUuid());
        if (exist(resume.getUuid())){
            throw new WebappException("Resume "  + resume.getUuid() + " not exist", resume);
        }
        doSave(resume);
    }

    protected abstract void doSave(Resume resume);

    @Override
    public void update(Resume resume) {
        logger.info("Update resume with uuid=" + resume.getUuid());

        if (!exist(resume.getUuid())) {
            throw new WebappException("Resume " + resume.getUuid() + " not exist", resume);
        }
        doUpdate(resume);
    }

    protected abstract void doUpdate(Resume resume);

    @Override
    public Resume load(String uuid) {
        logger.info("Load resume with uuid=" + uuid);
        if (!exist(uuid)) {
            throw new WebappException("Resume " + uuid + " not exist");
        }
        return doLoad(uuid);
    }

    protected abstract Resume doLoad(String uuid);

    @Override
    public void delete(String uuid) {
        logger.info("Delete resume with uuid=" + uuid);
        if (!exist(uuid)) {
            throw new WebappException("Resume " + uuid + " not exist");
        }
        doDelete(uuid);
    }

    protected abstract void doDelete(String uuid);

    @Override
    public Collection<Resume> getAllSorted() {
        logger.info("getAllSorted");
        List<Resume> list = doGetAll();
        Collections.sort(list);
        return list;
//        return Collections.singletonList(new Resume());
    }

    protected abstract List<Resume> doGetAll();

   public abstract int size();
}
