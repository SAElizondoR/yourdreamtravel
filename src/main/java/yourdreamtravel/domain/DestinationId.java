package yourdreamtravel.domain;

public class DestinationId {
    private static Integer counter = 0;
    private final Integer value;

    public DestinationId() {
        this.value = counter++;
    }

    public Integer getValue() {
        return value;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DestinationId))
            return false;
        DestinationId other = (DestinationId)obj;
        return value.equals(other.value);
    }

    public int hashCode() {
        return value;
    }
}
