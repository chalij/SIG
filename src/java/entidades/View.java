/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author chali
 */
@Entity
@Table(name = "view")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "View.findAll", query = "SELECT v FROM View v"),
    @NamedQuery(name = "View.findByIdView", query = "SELECT v FROM View v WHERE v.idView = :idView"),
    @NamedQuery(name = "View.findByUrl", query = "SELECT v FROM View v WHERE v.url = :url"),
    @NamedQuery(name = "View.findByEliminado", query = "SELECT v FROM View v WHERE v.eliminado = :eliminado")})
public class View implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_view")
    private Integer idView;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "url")
    private String url;
    @Column(name = "eliminado")
    private Boolean eliminado;
    @OneToMany(mappedBy = "idView")
    private Collection<Menu> menuCollection;

    public View() {
    }

    public View(Integer idView) {
        this.idView = idView;
    }

    public View(Integer idView, String url) {
        this.idView = idView;
        this.url = url;
    }

    public Integer getIdView() {
        return idView;
    }

    public void setIdView(Integer idView) {
        this.idView = idView;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public Collection<Menu> getMenuCollection() {
        return menuCollection;
    }

    public void setMenuCollection(Collection<Menu> menuCollection) {
        this.menuCollection = menuCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idView != null ? idView.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof View)) {
            return false;
        }
        View other = (View) object;
        if ((this.idView == null && other.idView != null) || (this.idView != null && !this.idView.equals(other.idView))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.View[ idView=" + idView + " ]";
    }
    
}
