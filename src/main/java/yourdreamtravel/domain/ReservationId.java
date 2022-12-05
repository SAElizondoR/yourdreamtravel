package yourdreamtravel.domain;

public class ReservationId {
    private static Integer counter = 0;
    private final Integer value;

    public ReservationId() {
        this.value = counter++;
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
