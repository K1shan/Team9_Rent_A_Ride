package edu.uga.cs.rentaride.presentation.regular;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.uga.cs.rentaride.presentation.regular.TemplateProcessor;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.logic.LogicLayer;
import edu.uga.cs.rentaride.session.Session;
import edu.uga.cs.rentaride.session.SessionManager;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

/**
 * Servlet implementation class signin
 */
@WebServlet("/signin")
public class signin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	Configuration cfg = null;
	private TemplateProcessor templateProcessor = null;
	private String templateDir = "/WEB-INF/signinTemplates";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public signin() {
        super();
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		// Create your Configuration instance, and specify if up to what FreeMarker
		// version (here 2.3.25) do you want to apply the fixes that are not 100%
		// backward-compatible. See the Configuration JavaDoc for details.
		cfg = new Configuration(Configuration.VERSION_2_3_25);

		// Specify the source where the template files come from.
		//cfg.setServletContextForTemplateLoading(getServletContext(), templateDir);

		// Sets how errors will appear.
		// During web page *development* TemplateExceptionHandler.HTML_DEBUG_HANDLER is better.
		// This handler outputs the stack trace information to the client, formatting it so 
		// that it will be usually well readable in the browser, and then re-throws the exception.
		//		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);

		// Don't log exceptions inside FreeMarker that it will thrown at you anyway:
		// Specifies if TemplateException-s thrown by template processing are logged by FreeMarker or not. 
		//		cfg.setLogTemplateExceptions(false);
		templateProcessor = new TemplateProcessor(cfg, getServletContext(), "/WEB-INF/signinTemplates");

	}

	public void registerUser1(HttpServletRequest request, HttpServletResponse response){
    	System.out.println("signin.registerUser2()");
		templateProcessor.setTemplate("signinTwo.ftl");
		String status = "";
    	String fname = request.getParameter("first-name");
		String lname = request.getParameter("last-name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		LogicLayer	logicLayer = null;
		HttpSession    httpSession = null;
        Session        session = null;
        String         ssid;

		httpSession = request.getSession();
		ssid = (String) httpSession.getAttribute( "ssid" );
		if( ssid != null ) {
            System.out.println( "Already have ssid: " + ssid );
            session = SessionManager.getSessionById( ssid );
            System.out.println( "Connection: " + session.getConnection() );
        }
        else
            System.out.println( "ssid is null" );
		
		if( session == null ){
			try {
				session = SessionManager.createSession();
			} catch ( Exception e ){
				status = e.toString();
				templateProcessor.setTemplate("signinOne.ftl");
				templateProcessor.addToRoot("status", status);
				templateProcessor.processTemplate(response);
			}
		}
		
		logicLayer = session.getLogicLayer();
		
		try {
			logicLayer.createAccount1(fname, lname, email, password);
		} catch (RARException e) {
			e.printStackTrace();
		} finally {
			templateProcessor.addToRoot("status", status);
			templateProcessor.processTemplate(response);
		}
    }
	
	
	public void registerUser2(HttpServletRequest request, HttpServletResponse response){
    	System.out.println("signin.registerUser2()");
		templateProcessor.setTemplate("signinOne.ftl");
		String status = "";


		templateProcessor.addToRoot("status", status);
		templateProcessor.processTemplate(response);
    }
	
	public void toSigninOne(HttpServletResponse response) {
    	System.out.println("signin.toSigninOne()");
		templateProcessor.setTemplate("signinOne.ftl");
		templateProcessor.processTemplate(response);
	} // toSigninOne
	
	public void toSigninTwo(HttpServletResponse response) {
    	System.out.println("signin.toSigninTwo()");
		templateProcessor.setTemplate("signinOneTwo.ftl");
		templateProcessor.processTemplate(response);
	} // toSigninTwo
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println( "signin.doGet()" );
//		Template template = null;
//		DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
//		SimpleHash root = new SimpleHash(df.build());
//		response.setContentType("text/html");
		
		System.out.println("email: "+request.getParameter("email"));
		
		if (!(request.getParameter("email").equals(""))) registerUser1(request, response);
		else if (request.getParameter("address") != null) registerUser2(request, response);
		else toSigninOne(response);

		
//		try {	
//			String templateName = "signinOne.ftl";
//			template = cfg.getTemplate(templateName );
//			response.setContentType("text/html");
//			Writer out = response.getWriter();
//			template.process(root, out);
//		}catch (TemplateException e) {
//			e.printStackTrace();
//		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println( "signin.doPost()" );
		doGet(request, response);
	}

}
