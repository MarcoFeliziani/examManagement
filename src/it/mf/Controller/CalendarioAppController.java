package it.mf.Controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.mf.Dao.AdDao;
import it.mf.Dao.CdsDao;
import it.mf.Dao.DocenteDao;
import it.mf.Dao.FacoltaDao;
import it.mf.Dao.RuoloDao;
import it.mf.Dao.CalendarioAppDao;
import it.mf.Model.CalendarioApp;
import it.mf.Util.Utility;



@WebServlet("/CalendarioAppController")
public class CalendarioAppController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String SEARCH_CAL_APP = "/SearchApp.jsp"; 
	private static String LIST_APPELLO = "/AppList.jsp";
	private CalendarioAppDao dao;

    public CalendarioAppController() {
        super();
        dao = new CalendarioAppDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String forward = "";
		
		setFacoltaList(request);
		setCdsList(request);
		setAdList(request);
		setDocenteList(request);
		setRuoloList(request);
		//request.setAttribute("action", "AppelloList");
		forward = SEARCH_CAL_APP;
		
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CalendarioApp retValue = new CalendarioApp();
		String forward = "";
		
		retValue.setFacId(Utility.parseInteger(request.getParameter("facId")));
		retValue.setCdsId(Utility.parseInteger(request.getParameter("cdsId")));
		retValue.setAdId(Utility.parseInteger(request.getParameter("adId")));
		retValue.setDoceMatricola(Utility.parseInteger(request.getParameter("doceMatricola")));
		retValue.setDoceRuolo(request.getParameter("doceRuolo"));
		retValue.setDataAppello(Utility.parseDate(request.getParameter("dataAppello")));
		
		Integer facId = Utility.parseInteger(request.getParameter("facId"));
		Integer cdsId = Utility.parseInteger(request.getParameter("cdsId"));
		Integer adId = Utility.parseInteger(request.getParameter("adId"));
		Integer doceMatricola = Utility.parseInteger(request.getParameter("doceMatricola"));
		String doceRuolo = request.getParameter("doceRuolo");
		Date dataAppello = Utility.parseDate(request.getParameter("dataAppello"));
		//Date aDataAppello = Utility.parseDate(request.getParameter("aDataAppello"));
		
		forward = LIST_APPELLO;
		String action = request.getParameter("check");
		
		if ((action != null)){
			
			request.setAttribute("beans", dao.getListaAppelli(facId, cdsId, adId, 0, doceRuolo, dataAppello, doceMatricola));
			request.setAttribute("action", "checkCompleted");
		}
		else{
		request.setAttribute("beans", dao.getListaAppelli(facId, cdsId, adId, doceMatricola, doceRuolo, dataAppello, 0));
		}
		
		setFacoltaList(request);
		setCdsList(request);
		setAdList(request);
		setDocenteList(request);
		setRuoloList(request);
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}
	
	private void setFacoltaList(HttpServletRequest request) {
		request.setAttribute("facList", new FacoltaDao().getAllOrdered());	
	}
	private void setAdList(HttpServletRequest request) {
		request.setAttribute("adList", new AdDao().getAllOrdered());	
	}
	private void setCdsList(HttpServletRequest request) {
		request.setAttribute("cdsList", new CdsDao().getAllOrdered());	
	}
	private void setDocenteList(HttpServletRequest request) {
		request.setAttribute("doceList", new DocenteDao().getAllOrdered());	
	}
	private void setRuoloList(HttpServletRequest request) {
		request.setAttribute("ruoloList", new RuoloDao().getAllOrdered());	
	}


}
