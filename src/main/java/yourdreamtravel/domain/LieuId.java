package yourdreamtravel.domain;

public class LieuId {
    private static Integer counter = 0;
    private final Integer value;

    public LieuId() {
        this.value = counter++;
    }

    public Integer getValue() {
        return value;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof LieuId))
            return false;
        LieuId other = (LieuId)obj;
        return value.equals(other.value);
    }

    public int hashCode() {
        return value;
    }
}
