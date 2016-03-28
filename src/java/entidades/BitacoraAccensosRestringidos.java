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
@Table(name = "bitacora_accensos_restringidos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BitacoraAccensosRestringidos.findAll", query = "SELECT b FROM BitacoraAccensosRestringidos b"),
    @NamedQuery(name = "BitacoraAccensosRestringidos.findByIdBitacoraAccensosRestringid", query = "SELECT b FROM BitacoraAccensosRestringidos b WHERE b.idBitacoraAccensosRestringid = :idBitacoraAccensosRestringid"),
    @NamedQuery(name = "BitacoraAccensosRestringidos.findByUrlSinPremisos", query = "SELECT b FROM BitacoraAccensosRestringidos b WHERE b.urlSinPremisos = :urlSinPremisos"),
    @NamedQuery(name = "BitacoraAccensosRestringidos.findByFecha", query = "SELECT b FROM BitacoraAccensosRestringidos b WHERE b.fecha = :fecha"),
    @NamedQuery(name = "BitacoraAccensosRestringidos.findByEliminado", query = "SELECT b FROM BitacoraAccensosRestringidos b WHERE b.eliminado = :eliminado")})
public class BitacoraAccensosRestringidos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_bitacora_accensos_restringid")
    private Integer idBitacoraAccensosRestringid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "url_sin_premisos")
    private String urlSinPremisos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "eliminado")
    private Boolean eliminado;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario idUsuario;

    public BitacoraAccensosRestringidos() {
    }

    public BitacoraAccensosRestringidos(Integer idBitacoraAccensosRestringid) {
        this.idBitacoraAccensosRestringid = idBitacoraAccensosRestringid;
    }

    public BitacoraAccensosRestringidos(Integer idBitacoraAccensosRestringid, String urlSinPremisos, Date fecha) {
        this.idBitacoraAccensosRestringid = idBitacoraAccensosRestringid;
        this.urlSinPremisos = urlSinPremisos;
        this.fecha = fecha;
    }

    public Integer getIdBitacoraAccensosRestringid() {
        return idBitacoraAccensosRestringid;
    }

    public void setIdBitacoraAccensosRestringid(Integer idBitacoraAccensosRestringid) {
        this.idBitacoraAccensosRestringid = idBitacoraAccensosRestringid;
    }

    public String getUrlSinPremisos() {
        return urlSinPremisos;
    }

    public void setUrlSinPremisos(String urlSinPremisos) {
        this.urlSinPremisos = urlSinPremisos;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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
        hash += (idBitacoraAccensosRestringid != null ? idBitacoraAccensosRestringid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BitacoraAccensosRestringidos)) {
            return false;
        }
        BitacoraAccensosRestringidos other = (BitacoraAccensosRestringidos) object;
        if ((this.idBitacoraAccensosRestringid == null && other.idBitacoraAccensosRestringid != null) || (this.idBitacoraAccensosRestringid != null && !this.idBitacoraAccensosRestringid.equals(other.idBitacoraAccensosRestringid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.BitacoraAccensosRestringidos[ idBitacoraAccensosRestringid=" + idBitacoraAccensosRestringid + " ]";
    }
    
}
