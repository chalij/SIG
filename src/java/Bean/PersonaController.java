package Bean;

import Bean.util.MobilePageController;
import entidades.Persona;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "personaController")
@ViewScoped
public class PersonaController extends AbstractController<Persona> {

    @Inject
    private MobilePageController mobilePageController;

    public PersonaController() {
        // Inform the Abstract parent controller of the concrete Persona Entity
        super(Persona.class);
    }

    /**
     * Sets the "items" attribute with a collection of Usuario entities that are
     * retrieved from Persona?cap_first and returns the navigation outcome.
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
