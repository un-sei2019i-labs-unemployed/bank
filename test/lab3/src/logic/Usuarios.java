
package logic;
import data.Usuario;

public class Usuarios {
    
    private Usuario user;
    
    public boolean handle (String hash){        
        String id = user.validateHash(hash);
        if (!id.isEmpty()) {
            return true; // Ya existe el hash
        }else{
            return false; // No existe el hash
        }
    }
    
}
