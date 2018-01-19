package com.cg.ams.main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteEntry")
public class DeleteEntry extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String rec[] = request.getParameterValues("records");
		for(String rc : rec){
			removeLine(rc);
			System.out.println("line removed");
		}
		

		response.sendRedirect("/AMS/CompletedJob");
	}

	public void removeLine(String lineContent) throws IOException
	{
		LocalDate ld = LocalDate.now();
		String fn = "/Files/AMS_Monitoring_"+ld.getMonthValue()+ld.getDayOfMonth()+ld.getYear()+".txt";
		String path = "D:/Project/Pro/AMS/WebContent/"+fn;
		File file = new File(path);
	    List<String> out = Files.lines(file.toPath()).filter(line -> !line.contains(lineContent)).collect(Collectors.toList());
	    Files.write(file.toPath(), out, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
