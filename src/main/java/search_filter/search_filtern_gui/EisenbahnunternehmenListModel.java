package search_filter.search_filtern_gui;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  Eine List der Eisenbahnunternehmen
 *  dieses Modul übernimmt das Laden von Daten aus CSV-Dateien,
 *  die Verwaltung der Liste der Eisenbahnunternehmen-Objekte,
 *  das Einrichten von Suchstrategien und das Filtern von Daten nach Suchzeichenfolge und Transportart (Personen oder Fracht).
 */
public class EisenbahnunternehmenListModel {

    /* ermöglicht eine direkte Datenverbindung zu Benutzeroberflächenkomponenten wie TableView.
        Wenn sich die ObservableList ändert (Name, Löschung oder Elementaktualisierung),
        wird die Benutzeroberfläche automatisch aktualisiert, um diese Änderungen widerzuspiegeln
        */
    private ObservableList<Eisenbahnunternehmen> _companies = FXCollections.observableArrayList();

    private SearchStrategy _searchStrategy;

    /**
     * Lädt Daten aus einer Liste von CSV-Zeilen.
     * @param lines Liste der CSV-Zeilen
     */
    public void loadData(List<String> lines) {
        _companies.clear();
        boolean firstLine = true;
        for (String line : lines) {
            if (firstLine) {
                firstLine = false; // Skip the first line (header)
                continue;
            }
            String[] data = line.split(";"); // Trennen der CSV-Zeile
                if(data.length != 6) {
                    continue;
                }
                String name = data[0];
                String straße = data[1];
                String postLeitZahl = data[2];
                String stadt = data[3];
                String gueterVerkehr = data[4];
                String personenVerkehr = data[5];
                _companies.add(new Eisenbahnunternehmen(name, straße, postLeitZahl, stadt, gueterVerkehr, personenVerkehr));
           
        }
    }

    /**
     * Gibt die aktuelle Liste der Unternehmen zurück.
     * @return ObservableList<Eisenbahnunternehmen>
     */
    public ObservableList<Eisenbahnunternehmen> getCompanies() {
        return _companies;
    }

    /**
     * Setzt die Suchstrategie für das Modell.
     * @param strategy Suchstrategie
     */
    public void setSearchStrategy(SearchStrategy strategy) {
        _searchStrategy = strategy;
    }

    /**
     * Filtert die Unternehmen nach dem Suchtext.
     * und setzt die Suchstrategie für das Modell.
     * @param searchText Suchtext
     * @return ObservableList<Eisenbahnunternehmen>
     */
    public ObservableList<Eisenbahnunternehmen> filterCompaniesSeearchStrategy(String searchText) {
        if (_searchStrategy != null) {
            List<Eisenbahnunternehmen> filteredList = _searchStrategy.search(_companies, searchText);
            return FXCollections.observableArrayList(filteredList);
        }
        return FXCollections.observableArrayList(_companies);
    }

    /**
     * Filtert die Unternehmen nach Transporttyp.
     * @param gueterVerkehr Filter für Güterverkehr
     * @param personenVerkehr Filter für Personenverkehr
     * @return ObservableList<Eisenbahnunternehmen>
     */
    public ObservableList<Eisenbahnunternehmen> filterByTransportType(String gueterVerkehr, String personenVerkehr) {
        return _companies.stream()
                .filter(company -> company.getGueterVerkehr().toLowerCase().contains(gueterVerkehr.toLowerCase()) && company.getPersonenVerkehr().toLowerCase().contains(personenVerkehr.toLowerCase()))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    /**
     * Filtert die Unternehmen nach Suchtext und Transporttyp.
     * @param searchText Suchtext
     * @param gueterVerkehr Filter für Güterverkehr
     * @param personenVerkehr Filter für Personenverkehr
     * @return ObservableList<Eisenbahnunternehmen>
     */
    public ObservableList<Eisenbahnunternehmen> filterCompanies(String searchText, String gueterVerkehr, String personenVerkehr) {
        ObservableList<Eisenbahnunternehmen> result = filterCompaniesSeearchStrategy(searchText); // Filtern nach SearchStrategy
        return result.stream()
                .filter(company -> company.getGueterVerkehr().toLowerCase().contains(gueterVerkehr.toLowerCase()) && company.getPersonenVerkehr().toLowerCase().contains(personenVerkehr.toLowerCase()))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }
}

