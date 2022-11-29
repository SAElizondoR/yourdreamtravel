package yourdreamtravel.domain;

public class ReservationId {
    private final Integer value;

    public ReservationId(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ReservationId))
            return false;
        ReservationId other = (ReservationId)obj;
        return value.equals(other.value);
    }

    public int hashCode() {
        return value;
    }
}
