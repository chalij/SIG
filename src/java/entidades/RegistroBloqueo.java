/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author chali
 */
@Entity
@Table(name = "registro_bloqueo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RegistroBloqueo.findAll", query = "SELECT r FROM RegistroBloqueo r"),
    @NamedQuery(name = "RegistroBloqueo.findByIdRegistroBloqueo", query = "SELECT r FROM RegistroBloqueo r WHERE r.idRegistroBloqueo = :idRegistroBloqueo"),
    @NamedQuery(name = "RegistroBloqueo.findByIntentosFallidos", query = "SELECT r FROM RegistroBloqueo r WHERE r.intentosFallidos = :intentosFallidos"),
    @NamedQuery(name = "RegistroBloqueo.findByFecha", query = "SELECT r FROM RegistroBloqueo r WHERE r.fecha = :fecha"),
    @NamedQuery(name = "RegistroBloqueo.findByIp", query = "SELECT r FROM RegistroBloqueo r WHERE r.ip = :ip"),
    @NamedQuery(name = "RegistroBloqueo.findByEliminado", query = "SELECT r FROM RegistroBloqueo r WHERE r.eliminado = :eliminado")})
public class RegistroBloqueo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_registro_bloqueo")
    private Integer idRegistroBloqueo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "intentos_fallidos")
    private int intentosFallidos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "ip")
    private String ip;
    @Column(name = "eliminado")
    private Boolean eliminado;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario idUsuario;

    public RegistroBloqueo() {
    }

    public RegistroBloqueo(Integer idRegistroBloqueo) {
        this.idRegistroBloqueo = idRegistroBloqueo;
    }

    public RegistroBloqueo(Integer idRegistroBloqueo, int intentosFallidos, Date fecha, String ip) {
        this.idRegistroBloqueo = idRegistroBloqueo;
        this.intentosFallidos = intentosFallidos;
        this.fecha = fecha;
        this.ip = ip;
    }

    public Integer getIdRegistroBloqueo() {
        return idRegistroBloqueo;
    }

    public void setIdRegistroBloqueo(Integer idRegistroBloqueo) {
        this.idRegistroBloqueo = idRegistroBloqueo;
    }

    public int getIntentosFallidos() {
        return intentosFallidos;
    }

    public void setIntentosFallidos(int intentosFallidos) {
        this.intentosFallidos = intentosFallidos;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRegistroBloqueo != null ? idRegistroBloqueo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegistroBloqueo)) {
            return false;
        }
        RegistroBloqueo other = (RegistroBloqueo) object;
        if ((this.idRegistroBloqueo == null && other.idRegistroBloqueo != null) || (this.idRegistroBloqueo != null && !this.idRegistroBloqueo.equals(other.idRegistroBloqueo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.RegistroBloqueo[ idRegistroBloqueo=" + idRegistroBloqueo + " ]";
    }
    
}
