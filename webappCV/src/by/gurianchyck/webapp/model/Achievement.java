package by.gurianchyck.webapp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexey Gurianchyck
 * 07.09.2015.
 */
public class Achievement {
    private List<String> listAchievement;

    public Achievement() {
        this.listAchievement = new ArrayList<>();
    }

    public List<String> getListAchievement() {
        return listAchievement;
    }

    public void setListAchievement(List<String> listAchievement) {
        this.listAchievement = listAchievement;
    }
}
