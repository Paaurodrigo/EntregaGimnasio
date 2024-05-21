package pruebas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hibernate.App;

class pruebas {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("Empiezan las pruebas");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		System.out.println("Han acabado las pruebas");
	}

	@BeforeEach
	void setUp() throws Exception {
		System.out.println("Ha empezado una nueva prueba");
	}

	@AfterEach
	void tearDown() throws Exception {
		System.out.println("Ha acabado la prueba");
	}

	@Test
	void test() {
		App app = new App();
		
		// Prueba con una cadena que coincide con la expresión regular
				String nombre1 = "prueba";
				String er1 = "prueba";
				assertTrue(app.comprobarExpReg(nombre1, er1));

				// Prueba con una cadena que no coincide con la expresión regular
				String nombre2 = "prueba";
				String er2 = "test";
				assertFalse(app.comprobarExpReg(nombre2, er2));
		
	
	}

}
