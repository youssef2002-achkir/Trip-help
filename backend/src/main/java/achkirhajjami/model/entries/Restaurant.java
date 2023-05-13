package achkirhajjami.model.entries;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import achkirhajjami.model.entries.Enums.Cuisine;
import achkirhajjami.model.entries.Enums.FormsOfNutrition;
import achkirhajjami.model.entries.Enums.MealType;
import achkirhajjami.model.reviews.RestaurantReview;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "restaurants")
public class Restaurant extends Entry {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long restaurantId;

	@NotNull
	@Enumerated(EnumType.STRING)
	private MealType mealType;
	@NotNull
	@Enumerated(EnumType.STRING)
	private Cuisine cuisine;
	@NotNull
	@Enumerated(EnumType.STRING)
	private FormsOfNutrition formsOfNutrition;
	@NotNull
	private Boolean isSmokeFree;
	@NotNull
	private Boolean isFamilyFriendly;

	@ManyToOne
	@JoinColumn(name = "city_id")
	@JsonBackReference
	private City city;

	@JsonManagedReference
	@OneToMany(mappedBy = "restaurant", fetch = FetchType.EAGER)
	private List<RestaurantReview> restaurantReviews;

	public void addReview(RestaurantReview restaurantReview) {
		restaurantReview.setRestaurant(this);
		restaurantReviews.add(restaurantReview);
		this.updateRating();

	}

	public void removeReview(Long restaurantReviewId) {
		restaurantReviews = restaurantReviews.stream()
										 	 .filter(review -> !review.getId().equals(restaurantReviewId))
										 	 .collect(Collectors.toList());
		this.updateRating();
		
	}
	
	@Override
	public void updateRating() {
		double avgRating = 0;
		for (RestaurantReview restaurantReview : this.restaurantReviews) {
			avgRating += restaurantReview.getRating();
		}

		avgRating = avgRating / this.restaurantReviews.size();
		this.setAverageRating(avgRating);

	}


}
