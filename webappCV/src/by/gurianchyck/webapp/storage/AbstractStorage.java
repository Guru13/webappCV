package by.gurianchyck.webapp.storage;

import by.gurianchyck.webapp.WebappException;
import by.gurianchyck.webapp.model.Resume;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Alexey Gurianchyck
 * 11.09.2015.
 */
public abstract class AbstractStorage<C> implements IStorage {

    protected final Logger logger = Logger.getLogger(getClass().getName());

    protected abstract boolean exist(C ctx);

    protected abstract C getContext(String uuid);


    @Override
    public void clear() {
        logger.info("Delete all resumes");
        doClear();
    }

    protected abstract void doClear();

    @Override
    public void save(Resume resume) {
        logger.info("Save resume with uuid=" + resume.getUuid());
        C ctx = getContext(resume.getUuid());
        if (exist(ctx)) {
            throw new WebappException("Resume " + resume.getUuid() + " not exist", resume);
        }
        doSave(ctx, resume);
    }

    protected abstract void doSave(C ctx, Resume resume);

    @Override
    public void update(Resume resume) {
        logger.info("Update resume with uuid=" + resume.getUuid());
        C ctx = getContext(resume.getUuid());
        if (!exist(ctx)) {
            throw new WebappException("Resume " + resume.getUuid() + " not exist", resume);
        }
        doUpdate(ctx, resume);
    }

    protected abstract void doUpdate(C ctx, Resume resume);

    @Override
    public Resume load(String uuid) {
        logger.info("Load resume with uuid=" + uuid);
        C ctx = getContext(uuid);
        if (!exist(ctx)) {
            throw new WebappException("Resume " + uuid + " not exist");
        }
        return doLoad(ctx);
    }

    protected abstract Resume doLoad(C ctx);

    @Override
    public void delete(String uuid) {
        logger.info("Delete resume with uuid=" + uuid);
        C ctx = getContext(uuid);
        if (!exist(ctx)) {
            throw new WebappException("Resume " + uuid + " not exist");
        }
        doDelete(ctx);
    }

    protected abstract void doDelete(C ctx);

    @Override
    public Collection<Resume> getAllSorted() {
        logger.info("getAllSorted");
        List<Resume> list = doGetAll();
        Collections.sort(list, (Resume o1, Resume o2) -> {
            int cmp = o1.getFullName().compareTo(o2.getFullName());
            if (cmp != 0) return cmp;
            return o1.getUuid().compareTo(o2.getUuid());
        });
        return list;
//        return Collections.singletonList(new Resume());
    }


    protected abstract List<Resume> doGetAll();

    public abstract int size();
}
