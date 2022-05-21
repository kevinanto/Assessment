package com.example.hardwareshop.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.example.hardwareshop.Domain.Product;
import com.example.hardwareshop.Service.ProductService;

@Controller
@RequestMapping("/Product")
public class ProductController {
	@Autowired
	private ProductService service;

	@GetMapping("/")
	public String viewHomePage(Model model) {
		List<Product> listproduct = service.listAll();
		model.addAttribute("listproduct", listproduct);
		System.out.print("Get / ");
		return "Product";
	}

	@GetMapping("/addproduct")
	public String add(Model model) {
		List<Product> listproduct = service.listAll();
		model.addAttribute("listproduct", listproduct);
		model.addAttribute("product", new Product());
		return "addproduct";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("product") Product product) {
		service.save(product);
		return "redirect:/product";
	}

	@RequestMapping("/edit/{id}")
	public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("addproduct");
		Product product = service.get(id);
		mav.addObject("product", product);
		return mav;

	}

	@RequestMapping("/delete/{id}")
	public String deleteProductPage(@PathVariable(name = "id") int id) {
		service.delete(id);
		return "redirect:/product";
	}
}
