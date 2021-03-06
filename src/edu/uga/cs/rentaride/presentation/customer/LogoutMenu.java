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
import edu.uga.cs.rentaride.logic.LogicLayer;
import edu.uga.cs.rentaride.presentation.regular.TemplateProcessor;
import edu.uga.cs.rentaride.session.Session;
import edu.uga.cs.rentaride.session.SessionManager;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

/**
 * Servlet implementation class LogoutMenu
 */
@WebServlet("/LogoutMenu")
public class LogoutMenu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Configuration cfg = null;
	private String templateDir = "/WEB-INF";
	private TemplateProcessor templateProcessor = null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutMenu() {
        super();
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
		templateProcessor = new TemplateProcessor(cfg, getServletContext(), templateDir);
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession 	httpSession = null;
        Session        	session = null;
        String         	ssid;
        String 			status = "";
        
        //Getting the http session and store it into the ssid
  		httpSession = request.getSession( false );
  		if( httpSession != null ){
  			ssid = (String) httpSession.getAttribute( "ssid" );
  			if( ssid != null ){
  				session = SessionManager.getSessionById( ssid );
  				if( session == null ){
  					return;
  				}
  				LogicLayer logicLayer = session.getLogicLayer();
  				try {
  					logicLayer.logout( ssid );
  					httpSession.removeAttribute( "ssid" );
  					httpSession.invalidate();
  				} catch ( RARException e ){
  					e.printStackTrace();
  				}
  			}else{
  				templateProcessor.setTemplate("CreateAccountTemplates/index.ftl");
  				templateProcessor.processTemplate(response);
  				return;
  			}
  		}else{
			templateProcessor.setTemplate("CreateAccountTemplates/index.ftl");
			templateProcessor.processTemplate(response);
			return;
  		}
  		
		templateProcessor.setTemplate("CreateAccountTemplates/SigninCreateForm.ftl");
		templateProcessor.processTemplate(response);  			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
}