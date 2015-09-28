package by.gurianchyck.webapp.storage;

import by.gurianchyck.webapp.WebappException;
import by.gurianchyck.webapp.model.ContactType;
import by.gurianchyck.webapp.model.Resume;
import by.gurianchyck.webapp.model.Section;
import by.gurianchyck.webapp.model.SectionType;

import java.io.*;
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
             DataOutputStream dos = new DataOutputStream(fos)) {
            writeString(dos, resume.getFullName());
            writeString(dos, resume.getLocation());
            writeString(dos, resume.getHomePage());
            Map<ContactType, String> contacts = resume.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeInt(entry.getKey().ordinal());
                writeString(dos, entry.getValue());
            }
            Map<SectionType, Section> sections = resume.getSections();
            dos.writeInt(sections.size());
            for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {
                dos.writeInt(entry.getKey().ordinal());
                writeString(dos, String.valueOf(entry.getValue()));
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
//                resume.addSection(SectionType.valueOf(dis.readUTF()), );
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

}
