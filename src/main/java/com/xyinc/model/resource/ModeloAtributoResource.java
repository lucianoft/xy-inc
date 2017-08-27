package com.xyinc.model.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xyinc.model.ModeloAtributo;
import com.xyinc.model.service.CrudService;
import com.xyinc.model.service.ModeloAtributoService;

@RestController
@RequestMapping("/modeloAtributos")
public class ModeloAtributoResource extends CrudResource<ModeloAtributo, Long>{

	@Autowired
	private ModeloAtributoService modeloAtributoService;
	
	@Override
	protected CrudService<ModeloAtributo, Long> getCrudService() {
		return modeloAtributoService;
	}

}