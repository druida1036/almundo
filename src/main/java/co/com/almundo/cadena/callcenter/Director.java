package co.com.almundo.cadena.callcenter;

import java.util.Stack;

import co.com.almundo.constantes.Constantes;
import co.com.almundo.modelo.Empleado;

public class Director extends Agente {
	
	private Stack<Empleado> empleados = new Stack<>();
	
	public Director() {
		cargarEmpleados(10);
		
	}
	
	public Director(int numeroEmpleados) {
		cargarEmpleados(numeroEmpleados);
	}
	
	private void cargarEmpleados(int numeroEmpleados) {
		for (int i = 0; i < numeroEmpleados; i++) {
			empleados.push(new Empleado());
		}
	}
	
	protected String getRol() {
		return Constantes.DIRECTOR_ROL;
	}

	protected Empleado getEmpleadoDisponible() {
		return empleados.pop();
	}

	protected boolean isEmpleadoDisponible() {
		return !empleados.isEmpty();
	}


}
