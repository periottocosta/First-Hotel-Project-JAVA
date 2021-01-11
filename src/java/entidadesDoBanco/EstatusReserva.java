/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidadesDoBanco;

/**
 *
 * @author User
 */
public class EstatusReserva {
    private int id;
    private String status;

    public EstatusReserva() {
    }

    public EstatusReserva(int id, String status) {
        this.id = id;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
