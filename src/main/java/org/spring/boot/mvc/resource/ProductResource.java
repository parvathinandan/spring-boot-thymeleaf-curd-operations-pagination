package org.spring.boot.mvc.resource;

import java.util.List;
import java.util.Optional;

import org.spring.boot.mvc.model.Product;
import org.spring.boot.mvc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductResource {

	@Autowired
	private ProductService productService;
	private Page<Product> pageProducts;
	private Optional<Product> product;
	private List<Product> productsList ;
	private long totalRows ;
	private Integer totalPages;
	private Integer primePage = 1;
	
	@Value("${home.welcome}")
	private String value3;
	
	@GetMapping("/new")
	public String viewHomePage(Model model) {

		Product product = new Product();
		model.addAttribute("product", product);
		
		return "new_product";
	}
	@GetMapping("/")
	public String homePage(Model model) {
		
		return linkWithPage(model,primePage);
	}

	@GetMapping("/page/{pageNumber}")
	public String linkWithPage(Model model , @PathVariable("pageNumber") Integer primaryPage) {
		
		pageProducts = productService.findAllProducts(primaryPage);
		productsList = pageProducts.getContent();
		totalRows = pageProducts.getTotalElements();
		totalPages = pageProducts.getTotalPages();
		
			
		model.addAttribute("productsList", productsList);
		model.addAttribute("totalRows", totalRows);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", primaryPage);
		model.addAttribute("value3", value3);
		
		return "display_products";
	}
	
	@GetMapping("/page/{pageNumber}/{myNumber}")
	public String linkWithPage(Model model , @PathVariable("pageNumber") Integer primaryPage , @PathVariable Integer myNumber) {
		
		pageProducts = productService.findAllProducts(primaryPage);
		productsList = pageProducts.getContent();
		totalRows = pageProducts.getTotalElements();
		totalPages = pageProducts.getTotalPages();
		
			
		model.addAttribute("productsList", productsList);
		model.addAttribute("totalRows", totalRows);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", primaryPage);
		model.addAttribute("value3", value3);
		
		return "display_products";
	}

	@PostMapping("/save")
	public String save(@ModelAttribute("product") Product product, BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasErrors()) {
			
			return "new_product";
		}
		System.out.println("product : "+product);
		productService.save(product);
		model.addAttribute("product", product);

		return "save_product";
	}

	@GetMapping("/edit/{id}")
	public String findById(@PathVariable("id") Long id, Model model) {

		product = productService.getById(id);
		model.addAttribute("product", product);

		return "update_product";
	}

	@GetMapping("/delete/{id}")
	public String deleteByid(@PathVariable("id") Long id, Model model) {
		
		model.addAttribute("id", id);
		productService.deleteById(id);
		
			 return "redirect:/";
	
		//return "delete";
	}

}
