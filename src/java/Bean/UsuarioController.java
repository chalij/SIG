package Bean;

import Bean.util.MobilePageController;
import entidades.Usuario;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "usuarioController")
@ViewScoped
public class UsuarioController extends AbstractController<Usuario> {

    @Inject
    private RolController idRolController;
    @Inject
    private PersonaController idPersonaController;
    @Inject
    private MobilePageController mobilePageController;

    public UsuarioController() {
        // Inform the Abstract parent controller of the concrete Usuario Entity
        super(Usuario.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idRolController.setSelected(null);
        idPersonaController.setSelected(null);
    }

    /**
     * Sets the "items" attribute with a collection of
     * BitacoraAccensosRestringidos entities that are retrieved from
     * Usuario?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for BitacoraAccensosRestringidos page
     */
    public String navigateBitacoraAccensosRestringidosCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("BitacoraAccensosRestringidos_items", this.getSelected().getBitacoraAccensosRestringidosCollection());
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/bitacoraAccensosRestringidos/index";
    }

    /**
     * Sets the "selected" attribute of the Rol controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdRol(ActionEvent event) {
        if (this.getSelected() != null && idRolController.getSelected() == null) {
            idRolController.setSelected(this.getSelected().getIdRol());
        }
    }

    /**
     * Sets the "selected" attribute of the Persona controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdPersona(ActionEvent event) {
        if (this.getSelected() != null && idPersonaController.getSelected() == null) {
            idPersonaController.setSelected(this.getSelected().getIdPersona());
        }
    }

    /**
     * Sets the "items" attribute with a collection of RegistroBloqueo entities
     * that are retrieved from Usuario?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for RegistroBloqueo page
     */
    public String navigateRegistroBloqueoCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("RegistroBloqueo_items", this.getSelected().getRegistroBloqueoCollection());
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/registroBloqueo/index";
    }

}
