package edu.uga.cs.rentaride.presentation.admin.update;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.User;
import edu.uga.cs.rentaride.logic.LogicLayer;
import edu.uga.cs.rentaride.presentation.regular.TemplateProcessor;
import edu.uga.cs.rentaride.session.Session;
import edu.uga.cs.rentaride.session.SessionManager;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

/**
 * Servlet implementation class ReservationUpdate
 */
@WebServlet("/ReservationUpdate")
public class ReservationUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Configuration cfg = null;
	private String templateDir = "/WEB-INF/AdminTemplates";
	private TemplateProcessor templateProcessor = null;
	private LogicLayer logicLayer = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReservationUpdate() {
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
		
		String statusAddTypeG = "";
		String statusAddTypeB = "";
		
		Date pickupTime = null;
		DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
		int rentalLength = Integer.parseInt(request.getParameter("pickupTime"));
		int vehicleTypeId = Integer.parseInt(request.getParameter("vehicleTypeId"));
		int locationId = Integer.parseInt(request.getParameter("locationId"));
		int customerId = Integer.parseInt(request.getParameter("customerId"));
		int reservationId = Integer.parseInt(request.getParameter("reservationId"));
		String pickupTimeString = request.getParameter("pickupTime");
		
		try {
			pickupTime = df.parse(pickupTimeString);
		} catch (ParseException e1) {
			System.out.println("can't parse date.");
		}
		
		//Setting the session to null
		HttpSession    httpSession = null;
        Session        session = null;
        String         ssid;
		templateProcessor.setTemplate("AdminView.ftl");

		
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
				
				statusAddTypeB = "Failed to create a session";
				templateProcessor.addToRoot("statusAddTypeB", statusAddTypeB);
				System.out.println("LocationCreate: "+e.toString());
				templateProcessor.processTemplate(response);
			}
		}
		
		logicLayer = session.getLogicLayer();
		User user = session.getUser();
		templateProcessor.addToRoot("user", user.getFirstName());
		templateProcessor.addToRoot("userSession", user);
		
		try {
			
			logicLayer.updateReservation(reservationId, pickupTime, rentalLength, vehicleTypeId, locationId, customerId);
			statusAddTypeG = "Woohoo!";
			templateProcessor.addToRoot("statusAddTypeG", statusAddTypeG);
			templateProcessor.processTemplate(response);
		}catch(RARException e) {
			
			statusAddTypeB = "NONEXISTENT.";
			templateProcessor.addToRoot("statusAddTypeB", statusAddTypeB);
			System.out.println("ReservationUpdate: "+e.toString());
			templateProcessor.processTemplate(response);
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}