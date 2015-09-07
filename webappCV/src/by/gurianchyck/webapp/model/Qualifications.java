package by.gurianchyck.webapp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexey Gurianchyck
 * 07.09.2015.
 */
public class Qualifications {
    private List<String> listQualifications;

    public Qualifications() {
        this.listQualifications = new ArrayList<>();
    }

    public List<String> getListQualifications() {
        return listQualifications;
    }

    public void setListQualifications(List<String> listQualifications) {
        this.listQualifications = listQualifications;
    }
}
