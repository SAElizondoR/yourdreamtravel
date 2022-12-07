package yourdreamtravel.domain;

public class VoitureId {
    private static Integer counter = 0;
    private final Integer value;

    public VoitureId() {
        this.value = counter++;
    }

    public Integer getValue() {
        return value;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof VoitureId))
            return false;
        VoitureId other = (VoitureId)obj;
        return value.equals(other.value);
    }

    public int hashCode() {
        return value;
    }
}
