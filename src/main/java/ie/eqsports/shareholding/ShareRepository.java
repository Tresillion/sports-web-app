package ie.eqsports.shareholding;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface ShareRepository extends CrudRepository<Share, Long>,
 JpaSpecificationExecutor<Share> {
	
	//@Query(value="select * from SPORTSEQ.SHARE where SHARE_NAME like %:playerName%;")	
	Iterable<Share> findByNameContainingIgnoreCase(String name);
	
	
}
