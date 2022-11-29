package yourdreamtravel.domain;

public class LoueurVoitureId {
    private final Integer value;

    public LoueurVoitureId(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof LoueurVoitureId))
            return false;
        LoueurVoitureId other = (LoueurVoitureId)obj;
        return value.equals(other.value);
    }

    public int hashCode() {
        return value;
    }
}
