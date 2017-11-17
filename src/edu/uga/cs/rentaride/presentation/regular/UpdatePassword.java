package edu.uga.cs.rentaride.presentation.regular;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.uga.cs.rentaride.logic.LogicLayer;
import edu.uga.cs.rentaride.presentation.regular.TemplateProcessor;
import edu.uga.cs.rentaride.session.Session;
import edu.uga.cs.rentaride.session.SessionManager;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import edu.uga.cs.rentaride.RARException;

/**
 * Servlet implementation class UpdatePassword
 */
@WebServlet("/UpdatePassword")
public class UpdatePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Configuration cfg = null;
	
	//This the folder the it will return too
	private String templateDir = "/WEB-INF/CreateAccountTemplates";
	private TemplateProcessor templateProcessor = null;
	private LogicLayer logicLayer = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePassword() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String statusUpdatePasswordG = "";
		String statusUpdatePasswordB = "";
		//Setting the session to null
		HttpSession    httpSession = null;
        Session        session = null;
        String         ssid;
        String         username;
        String		   password;
        String         fname;
        String		   lname;
        
        username = request.getParameter("email");
        password = request.getParameter("password");
        fname = request.getParameter("fname");
        lname = request.getParameter("lname");
		
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
				statusUpdatePasswordB = e.toString();
				templateProcessor.addToRoot("statusUpdatePasswordB", statusUpdatePasswordB);
				templateProcessor.processTemplate(response);
			}
		}
        
		logicLayer = session.getLogicLayer();
		
		try {
			
			logicLayer.resetUserPassword(username, fname, lname, password);
			statusUpdatePasswordG = "Woohoo!";
			templateProcessor.setTemplate("SigninCreateForm.ftl");
			templateProcessor.addToRoot("statusUpdatePasswordG", statusUpdatePasswordG);
			templateProcessor.processTemplate(response);
		}
		catch(RARException e){
			statusUpdatePasswordB = "BAD.";
			templateProcessor.setTemplate("ForgotPassword.ftl");
			templateProcessor.addToRoot("statusUpdatePasswordB", statusUpdatePasswordB);
			templateProcessor.processTemplate(response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
