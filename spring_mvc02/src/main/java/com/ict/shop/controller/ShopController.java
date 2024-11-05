package com.ict.shop.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ict.member.vo.MemberVO;
import com.ict.shop.service.ShopService;
import com.ict.shop.vo.CartVO;
import com.ict.shop.vo.ShopVO;

@Controller
public class ShopController {
	@Autowired
	private ShopService shopService;
	@GetMapping("/shop")
	public ModelAndView getShopList(String category) {
		try {
			ModelAndView mv = new ModelAndView("shop/product_list");
			if(category == null || category =="" ) {
				category = "ele002";
			}
			
			List<ShopVO> shop_list =  shopService.getShopList(category);
			
			if(shop_list != null) {
				mv.addObject("shop_list", shop_list);
				return mv;
			}
			
			return null;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}	
	
	@GetMapping("/shop_detail")
	public ModelAndView getShopDetail(@RequestParam("shop_idx") String shop_idx) {
		try {
			ModelAndView mv = new ModelAndView("shop/product_content");
		
			ShopVO svo = shopService.getShopDetail(shop_idx);
			if(svo !=null) {
				mv.addObject("svo", svo);
				return mv;
			}
			return null;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	@GetMapping("/shop_addCart")
	public ModelAndView getShopAddCart(@ModelAttribute("shop_idx") String shop_idx, 
			HttpSession session) {
		try {
			String loginchk = (String) session.getAttribute("loginchk");
			if(loginchk.equals("ok")) {
				ModelAndView mv = new ModelAndView("redirect:/shop_detail");
				
				//로그인한 정보를 가져온다.
				MemberVO mvo = (MemberVO) session.getAttribute("mvo2");
				
				// 제품정보를 가져오자.
				ShopVO svo = shopService.getShopDetail(shop_idx);
				
				//카트리스트에 해당 사용자의 m_id 와 제품번호가 있는지(수량1증가) 없는지(카트 추가)판별하자.
				CartVO cartVO = shopService.getCartChk(mvo.getM_id(), svo.getP_num());
				
				if(cartVO == null) {
					// 카트가 비어 있으므로 카트 테이블에 추가 (insert)
					CartVO cavo = new CartVO();
					cavo.setP_num(svo.getP_num());
					cavo.setP_name(svo.getP_name());
					cavo.setP_price(String.valueOf(svo.getP_price()) );
					cavo.setP_saleprice(String.valueOf(svo.getP_saleprice()));
					cavo.setM_id(mvo.getM_id());
					
					int result = shopService.getCartInsert(cavo);
					
				}else {
					// 카트에 있으므로 수량증가(update)
					int result = shopService.getCartUpdate(cartVO);
				}
				
				return mv;
			}else {
				return new ModelAndView("sns/login_error");
			}
		} catch (Exception e) {
			 System.out.println(e);
			 return new ModelAndView("sns/login_error");
		}
	}	
	@GetMapping("/shop_showCart")
	public ModelAndView getShopShowCart(HttpSession session) {
		try {
			String loginchk = (String) session.getAttribute("loginchk");
			if(loginchk.equals("ok")) {
				ModelAndView mv = new ModelAndView("shop/cartList");
				
				//로그인한 사람의 정보를 가져와서 카트에 검색한 후 cartlist에 넘기자.
				MemberVO mvo = (MemberVO) session.getAttribute("mvo2");		
				
				List<CartVO> cart_list = shopService.getCartList(mvo.getM_id());
				if(cart_list != null) {
					mv.addObject("cart_list", cart_list);
				}
				
				return mv;
			}else {
				return new ModelAndView("sns/login_error");
			}
		} catch (Exception e) {
			 System.out.println(e);
			 return new ModelAndView("sns/login_error");
		}
	}	
    @PostMapping("/cart_edit")
    public ModelAndView getShopCartEdit(CartVO cavo) {
    	try {
			ModelAndView mv = new ModelAndView("redirect:/shop_showCart");
			int result = shopService.getCartEdit(cavo);
			if(result>0) {
				return mv;
			}
			return null;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
    }
    @GetMapping("/cart_delete")
    public ModelAndView getShopCartDelete(String cart_idx) {
    	try {
			ModelAndView mv = new ModelAndView("redirect:/shop_showCart");
			int result = shopService.getCartDelete(cart_idx);
			if(result>0) {
				return mv;
			}
			return null;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
    }
    @GetMapping("/shop_add_form")
    public ModelAndView getShopAddForm() {
    	return new ModelAndView("shop/product_insert");
    	
    }
    
//    @PostMapping("/shop_product_insert_ok")
//    public ModelAndView getProductInsert(ShopVO svo , 
//    		                             HttpServletRequest request) {
//    	ModelAndView mv = new ModelAndView("redirect:/shop?category="+svo.getCategory());
//    	
//    	try {
//       		
//    		svo.setP_image_s(svo.getF_name1().getOriginalFilename()); 
//    		svo.setP_image_l(svo.getF_name2().getOriginalFilename()); 
//    		
//    		MultipartFile f_file1 = svo.getF_name1();
//    		MultipartFile f_file2 = svo.getF_name1();
//    		
//    		
//    		int result = shopService.getProductInsert(svo);
//			if(result>0) {
//				
//				String s1 = fileUp ( f_file1, request);
//	    		String s2 = fileUp ( f_file2, request);
//	    		
//				return mv;
//				
//			}
//			return null;
//     	} catch (Exception e) {
//			System.out.println(e);
//		}
//    	return null;
//    }
//    
//	public String fileUp(
//		    MultipartFile f_name	,
//		    HttpServletRequest request ) {
//		
//		try {
//			
//			String path = request.getSession().getServletContext().getRealPath("/resources/images");
//			
//			UUID uuid = UUID.randomUUID();
//			String fname = uuid.toString()+"_"+f_name.getOriginalFilename();
//			
//			String file_type = f_name.getContentType();
//			long size = f_name.getSize()/1024 ;
//			                                             
//			// 실제 올리는 작업을 하자 
//			// (FileCopyUtils.copy(byte[] in, File out) byte타입배열을 지정한 File에 복사한다.
//			
//			//올린 파일을 byte[]로 만듬
//			byte[] in = f_name.getBytes();
//            
//			// 업로드할 장소와 저장이름을 지정
//            File out = new File(path, fname);
//            
//            // 파일 복사(upload = down)
//            FileCopyUtils.copy(in, out);
//            
//           // String name = request.getParameter("name");
// 			return "1";
//		} catch (Exception e) {
//			System.out.println(e);
//			return "0";
//		}
//		
//	 	
//	}
    
    @PostMapping("/shop_product_insert_ok")
	public ModelAndView getShopAddOK(ShopVO svo, HttpServletRequest request) {
		try {
			ModelAndView mv = new ModelAndView("redirect:/shop?category="+svo.getCategory());
			String path = request.getSession().getServletContext().getRealPath("/resources/images");
			MultipartFile file_s = svo.getFile_s();
			MultipartFile file_l = svo.getFile_l();
			
			// 파일은 둘다 required 라서 무조건 넘어온다.
			UUID uuid = UUID.randomUUID();
			svo.setP_image_s(uuid.toString()+"_"+file_s.getOriginalFilename());
			svo.setP_image_l(uuid.toString()+"_"+file_l.getOriginalFilename());
			
			file_s.transferTo(new File(path, svo.getP_image_s()));
			file_l.transferTo(new File(path, svo.getP_image_l()));
			
			// DB에 저장
			int result = shopService.getProductInsert(svo);
			if(result>0) {
				return mv ;
			}
			return null;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
}
