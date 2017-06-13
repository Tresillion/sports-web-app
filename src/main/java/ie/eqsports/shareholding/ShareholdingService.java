package ie.eqsports.shareholding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ShareholdingService {

	@Autowired
	private ShareholdingRepository shareholdingRepository;
	
	
	
	public long getActiveShares(long id) {
		
		return shareholdingRepository.getActiveShares(id);
	}
	
}
