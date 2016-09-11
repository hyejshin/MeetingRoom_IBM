

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.ibm.cof.dao.MemberDAO;
import com.ibm.cof.dto.MemberDTO;

/**
 * Servlet implementation class test_auto
 */
@WebServlet("/test_auto.do")
public class test_auto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public test_auto() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doAction(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doAction(request,response);
	}
	
	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* 자동완성로직 */
		String id = request.getParameter("term");
		System.out.println("ID : "+id);

		//JSONArray json = new JSONArray();
		MemberDAO mdao = new MemberDAO();
		ArrayList<String> list = mdao.getNameList(id);
		
		String searchList = new Gson().toJson(list);
        response.getWriter().write(searchList);
	
	}

}
