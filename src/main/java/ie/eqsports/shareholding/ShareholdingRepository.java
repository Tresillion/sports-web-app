package ie.eqsports.shareholding;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ShareholdingRepository extends CrudRepository<Shareholding, Long> {

	@Query(value="select (SHD_QUANTITY - SHD_QUANTITY_HELD) AS ACTIVE_SHARES from SPORTSEQ.SHAREHOLDING where  SHD_ID = :id",
			nativeQuery=true)
	int getActiveShares(@Param("id") long id);
	
	
}
