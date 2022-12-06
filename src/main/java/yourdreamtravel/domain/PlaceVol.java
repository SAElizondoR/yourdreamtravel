package yourdreamtravel.domain;

public class PlaceVol {
    private final PlaceVolId id;
    private Vol vol;
    private Integer place;
    private Integer classe;

    public PlaceVol(Vol vol, Integer classe) {
        id = new PlaceVolId();
        this.vol = vol;
        this.classe = classe;
    }

    public PlaceVolId getId() {
        return id;
    }

    public Vol getVol() {
        return vol;
    }

    public Integer getPlace() {
        return place;
    }

    public Integer getClasse() {
        return classe;
    }
}
