package beans.backing;

/*
    Created by IntelliJ IDEA.
    User: @EddyEscalanteU
    Date: 05/01/2021
*/

import beans.helper.ColoniaHelper;
import beans.helper.FacesContextHelper;
import beans.model.Candidato;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RequestScoped
@Named
public class VacanteForm {

    Logger log = LogManager.getRootLogger();
    private boolean comentarioEnviado = false;

    @Inject
    private Candidato candidato;

    @Inject
    private ColoniaHelper coloniaHelper;

    public VacanteForm() {
        log.info("Creando objeto VacanteForm");
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    //Metodo de flujo de control
    public String enviar() {

        System.out.println("enviar() Nombre=" + this.candidato.getNombre());
        System.out.println("enviar() Apellido=" + this.candidato.getApellido());
        System.out.println("enviar() Sueldo deseado" + this.candidato.getSueldoDeseado());

        if (this.candidato.getNombre().equals("Juan")) {

            if (this.candidato.getApellido().equals("Perez")) {
                String msg = "Gracias, pero Juan Perez ya trabaja con nosotros.";
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
                FacesContext facesContext = FacesContext.getCurrentInstance();
                String clientId = null; //Este es un mensaje global
                facesContext.addMessage(clientId, facesMessage);
                return "index";
            }
            return "exito";//exito.xhtml
        } else {
            return "fallo"; //fallo.xhtml
        }
    }

    //Metodo de tipo Value Change Listener
    public void codigoPostalListener(ValueChangeEvent valueChangeEvent) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        UIViewRoot uiViewRoot = facesContext.getViewRoot();
        Long newCodigoPostal = (Long) valueChangeEvent.getNewValue();
        log.info("Nuevo codigo postal: " + newCodigoPostal);
        UIInput ciudadInputText = (UIInput) uiViewRoot.findComponent("vacanteForm:ciudad");
        String ciudad = "Ciudad de Mexico";
        ciudadInputText.setValue(ciudad);
        ciudadInputText.setSubmittedValue(ciudad);
        UIInput coloniaInputText = (UIInput) uiViewRoot.findComponent("vacanteForm:coloniaId");
        //Buscamos la colonia por id con ayuda del bean coloniaHelper
        Long coloniaId = this.coloniaHelper.getColoniaIdPorCP(newCodigoPostal);
        coloniaInputText.setValue(coloniaId);
        coloniaInputText.setSubmittedValue(coloniaId);
        //Enviamos la respuesta
        facesContext.renderResponse();
    }

    public void ocultarComentario(ActionEvent actionEvent) {
        this.comentarioEnviado = !this.comentarioEnviado;
        log.info("Mostrando/ocultando el comentario");
        FacesContext facesContext = FacesContext.getCurrentInstance();
        FacesContextHelper.limpiarImmediateFacesMessages(facesContext);
    }

    public boolean isComentarioEnviado() {
        return comentarioEnviado;
    }

    public void setComentarioEnviado(boolean comentarioEnviado) {
        this.comentarioEnviado = comentarioEnviado;
    }

    public ColoniaHelper getColoniaHelper() {
        return coloniaHelper;
    }

    public void setColoniaHelper(ColoniaHelper coloniaHelper) {
        this.coloniaHelper = coloniaHelper;
    }
}