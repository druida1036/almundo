package co.com.almundo.dispatcher.callcenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import co.com.almundo.cadena.callcenter.Agente;
import co.com.almundo.configuracion.CallCenterConfig;
import co.com.almundo.constantes.Constantes;
import co.com.almundo.dispatcher.Dispatcher;

@Test
@ContextConfiguration(classes = { CallCenterConfig.class })
public class CallCenterDispatcherConcurrencyTest extends AbstractTestNGSpringContextTests {

	private static final String MENSAJE_BASE = "Test ";

	@Autowired
	private Dispatcher dispatcher;

	@Test(threadPoolSize = 1, invocationCount = 10, timeOut = 1000)
	public void testDispatchCall() {
		cargarAgentes(5, 3, 2);
		String respuesta = dispatcher.dispatchCall(MENSAJE_BASE + Thread.currentThread().getId());
		Assert.assertNotEquals(respuesta,
				Constantes.AGENTE_NO_DISPONIBLE + MENSAJE_BASE + Thread.currentThread().getId());
	}

	@Test(threadPoolSize = 1, invocationCount = 20, timeOut = 1000)
	public void testDispatchCall_con_veite_agentes_veiteLlamadasConcurrentes() {
		cargarAgentes(10, 8, 2);
		String respuesta = dispatcher.dispatchCall(MENSAJE_BASE + Thread.currentThread().getId());
		Assert.assertNotEquals(respuesta, MENSAJE_BASE + Thread.currentThread().getId());
	}

	@Test(threadPoolSize = 10, invocationCount = 1000, timeOut = 1000)
	public void testDispatchCall_carga_con_veite_agentes() {
		cargarAgentes(10, 8, 2);
		String respuesta = dispatcher.dispatchCall(MENSAJE_BASE + Thread.currentThread().getId());
		Assert.assertNotEquals(respuesta, MENSAJE_BASE + Thread.currentThread().getId());
	}

	private void cargarAgentes(int numeroOperadores, int numeroSupervisores, int numeroDirectores) {
		((CallCenterDispatcher) dispatcher).setAgente((Agente) applicationContext.getBean("agentePruebas",
				numeroOperadores, numeroSupervisores, numeroDirectores));
	}

}
