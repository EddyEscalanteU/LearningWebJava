package beans.domain;

/*
    Created by IntelliJ IDEA.
    User: @EddyEscalanteU
    Date: 05/01/2021
*/

import javax.persistence.*;

@Entity
@Table(name = "colonia", schema = "public", catalog = "learning-jsf")
@NamedQuery(name = "ColoniaEntity.findAll", query = "SELECT c FROM ColoniaEntity c")
public class ColoniaEntity {
    private int coloniaId;
    private String nombreColonia;
    private int codigoPostal;

    @Id
    @Column(name = "colonia_id")
    public int getColoniaId() {
        return coloniaId;
    }

    public void setColoniaId(int coloniaId) {
        this.coloniaId = coloniaId;
    }

    @Basic
    @Column(name = "nombre_colonia")
    public String getNombreColonia() {
        return nombreColonia;
    }

    public void setNombreColonia(String nombreColonia) {
        this.nombreColonia = nombreColonia;
    }

    @Basic
    @Column(name = "codigo_postal")
    public int getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ColoniaEntity that = (ColoniaEntity) o;

        if (coloniaId != that.coloniaId) return false;
        if (codigoPostal != that.codigoPostal) return false;
        if (nombreColonia != null ? !nombreColonia.equals(that.nombreColonia) : that.nombreColonia != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = coloniaId;
        result = 31 * result + (nombreColonia != null ? nombreColonia.hashCode() : 0);
        result = 31 * result + codigoPostal;
        return result;
    }
}
