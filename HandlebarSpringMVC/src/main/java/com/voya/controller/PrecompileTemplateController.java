/**
 * 
 */
package com.voya.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Amit
 *
 */
@Controller
public class PrecompileTemplateController {
	@RequestMapping("/precompileHandlebars")
	public ModelAndView precompileHandlebars(HttpServletRequest request) {

		ModelAndView mav = new ModelAndView("precompile");

		// String productData = "{'name' : 'Apple', 'color' : 'Red'}";
		//mav.addObject("productList", productList);
		//String js = PrecompileHelper.INSTANCE.apply("partial", new Options.Builder().build()); 
		return mav;

	}
}
