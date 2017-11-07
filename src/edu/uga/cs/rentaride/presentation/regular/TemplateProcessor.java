package edu.uga.cs.rentaride.presentation.regular;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

/**
 * This class configures the application and provides functions for template processing, where Freemarker 
 * templates are utilized to generate dynamic content. Data is drawn from the "root" hash map.
 * @author Alex White
 */
public class TemplateProcessor {
	private String templateDir = null;
	private DefaultObjectWrapperBuilder db = null;
	private SimpleHash root = null;
	private Template template = null;
	private Configuration cfg = null;
	
	/**
	 * Configures for template processing. Assumes that the directory containing templates is located in "/WEB-INF/templates"
	 */
	public TemplateProcessor(Configuration cfg, ServletContext servletContext) {
		this.cfg = cfg;
		templateDir = "/WEB-INF/signinTemplates";
		cfg.setServletContextForTemplateLoading(servletContext, templateDir);
    	cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
		db = new DefaultObjectWrapperBuilder(Configuration.getVersion());
		root = new SimpleHash(db.build());
	} // TemplateProcessor
	
	/**
	 * Configures for template processing, but defines a specific directory that contains Freemarker templates.
	 * @param templateDir the directory containing freemarker templates
	 */
	public TemplateProcessor(Configuration cfg, ServletContext servletContext, String templateDir) {
		this(cfg, servletContext);
		this.templateDir = templateDir;
		cfg.setServletContextForTemplateLoading(servletContext, templateDir);
	} // TemplateProcessor
	
	/**
	 * Adds an object of type T to the root hash map.
	 * @param key is used to access the object t in the hashmap
	 * @param t is the object to be stored
	 */
	public <T> void addToRoot(String key, T t) {
		root.put(key, t);
	} // addToRoot
	
	/**
	 * Displays the template to the client and resets the builder and root hashmap.
	 * @param response 
	 */
	public void processTemplate(HttpServletResponse response) {
		try {
			response.setContentType("text/html");
			template.process(root, response.getWriter());
			db = new DefaultObjectWrapperBuilder(Configuration.getVersion());
			root = new SimpleHash(db.build());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		} // try-catch	
	} // processTemplate 
	
	/**
	 * @return The directory by which templates are being processed from.
	 */
	public String getTemplateDir() {
		return templateDir;
	} // getTemplateDir

	/**
	 * templateDir is the directory by which templates are being processed from. Unless specified,
	 * this directory is by default "/WEB-INF/templates"
	 * 
	 * @param templateDir can be a relative path or absolute path.
	 */
	public void setTemplateDir(String templateDir) {
		this.templateDir = templateDir;
	} // setTemplateDir
	
	/**
	 * @return A hash map that stores data to be displayed on dynamically generated Freemarker templates. 
	 */
	public SimpleHash getRoot() {
		return root;
	} // getRoot

	/**
	 * @param root 
	 */
	public void setRoot(SimpleHash root) {
		this.root = root;
	} // setRoot

	/**
	 * @return the processor's working template. 
	 */
	public Template getTemplate() {
		return template;
	} // getTemplate

	/**
	 * Set the processor's working template
	 * @param template
	 */
	public void setTemplate(Template template) {
		this.template = template;
	} // setTemplate

	/**
	 * Sets the processor's working template, which can be processed and displayed to the client.
	 * @param templateName should exist in the templateDir directory.
	 */
	public void setTemplate(String templateName) {
		try {
			this.template = cfg.getTemplate(templateName);
		} catch (IOException e) {
			e.printStackTrace();
		} // try-catch
	} // setTemplate
	
} // TemplateProcessor

