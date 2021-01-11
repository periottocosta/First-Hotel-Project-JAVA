/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidadesDoBanco;

import java.util.Date;

/**
 *
 * @author User
 */
public class Reserva {
    private int id;
    private Quarto idQuarto;
    private Cliente idCliente;
    private Date dataReserva;
    private Date dataEntrada;
    private Date dataSaida;
    private EstatusReserva idEstatusReser;

    public Reserva() {
    }

    public Reserva(int id, Quarto idQuarto, Cliente idCliente, Date dataReserva, Date dataEntrada, Date dataSaida, EstatusReserva idEstatusReser) {
        this.id = id;
        this.idQuarto = idQuarto;
        this.idCliente = idCliente;
        this.dataReserva = dataReserva;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.idEstatusReser = idEstatusReser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Quarto getIdQuarto() {
        return idQuarto;
    }

    public void setIdQuarto(Quarto idQuarto) {
        this.idQuarto = idQuarto;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public Date getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(Date dataReserva) {
        this.dataReserva = dataReserva;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    public EstatusReserva getIdEstatusReser() {
        return idEstatusReser;
    }

    public void setIdEstatusReser(EstatusReserva idEstatusReser) {
        this.idEstatusReser = idEstatusReser;
    }
    
    
}
