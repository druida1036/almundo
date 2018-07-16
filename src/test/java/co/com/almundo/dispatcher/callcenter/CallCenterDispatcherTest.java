package co.com.almundo.dispatcher.callcenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import co.com.almundo.cadena.callcenter.Agente;
import co.com.almundo.configuracion.CallCenterConfig;
import co.com.almundo.constantes.Constantes;
import co.com.almundo.dispatcher.Dispatcher;

@Test
@ContextConfiguration(classes = { CallCenterConfig.class })
public class CallCenterDispatcherTest extends AbstractTestNGSpringContextTests {

	private static final String MENSAJE_BASE = "Test ";

	@Autowired
	private Dispatcher dispatcher;

	@DataProvider
	public Object[][] datosPrueba() {
		return new Object[][] { { 1, 3, 2, Constantes.OPERADOR_ROL + Constantes.EMPLEADO_MENSAJE + MENSAJE_BASE },
				{ 0, 1, 0, Constantes.SUPERVISOR_ROL + Constantes.EMPLEADO_MENSAJE + MENSAJE_BASE },
				{ 0, 0, 1, Constantes.DIRECTOR_ROL + Constantes.EMPLEADO_MENSAJE + MENSAJE_BASE },
				{ 0, 0, 0, Constantes.AGENTE_NO_DISPONIBLE + MENSAJE_BASE } };
	}

	@Test(dataProvider = "datosPrueba")
	public void testDispatchCall(int numeroOperadores, int numeroSupervisores, int numeroDirectores, String resultado) {
		((CallCenterDispatcher) dispatcher).setAgente((Agente) applicationContext.getBean("agentePruebas",
				numeroOperadores, numeroSupervisores, numeroDirectores));
		String respuesta = dispatcher.dispatchCall(MENSAJE_BASE);
		Assert.assertEquals(respuesta, resultado);
	}

}
