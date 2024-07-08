package search_filter.search_filtern_gui;
import java.util.List;
import java.util.stream.Collectors;

public class LowUpSearchStrategy implements SearchStrategy{

    @Override
    public List<Eisenbahnunternehmen> search(List<Eisenbahnunternehmen> companies, String searchText) {
        //C1 foreach
        //C2 dùng Stream API
        return companies.stream().filter( e -> e.getName().contains(searchText) || 
                                               e.getStraße().contains(searchText) ||
                                               e.getPostLeitZahl().contains(searchText) ||
                                               e.getStadt().contains(searchText) )
                                 .collect(Collectors.toList());
    }
}
