package com.cg.ams.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;

import com.cg.ams.bean.Record;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

@WebServlet("/AmsServlet")
public class AmsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
		
		}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Record> listRec = new ArrayList<Record>();
		ServletContext context = getServletContext();
        ArrayList<Integer> recIdOfCompletedFile = new ArrayList<Integer>();
        String filename = "/Files/FileDetails.txt";
        listRec = getRawFileDetails(filename,context);
        
        
		LocalDate ld = LocalDate.now();
		String fn = "/Files/AMS_Monitoring_"+ld.getMonthValue()+ld.getDayOfMonth()+ld.getYear()+".txt";

		String URI = request.getContextPath();
		System.out.println(URI);
		
		String fullPath = "D:/Project/Pro/"+URI+"/WebContent/"+fn;
		File theFile = new File(fullPath);
		System.out.println(theFile);
		
		System.out.print("File Exists: " + theFile.exists() + "<br>");
		if(theFile.exists() == false)
		{
        context.setAttribute("data", listRec);   
		}
		
		else
		{
			/*ArrayList<Record> updatedRecord = new ArrayList<Record>();
			System.out.println("file exist");
			recIdOfCompletedFile = getCompletedFileIds(fn);
			for(int rId : recIdOfCompletedFile){
				if(!(listRec.contains(rId))){
					Record r = getRecordById(rId,listRec);
					System.out.println(r);
					updatedRecord.add(r);
				}
			}
			
			context.setAttribute("data", updatedRecord);*/
			
			System.out.println("file exist");
			recIdOfCompletedFile = getCompletedFileIds(fn,getServletContext());
			for(int rId : recIdOfCompletedFile){
				System.out.println(rId);
				Record rc = getRecordById(rId, listRec);
				listRec.remove(rc);
			}
			context.setAttribute("data", listRec);   
			
		}
		
		RequestDispatcher rd=request.getRequestDispatcher("AMS_Home.jsp");
		rd.forward(request,response);
		
	}
	
	
	
	public Record getRecordById(int rId, ArrayList<Record> listRec) {
		for(Record rc : listRec){
			if(rc.getRecId() == rId)
				return rc;
		}
		return null;
	}

	public ArrayList<Integer> getCompletedFileIds(String fn, ServletContext context) {
		
		System.out.println("in Completed File details"+fn);
		ArrayList<Integer> recIds = new ArrayList<Integer>();
		InputStream is = context.getResourceAsStream(fn);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader reader = new BufferedReader(isr);
        String contentLine = null;
		try {
			contentLine = reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        while (contentLine != null) {
	      int id;
	      int p1 = contentLine.indexOf('|');
	      id = Integer.parseInt(contentLine.substring(0, p1));
	      recIds.add(id);
	      try {
			contentLine = reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	   		}
		
		return recIds;
	}

	public ArrayList<Record> getRawFileDetails(String filename, ServletContext context) {
		ArrayList<Record> listRec = new ArrayList<Record>();
        InputStream is = context.getResourceAsStream(filename);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader reader = new BufferedReader(isr);
        String contentLine = null;
		try {
			contentLine = reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        while (contentLine != null) {
	      System.out.println(contentLine);
	      Record rc = new Record();
	      int p1 = contentLine.indexOf('|');
	      int p2 = contentLine.indexOf('|',p1+1);
	      int p3 = contentLine.indexOf('|',p2+1);
	      int p4 = contentLine.indexOf('|',p3+1);
	      System.out.println(p1+" "+p2+" "+p3+" "+p4);
	      rc.setRecId(Integer.parseInt(contentLine.substring(0, p1)));
	      rc.setTeam(contentLine.substring(p1+1,p2));
	      rc.setJobName(contentLine.substring(p2+1, p3));
	      rc.setHrWhenItsRun(Integer.parseInt(contentLine.substring(p3+1,p4)));
	      listRec.add(rc);
	      
 	      System.out.println("**********************");
 	      try {
			contentLine = reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	   		}
       
		return listRec;
	}

}
