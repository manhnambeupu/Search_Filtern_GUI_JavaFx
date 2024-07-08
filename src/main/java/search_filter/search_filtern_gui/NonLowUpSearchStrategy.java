package search_filter.search_filtern_gui;
import java.util.List;
import java.util.stream.Collectors;


public class NonLowUpSearchStrategy implements SearchStrategy{
    @Override
    public List<Eisenbahnunternehmen> search (List<Eisenbahnunternehmen> companies, String searchText) {
        
        return companies.stream().filter( e -> e.getName().toLowerCase().contains(searchText.toLowerCase()) || 
                                               e.getStraße().toLowerCase().contains(searchText.toLowerCase()) ||
                                               e.getPostLeitZahl().toLowerCase().contains(searchText.toLowerCase()) ||
                                               e.getStadt().toLowerCase().contains(searchText.toLowerCase()) )
                                 .collect(Collectors.toList());       
    }
}
