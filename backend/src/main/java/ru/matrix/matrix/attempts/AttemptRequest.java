package ru.matrix.matrix.attempts;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public record AttemptRequest(
        String questionnaireVersion,
        @NotEmpty List<@Valid @NotNull LevelRequest> levels
) {

    public static class LevelRequest {
        @NotBlank
        private String id;

        private Map<String, Map<String, String>> cells = new LinkedHashMap<>();

        private final Map<String, String> otherFields = new LinkedHashMap<>();

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Map<String, Map<String, String>> getCells() {
            return cells;
        }

        public void setCells(Map<String, Map<String, String>> cells) {
            this.cells = cells == null ? new LinkedHashMap<>() : cells;
        }

        public Map<String, String> getOtherFields() {
            return otherFields;
        }

        @JsonAnySetter
        public void putOtherField(String key, String value) {
            otherFields.put(key, value);
        }
    }
}
