package yourdreamtravel.domain;

public class ServiceLoueurId {
    private static Integer counter = 0;
    private final Integer value;

    public ServiceLoueurId() {
        this.value = counter++;
    }

    public Integer getValue() {
        return value;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ServiceLoueurId))
            return false;
        ServiceLoueurId other = (ServiceLoueurId)obj;
        return value.equals(other.value);
    }

    public int hashCode() {
        return value;
    }
}
