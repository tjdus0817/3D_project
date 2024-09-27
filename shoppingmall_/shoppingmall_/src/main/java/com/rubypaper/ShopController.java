package com.rubypaper;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShopController {
	
	@GetMapping(value = "/index")
	public String main() {
		return "index.html";
	}
	@GetMapping(value = "/main")
	public String main1() {
		return "main.html";
	}
	
	@GetMapping(value = "/login")
	public String login() {
		return "login.html";
	}
	
	@GetMapping(value = "/write")
	public String write() {
		return "write.html";
	}
	@GetMapping(value = "/list_1")
	public String list_1() {
		return "list_1.html";
	}
	@GetMapping(value = "/detail")
	public String detail() {
		return "detail.html";
	}
	@GetMapping(value = "/header")
	public String header() {
		return "header.html";
	}
	@GetMapping(value = "/test")
	public String test() {
		return "test.html";
	}
	@GetMapping(value = "/main11")
	public String main11() {
		return "main11.html";
	}
	@GetMapping(value = "/cart")
	public String cart() {
		return "cart.html";
	}
	@GetMapping(value = "/inquiry_1")
	public String inquiry_1() {
		return "inquiry_1.html";
	}
	@GetMapping(value = "/mypage")
	public String mypage() {
		return "mypage.html";
	}
}
	