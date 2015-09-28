package by.gurianchyck.webapp.storage;

import by.gurianchyck.webapp.WebappException;
import by.gurianchyck.webapp.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by Alexey Gurianchyck
 * 23.09.2015.
 */
public class DataStreamFileStorage extends FileStorage {
    public final String NULL = "null";
    public DataStreamFileStorage(String path) {
        super(path);
    }

    protected void write(File file, Resume resume) {
        try (FileOutputStream fos = new FileOutputStream(file);
             final DataOutputStream dos = new DataOutputStream(fos)) {
            writeString(dos, resume.getFullName());
            writeString(dos, resume.getLocation());
            writeString(dos, resume.getHomePage());
            Map<ContactType, String> contacts = resume.getContacts();
//            dos.writeInt(contacts.size());
//            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
//                dos.writeInt(entry.getKey().ordinal());
//                writeString(dos, entry.getValue());
//            }
            writeCollection(dos, contacts.entrySet(), new ElementWriter<Map.Entry<ContactType, String>>() {
                @Override
                public void write(Map.Entry<ContactType, String> entry) throws IOException {
                    dos.writeInt(entry.getKey().ordinal());
                    writeString(dos, entry.getValue());
                }
            });
            Map<SectionType, Section> sections = resume.getSections();
            dos.writeInt(sections.size());
            for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {
                SectionType type = entry.getKey();
                Section section = entry.getValue();
//                dos.writeInt(entry.getKey().ordinal());
                writeString(dos, type.name());
                switch (type){
                    case OBJECTIVE:
                        writeString(dos, ((TextSection) section).getValue());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATION:
                        writeCollection(dos, ((MultiTextSection) section).getValues(), new ElementWriter<String>() {
                            @Override
                            public void write(String value) throws IOException {
                                writeString(dos, value);
                            }
                        });
                        break;
                    case EDUCATION:
                    case EXPERIENCE:
                        //TODO implement
                        break;
                }
            }
        } catch (IOException e) {
            throw new WebappException("Couldn't create new file  " + file.getAbsolutePath(), e, resume);
        }
    }

    protected Resume read(File file) {
        Resume resume = new Resume();
        try (FileInputStream fis = new FileInputStream(file);
             DataInputStream dis = new DataInputStream(fis)) {

            resume.setFullName(readString(dis));
            resume.setLocation(readString(dis));
            resume.setHomePage(readString(dis));
            int contactsSize = dis.readInt();
            for (int i = 0; i < contactsSize; i++) {
                resume.addContact(ContactType.VALUES[dis.readInt()], readString(dis));
            }
            resume.setUuid(file.getName());
           int sectionsSize = dis.readInt();
            for (int i = 0; i < sectionsSize; i++) {
                SectionType sectionType = SectionType.valueOf(readString(dis));
                switch (sectionType){
                    case OBJECTIVE:
                        resume.addObjective(readString(dis));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATION:
                        resume.addSection(sectionType, new MultiTextSection(readList(dis, () -> readString(dis))));
                        break;
                    case EDUCATION:
                    case EXPERIENCE:
                        //TODO section implementation
                        break;
                }
            }
        } catch (IOException e) {
            throw new WebappException("Couldn't create new file  " + file.getAbsolutePath(), e, resume);
        }
        return resume;
    }
    public void writeString(DataOutputStream dos, String str) throws IOException {
        dos.writeUTF(str == null ? NULL :str);
    }

    public String readString(DataInputStream dis) throws IOException {
        String str = dis.readUTF();
        return str.equals(NULL) ? null : str;
    }
    private <T> void writeCollection(DataOutputStream dos, Collection<T> collection, ElementWriter<T> writer) throws IOException{
        dos.writeInt(collection.size());
        for (T item : collection) {
           writer.write(item);
        }
    }

    private <T> List<T> readList(DataInputStream dis, ElementReader<T> reader) throws  IOException{
        int size = dis.readInt();
        List<T> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(reader.read());
        }
        return list;
    }
    private interface ElementWriter<T>{
        void write(T t) throws IOException;
    }
    private interface ElementReader<T>{
        T read() throws IOException;
    }
}
