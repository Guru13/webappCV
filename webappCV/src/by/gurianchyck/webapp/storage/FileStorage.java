package by.gurianchyck.webapp.storage;

import by.gurianchyck.webapp.WebappException;
import by.gurianchyck.webapp.model.ContactType;
import by.gurianchyck.webapp.model.Resume;
import by.gurianchyck.webapp.model.Section;
import by.gurianchyck.webapp.model.SectionType;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by Alexey Gurianchyck
 * 23.09.2015.
 */
public abstract class FileStorage extends AbstractStorage<File> {
    private File dir;

    public FileStorage(String path) {
        this.dir = new File(path);
        if (!dir.isDirectory() && !dir.canWrite()) {
            throw new IllegalArgumentException("'" + path + "' is not directory or is not writeble");
        }
    }


    @Override
    protected boolean exist(File file) {
        return file.exists();
    }

    @Override
    protected File getContext(String fileName) {
        return new File(dir, fileName);
    }

    @Override
    protected void doClear() {
        File[] files = dir.listFiles();
        if (files == null) {
            return;
        }
        for (File file : files) {
            doDelete(file);
        }
    }

    @Override
    protected void doSave(File file, Resume resume) {
        try {
            file.createNewFile();
            write(file, resume);
        } catch (IOException e) {
            throw new WebappException("Couldn't create new file  " + file.getAbsolutePath(), e, resume);
        }
    }

    protected abstract void write(File file, Resume resume);

    protected abstract Resume read(File file);

    @Override
    protected void doUpdate(File file, Resume resume) {
        write(file, resume);
    }

    @Override
    protected Resume doLoad(File file) {
        return read(file);
    }

    @Override
    protected void doDelete(File file) {
        if (!file.delete()) {
            throw new WebappException("File " + file.getAbsolutePath() + "can't be deleted");
        }
    }


    @Override
    protected List<Resume> doGetAll() {
        File[] files = dir.listFiles();
        if(files == null){
            return Collections.emptyList();
        }
        List<Resume> list = new ArrayList<>(files.length);
        for (File file : files){
            list.add(read(file));
        }
        return list;
    }

    @Override
    public int size() {
        String[] list = dir.list();
        if (list == null){
            throw new WebappException("Couldn't read directory " + dir.getAbsolutePath());
        }
        return list.length;
    }
}
