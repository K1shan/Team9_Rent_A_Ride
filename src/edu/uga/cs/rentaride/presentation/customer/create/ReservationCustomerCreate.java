package edu.uga.cs.rentaride.presentation.customer.create;

import java.io.IOException;
import java.util.Calendar;
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
import edu.uga.cs.rentaride.entity.Reservation;
import edu.uga.cs.rentaride.entity.User;
import edu.uga.cs.rentaride.entity.VehicleType;
import edu.uga.cs.rentaride.logic.LogicLayer;
import edu.uga.cs.rentaride.presentation.regular.TemplateProcessor;
import edu.uga.cs.rentaride.session.Session;
import edu.uga.cs.rentaride.session.SessionManager;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

/**
 * Servlet implementation class CustomerLocation
 */
@WebServlet("/ReservationCustomerCreate")
public class ReservationCustomerCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Configuration cfg = null;
	
	//This the folder the it will return too
	private String templateDir = "/WEB-INF";
	private TemplateProcessor templateProcessor = null;
	private LogicLayer logicLayer = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReservationCustomerCreate() {
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
		String statusAdminCreateReservationG = "";
		String statusAdminCreateReservationB = "";
		String statusCustomersCreateReservationG = "";
		String statusCustomersCreateReservationB = "";
		//Setting the session to null
		HttpSession    httpSession = null;
        Session        session = null;
        String         ssid;		
        
		int vehicleTypeId = Integer.parseInt(request.getParameter("selectReservationVehicleTypeAdd"));
		int locationId = Integer.parseInt(request.getParameter("locationId"));
		int length = Integer.parseInt(request.getParameter("selectReservationLengthAdd"));
		
		
		Calendar cal = Calendar.getInstance();
		Date pickupDate = new Date();
		String pickupDateString = request.getParameter("date");
		String pickupTimeString = request.getParameter("time");
		
		int year;
		int month;
		int day;
		int hour;
		int minute;
		
		System.out.println("pickupDate: "+pickupDateString);
		System.out.println("pickupTime: "+pickupTimeString);
		System.out.println("timestamp:  "+cal);
		
		year = Integer.parseInt(pickupDateString.substring(0,pickupDateString.indexOf("-")));
		System.out.println("year: "+year);
		
		month = Integer.parseInt(pickupDateString.substring(pickupDateString.indexOf("-")+1, pickupDateString.indexOf("-")+3));
		System.out.println("month: "+month);
		
		day = Integer.parseInt(pickupDateString.substring(pickupDateString.length()-2));
		System.out.println("day: "+day);
		
		
		
		hour = Integer.parseInt(pickupTimeString.substring(0,pickupTimeString.indexOf(":")));
		System.out.println("hour: "+hour);
		
		minute = Integer.parseInt(pickupTimeString.substring(pickupTimeString.indexOf(":")+1,pickupTimeString.indexOf(":")+3));
		System.out.println("minute: "+minute);
		
		if(pickupTimeString.substring(pickupTimeString.length()-2).equals("PM")){
			hour += 12;
		}
		
		cal.set(year, month-1, day);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, minute);
		cal.set(Calendar.SECOND, 0);
		pickupDate = cal.getTime();
		
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
				statusAdminCreateReservationB = e.toString();
				templateProcessor.addToRoot("statusAdminCreateReservationB", statusAdminCreateReservationB);
				templateProcessor.processTemplate(response);
			}
		}
        
		logicLayer = session.getLogicLayer();
		User user = session.getUser();
		int customerId = (int) user.getId();
		templateProcessor.addToRoot("user", user.getFirstName());
		templateProcessor.addToRoot("userSession", user);
		
		try {
			logicLayer.createReservation(pickupDate, length, vehicleTypeId, locationId, customerId);
			if(user.getIsAdmin()) 
				templateProcessor.setTemplate("/AdminTemplates/AdminIndex.ftl");
			else
				templateProcessor.setTemplate("/CustomerTemplates/CustomerReservation.ftl");
			
			List<Reservation> reservations = logicLayer.findCustomerReservations(customerId); 
			templateProcessor.addToRoot("reservations", reservations);
			templateProcessor.processTemplate(response);
			return;

		} catch(RARException e){
			if(user.getIsAdmin()){
				statusAdminCreateReservationB = e.toString();
			}else{
				statusCustomersCreateReservationB = e.toString();
			}
		}
		
		try {
			List<VehicleType> vehicleTypesAvail = logicLayer.findLocationAvailableVehicleTypes(locationId);
			templateProcessor.addToRoot("vehicleTypesAvail", vehicleTypesAvail);
			templateProcessor.addToRoot("locationId", locationId);
		} catch (RARException e){
			if(user.getIsAdmin()){
				statusAdminCreateReservationB += e.toString();templateProcessor.setTemplate("/AdminTemplates/AdminReservationLocation.ftl");
			}else{
				statusCustomersCreateReservationB += e.toString();templateProcessor.setTemplate("/Customer/CustomerReservationLocation.ftl");
			}
		}
		
		if(user.getIsAdmin()){
			templateProcessor.setTemplate("/AdminTemplates/AdminReservationLocation.ftl");
		}else{
			templateProcessor.setTemplate("/Customer/CustomerReservationLocation.ftl");
		}
		templateProcessor.addToRoot("statusAdminCreateReservationB", statusAdminCreateReservationB);
		templateProcessor.addToRoot("statusCustomersCreateReservationB", statusCustomersCreateReservationB);
		templateProcessor.processTemplate(response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}