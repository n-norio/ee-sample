package biz.allrounder.jee7sample.web;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/web/errors")
public class ErrorServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html");

		int statusCode = (Integer) req.getAttribute("javax.servlet.error.status_code");
		Exception exception = (Exception) req.getAttribute("javax.servlet.error.exception");
		String servletName = (String) req.getAttribute("javax.servlet.error.servlet_name");
		String requestUri = (String) req.getAttribute("javax.servlet.error.request_uri");

		PrintWriter out = resp.getWriter();

		if (statusCode >= 400 || statusCode <= 499) {
			out.write(loadHtml("/Users/nakasai/tmp/invalidAccess.html"));
			
		} else if (statusCode >= 500 || statusCode <= 599) {
			out.write(loadHtml("/Users/nakasai/tmp/error.html"));
		}
	}

	private String loadHtml(String path) {
		try {
            FileReader reader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(reader);
 
            StringBuffer sb = new StringBuffer();
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
			return sb.toString();
			
		} catch (Exception e) {
			return "system error.";
		}
	}
}
