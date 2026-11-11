public enum AnswerStage {
    YES("да"),
    NO_STOP("нет, остановка"),
    NO_OUTGROWN("нет, перерос");

    private final String value;

    AnswerStage(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}