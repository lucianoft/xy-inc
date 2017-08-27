package com.xyinc.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.xyinc.model.ModeloAtributo;
import com.xyinc.repository.ModeloAtributoRepository;

@Service
public class ModeloAtributoService extends CrudService<ModeloAtributo, Long> {

	@Autowired
	private ModeloAtributoRepository modeloRepository;
	
	@Override
	protected JpaRepository<ModeloAtributo, Long> getRepository() {
		return modeloRepository;
	}

	@Override
	protected void copiarModel(ModeloAtributo modelo, ModeloAtributo modeloAtributoSalvo) {
		BeanUtils.copyProperties(modelo, modeloAtributoSalvo, "id");
	}
	
}
