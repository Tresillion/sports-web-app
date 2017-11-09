package ie.eqsports.tasks;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import ie.eqsports.trade.TradeService;

@Component
public class OfferTasks {
	
	private static final Logger log = LoggerFactory.getLogger(OfferTasks.class);	
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Autowired
	TradeService tradeService;
	
	@Scheduled(fixedDelay = 600000)
	public void closeExpiredOffers() {
		
		log.debug("Close Expired Offers Tasks Run at " + dateFormat.format(new Date()));
		tradeService.processExpiredOffers();
	}
	
	
	
}
