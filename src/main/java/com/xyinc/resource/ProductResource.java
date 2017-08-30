package com.xyinc.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xyinc.model.Product;
import com.xyinc.service.CrudService;
import com.xyinc.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductResource extends CrudResource<Product, Long>{

	@Autowired
	private ProductService productService;
	
	@Override
	protected CrudService<Product, Long> getCrudService() {
		return productService;
	}

}