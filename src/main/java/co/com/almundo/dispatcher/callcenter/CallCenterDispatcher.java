package co.com.almundo.dispatcher.callcenter;

import org.springframework.beans.factory.annotation.Autowired;

import co.com.almundo.cadena.callcenter.Agente;
import co.com.almundo.dispatcher.Dispatcher;

public class CallCenterDispatcher implements Dispatcher {
	
	@Autowired
	private Agente agente;
	
	public String dispatchCall(String mensaje) {
		return agente.procesarLlamada(mensaje);
	}

	public Agente getAgente() {
		return agente;
	}

	public void setAgente(Agente agente) {
		this.agente = agente;
	}

}
