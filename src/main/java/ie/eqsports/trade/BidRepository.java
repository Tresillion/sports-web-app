package ie.eqsports.trade;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface BidRepository extends CrudRepository<Bid, Long> {

	
	
	//add query
	Bid findByIdAndStatus(@Param("id") long id, 
			@Param("status") int status);
	
	//add query
	Bid findByOfferIdAndStatus(@Param("offerId") long offerId, 
			@Param("status") int status);	
	//Bid saveBid(Bid bid);
}
