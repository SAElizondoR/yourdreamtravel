package yourdreamtravel.domain;

public class ClientId {
    private static Integer counter = 0;
    private final Integer value;

    public ClientId() {
        this.value = counter++;
    }

    public Integer getValue() {
        return value;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ClientId))
            return false;
        ClientId other = (ClientId)obj;
        return value.equals(other.value);
    }

    public int hashCode() {
        return value;
    }
}
