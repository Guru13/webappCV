package by.gurianchyck.webapp.model;

import java.util.*;

/**
 * Created by Alexey Gurianchyck
 * 06.09.2015.
 */
public class Resume { //implements Comparable<Resume>{
    private String uuid;
    private String fullName;
    private String location;
    private String homePage;
//    private List<Contact> contacts = new LinkedList<>();
    private List<Section> sections = new LinkedList<>();
    private Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
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


    public void addSection(Section section){
        sections.add(section);
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

    public List<Section> getSections() {
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
