package edu.uga.cs.rentaride.presentation.customer.update;

import java.io.IOException;
import java.util.Date;
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
 * Servlet implementation class CustomerLocation
 */
@WebServlet("/CustomerReturn")
public class CustomerReturn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Configuration cfg = null;
	
	//This the folder the it will return too
	private String templateDir = "/WEB-INF/CustomerTemplates";
	private TemplateProcessor templateProcessor = null;
	private LogicLayer logicLayer = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerReturn() {
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
		String statusRetrieveCustomerReservationG = "";
		String statusRetrieveCustomerReservationB = "";
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
				statusRetrieveCustomerReservationB = e.toString();
				templateProcessor.addToRoot("statusRetrieveCustomerReservationB", statusRetrieveCustomerReservationB);
				templateProcessor.processTemplate(response);
			}
		}
        
		logicLayer = session.getLogicLayer();
		User user = session.getUser();
		templateProcessor.addToRoot("user", user.getFirstName());
		System.out.println("sessionuser: "+user);
		templateProcessor.addToRoot("userSession", user);
			
		try {
			RentARideParams params = logicLayer.findParams();
			int latefee = params.getLateFee();
			templateProcessor.addToRoot("latefee", latefee);
			List<Reservation> reservations = logicLayer.findReservations(reservationId);
			List<Reservation> reservationAll = logicLayer.findCustomerReservations((int)user.getId());
			Reservation reservation = reservations.get(0);
			if(reservation.getRental() != null){
				Rental rental = reservation.getRental();
				int rentalId = (int)rental.getId();
				logicLayer.updateRental( rentalId, rental.getPickupTime(), reservationId, -1);
				List<Rental> rentals = logicLayer.findRentals(rentalId);
				rental = rentals.get(0);
				long diff = rental.getReturnTime().getTime()-rental.getPickupTime().getTime();
				long hours = diff / (60 * 60 * 1000);
				templateProcessor.addToRoot("charges", rental.getCharges());
				templateProcessor.addToRoot("rentalId", rentalId);
				templateProcessor.addToRoot("hours", hours);
				statusRetrieveCustomerReservationG = "Successfully returned a rental";
				user = session.getUser();
		        templateProcessor.setTemplate("CustomerComment.ftl");
				templateProcessor.addToRoot("user", user.getFirstName());
				templateProcessor.addToRoot("userSession", user);
				templateProcessor.addToRoot("reservationId", reservationId);
				templateProcessor.addToRoot("statusRetrieveCustomerReservationG", statusRetrieveCustomerReservationG);
				templateProcessor.addToRoot("reservations", reservationAll);
				//templateProcessor.processTemplate(response);
			}else{
				throw new RARException("rental was never picked up");
			}
			

		} catch(RARException e){
			templateProcessor.setTemplate("CustomerReservation.ftl");
			e.printStackTrace();
			statusRetrieveCustomerReservationB = e.toString();
			templateProcessor.addToRoot("statusRetrieveCustomerReservationB", statusRetrieveCustomerReservationB);
		}
		
		
		try {
			List<Reservation> reservations = logicLayer.findCustomerReservations((int)user.getId());
			templateProcessor.addToRoot("reservations", reservations);
			templateProcessor.processTemplate(response);
		} catch (RARException e){
			statusRetrieveCustomerReservationB += " Failed to find reservations";
			templateProcessor.addToRoot("statusRetrieveCustomerReservationB", statusRetrieveCustomerReservationB);
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