package search_filter.search_filtern_gui;

/**
 * Eisenbahnunternehmen
 */
public class Eisenbahnunternehmen {
    private String _name;
    private String _straße;
    private String _postLeitZahl;
    private String _stadt;
    private String _gueterVerkehr ;
    private String _personenVerkehr ;

    /**
     * Eisenbahnunternehmen-Construktor
     * @param name Name
     * @param straße Straße
     * @param postLeitZahl Postleitzahl
     * @param stadt Stadt
     */
    public Eisenbahnunternehmen(String name, String straße, String postLeitZahl, String stadt, String gueterVerkehr, String personenVerkehr) {
        _name = name;
        _straße = straße;
        _postLeitZahl = postLeitZahl;
        _stadt = stadt;
        _gueterVerkehr = gueterVerkehr;
        _personenVerkehr = personenVerkehr;
    }

    /**
     * Gibt den Namen zurück
     * @return Name
     */
    public String getName() {
        return _name;
    }

    /**
     * Gibt die Straße zurück
     * @return Straße
     */
    public String getStraße() {
        return _straße;
    }

    /**
     * Gibt die Postleitzahl zurück
     * @return Postleitzahl
     */
    public String getPostLeitZahl() {
        return _postLeitZahl;
    }

    /**
     * Gibt die Stadt zurück
     * @return Stadt
     */
    public String getStadt() {
        return _stadt;
    }

    /**
     * Gibt den Güterverkehr zurück
     * @return Güterverkehr
     */
    public String getGueterVerkehr() {
        return _gueterVerkehr;
    }

    /**
     * Gibt den Personenverkehr zurück
     * @return Personenverkehr
     */
    public String getPersonenVerkehr() {
        return _personenVerkehr;
    }
}
