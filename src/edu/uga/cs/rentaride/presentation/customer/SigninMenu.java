package edu.uga.cs.rentaride.presentation.customer;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.*;
import edu.uga.cs.rentaride.logic.LogicLayer;
import edu.uga.cs.rentaride.presentation.regular.TemplateProcessor;
import edu.uga.cs.rentaride.session.Session;
import edu.uga.cs.rentaride.session.SessionManager;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

/**
 * Servlet implementation class signinOne
 */
@WebServlet("/SigninMenu")
public class SigninMenu extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	Configuration cfg = null;
	private String templateDir = "/WEB-INF";
	private TemplateProcessor templateProcessor = null;
	private LogicLayer logicLayer = null;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SigninMenu() {
        super();
        // TODO Auto-generated constructor stub
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
		templateProcessor = new TemplateProcessor(cfg, getServletContext(), templateDir);

	}

	public void toLoginPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// File sent when login success
		templateProcessor.setTemplate("CreateAccountTemplates/SigninCreateForm.ftl");
		templateProcessor.processTemplate(response);
	} // toHomePage
	
	
	public void toHomePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String status = "";
		//Setting the session to null
		HttpSession    httpSession = null;
        Session        session = null;
        String         ssid;		
        String 			email;
        String 			password;

        
        email = request.getParameter("email");
        password = request.getParameter("password");
        
        //Getting the http session and store it into the ssid
        httpSession = request.getSession();
		ssid = (String) httpSession.getAttribute( "ssid" );
        
		//Here it will get the existing id
		if( ssid != null ) {

            session = SessionManager.getSessionById( ssid );
        }
		
		//Here it will create the session id 
		if( session == null ){
			try {
				
				session = SessionManager.createSession();
			} catch ( Exception e ){
				status = e.toString();
				templateProcessor.addToRoot("status", status);
				templateProcessor.processTemplate(response);
			}
		}
        
		logicLayer = session.getLogicLayer();
		User user;
		
   	 	try {
			ssid = logicLayer.checkAdminCredentials(session,email,password);
            httpSession.setAttribute( "ssid", ssid );
			user = session.getUser();
			templateProcessor.setTemplate("AdminTemplates/AdminIndex.ftl");
			templateProcessor.addToRoot("user", user.getFirstName());
			templateProcessor.addToRoot("userSession", user);
			templateProcessor.addToRoot("status", status);
			templateProcessor.processTemplate(response);
			return;
   	 	} catch (RARException e1){
   	 		try {
   	 			
   	 			
   	 			ssid = logicLayer.checkCustomerCredentials(session,email,password);
   	 			httpSession.setAttribute( "ssid", ssid );
   	 			user = session.getUser();
				templateProcessor.setTemplate("CustomerTemplates/CustomerIndex.ftl");
				templateProcessor.addToRoot("user", user.getFirstName());
				templateProcessor.addToRoot("userSession", user);				
				templateProcessor.addToRoot("status", status);
				templateProcessor.processTemplate(response);
				return;
   	 		} catch (RARException e2){
   	 			
	   	 		status = "1";
	   	 		templateProcessor.setTemplate("CreateAccountTemplates/SigninCreateForm.ftl");
				templateProcessor.addToRoot("status", status);
				templateProcessor.processTemplate(response);
   	 		}
   	 	}
   	 	

	} // toLoginPage
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("signin") != null) toHomePage(request, response);
		else toLoginPage(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
}