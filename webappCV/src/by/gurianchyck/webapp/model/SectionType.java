package by.gurianchyck.webapp.model;

import java.io.Serializable;

/**
 * Created by Alexey Gurianchyck
 * 09.09.2015.
 */
public enum SectionType implements Serializable{
    OBJECTIVE("Позиция"),
    ACHIEVEMENT("Достижения"),
    QUALIFICATION("Квалификация"),
    EXPERIENCE("Опыт работы"),
    EDUCATION("Образование");

    private String title;

    SectionType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
