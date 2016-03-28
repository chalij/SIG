package Bean;

import Bean.util.MobilePageController;
import entidades.BitacoraAccensosRestringidos;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "bitacoraAccensosRestringidosController")
@ViewScoped
public class BitacoraAccensosRestringidosController extends AbstractController<BitacoraAccensosRestringidos> {

    @Inject
    private UsuarioController idUsuarioController;
    @Inject
    private MobilePageController mobilePageController;

    public BitacoraAccensosRestringidosController() {
        // Inform the Abstract parent controller of the concrete BitacoraAccensosRestringidos Entity
        super(BitacoraAccensosRestringidos.class);
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
