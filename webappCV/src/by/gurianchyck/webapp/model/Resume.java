package by.gurianchyck.webapp.model;

import java.io.Serializable;
import java.util.*;

/**
 * Created by Alexey Gurianchyck
 * 06.09.2015.
 */
public class Resume implements Serializable { //implements Comparable<Resume>{
    static final long serialVersionUID = 1L;

    private String uuid;
    private String fullName;
    private String location;
    private String homePage;
//    private List<Contact> contacts = new LinkedList<>();
//    private List<Section> sections = new LinkedList<>();
    private Map<SectionType, Section> sections = new EnumMap<>(SectionType.class);
    private Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
    public static final Resume EMPTY;
    static {
        EMPTY = new Resume();
        for (SectionType type : SectionType.values()){
//            EMPTY.addSection(type, type.getSectionClass().getEmptySection());
        }
    }
    public Resume() {
    }

    public Resume(String fullName, String location) {
       this(UUID.randomUUID().toString(), fullName, location);
    }

    public Resume(String uuid, String fullName, String location) {
        this.uuid = uuid;
        this.fullName = fullName;
        this.location = location;
    }

    public void addObjective(String value){
        addSection(SectionType.OBJECTIVE, new TextSection(value));
    }
    public void addMultiTextSection(SectionType type, String... values){
        addSection(type, new MultiTextSection(values));
    }
    public void addSection(SectionType type, Section section){
        sections.put(type, section);
    }

    public void addContact(ContactType type, String value){
        contacts.put(type, value);
    }

    public String getContact(ContactType type){
        return contacts.get(type);
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public String getLocation() {
        return location;
    }

    public String getHomePage() {
        return homePage;
    }

    public Map<ContactType, String> getContacts() {
        return contacts;
    }

    public Map<SectionType, Section> getSections() {
        return sections;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Resume other = (Resume) obj;
        return Objects.equals(this.uuid, other.uuid);
    }

//    @Override
//    public String toString() {
//        return "Resume{" +
//                "uuid='" + uuid + '\'' +
//                ", fullName='" + fullName + '\'' +
//                '}';
//    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Resume{");
        sb.append("uuid='").append(uuid).append('\'');
        sb.append(", fullName='").append(fullName).append('\'');
        sb.append('}');
        return sb.toString();
    }

    //    @Override
    public int compareTo(Resume o) {
        return fullName.compareTo(o.fullName);
    }

//    private String getEmail(Resume o1) {
//        List<Contact> list = o1.getContacts();
//        for (Contact c : list){
//            if (c.getType() == ContactType.MAIL){
//                return c.getValue();
//            }
//        }
//        return null;
//    }
}
