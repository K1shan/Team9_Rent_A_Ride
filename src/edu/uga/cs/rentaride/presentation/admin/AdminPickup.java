package edu.uga.cs.rentaride.presentation.admin;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class AdminPickup
 */
@WebServlet("/AdminPickup")
public class AdminPickup extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Configuration cfg = null;
	
	//This the folder the it will return too
	private String templateDir = "/WEB-INF/AdminTemplates";
	private TemplateProcessor templateProcessor = null;
	private LogicLayer logicLayer = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminPickup() {
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
		String statusRetrieveAdminReservationG = "";
		String statusRetrieveAdminReservationB = "";
		//Setting the session to null
		HttpSession    httpSession = null;
        Session        session = null;
        String         ssid;
        int 		   reservationId = Integer.parseInt(request.getParameter("reservationId"));
        
		
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
				statusRetrieveAdminReservationB = e.toString();
				templateProcessor.addToRoot("statusRetrieveAdminReservationB", statusRetrieveAdminReservationB);
				templateProcessor.processTemplate(response);
			}
		}
        
		logicLayer = session.getLogicLayer();
		User user = session.getUser();
		int customerId = (int) user.getId();
		templateProcessor.addToRoot("user", user.getFirstName());
		templateProcessor.addToRoot("userSession", user);
			
		user = session.getUser();
		templateProcessor.addToRoot("user", user.getFirstName());
		templateProcessor.addToRoot("userSession", user);
		templateProcessor.addToRoot("reservationId", reservationId);
		templateProcessor.addToRoot("userSession", user);
		
		try {
			RentARideParams params = logicLayer.findParams();
			int latefee = params.getLateFee();
			templateProcessor.addToRoot("latefee", latefee);
			List<Reservation> reservations = logicLayer.findCustomerReservations(customerId);
			templateProcessor.addToRoot("reservations", reservations);
			logicLayer.checkPickupTime( reservationId );
			List<Vehicle> vehicles = logicLayer.findReservationVehicles( reservationId );
			templateProcessor.addToRoot("vehicles", vehicles);
	        templateProcessor.setTemplate("AdminRental.ftl");
			templateProcessor.processTemplate(response);

		} catch(RARException e){
			
			statusRetrieveAdminReservationB = e.toString();
			e.printStackTrace();
			templateProcessor.addToRoot("statusRetrieveAdminReservationB", statusRetrieveAdminReservationB);
	        templateProcessor.setTemplate("AdminReservations.ftl");
			templateProcessor.processTemplate(response);
		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
