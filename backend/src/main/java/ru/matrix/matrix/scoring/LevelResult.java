package ru.matrix.matrix.scoring;

import java.util.List;

public record LevelResult(String id, List<CellResult> cells) {
    public LevelResult {
        cells = List.copyOf(cells);
    }
}
