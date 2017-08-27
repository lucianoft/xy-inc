package com.xyinc.model.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xyinc.model.Modelo;
import com.xyinc.model.service.CrudService;
import com.xyinc.model.service.ModeloService;

@RestController
@RequestMapping("/modelos")
public class ModeloResource extends CrudResource<Modelo, Long>{

	@Autowired
	private ModeloService modeloService;
	
	@Override
	protected CrudService<Modelo, Long> getCrudService() {
		return modeloService;
	}

}