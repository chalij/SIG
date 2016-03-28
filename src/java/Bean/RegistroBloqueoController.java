package Bean;

import Bean.util.MobilePageController;
import entidades.RegistroBloqueo;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "registroBloqueoController")
@ViewScoped
public class RegistroBloqueoController extends AbstractController<RegistroBloqueo> {

    @Inject
    private UsuarioController idUsuarioController;
    @Inject
    private MobilePageController mobilePageController;

    public RegistroBloqueoController() {
        // Inform the Abstract parent controller of the concrete RegistroBloqueo Entity
        super(RegistroBloqueo.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idUsuarioController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Usuario controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdUsuario(ActionEvent event) {
        if (this.getSelected() != null && idUsuarioController.getSelected() == null) {
            idUsuarioController.setSelected(this.getSelected().getIdUsuario());
        }
    }
}
