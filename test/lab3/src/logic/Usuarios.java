
package logic;
import data.Usuario;

public class Usuarios {
    
    private Usuario user;
    
    public boolean handle (int size){        
        int tries = user.validateHash(1,size);
        return tries<2; // Ya existe el hash cuando retorna m�s de un intento de hash
    }
    
}
