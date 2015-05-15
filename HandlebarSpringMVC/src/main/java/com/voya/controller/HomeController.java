/**
 * 
 */
package com.voya.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.jknack.handlebars.Context;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.JsonNodeValueResolver;
import com.google.gson.Gson;
import com.voya.model.Comment;
import com.voya.model.HomeLink;
import com.voya.model.Post;
import com.voya.model.PostSource;

/**
 * @author Amit Kumar
 *
 */
@Controller
public class HomeController {

	private @Autowired ServletContext servletContext;

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public @ResponseBody String myTest() throws IOException {
		InputStream inputStream = null;
		StringBuilder sb = new StringBuilder();
		String line;
		try {
			inputStream = servletContext
					.getResourceAsStream("/WEB-INF/content/links.json");
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(inputStream));

			while ((line = bufferedReader.readLine()) != null) {
				sb.append(line);
			}
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
		return sb.toString();
	}

	@RequestMapping("/home")
	public ModelAndView homeController(HttpServletRequest request)
			throws IOException {

		ModelAndView mav = new ModelAndView("index");
		InputStream inputStream = null;
		StringBuilder sb = new StringBuilder();
		String line;
		try {
			inputStream = servletContext
					.getResourceAsStream("/WEB-INF/content/links.json");
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(inputStream));

			while ((line = bufferedReader.readLine()) != null) {
				sb.append(line);
			}
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}

		// MegaNavigation megaNavigation = new MegaNavigation();
		Gson gson = new Gson();
		HomeLink obj = gson.fromJson(sb.toString(), HomeLink.class);

		System.out.println(obj.toString());
		mav.addObject("homePageLinks", obj);
		return mav;

	}

	@RequestMapping("/withEachIf")
	public ModelAndView withController(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("helpers/with");
		/*try {
			String value = "{\"title\": \"First Post\",\"story\": {\"intro\": \"Before the jump\",\"body\": \"After the jump\"}}";

			ObjectMapper mapper = new ObjectMapper();
			JsonNode jsonNode = mapper.readTree(value);

			Handlebars handlebars = new Handlebars();

			Context context = Context.newBuilder(jsonNode)
					.resolver(JsonNodeValueResolver.INSTANCE).build();

			String output = handlebars.compileInline(
					"<div class=\"entry\"> <h1>{{title}}</h1> {{#with story}}  <div class=\"intro\">{{{intro}}}"
					+ "</div> <div class=\"body\">{{{body}}}</div>{{/with}}</div>")
					.apply(context);
			//mav.addObject("result", output);
			mav.addObject("jsonObject: " + jsonNode);
			mav.addObject("StringObject: " + value);
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		*/
		
		
		Post newPost = new Post("Amit" , "First Post" , "Wonderful Summer day");
		ArrayList<Comment> commentList = new ArrayList<Comment>();
		Comment newComment = new Comment("Mark" , "After very long...");
		commentList.add(newComment);
		newPost.setCommentList(commentList);
		
		newComment = new Comment("Steve" , "I like summer");
		commentList.add(newComment);
		
		newComment = new Comment("Erwin" , "Off to holidays");
		commentList.add(newComment);
		
		newComment = new Comment("Bob" , "Cannot wait for weekend anymore :)");
		commentList.add(newComment);
		
		PostSource postSource = new PostSource("Mobile", "111.222.333.444");
		newPost.setPostSource(postSource);
		mav.addObject("newPost" ,  newPost);
		return mav;
	}

	@RequestMapping("/precompile")
	public ModelAndView precompileController(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("helpers/precompile");
		return mav;
	}

	@RequestMapping("/precompileTemplate")
	public ModelAndView precompileTemplateController(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("helpers/precompile-template");
		return mav;
	}
	
	@RequestMapping("/ii18n")
	public ModelAndView ii18nController(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("helpers/ii18n");
		return mav;
	}
}
