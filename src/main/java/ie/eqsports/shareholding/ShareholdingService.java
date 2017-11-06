package ie.eqsports.shareholding;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;;

@Transactional
@Service
public class ShareholdingService {

	@Autowired
	private ShareholdingRepository shareholdingRepository;
	
	@Autowired
	private ShareRepository shareRepository;
	
	
	
	public long getActiveShares(long id) {
		
		return shareholdingRepository.getActiveShares(id);
	}
	
	
	public List<Share> getShares() {

		Iterable<Share> it = shareRepository.findAll();
		return Lists.newArrayList(it);

	}
	
	public List<Share> getShares(String name) {

		Iterable<Share> it = shareRepository.findByNameContainingIgnoreCase(name);
		return Lists.newArrayList(it);

	}
	
	public Share getShare(long id) {

		return shareRepository.findOne(id);


	}
	
	
}
