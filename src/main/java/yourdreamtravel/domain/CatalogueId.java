package yourdreamtravel.domain;

public class CatalogueId {
    private final Integer value;

    public CatalogueId(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof CatalogueId))
            return false;
        CatalogueId other = (CatalogueId)obj;
        return value.equals(other.value);
    }

    public int hashCode() {
        return value;
    }
}
