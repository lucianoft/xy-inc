package com.xyinc.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.xyinc.model.Modelo;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ModeloTest extends AbstractCrudTest{

	private final String  BASE_URL_MODELO  = "/modelos";
    
    @Test
    public void test1FindAll() throws JsonParseException, JsonMappingException, IOException {
       	getList(BASE_URL_MODELO, Modelo.class);
    }

    @Test
    public void test2CriarModulo() throws JsonProcessingException {

       Modelo modelo = new Modelo();
       modelo.setNome("Teste");

       Modelo response = postEntity(BASE_URL_MODELO, modelo, Modelo.class);
       assertNotNull(response);
    }

    @Test
    public void test3FindOne() {
        Modelo response = getEntity(BASE_URL_MODELO + "/1" , Modelo.class);
    	assertNotNull(response);
    }

    @Test
    public void test4Editar() throws JsonProcessingException {
        //Fazemos uma requisição HTTP GET buscando por todas as modeulos
    	Modelo modelo = getEntity(BASE_URL_MODELO + "/4" , Modelo.class);
    	assertNotNull(modelo);
    	
    	modelo.setNome(modelo.getNome() + "_");
    	String nome = modelo.getNome();

    	modelo = putEntity(BASE_URL_MODELO + "/4", modelo, Modelo.class);
    	assertEquals(modelo.getNome(), nome);
    }

    @Test
    public void test5Excluir() throws JsonParseException, JsonMappingException, IOException {
    	
       	List<Modelo> list = getList(BASE_URL_MODELO, Modelo.class);
    	
    	Modelo modelo = list != null && !list.isEmpty() ? list.get(list.size()-1) : null;
        deleteEntity(BASE_URL_MODELO + "/" + modelo.getId());
    	
    }
    
  
}







