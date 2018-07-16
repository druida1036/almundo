package co.com.almundo.cadena.callcenter;

import co.com.almundo.constantes.Constantes;
import co.com.almundo.modelo.Empleado;

public abstract class Agente {

	protected Agente siguienteAgente;

	/**
	 * Metodo sincronizado que centraliza la gestion de una llamada. Utiliza un
	 * patron Cadena de responsabilidad para organizar el llamado de un operador,
	 * supervisor o director segun disponiblidad.
	 * 
	 * Cuando mas de 10 peticiones concurrentes (Problema planteado) se debe tener
	 * en cuenta el numero total de empleados disponibles.
	 * 
	 * Si se cuenta con los empleados suficientes las peticiones pueden ser
	 * atendidas.
	 * 
	 * Si no se cuenta con los empleados suficientes, entonces sera respondida con
	 * el siguiente mensaje: 'Agente no disponible: ' + @param mensaje
	 * 
	 * @param mensaje
	 * @return
	 */
	public synchronized String procesarLlamada(String mensaje) {
		if (isEmpleadoDisponible()) {
			Empleado empleado = getEmpleadoDisponible();
			String llamadaAtendida = empleado.atenderLlamada(mensaje);
			return getRol() + llamadaAtendida;

		}
		if (siguienteAgente != null) {
			return siguienteAgente.procesarLlamada(mensaje);
		}
		return Constantes.AGENTE_NO_DISPONIBLE + mensaje;
	}

	public void setSiguienteAgente(Agente siguienteAgente) {
		this.siguienteAgente = siguienteAgente;
	}

	protected abstract String getRol();

	protected abstract Empleado getEmpleadoDisponible();

	protected abstract boolean isEmpleadoDisponible();

}
