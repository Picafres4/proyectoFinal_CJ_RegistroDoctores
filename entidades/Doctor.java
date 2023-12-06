package entidades;

import java.io.Serializable;

public class Doctor implements Serializable {

    // declaración atributos, constructores etc para Doctor

    public String getIdDoc() {
        return idDoc;
    }

    public void setIdDoc(String idDoc) {
        this.idDoc = idDoc;
    }

    String idDoc;
    String nombreDoc;
    String apPaternoDoc;
    String apMaternoDoc;

    String sexoDoc;

    public String getNombreDoc() {
        return nombreDoc;
    }

    public void setNombreDoc(String nombreDoc) {
        this.nombreDoc = nombreDoc;
    }

    public String getApPaternoDoc() {
        return apPaternoDoc;
    }

    public void setApPaternoDoc(String apPaternoDoc) {
        this.apPaternoDoc = apPaternoDoc;
    }

    public String getApMaternoDoc() {
        return apMaternoDoc;
    }

    public void setApMaternoDoc(String apMaternoDoc) {
        this.apMaternoDoc = apMaternoDoc;
    }

    public String getSexoDoc() {
        return sexoDoc;
    }

    public void setSexoDoc(String sexoDoc) {
        this.sexoDoc = sexoDoc;
    }

    public String getCPDoc() {
        return CPDoc;
    }

    public void setCPDoc(String CPDoc) {
        this.CPDoc = CPDoc;
    }

    public String getEspecialidadDoc() {
        return especialidadDoc;
    }

    public void setEspecialidadDoc(String especialidadDoc) {
        this.especialidadDoc = especialidadDoc;
    }

    String CPDoc;

    String especialidadDoc;


}
