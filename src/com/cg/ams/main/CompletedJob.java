package com.cg.ams.main;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cg.ams.bean.Record;

@WebServlet("/CompletedJob")
public class CompletedJob extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Integer> recIdOfCompletedFile = new ArrayList<Integer>();
		LocalDate ld = LocalDate.now();
		String fn = "/Files/AMS_Monitoring_"+ld.getMonthValue()+ld.getDayOfMonth()+ld.getYear()+".txt";
		AmsServlet ams = new AmsServlet();
		recIdOfCompletedFile = ams.getCompletedFileIds(fn,getServletContext());
		
		ArrayList<Record> CompletedRecList = new ArrayList<Record>();
		
		ArrayList<Record> listRec = new ArrayList<Record>();
		ServletContext context = getServletContext();
        String filename = "/Files/FileDetails.txt";
        listRec = ams.getRawFileDetails(filename,context);
        
        for(int rId : recIdOfCompletedFile){
        	Record rc = new Record();
        	rc = ams.getRecordById(rId, listRec);
        	CompletedRecList.add(rc);
        }
        context.setAttribute("data",CompletedRecList);
        RequestDispatcher rd=request.getRequestDispatcher("CompletedJob.jsp");
		rd.forward(request,response);
		
	}
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
		
	}

}
