package data;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class Cuenta {

    private long no_cuenta;
    private long saldo;
    private Date fecha_creacion;
    Connection conexion = null;

    public Cuenta(long _no_cuenta, long _saldo, Date _fecha_creacion) {
        this.no_cuenta = _no_cuenta;
        this.saldo = _saldo;
        this.fecha_creacion = _fecha_creacion;
    }

    public long getNo_cuenta() {
        return no_cuenta;
    }

    public void setNo_cuenta(long no_cuenta) {
        this.no_cuenta = no_cuenta;
    }

    public long getSaldo() {
        return saldo;
    }

    public void setSaldo(long saldo) {
        this.saldo = saldo;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public void searchHash(long no_cuenta) {

        try {
            CallableStatement cst = conexion.prepareCall("{buscar_no_cuenta}");

            cst.setLong(1, no_cuenta);

            cst.registerOutParameter(2, java.sql.Types.INTEGER);
            cst.registerOutParameter(3, java.sql.Types.INTEGER);
            cst.registerOutParameter(4, java.sql.Types.INTEGER);
            
            this.no_cuenta = cst.getLong(2);
            this.saldo = cst.getLong(3);
            this.fecha_creacion = cst.getDate(4);

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }

    }

}
