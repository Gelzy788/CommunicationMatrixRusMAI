public enum AnswerFrequency {
    NEVER("никогда"),
    SOMETIMES("иногда"),
    OFTEN("часто");

    private final String value;

    AnswerFrequency(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}