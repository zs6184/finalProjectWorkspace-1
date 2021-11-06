package tw.springbootfinal.shoppingcart.contorller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import tw.springbootfinal.shoppingcart.model.ProductsBean;
import tw.springbootfinal.shoppingcart.model.ShopCartService;
import tw.springbootfinal.users.model.CustomerBean;
import tw.springbootfinal.users.model.CustomerService;

@Controller
public class ShopCartController {
	@Autowired
	ShopCartService sService;
	@Autowired
	CustomerService cService;
	
	
	@RequestMapping(path = "/addshoppingcart.controller", produces = "text/plain;charset=utf-8")
	public String selectProduct(Principal p,Model m,HttpSession session, HttpServletRequest request//, @RequestParam("productId") int id
			//,@CookieValue(value = "121") String cookieStr
			) throws UnsupportedEncodingException {
		//取得Session連線用戶物件
		CustomerBean resultUser = cService.getLoginCustomerBean(session);
		System.out.println("用戶名稱"+resultUser.getCusUsername());
		//取得session資料
				HttpSession session1 = request.getSession();
				String googleUsername = (String)session1.getAttribute("username");
				System.out.println("用戶名稱"+googleUsername);
				if(googleUsername==null) { //如果沒有取得session資料，代表是用一般帳密登入
					String username = p.getName();// Principal可以用來取得使用者名稱
					System.out.println("username: " + username);
					
					CustomerBean cusBean = cService.getByCusUsername(username);// 透過使用者名稱搜尋資料
					String realName = cusBean.getCusRealname();// 取得真實姓名
					String role = cusBean.getRole(); //取得權限
					
					// 設為session層級的變數給jsp使用
					m.addAttribute("username", username);
					m.addAttribute("realName", realName);
					m.addAttribute("role", role);

				}else {//如果有取得session代表為google登入
					CustomerBean theCus = cService.googleSelectUser(googleUsername);
					System.out.println("googleUsername: "+googleUsername);
					
					String realName = theCus.getCusRealname();// 取得真實姓名
					String role = theCus.getRole(); //取得權限
					
					// 設為session層級的變數給jsp使用
					m.addAttribute("username", googleUsername);
					m.addAttribute("realName", realName);
					m.addAttribute("role", role);
				}
		// 取出cookies
		Cookie[] cookies = request.getCookies();
		String cookieStr = null;
		for(Cookie c:cookies) {
			System.out.println(c.getName());
			if(c.getName().equals("cart"+resultUser.getCusUsername())) {
			//if(c.getName().equals("cart123")) {
				System.out.println(c.getValue());
				cookieStr = URLDecoder.decode(c.getValue(), "utf-8");
			}
		}
		//JSON字串解析成商品物件
		if(cookieStr != null) {
			Set<ProductsBean> list = new HashSet<ProductsBean>();
			System.out.println("我是cookie====" + cookieStr);
			List<ProductsBean> cartitem = JSON.parseArray(cookieStr, ProductsBean.class);
			for (ProductsBean o : cartitem) {
				ProductsBean target = sService.selecyProductbyId(o.getId());
				target.setnum(o.getnum());
				list.add(target);
			}
			for (ProductsBean p1 : list) {
				System.out.println("我是商品ID:"+p1.getId()+"我是商品名稱:"+p1.getName()+"商品數量"+p1.getnum());
			}
			m.addAttribute("catitem",list);
		}else {
			m.addAttribute("catitem",null);
			System.out.println("空");
		}
		// return "使用者:"+resultUser.getCusUsername()+"選取商品:"+resultProduct.getName();
		return "shoppingcart";
	}
	
	//假商品頁面
	@RequestMapping(path = "/fakeProductList.controller")
	public String testProduct() {
		return "fakeProductList";
	}
}
