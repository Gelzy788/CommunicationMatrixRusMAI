package ru.matrix.matrix.questionnaire;

import java.util.List;

public class CommunicationMatrix {
    private String version;
    private List<Level> levels;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<Level> getLevels() {
        return levels;
    }

    public void setLevels(List<Level> levels) {
        this.levels = levels;
    }
}