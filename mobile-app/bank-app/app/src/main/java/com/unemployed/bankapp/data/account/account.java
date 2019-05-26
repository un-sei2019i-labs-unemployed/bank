package com.unemployed.bankapp.data.account;

import java.util.Date;

public class account {

    private int no_cuenta;
    private int saldo;
    private Date fecha_creacion;
    private int propietario;

    public account(int no_cuenta, int saldo, Date fecha_creacion, int propietario) {
        this.no_cuenta = no_cuenta;
        this.saldo = saldo;
        this.fecha_creacion = fecha_creacion;
        this.propietario = propietario;
    }

    public int getNo_cuenta() {
        return no_cuenta;
    }

    public void setNo_cuenta(int no_cuenta) {
        this.no_cuenta = no_cuenta;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public int getPropietario() {
        return propietario;
    }

    public void setPropietario(int propietario) {
        this.propietario = propietario;
    }
}
