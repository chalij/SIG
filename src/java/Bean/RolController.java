package Bean;

import Bean.util.MobilePageController;
import entidades.Rol;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "rolController")
@ViewScoped
public class RolController extends AbstractController<Rol> {

    @Inject
    private MobilePageController mobilePageController;

    public RolController() {
        // Inform the Abstract parent controller of the concrete Rol Entity
        super(Rol.class);
    }

    /**
     * Sets the "items" attribute with a collection of MenuRol entities that are
     * retrieved from Rol?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for MenuRol page
     */
    public String navigateMenuRolCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("MenuRol_items", this.getSelected().getMenuRolCollection());
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/menuRol/index";
    }

    /**
     * Sets the "items" attribute with a collection of Usuario entities that are
     * retrieved from Rol?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Usuario page
     */
    public String navigateUsuarioCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Usuario_items", this.getSelected().getUsuarioCollection());
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/usuario/index";
    }

}
