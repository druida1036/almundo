package co.com.almundo.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import co.com.almundo.cadena.callcenter.Agente;
import co.com.almundo.cadena.callcenter.Director;
import co.com.almundo.cadena.callcenter.Operador;
import co.com.almundo.cadena.callcenter.Supervisor;
import co.com.almundo.dispatcher.Dispatcher;
import co.com.almundo.dispatcher.callcenter.CallCenterDispatcher;

@Configuration
public class CallCenterConfig {

	@Bean
	public Dispatcher callCenterDispatcher() {
		return new CallCenterDispatcher();
	}

	@Bean()
	public Agente agente() {
		Supervisor supervisor = new Supervisor(3);
		Agente agente = new Operador(8);
		agente.setSiguienteAgente(supervisor);
		Director director = new Director(12);
		supervisor.setSiguienteAgente(director);
		return agente;
	}
	
	@Bean("agentePruebas")
	@Scope("prototype")
	public Agente agente(int numeroOperadores, int numeroSupervisores, int numeroDirectores) {
		Agente agente = new Operador(numeroOperadores);
		Supervisor supervisor = new Supervisor(numeroSupervisores);
		agente.setSiguienteAgente(supervisor);
		Director director = new Director(numeroDirectores);
		supervisor.setSiguienteAgente(director);
		return agente;
	}

}
