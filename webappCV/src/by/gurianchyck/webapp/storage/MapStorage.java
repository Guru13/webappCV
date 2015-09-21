package by.gurianchyck.webapp.storage;

import by.gurianchyck.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Alexey Gurianchyck
 * 21.09.2015.
 */
public class MapStorage extends AbstractStorage{

    private Map<String, Resume> map = new HashMap<>();


    @Override
    protected boolean exist(String uuid) {
        return map.containsKey(uuid);
    }

    @Override
    protected void doClear() {
        map.clear();
    }

    @Override
    protected void doSave(Resume resume) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    protected void doUpdate(Resume resume) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume doLoad(String uuid) {
        return map.get(uuid);
    }

    @Override
    protected void doDelete(String uuid) {
        map.remove(uuid);
    }

    @Override
    protected List<Resume> doGetAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    public int size() {
        return map.size();
    }
}
