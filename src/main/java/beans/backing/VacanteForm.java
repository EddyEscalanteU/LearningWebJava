package beans.backing;
/*
    Created by IntelliJ IDEA.
    User: @EddyEscalanteU
    Date: 05/01/2021
*/

import beans.model.Candidato;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RequestScoped
@Named
public class VacanteForm {

    Logger log = LogManager.getRootLogger();

    @Inject
    private Candidato candidato;

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

}