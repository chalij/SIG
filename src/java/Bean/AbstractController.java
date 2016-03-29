package Bean;

import Controller.AbstractFacade;
import Controller.LazyEntityDataModel;
import Bean.util.JsfUtil;
import entidades.Menu;
import entidades.MenuRol;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import java.util.ResourceBundle;
import javax.ejb.EJBException;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;

/**
 * Represents an abstract shell of to be used as JSF Controller to be used in
 * AJAX-enabled applications. No outcomes will be generated from its methods
 * since handling is designed to be done inside one page.
 *
 * @param <T> the concrete Entity type of the Controller bean to be created
 */
public abstract class AbstractController<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private AbstractFacade<T> ejbFacade;
    private Class<T> itemClass;
    private T selected;
    private Collection<T> items;
    private LazyEntityDataModel<T> lazyItems;
    private DefaultMenuModel menuModel = new DefaultMenuModel();

    /**
     * @return the models
     */

    /**
     * @param models the models to set
     */

    /**
     * @return the menuModel
     */
    public DefaultMenuModel getMenuModel() {
        List<MenuRol> models2= (List<MenuRol>)this.ejbFacade.findAllCondition();
        System.out.println("///Agregas elemento///");
        menuModel.getElements().clear();
        DefaultSubMenu subMenu = new DefaultSubMenu("chali");
        List<MenuRol> padres=new ArrayList<MenuRol>();
        for(int i=0;i<models2.size();i++){
            if(models2.get(i).getIdMenu().getMenIdMenu()==null){
                DefaultMenuItem menuItem = new DefaultMenuItem(models2.get(i).getIdMenu().getDescripcion());
                menuItem.setUrl(models2.get(i).getIdMenu().getIdView().getUrl());
                subMenu.addElement(menuItem);
                menuModel.addElement(subMenu);
                padres.add(models2.get(i));
            }
            else{
                for(int j=0;j<padres.size();j++){
                    if(padres.get(j).getIdMenu().getIdMenu()==models2.get(i).getIdMenu().getMenIdMenu().getIdMenu()){
                        DefaultMenuItem item= new DefaultMenuItem(models2.get(i).getIdMenu().getDescripcion());
                        item = new DefaultMenuItem(models2.get(i).getIdMenu().getMenIdMenu().getDescripcion());
                        item.setUrl(models2.get(i).getIdMenu().getMenIdMenu().getIdView().getUrl());
                        subMenu.addElement(item);
                        menuModel.addElement(subMenu);
                    }
                }
            }
        }    
        return menuModel;
    }

    /**
     * @param menuModel the menuModel to set
     */
    public void setMenuModel(DefaultMenuModel menuModel) {
        this.menuModel = menuModel;
    }

    private enum PersistAction {
        CREATE,
        DELETE,
        UPDATE
    }

    public AbstractController() {
    }

    public AbstractController(Class<T> itemClass) {
        this.itemClass = itemClass;
    }

    /**
     * Retrieve the currently selected item.
     *
     * @return the currently selected Entity
     */
    public T getSelected() {
        return selected;
    }

    /**
     * Pass in the currently selected item.
     *
     * @param selected the Entity that should be set as selected
     */
    public void setSelected(T selected) {
        this.selected = selected;
    }

    /**
     * Sets any embeddable key fields if an Entity uses composite keys. If the
     * entity does not have composite keys, this method performs no actions and
     * exists purely to be overridden inside a concrete controller class.
     */
    protected void setEmbeddableKeys() {
        // Nothing to do if entity does not have any embeddable key.
    }

    ;

    /**
     * Sets the concrete embedded key of an Entity that uses composite keys.
     * This method will be overriden inside concrete controller classes and does
     * nothing if the specific entity has no composite keys.
     */
    protected void initializeEmbeddableKey() {
        // Nothing to do if entity does not have any embeddable key.
    }

    /**
     * Returns all items as a Collection object.
     *
     * @return a collection of Entity items returned by the data layer
     */
    public Collection<T> getItems() {
        if (items == null) {
            if(this.ejbFacade==null){
                return null;
            }
            else
            items = this.ejbFacade.findAll();
        }
        return items;
    }

    /**
     * Pass in collection of items
     *
     * @param items a collection of Entity items
     */
    public void setItems(Collection<T> items) {
        this.items = items;
    }

    /**
     *
     * @return Entity-specific Lazy Data Model
     */
    public LazyEntityDataModel<T> getLazyItems() {
        if (lazyItems == null) {
            lazyItems = new LazyEntityDataModel<>(this.ejbFacade);
        }
        return lazyItems;
    }

    public void setLazyItems(LazyEntityDataModel<T> lazyItems) {
        this.lazyItems = lazyItems;
    }

    public void setLazyItems(Collection<T> items) {
        if (items instanceof List) {
            lazyItems = new LazyEntityDataModel<>((List<T>) items);
        } else {
            lazyItems = new LazyEntityDataModel<>(new ArrayList<>(items));
        }
    }

    /**
     * Apply changes to an existing item to the data layer.
     *
     * @param event an event from the widget that wants to save an Entity to the
     * data layer
     */
    public void save(ActionEvent event) {
        String msg = ResourceBundle.getBundle("/MyBundle").getString(itemClass.getSimpleName() + "Updated");
        persist(PersistAction.UPDATE, msg);
    }

    /**
     * Store a new item in the data layer.
     *
     * @param event an event from the widget that wants to save a new Entity to
     * the data layer
     */
    public void saveNew(ActionEvent event) {
        String msg = ResourceBundle.getBundle("/MyBundle").getString(itemClass.getSimpleName() + "Created");
        persist(PersistAction.CREATE, msg);
        if (!isValidationFailed()) {
            items = null; // Invalidate list of items to trigger re-query.
            lazyItems = null; // Invalidate list of lazy items to trigger re-query.
        }
    }

    /**
     * Remove an existing item from the data layer.
     *
     * @param event an event from the widget that wants to delete an Entity from
     * the data layer
     */
    public void delete(ActionEvent event) {
        String msg = ResourceBundle.getBundle("/MyBundle").getString(itemClass.getSimpleName() + "Deleted");
        persist(PersistAction.DELETE, msg);
        if (!isValidationFailed()) {
            selected = null; // Remove selection
            items = null; // Invalidate list of items to trigger re-query.
            lazyItems = null; // Invalidate list of lazy items to trigger re-query.
        }
    }

    /**
     * Performs any data modification actions for an entity. The actions that
     * can be performed by this method are controlled by the
     * {@link PersistAction} enumeration and are either CREATE, EDIT or DELETE.
     *
     * @param persistAction a specific action that should be performed on the
     * current item
     * @param successMessage a message that should be displayed when persisting
     * the item succeeds
     */
    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            this.setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    this.ejbFacade.edit(selected);
                } else {
                    this.ejbFacade.remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                Throwable cause = JsfUtil.getRootCause(ex.getCause());
                if (cause != null) {
                    if (cause instanceof ConstraintViolationException) {
                        ConstraintViolationException excp = (ConstraintViolationException) cause;
                        for (ConstraintViolation s : excp.getConstraintViolations()) {
                            JsfUtil.addErrorMessage(s.getMessage());
                        }
                    } else {
                        String msg = cause.getLocalizedMessage();
                        if (msg.length() > 0) {
                            JsfUtil.addErrorMessage(msg);
                        } else {
                            JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                        }
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/MyBundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    /**
     * Creates a new instance of an underlying entity and assigns it to Selected
     * property.
     *
     * @param event an event from the widget that wants to create a new,
     * unmanaged Entity for the data layer
     * @return a new, unmanaged Entity
     */
    public T prepareCreate(ActionEvent event) {
        T newItem;
        try {
            newItem = itemClass.newInstance();
            this.selected = newItem;
            initializeEmbeddableKey();
            return newItem;
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Inform the user interface whether any validation error exist on a page.
     *
     * @return a logical value whether form validation has passed or failed
     */
    public boolean isValidationFailed() {
        return JsfUtil.isValidationFailed();
    }

    /**
     * Retrieve all messages as a String to be displayed on the page.
     *
     * @param clientComponent the component for which the message applies
     * @param defaultMessage a default message to be shown
     * @return a concatenation of all messages
     */
    public String getComponentMessages(String clientComponent, String defaultMessage) {
        return JsfUtil.getComponentMessages(clientComponent, defaultMessage);
    }

    /**
     * Retrieve a collection of Entity items for a specific Controller from
     * another JSF page via Request parameters.
     */
    @PostConstruct
    public void initParams() {
        Object paramItems = FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get(itemClass.getSimpleName() + "_items");
        if (paramItems != null) {
            setItems((Collection<T>) paramItems);
            setLazyItems((Collection<T>) paramItems);
        }
    }

}
