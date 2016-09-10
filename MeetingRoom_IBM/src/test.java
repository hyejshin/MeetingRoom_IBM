

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.ibm.cof.dao.MemberDAO;
import com.ibm.cof.dto.MemberDTO;

/**
 * Servlet implementation class test
 */
@WebServlet("/test.do")
public class test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public test() {
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
		String id = request.getParameter("id");
		System.out.println("ID : "+id);
		
		JSONArray json = new JSONArray();
		MemberDAO mdao = new MemberDAO();
		json = mdao.selectListByName(id);
		JSONObject obj = new JSONObject();
		obj.put("result",json);
		System.out.println(obj);
		
		response.setContentType("application/x-json; charset=UTF-8");
		response.getWriter().print(obj);
		
	}
	
	/*public static Map ConverObjectToMap(Object obj){
		try {
			//Field[] fields = obj.getClass().getFields(); //private field는 나오지 않음.
			Field[] fields = obj.getClass().getDeclaredFields();
			Map resultMap = new HashMap();
			for(int i=0; i<=fields.length-1;i++){
				fields[i].setAccessible(true);
				resultMap.put(fields[i].getName(), fields[i].get(obj));
			}
			return resultMap;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}*/
}



