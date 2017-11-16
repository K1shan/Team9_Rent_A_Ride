package edu.uga.cs.rentaride.presentation.admin.admin.update;

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
import edu.uga.cs.rentaride.entity.VehicleCondition;
import edu.uga.cs.rentaride.entity.VehicleStatus;
import edu.uga.cs.rentaride.logic.LogicLayer;
import edu.uga.cs.rentaride.presentation.regular.TemplateProcessor;
import edu.uga.cs.rentaride.session.Session;
import edu.uga.cs.rentaride.session.SessionManager;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

/**
 * Servlet implementation class LocationCreate
 */
@WebServlet("/VehicleUpdate")
public class VehicleUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Configuration cfg = null;
	private String templateDir = "/WEB-INF/AdminTemplates";
	private TemplateProcessor templateProcessor = null;
	private LogicLayer logicLayer = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VehicleUpdate() {
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
		String status = "";
		//Setting the session to null
		HttpSession    httpSession = null;
        Session        session = null;
        String         ssid; 
		templateProcessor.setTemplate("AdminView.ftl");
		DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
		int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
		int typeId = Integer.parseInt(request.getParameter("typeId"));
		int locationId = Integer.parseInt(request.getParameter("locationId"));
		String make = request.getParameter("make");
		String model = request.getParameter("model");
		int year = Integer.parseInt(request.getParameter("year"));
		int mileadge = Integer.parseInt(request.getParameter("mileadge"));
		String tag = request.getParameter("tag");
		String serviced = request.getParameter("lastServiced");
		VehicleStatus vehicleStatus = VehicleStatus.INLOCATION;
		VehicleCondition vehicleCondition = VehicleCondition.GOOD;
		Date lastServiced = null;
		try {
			lastServiced = df.parse(serviced);
		} catch (ParseException e1) {
			System.out.println("can't parse date.");
		}
		
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
				status = "Failed to create a session";
				templateProcessor.addToRoot("status", status);
				System.out.println("LocationCreate: "+e.toString());
				templateProcessor.processTemplate(response);
			}
		}
		logicLayer = session.getLogicLayer();
		User user = session.getUser();
		templateProcessor.addToRoot("user", user.getFirstName());
		try {
			logicLayer.updateVehicle(vehicleId, typeId, locationId, make, model, year, mileadge, tag, lastServiced, vehicleStatus, vehicleCondition);
			status = "Successfully updated Vehicle.";
			templateProcessor.addToRoot("status", status);
    		templateProcessor.processTemplate(response);
    		return;
		} catch (RARException e){
			System.out.println("VehicleUpdate: "+e.toString());
			status = "Failed to update Vehicle.";
			templateProcessor.addToRoot("status", status);
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