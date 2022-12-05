package yourdreamtravel.domain;

public class HotelId {
    private static Integer counter = 0;
    private final Integer value;

    public HotelId() {
        this.value = counter++;
    }

    public Integer getValue() {
        return value;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof HotelId))
            return false;
        HotelId other = (HotelId)obj;
        return value.equals(other.value);
    }

    public int hashCode() {
        return value;
    }
}
