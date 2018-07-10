package org.spring.springboot.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.spring.springboot.domain.City;
import org.spring.springboot.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by bysocket on 07/02/2017.
 */
@Controller
public class MainController {
	
	private Log log = LogFactory.getLog(MainController.class);

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public ModelAndView main(ModelAndView mv) {
    	log.info("22222222222222222222222222222222222111111111111111111111111111111111111111111111");
    	mv.setViewName("main");
        return	mv;
    }
    
}
