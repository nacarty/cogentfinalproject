package com.carty.log4j2;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service(value="logger")
public class log4j2 {		
		static final Logger  log = Logger.getLogger(log4j2.class);

		log4j2(){
			BasicConfigurator.configure();
		}
		
		public void doLog(String info) {
			log.info(info);
		}
}
