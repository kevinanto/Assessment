package com.example.hardwareshop.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.hardwareshop.Domain.Product;
import com.example.hardwareshop.Service.ProductService;

@Controller
@RequestMapping("/")
public class IndexController {

	@Autowired
	private ProductService service;


	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/product", method = RequestMethod.GET)
	public String viewHomePage(Model model)

	{
		List<Product> listproduct = service.listAll();
		model.addAttribute("listproduct", listproduct);
		System.out.print("Get / ");
		return "product";
	}
}
