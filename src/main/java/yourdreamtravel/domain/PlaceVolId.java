package yourdreamtravel.domain;

public class PlaceVolId {
    private static Integer counter = 0;
    private final Integer value;

    public PlaceVolId() {
        this.value = counter++;
    }

    public Integer getValue() {
        return value;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PlaceVolId))
            return false;
        PlaceVolId other = (PlaceVolId)obj;
        return value.equals(other.value);
    }

    public int hashCode() {
        return value;
    }
}
