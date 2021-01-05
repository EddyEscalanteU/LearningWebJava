package beans.helper;

/*
    Created by IntelliJ IDEA.
    User: @EddyEscalanteU
    Date: 05/01/2021
*/

import java.util.Iterator;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.ActionSource;
import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

public class FacesContextHelper {

    public static void limpiarFacesMessages(FacesContext facesContext, String clientId) {
        Iterator<FacesMessage> facesMessags = facesContext.getMessages(clientId);
        while (facesMessags.hasNext()) {
            facesMessags.next();
            facesMessags.remove();
        }
    }

    public static void limpiarImmediateFacesMessages(FacesContext facesContext) {
        limpiarImmediateFacesMessages(facesContext, facesContext.getViewRoot());
    }

    public static void limpiarImmediateFacesMessages(FacesContext facesContext, UIComponent uiComponent) {
        if (uiComponent instanceof ActionSource) {
            ActionSource actionSource = (ActionSource) uiComponent;
            if (actionSource.isImmediate()) {
                limpiarFacesMessages(facesContext, uiComponent.getClientId(facesContext));
            }
        } else if (uiComponent instanceof EditableValueHolder) {
            EditableValueHolder editableValueHolder = (EditableValueHolder) uiComponent;
            if (editableValueHolder.isImmediate()) {
                limpiarFacesMessages(facesContext, uiComponent.getClientId(facesContext));
            }
        }
        List<UIComponent> childComponents = uiComponent.getChildren();
        for (UIComponent childComponent : childComponents) {
            limpiarImmediateFacesMessages(facesContext, childComponent);
        }
    }
}
