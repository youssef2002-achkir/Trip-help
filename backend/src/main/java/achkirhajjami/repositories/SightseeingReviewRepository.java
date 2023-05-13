package achkirhajjami.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import achkirhajjami.model.reviews.SightseeingReview;

@Repository
public interface SightseeingReviewRepository extends JpaRepository<SightseeingReview, Long> {
	
	@Query(value = "SELECT * FROM sightseeing_reviews s WHERE s.sightseeing_id = :sightseeingId ORDER BY review_creation_date DESC", nativeQuery = true)
	List<SightseeingReview> findReviewsOfSightseeingBySightseeingId(@Param("sightseeingId") Long sightseeingId);
	
	@Query(value = "SELECT sightseeing_id FROM sightseeing_reviews s WHERE s.id = :sightseeingReviewId", nativeQuery = true)
	Long findSightseeingIdBySightseeingReviewId(@Param("sightseeingReviewId") Long sightseeingReviewId);

}