package com.example.usuario.goutuadministra;

/**
 * Created by usuario on 27/08/2017.
 */

public class Empresa_Dato {


    private String imagen;
    private  String nombreEmpresa;
    private  String descripcionEmpresa;

    public Empresa_Dato(String imagen, String nombreEmpresa, String descripcionEmpresa) {

        this.imagen = imagen;
        this.nombreEmpresa = nombreEmpresa;
        this.descripcionEmpresa = descripcionEmpresa;
    }


    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getDescripcionEmpresa() {
        return descripcionEmpresa;
    }

    public void setDescripcionEmpresa(String descripcionEmpresa) {
        this.descripcionEmpresa = descripcionEmpresa;
    }
}
