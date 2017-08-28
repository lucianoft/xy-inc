
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.xyinc.XyIncApplication;
import com.xyinc.model.Modelo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = XyIncApplication.class)
@SpringBootTest()
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ModuloTestes {

	//URL base para onde as requests serão feitas
    final String BASE_PATH = "http://localhost:8080/modelos";

    private RestTemplate restTemplate;

    @Before
    public void setUp() throws Exception {
        //Inicializamos o objeto restTemplate
        restTemplate = new RestTemplate();
    }
    
    @Test
    public void test1FindAll() {
        //Fazemos uma requisição HTTP GET buscando por todas as modeulos
        List response = restTemplate.getForObject(BASE_PATH, List.class);
        System.out.println(response);
    }
    
    @Test
    public void test2CriarModulo() {

        Modelo modelo = new Modelo();
        modelo.setNome("Teste");

       Modelo response = restTemplate.postForObject(BASE_PATH, modelo, Modelo.class);
       assertNotNull(response);
    }

    @Test
    public void test3FindOne() {
        //Fazemos uma requisição HTTP GET buscando por todas as modeulos
    	Modelo response = restTemplate.getForObject(BASE_PATH + "/1" , Modelo.class);
    	assertNotNull(response);
        System.out.println(response);
    }

    @Test
    public void test4Editar() {
        //Fazemos uma requisição HTTP GET buscando por todas as modeulos
    	Modelo modelo = restTemplate.getForObject(BASE_PATH + "/4" , Modelo.class);
    	assertNotNull(modelo);
    	
    	modelo.setNome(modelo.getNome() + "_");
    	String nome = modelo.getNome();
    	
    	restTemplate.put(BASE_PATH + "/4" , modelo);

    	modelo = restTemplate.getForObject(BASE_PATH + "/4" , Modelo.class);
    	assertEquals(modelo.getNome(), nome);
    }

    @Test
    public void test5Excluir() {
    	List response = restTemplate.getForObject(BASE_PATH, List.class);
    	Modelo modelo = (Modelo) response.get(response.size()-1);
        //Fazemos uma requisição HTTP GET buscando por todas as modeulos
    	restTemplate.delete(BASE_PATH + "/" + modelo.getId());
    }
}







