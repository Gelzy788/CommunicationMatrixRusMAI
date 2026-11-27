package ru.matrix.matrix.scoring;

import java.util.List;


public record CommunicationMatrixResult(String questionnaireVersion, List<LevelResult> levels) {
    public CommunicationMatrixResult {
        levels = List.copyOf(levels);
    }
}
