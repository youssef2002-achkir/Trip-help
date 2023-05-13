package achkirhajjami.filterservices;

import achkirhajjami.model.entries.Enums.CategoryToSortBy;
import achkirhajjami.model.entries.Enums.ResultsOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SortObject {
	
	private CategoryToSortBy categoryToSortBy;
	
	private ResultsOrder resultsOrder;
	
	public void initialize(String sortQueryString) {
		
		if(sortQueryString.toLowerCase().contains("price")) {
			this.categoryToSortBy = CategoryToSortBy.PRICE;
		} else if (sortQueryString.toLowerCase().contains("rating")) {
			this.categoryToSortBy = CategoryToSortBy.RATING;
		} else {
			this.categoryToSortBy = CategoryToSortBy.NAME;
		}
		
		if(sortQueryString.toLowerCase().contains("asc")) {
			this.resultsOrder = ResultsOrder.ASC;
		}  else {
			this.resultsOrder = ResultsOrder.DES;
		}
		
		
	}

}
