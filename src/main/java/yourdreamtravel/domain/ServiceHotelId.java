package yourdreamtravel.domain;

public class ServiceHotelId {
    private static Integer counter = 0;
    private final Integer value;

    public ServiceHotelId() {
        this.value = counter++;
    }

    public Integer getValue() {
        return value;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ServiceHotelId))
            return false;
        ServiceHotelId other = (ServiceHotelId)obj;
        return value.equals(other.value);
    }

    public int hashCode() {
        return value;
    }
}
