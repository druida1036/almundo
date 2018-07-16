package co.com.almundo.modelo;

import co.com.almundo.constantes.Constantes;

public class Empleado {
	
	
	public Empleado() {
		super();
	}

	public String atenderLlamada(String mensaje) {
		return Constantes.EMPLEADO_MENSAJE + mensaje;
	}

}
