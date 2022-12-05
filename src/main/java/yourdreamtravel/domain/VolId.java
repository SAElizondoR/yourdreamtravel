package yourdreamtravel.domain;

public class VolId {
    private static Integer counter = 0;
    private final Integer value;

    public VolId() {
        this.value = counter++;
    }

    public Integer getValue() {
        return value;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof VolId))
            return false;
        VolId other = (VolId)obj;
        return value.equals(other.value);
    }

    public int hashCode() {
        return value;
    }
}
