package search_filter.search_filtern_gui;

import java.util.List;

/**
 * Interface für Suchstrategien
 */
public interface SearchStrategy {
    /**
     * Sucht Unternehmen in der Liste basierend auf dem Suchtext.
     * @param companies Liste der Unternehmen
     * @param searchText Suchtext
     * @return Liste der gefilterten Unternehmen
     */
    List<Eisenbahnunternehmen> search(List<Eisenbahnunternehmen> companies, String searchText);
}
