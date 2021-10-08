package tw.finalproject.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import tw.finalproject.model.PetBean;
import tw.finalproject.model.PetDAO;
import tw.finalproject.util.HibernateUtil;


@WebServlet("/Servlet/PetInfoLoad")
public class PetInfoLoad extends HttpServlet {
	private static final long serialVersionUID = 1L;

	SessionFactory factory = HibernateUtil.getSessionFactory();
	Session session = factory.getCurrentSession();

	private PetDAO petDAO;

	public void init() {
		this.petDAO = new PetDAO(session);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
//從DB新增全部資料	
		List<PetBean> allPet = petDAO.selectAll();

		JSONObject data = new JSONObject(); // 最後包裝jsonArray用進行回傳的json物件
		JSONArray jsonArr = new JSONArray();// 存放多筆資料的json陣列
		int i = 0;

		for (PetBean aPet : allPet) {
			String tempStr = (JSON.toJSONString(aPet,SerializerFeature.WriteMapNullValue)); //轉為JSON字串時輸出NULL值
			String transfer = tempStr.replaceAll("null", "\"N/A\""); //將null轉為N/A字串
			JSONObject tempObj = JSONObject.parseObject(transfer); //轉換後的字串轉為JSON物件
			jsonArr.add(i, tempObj);
			i++;
			System.out.println("tempStr"+tempStr);
			System.out.println("transfer"+transfer);
		}
		data.put("pets", jsonArr); // 設置回傳的陣列物件與其名稱
		System.out.println("共有" + i + "筆資料輸出");
		PrintWriter out = response.getWriter();
		out.println(data);
		out.close();
		System.out.println("輸出完成");

		
////從DB新增單筆資料		
//		PetBean tempBean = petDAO.select(1001);
//
////Bean轉成JSON字串		
//		String rs = JSONObject.toJSONString(tempBean);
//		System.out.println("rs = " + rs);
//
////JSON字串轉成JSON物件
//		JSONObject jsonObj = JSONObject.parseObject(rs);
//		System.out.println("note=" + jsonObj.get("note"));
//
////向前台輸出結果(json物件)		
//		PrintWriter out = response.getWriter();
//		out.print(jsonObj);
//		out.close();
//
//		System.out.println("輸出完成");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
