package Bean;

import Bean.util.MobilePageController;
import entidades.MenuRol;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "menuRolController")
@ViewScoped
public class MenuRolController extends AbstractController<MenuRol> {

    @Inject
    private RolController idRolController;
    @Inject
    private MenuController idMenuController;
    @Inject
    private MobilePageController mobilePageController;

    public MenuRolController() {
        // Inform the Abstract parent controller of the concrete MenuRol Entity
        super(MenuRol.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idRolController.setSelected(null);
        idMenuController.setSelected(null);
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
     * Sets the "selected" attribute of the Menu controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdMenu(ActionEvent event) {
        if (this.getSelected() != null && idMenuController.getSelected() == null) {
            idMenuController.setSelected(this.getSelected().getIdMenu());
        }
    }
}
