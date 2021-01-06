package beans.servicio;

/*
    Created by IntelliJ IDEA.
    User: @EddyEscalanteU
    Date: 05/01/2021
*/

import beans.domain.ColoniaEntity;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ColoniaService {

    @PersistenceContext
    EntityManager em;

    public List<ColoniaEntity> findAll() {
        return em.createNamedQuery("ColoniaEntity.findAll").getResultList();
    }
}
