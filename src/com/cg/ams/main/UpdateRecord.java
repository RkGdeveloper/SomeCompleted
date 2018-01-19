package com.cg.ams.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/UpdateRecord")
public class UpdateRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LocalDate ld = LocalDate.now();
		String filename = "AMS_Monitoring_"+ld.getMonthValue()+ld.getDayOfMonth()+ld.getYear()+".txt";
		System.out.println(filename);
		try{
			//String path = getServletContext().getRealPath("/Files/"+filename);
			
			
			String URI = request.getContextPath();
			System.out.println(URI);
			/* String p = getServletContext().getRealPath("/WEB-INF/products.txt");*/
			String path = "D:/Project/Pro/"+URI+"/WebContent/Files/"+filename;
			File file = new File(path);
			 System.out.println(file.getAbsolutePath());
			//System.out.println(path);
			 FileWriter fstream = new FileWriter(file,true);
			 
	         BufferedWriter fbw = new BufferedWriter(fstream);
	         String rec[] = request.getParameterValues("records");
	            
 	            for(String r : rec){
					System.out.println(r);
					fbw.write(r);
					fbw.newLine();
		        }
	            
	            fbw.close();
	            fstream.close();
	        }catch (Exception e) {
	            System.out.println("Error: " + e.getMessage());
	        }
		
		/*PrintWriter out = response.getWriter();
		out.print("Submitted");*/
		response.sendRedirect("/AMS/AmsServlet");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
