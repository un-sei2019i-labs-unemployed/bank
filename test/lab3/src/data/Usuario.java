
package data;


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
    
    public long getHash(int size, int intentos){
        return (this.nombre.hashCode()+this.cedula+this.contraseña-1+intentos)%size;
    }
    
    public int validateHash (int intentos, int size){
        boolean isRepeated=false;
        long hash=this.getHash(size,intentos);
        //función para revisar si el hashCode ya existe...
        
        if(hash>=0&&!isRepeated){
            return intentos; //retorna el número de intentos para hacer hash
        }else{
            return validateHash(intentos+1, size);
        }
    }
    
    
}
