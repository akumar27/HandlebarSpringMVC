package com.voya.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.voya.model.Article;
import com.voya.model.MegaNavigation;
import com.voya.model.NavigationLink;
import com.voya.model.Product;

@Controller
public class ProductController {
	@RequestMapping("/voya")
	public ModelAndView showVoyaHome(HttpServletRequest request) {
		MegaNavigation megaNavigation = new MegaNavigation();
		megaNavigation.setTitle("Mega-Nav");

		List<NavigationLink> subNavigationList = new ArrayList<NavigationLink>();
		NavigationLink planningAndAdviceLink = new NavigationLink();
		megaNavigation.setSubNavigationList(subNavigationList);
		subNavigationList.add(planningAndAdviceLink);
		planningAndAdviceLink.setLabel("PLANNING & ADVICE");
		planningAndAdviceLink.setHref("#");

		NavigationLink planningAndAdviceChild1Link = new NavigationLink();
		List<NavigationLink> planningAndAdviceChildList = new ArrayList<NavigationLink>();
		planningAndAdviceChildList.add(planningAndAdviceChild1Link);
		planningAndAdviceLink
				.setSubNavigationLinkList(planningAndAdviceChildList);
		planningAndAdviceChild1Link
				.setLabel("Getting Started with Personal Finance");
		planningAndAdviceChild1Link.setHref("#");

		List<NavigationLink> childOfplanningAndAdviceChild1 = new ArrayList<NavigationLink>();
		planningAndAdviceChild1Link
				.setSubNavigationLinkList(childOfplanningAndAdviceChild1);
		NavigationLink gettingStartedLink = new NavigationLink();
		gettingStartedLink.setLabel("Getting Started");
		gettingStartedLink.setHref("http://www.yahoo.com");
		childOfplanningAndAdviceChild1.add(gettingStartedLink);

		gettingStartedLink = new NavigationLink();
		gettingStartedLink.setLabel("Saving and Budgeting");
		gettingStartedLink.setHref("http://www.gmail.com");
		childOfplanningAndAdviceChild1.add(gettingStartedLink);

		
		gettingStartedLink = new NavigationLink();
		gettingStartedLink.setLabel("Setting a Goal");
		gettingStartedLink.setHref("http://www.voya.com");
		childOfplanningAndAdviceChild1.add(gettingStartedLink);

		ModelAndView mav = new ModelAndView("layout");
		

		List<Article> listArticle = new ArrayList<Article>();
		Article article = new Article();
		article.setImagePath("http://www.britishlegion.org.uk/ImageGen.ashx?width=800&image=/media/2019101/id23055-normandy-66th_-schools-visit-poppy-choice_-pupils-from-london-city-academy.jpg");
		article.setTitle("This is title 1");
		article.setBody("Article 1. Ipsum ipsum dolor sit amet nulla ham qui sint exercitation eiusmod commodo, chuck duis velit. Aute in reprehenderit, "
				+ "dolore aliqua non est magna in labore pig pork biltong. Eiusmod swine spare ribs reprehenderit culpa. Boudin aliqua adipisicing rusi"
				+ " soosu ususu.");
		listArticle.add(article);

		article = new Article();
		article.setImagePath("http://static.guim.co.uk/sys-images/Guardian/Pix/pictures/2014/4/11/1397210130748/Spring-Lamb.-Image-shot-2-011.jpg");
		article.setTitle("This is title 2");
		article.setBody("Article 2. Ipsum ipsum dolor sit amet nulla ham qui sint exercitation eiusmod commodo, chuck duis velit. Aute in reprehenderit, "
				+ "dolore aliqua non est magna in labore pig pork biltong. Eiusmod swine spare ribs reprehenderit culpa. Boudin aliqua adipisicing rusi"
				+ " soosu ususu.");
		listArticle.add(article);

		article = new Article();
		article.setImagePath("http://www.keenthemes.com/preview/metronic/theme/assets/global/plugins/jcrop/demos/demo_files/image2.jpg");
		article.setTitle("This is title 3");
		article.setBody("Article 3. Ipsum ipsum dolor sit amet nulla ham qui sint exercitation eiusmod commodo, chuck duis velit. Aute in reprehenderit, "
				+ "dolore aliqua non est magna in labore pig pork biltong. Eiusmod swine spare ribs reprehenderit culpa. Boudin aliqua adipisicing rusi"
				+ " soosu ususu.");
		listArticle.add(article);
		mav.addObject("listArticle", listArticle);
		mav.addObject("projectType", "Voya.com");

		
		try {
			FileSystemResource resource = new FileSystemResource("C:/Users/i707259/git_test/HandlebarSpringMVC/src/main/resources/meganav.json");
			File file =resource.getFile();
			BufferedReader br = new BufferedReader(
					new FileReader(file));
			
			//MegaNavigation megaNavigation = new MegaNavigation();
			Gson gson = new Gson();
			MegaNavigation obj = gson.fromJson(br, MegaNavigation.class);
			 
			System.out.println(obj.toString());
			mav.addObject("megaNavigationList", obj.getSubNavigationList());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println("File not found...");
			e.printStackTrace();
			mav.addObject("megaNavigationList", subNavigationList);
		}
		
		
		return mav;
	}

	@RequestMapping("/products")
	public ModelAndView showProducts(HttpServletRequest request) {

		List<Product> productList = null;//new ArrayList<Product>();
		Product newProduct = new Product();
		newProduct.setName("Apple iPhone 6");
		newProduct.setColor("White");
		//productList.add(newProduct);
		newProduct = new Product();
		newProduct.setName("Apple iPad 3");
		newProduct.setColor("Black");
		//productList.add(newProduct);
		newProduct = new Product();
		newProduct.setName("Samsung Galaxy");
		newProduct.setColor("Silver");
		//productList.add(newProduct);

		ModelAndView mav = new ModelAndView("product");

		// String productData = "{'name' : 'Apple', 'color' : 'Red'}";
		mav.addObject("productList", productList);
		return mav;

	}
}
