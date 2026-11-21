package ru.matrix.matrix.scoring;

import java.util.List;


public record CommunicationMatrixResult(List<CellResult> cells) {
    public CommunicationMatrixResult {
        cells = List.copyOf(cells);
    }
}
