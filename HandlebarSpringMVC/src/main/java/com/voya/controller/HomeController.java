/**
 * 
 */
package com.voya.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.mozilla.javascript.NativeArray;
import org.mozilla.javascript.NativeObject;
import org.mozilla.javascript.Scriptable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.jknack.handlebars.Context;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.JsonNodeValueResolver;
import com.github.jknack.handlebars.Options;
import com.github.jknack.handlebars.cache.HighConcurrencyTemplateCache;
import com.github.jknack.handlebars.context.FieldValueResolver;
import com.github.jknack.handlebars.context.JavaBeanValueResolver;
import com.github.jknack.handlebars.context.MapValueResolver;
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

		Post newPost = new Post("Amit", "First Post", "Wonderful Summer day");
		ArrayList<Comment> commentList = new ArrayList<Comment>();
		Comment newComment = new Comment("Mark", "After very long...");
		commentList.add(newComment);
		newPost.setCommentList(commentList);

		newComment = new Comment("Steve", "I like summer");
		commentList.add(newComment);

		newComment = new Comment("Erwin", "Off to holidays");
		commentList.add(newComment);

		newComment = new Comment("Bob", "Cannot wait for weekend anymore :)");
		commentList.add(newComment);
		
		PostSource postSource = new PostSource("Mobile", "111.222.333.444");
		newPost.setPostSource(postSource);
		mav.addObject("newPost", newPost);
		return mav;
	}

	@RequestMapping("/precompile")
	public ModelAndView precompileController(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("helpers/precompile");
		return mav;
	}

	@RequestMapping("/cachingExample1")
	public ModelAndView cachingController(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("viewResolver/cachingExample1");
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

	@RequestMapping("/ii18njs")
	public ModelAndView ii18njsController(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("helpers/ii18njs");
		return mav;
	}

	@RequestMapping("/partial")
	public ModelAndView partialController(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("helpers/partials");
		return mav;
	}

	@RequestMapping("/embedded")
	public ModelAndView embeddedController(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("helpers/embedded");
		mav.addObject("firstName", "Amit");
		mav.addObject("lastName", "Kumar");
		return mav;
	}

	@RequestMapping("/block")
	public ModelAndView blockController(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("helpers/block");
		return mav;
	}

	@RequestMapping("/JsonNodeValueResolver")
	@ResponseBody
	String jsonNodeValueResolverController(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView(
				"viewResolver/jsonNodeValueResolver");

		try {
			String value = "{\"title\": \"First Post\",\"story\": {\"intro\": \"Before the jump\",\"body\": \"After the jump\"}}";

			ObjectMapper mapper = new ObjectMapper();
			JsonNode jsonNode = mapper.readTree(value);

			Handlebars handlebars = new Handlebars();
			handlebars.with(new HighConcurrencyTemplateCache());
			Context context = Context.newBuilder(jsonNode)
					.resolver(JsonNodeValueResolver.INSTANCE).build();

			String output = handlebars
					.compileInline(
							"<a href='home'>Home</a><div class=\"entry\"> <h1>{{title}}</h1> {{#with story}}  <div class=\"intro\">{{{intro}}}"
									+ "</div> <div class=\"body\">{{{body}}}</div>{{/with}}</div>")
					.apply(context);
			// mav.addObject("result", output);
			// mav.addObject("jsonObject: " + jsonNode);
			mav.addObject("output", output);
			response.setContentType("text/html");
			// response.setCharacterEncoding("UTF-8");
			System.out.println("output: " + output);
			return output;

		}

		catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

	@RequestMapping("/MapValueResolver")
	@ResponseBody
	String mapValueResolverController(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView(
				"viewResolver/jsonNodeValueResolver");

		try {
			Handlebars handlebars = new Handlebars();

			handlebars.registerHelper("sayHi", new Helper<String>() {
				// @Override
				public CharSequence apply(final String text,
						final Options options) {
					return "Hello World";
				}
			});
			NativeObject fruits = new NativeObject();

			NativeObject pear = new NativeObject();
			NativeObject.putProperty(pear, "name", "pear");
			NativeObject.putProperty(pear, "taste", "sweet");
			NativeObject.putProperty(pear, "color", "green");

			NativeObject lemon = new NativeObject();
			NativeObject.putProperty(lemon, "name", "lemon");
			NativeObject.putProperty(lemon, "taste", "sour");
			NativeObject.putProperty(lemon, "color", "yellow");

			NativeObject grapes = new NativeObject();
			NativeObject.putProperty(grapes, "name", "grapes");
			NativeObject.putProperty(grapes, "taste", "sweet and sour");
			NativeObject.putProperty(grapes, "color", "greem");

			NativeObject apple = new NativeObject();
			NativeObject.putProperty(apple, "name", "grapes");
			NativeObject.putProperty(apple, "taste", "sweet");
			NativeObject.putProperty(apple, "color", "Red");

			NativeObject.putProperty(fruits, "pear", pear);
			NativeObject.putProperty(fruits, "lemon", lemon);
			NativeObject.putProperty(fruits, "apple", apple);
			NativeObject.putProperty(fruits, "grapes", grapes);

			Map<String, Scriptable> fruitsMap = new HashMap<String, Scriptable>();
			fruitsMap.put("frutsMap", fruits);
			fruitsMap.put("fruitsArr", new NativeArray(new NativeObject[] {
					lemon, pear, grapes, apple }));

			Context ctxFruits = Context
					.newBuilder(fruitsMap)
					.resolver(MapValueResolver.INSTANCE,
							JavaBeanValueResolver.INSTANCE,
							FieldValueResolver.INSTANCE).build();
			System.out.println("Map: "
					+ handlebars.compileInline(
							"{{#each fruitsMap}} {{ taste }} {{/each}}").apply(
							ctxFruits));
			System.out.println("Array: "
					+ handlebars.compileInline(
							"{{#each fruitsArr}} {{ taste }} {{/each}}").apply(
							ctxFruits));

			String output = handlebars
					.compileInline(
							"<a href='home'>Home</a><br><br><table border=1><tr><th>Fruit Name</th><th>Color</th><th>Taste</th></tr>{{#each fruitsArr}}"
									+ "<tr><td>{{ name }}</td>"
									+ "<td>{{ color }}</td><td>{{ taste }}</td></tr>{{/each}}</table>")
					.apply(ctxFruits);
			response.setContentType("text/html");
			// response.setCharacterEncoding("UTF-8");

			return output;

		}

		catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

	@RequestMapping("/FieldValueResolver")
	@ResponseBody
	String fieldValueResolverController(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView(
				"viewResolver/jsonNodeValueResolver");

		try {
			Handlebars handlebars = new Handlebars();

			Object[] array = { "Apple", "Grapes", "Pear" };
			Context ctxFruits = Context
					.newBuilder(array)
					.resolver(FieldValueResolver.INSTANCE).build();
		

			String output = handlebars
					.compileInline(
							"<a href='home'>Home</a><ul>{{#each this}}<li>{{.}}</li>{{/each}}</table></ul>")
					.apply(ctxFruits);
			response.setContentType("text/html");
			// response.setCharacterEncoding("UTF-8");

			return output;

		}

		catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

	public String getMessage() {
		return "Hi Hello";
	}

}
