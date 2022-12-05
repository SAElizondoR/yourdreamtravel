package yourdreamtravel.domain;

public class VolId {
    private final Integer value;

    public VolId(Integer value) {
        this.value = value;
    }

    public VolId() {
        value = 0;
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
