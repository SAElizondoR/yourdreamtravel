package yourdreamtravel.domain;

public class AgenceId {
    private static Integer counter = 0;
    private final Integer value;

    public AgenceId() {
        this.value = counter++;
    }

    public Integer getValue() {
        return value;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AgenceId))
            return false;
        AgenceId other = (AgenceId)obj;
        return value.equals(other.value);
    }

    public int hashCode() {
        return value;
    }
}
