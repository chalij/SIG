package Bean;

import Bean.util.MobilePageController;
import entidades.View;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "viewController")
@ViewScoped
public class ViewController extends AbstractController<View> {

    @Inject
    private MobilePageController mobilePageController;

    public ViewController() {
        // Inform the Abstract parent controller of the concrete View Entity
        super(View.class);
    }

    /**
     * Sets the "items" attribute with a collection of Menu entities that are
     * retrieved from View?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Menu page
     */
    public String navigateMenuCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Menu_items", this.getSelected().getMenuCollection());
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/menu/index";
    }

}
