package yourdreamtravel.domain;

public class ChambreId {
    private static Integer counter = 0;
    private final Integer value;

    public ChambreId() {
        this.value = counter++;
    }

    public Integer getValue() {
        return value;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ChambreId))
            return false;
        ChambreId other = (ChambreId)obj;
        return value.equals(other.value);
    }

    public int hashCode() {
        return value;
    }
}
