package com.unemployed.bankapp.data.transaction;

import java.util.Date;

public class transaction {

    private int id_transaction;
    private Date fecha;
    private int origen;
    private int destino;
    private int cantidad;

    public transaction(int id_transaction, Date fecha, int origen, int destino, int cantidad) {
        this.id_transaction = id_transaction;
        this.fecha = fecha;
        this.origen = origen;
        this.destino = destino;
        this.cantidad = cantidad;
    }

    public int getId_transaction() {
        return id_transaction;
    }

    public void setId_transaction(int id_transaction) {
        this.id_transaction = id_transaction;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getOrigen() {
        return origen;
    }

    public void setOrigen(int origen) {
        this.origen = origen;
    }

    public int getDestino() {
        return destino;
    }

    public void setDestino(int destino) {
        this.destino = destino;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
