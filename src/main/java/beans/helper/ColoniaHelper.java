package beans.helper;

/*
    Created by IntelliJ IDEA.
    User: @EddyEscalanteU
    Date: 05/01/2021
*/

import beans.domain.ColoniaEntity;
import beans.servicio.ColoniaService;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RequestScoped
@Named
public class ColoniaHelper {

    @Inject
    ColoniaService coloniaService;

    Logger log = LogManager.getRootLogger();

    public List<ColoniaEntity> getColonias() {
        List<ColoniaEntity> colonias = coloniaService.findAll();
        log.info("Recuperamos el listado de personas del EJB");
        return colonias;
    }

    public long getColoniaIdPorNombre(String nombreColonia) {
        long coloniaId = 0;
        List<ColoniaEntity> colonias = getColonias();//Lista de colonias
        for (ColoniaEntity colonia : colonias) {
            if (colonia.getNombreColonia().equals(nombreColonia)) {
                coloniaId = colonia.getColoniaId();
                break;
            }
        }
        return coloniaId;
    }

    public long getColoniaIdPorCP(long codigoPostal) {
        long coloniaId = 0;
        List<ColoniaEntity> colonias = getColonias();
        for (ColoniaEntity colonia : colonias) {
            if (colonia.getCodigoPostal() == codigoPostal) {
                coloniaId = colonia.getColoniaId();
                break;
            }
        }
        return coloniaId;
    }

    public List<SelectItem> getSelectItems() {
        List<SelectItem> selectItems;
        selectItems = new ArrayList<>();
        List<ColoniaEntity> colonias = getColonias();
        for (ColoniaEntity colonia : colonias) {
            SelectItem selectItem = new SelectItem(colonia.getColoniaId(),
                    colonia.getNombreColonia());
            selectItems.add(selectItem);
        }
        return selectItems;
    }
}
