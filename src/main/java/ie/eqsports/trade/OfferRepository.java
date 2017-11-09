package ie.eqsports.trade;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface OfferRepository extends CrudRepository<Offer, Long> {

	@Query(value="select off.* from SPORTSEQ.OFFER off where off.SHAREHOLDING_SHD_ID = :shareholdingId and off.OFR_STATUS = 1 and off.OFR_CLOSE_DATETIME > now();",
			nativeQuery=true)
	List<Offer> getOffersByShareholdingId(@Param("shareholdingId") long shareholdingId) ;
	
	List<Offer> getOffersByStatus(@Param("status") int status);
	

	@Query(value="select off.* from SPORTSEQ.OFFER off join SPORTSEQ.SHAREHOLDING sh on off.SHAREHOLDING_SHD_ID = sh.SHD_ID where sh.SHARE_SHR_ID = :shareId and off.OFR_STATUS = 1 and off.OFR_CLOSE_DATETIME > now();",
	nativeQuery=true)	
	List<Offer> getAllOffersForAShare(@Param("shareId") long shareId);
			
	
	@Query(value="select off.* from SPORTSEQ.OFFER off where off.OFR_STATUS = 1 and off.OFR_CLOSE_DATETIME < now();",
	nativeQuery=true)	
	List<Offer> getActiveExpiredOffers();
	
}
