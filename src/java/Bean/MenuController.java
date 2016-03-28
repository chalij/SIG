package Bean;

import Bean.util.MobilePageController;
import entidades.Menu;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "menuController")
@ViewScoped
public class MenuController extends AbstractController<Menu> {

    @Inject
    private ViewController idViewController;
    @Inject
    private MenuController menIdMenuController;
    @Inject
    private MobilePageController mobilePageController;

    public MenuController() {
        // Inform the Abstract parent controller of the concrete Menu Entity
        super(Menu.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idViewController.setSelected(null);
        menIdMenuController.setSelected(null);
    }

    /**
     * Sets the "items" attribute with a collection of MenuRol entities that are
     * retrieved from Menu?cap_first and returns the navigation outcome.
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
     * Sets the "selected" attribute of the View controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdView(ActionEvent event) {
        if (this.getSelected() != null && idViewController.getSelected() == null) {
            idViewController.setSelected(this.getSelected().getIdView());
        }
    }

    /**
     * Sets the "items" attribute with a collection of Menu entities that are
     * retrieved from Menu?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Menu page
     */
    public String navigateMenuCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Menu_items", this.getSelected().getMenuCollection());
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/menu/index";
    }

    /**
     * Sets the "selected" attribute of the Menu controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareMenIdMenu(ActionEvent event) {
        if (this.getSelected() != null && menIdMenuController.getSelected() == null) {
            menIdMenuController.setSelected(this.getSelected().getMenIdMenu());
        }
    }
}
