
package data;

/**
 * @author Jhon Acosta
 */
public class Usuario {
    
    private int cedula;
    private String nombre;
    private int contraseña;
    
    public Usuario(int _cedula, String _nombre, int _contraseña){
        this.cedula = _cedula;
        this.nombre = _nombre;
        this.contraseña = _contraseña;
    }

    public int getCedula() {
        return cedula;
    }
    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getContraseña() {
        return contraseña;
    }
    public void setContraseña(int contraseña) {
        this.contraseña = contraseña;
    }
    
    
    
    
}
