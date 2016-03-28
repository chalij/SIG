/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author chali
 */
@Entity
@Table(name = "menu_rol")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MenuRol.findAll", query = "SELECT m FROM MenuRol m"),
    @NamedQuery(name = "MenuRol.findByIdMenuRol", query = "SELECT m FROM MenuRol m WHERE m.idMenuRol = :idMenuRol"),
    @NamedQuery(name = "MenuRol.findByEliminado", query = "SELECT m FROM MenuRol m WHERE m.eliminado = :eliminado")})
public class MenuRol implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_menu_rol")
    private Integer idMenuRol;
    @Column(name = "eliminado")
    private Boolean eliminado;
    @JoinColumn(name = "id_rol", referencedColumnName = "id_rol")
    @ManyToOne
    private Rol idRol;
    @JoinColumn(name = "id_menu", referencedColumnName = "id_menu")
    @ManyToOne
    private Menu idMenu;

    public MenuRol() {
    }

    public MenuRol(Integer idMenuRol) {
        this.idMenuRol = idMenuRol;
    }

    public Integer getIdMenuRol() {
        return idMenuRol;
    }

    public void setIdMenuRol(Integer idMenuRol) {
        this.idMenuRol = idMenuRol;
    }

    public Boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }

    public Rol getIdRol() {
        return idRol;
    }

    public void setIdRol(Rol idRol) {
        this.idRol = idRol;
    }

    public Menu getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Menu idMenu) {
        this.idMenu = idMenu;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMenuRol != null ? idMenuRol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MenuRol)) {
            return false;
        }
        MenuRol other = (MenuRol) object;
        if ((this.idMenuRol == null && other.idMenuRol != null) || (this.idMenuRol != null && !this.idMenuRol.equals(other.idMenuRol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.MenuRol[ idMenuRol=" + idMenuRol + " ]";
    }
    
}
