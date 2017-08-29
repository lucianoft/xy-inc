
import java.util.Arrays;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xyinc.XyIncApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = XyIncApplication.class)
@SpringBootTest()
@DirtiesContext
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ModuloTestes {

	//URL base para onde as requests serão feitas
    final String BASE_PATH_TOKEN = "http://localhost:8080/oauth/token";
    final String BASE_PATH = "http://localhost:8080/modelos";

    private String accessToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MDM5NjExNTUsInVzZXJfbmFtZSI6ImFkbWluIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9ST0xFIl0sImp0aSI6IjJjZTRjZDVjLTE0NzUtNDMzOC1hNjhkLWVhOWQ4YTI0YTUzNyIsImNsaWVudF9pZCI6Inh5X2luYyIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSJdfQ.5mCcrnJBRPZ6aV8EQk3hGngEv31cPzp7Xdk4CpWhvdw";

    @Before
    public void setUp() throws Exception {
        //Inicializamos o objeto restTemplate
    }
    
    @Test
    public void test1FindAll() {
    	
    	ClientCredentialsResourceDetails resource = new ClientCredentialsResourceDetails();

    	resource.setAccessTokenUri(BASE_PATH_TOKEN);
		resource.setClientId("xy_inc");
		//resource.setId("@xy_inc@");
		resource.setClientSecret("@xy_inc@");
		//resource.setScope(Arrays.asList("write"));
		resource.setGrantType("password");
		
		
		BasicAuthorizationInterceptor authorizationInterceptor = new BasicAuthorizationInterceptor("admin", "admin");
		DefaultAccessTokenRequest request = new DefaultAccessTokenRequest();
		
		request.set("username", "admin");
		request.set("password", "admin");
		
		ClientCredentialsAccessTokenProvider provider = new ClientCredentialsAccessTokenProvider();
		OAuth2AccessToken accessToken = provider.obtainAccessToken(resource, request );
    }

    /*
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
    
    */
}







