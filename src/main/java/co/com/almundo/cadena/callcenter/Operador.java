package co.com.almundo.cadena.callcenter;

import java.util.Stack;

import co.com.almundo.constantes.Constantes;
import co.com.almundo.modelo.Empleado;

public class Operador extends Agente {

	private Stack<Empleado> empleados = new Stack<>();

	public Operador() {
		cargarEmpleados(10);
		}

	public Operador(int numeroEmpleados) {
		cargarEmpleados(numeroEmpleados);
	}

	private void cargarEmpleados(int numeroEmpleados) {
		for (int i = 0; i < numeroEmpleados; i++) {
			empleados.push(new Empleado());
		}
	}
	
	protected String getRol() {
		return Constantes.OPERADOR_ROL;
	}

	protected Empleado getEmpleadoDisponible() {
		return empleados.pop();
	}

	protected boolean isEmpleadoDisponible() {
		return !empleados.isEmpty();
	}

}
