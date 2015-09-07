package by.gurianchyck.webapp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexey Gurianchyck
 * 07.09.2015.
 */
public class Objective {
    private List<String> listPosition;

    public Objective() {
        this.listPosition = new ArrayList<>();
    }

    public List<String> getListPosition() {
        return listPosition;
    }

    public void setListPosition(List<String> listPosition) {
        this.listPosition = listPosition;
    }
}
