package com.slokam.da.hc;

import java.time.LocalDate;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OurSce {

	private static Logger  LOGGER = LoggerFactory.getLogger(OurSce.class);
	@Scheduled(cron = "* * 12 2 1 *" )
	public void test() {
		LocalDate date = LocalDate.now();
		if(date.isLeapYear()) {
		   LOGGER.debug("This is Scheduled task " );
		}
	}
}
