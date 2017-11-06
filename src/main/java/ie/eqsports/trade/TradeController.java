package ie.eqsports.trade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class TradeController {

	@Autowired
	TradeService tradeService;
	
    @CrossOrigin()
    @RequestMapping(value = "/allOffers", method = RequestMethod.GET) 
    public ResponseEntity<OfferDto[]> getAllOffers(@RequestParam(name="shareId") long shareId) {
	
    	
    	List<OfferDto> offerDtos = tradeService.getAllActiveOffers(shareId);
    	OfferDto[] odArr = new OfferDto[offerDtos.size()];
    	odArr = (OfferDto[])offerDtos.toArray(odArr);
    	return ResponseEntity.ok(odArr);
    	
    }   
	
	
}
