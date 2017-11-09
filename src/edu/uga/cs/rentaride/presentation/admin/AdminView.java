package edu.uga.cs.rentaride.presentation.admin;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.uga.cs.rentaride.logic.LogicLayer;
import edu.uga.cs.rentaride.presentation.regular.TemplateProcessor;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

/**
 * Servlet implementation class AdminView
 */
@WebServlet("/AdminView")
public class AdminView extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Configuration cfg = null;
	private String templateDir = "/WEB-INF";
	private TemplateProcessor templateProcessor = null;
	private LogicLayer logicLayer = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// Create your Configuration instance, and specify if up to what FreeMarker
		// version (here 2.3.25) do you want to apply the fixes that are not 100%
		// backward-compatible. See the Configuration JavaDoc for details.
		cfg = new Configuration(Configuration.VERSION_2_3_25);

		// Specify the source where the template files come from.
		cfg.setServletContextForTemplateLoading(getServletContext(), templateDir);

		// Sets how errors will appear.
		// During web page *development* TemplateExceptionHandler.HTML_DEBUG_HANDLER is better.
		// This handler outputs the stack trace information to the client, formatting it so 
		// that it will be usually well readable in the browser, and then re-throws the exception.
		//		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);

		// Don't log exceptions inside FreeMarker that it will thrown at you anyway:
		// Specifies if TemplateException-s thrown by template processing are logged by FreeMarker or not. 
		//		cfg.setLogTemplateExceptions(false);
		templateProcessor = new TemplateProcessor(cfg, getServletContext(), templateDir);	}

	public void toAdminIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		templateProcessor.setTemplate("AdminTemplates/AdminIndex.ftl");
		templateProcessor.processTemplate(response);
	} // toAdminIndex
	
	public void toAdminView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		templateProcessor.setTemplate("AdminTemplates/AdminView.ftl");
		templateProcessor.processTemplate(response);
	} // toAdminView
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		toAdminIndex(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
