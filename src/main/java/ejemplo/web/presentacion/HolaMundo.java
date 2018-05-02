package ejemplo.web.presentacion;

import javax.enterprise.inject.Model;

@Model
public class HolaMundo {

	private String nombre	= "Pedro";
	private String apellido = "Perez";
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String mostrarSaludo() {
		return "¡ Hola " + nombre + " " + apellido + " !";
	}
	
	
}
