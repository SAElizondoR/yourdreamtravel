package yourdreamtravel.domain;

public class ServiceId {
    private static Integer counter = 0;
    private final Integer value;

    public ServiceId() {
        this.value = counter++;
    }

    public Integer getValue() {
        return value;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ServiceId))
            return false;
        ServiceId other = (ServiceId)obj;
        return value.equals(other.value);
    }

    public int hashCode() {
        return value;
    }
}
