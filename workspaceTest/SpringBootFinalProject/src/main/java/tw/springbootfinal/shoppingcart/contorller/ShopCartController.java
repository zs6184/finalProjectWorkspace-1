package tw.springbootfinal.shoppingcart.contorller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
	public String selectProduct(Model m,HttpSession session, HttpServletRequest request//, @RequestParam("productId") int id
			//,@CookieValue(value = "121") String cookieStr
			) throws UnsupportedEncodingException {
		//取得Session連線用戶物件
		CustomerBean resultUser = cService.getLoginCustomerBean(session);
		System.out.println("用戶名稱"+resultUser.getCusUsername());
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
			for (ProductsBean p : list) {
				System.out.println("我是商品ID:"+p.getId()+"我是商品名稱:"+p.getName()+"商品數量"+p.getnum());
			}
			m.addAttribute("catitem",list);
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
