package com.xyinc.model.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.xyinc.model.Product;
import com.xyinc.model.repository.ProductRepository;

@Service
public class ProductService extends CrudService<Product, Long> {

	@Autowired
	private ProductRepository modeloRepository;
	
	@Override
	protected JpaRepository<Product, Long> getRepository() {
		return modeloRepository;
	}

	@Override
	protected void copiarModel(Product modelo, Product productSalvo) {
		BeanUtils.copyProperties(modelo, productSalvo, "id");
	}
	
}

