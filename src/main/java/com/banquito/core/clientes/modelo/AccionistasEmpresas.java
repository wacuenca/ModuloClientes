package com.banquito.core.clientes.modelo;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "accionistas_empresas")
public class AccionistasEmpresas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('accionistas_empresas_id_accionista_seq')")
    @Column(name = "id_accionista", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_empresa")
    private Empresas empresa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_participe_persona")
    private Persona personaParticipe;  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_participe_empresa")
    private Empresas empresaParticipe;

    @Column(name = "participacion", nullable = false, precision = 5, scale = 2)
    private BigDecimal participacion;

    @Column(name = "tipo_entidad_participe", nullable = false, length = 10)
    private String tipoEntidadParticipe;

    @Column(name = "estado", nullable = false, length = 15)
    private String estado;

    @Column(name = "FECHA_CREACION")
    private Instant fechaCreacion;

    @Column(name = "FECHA_ULTIMA_MODIFICACION")
    private Instant fechaUltimaModificacion;

    @Column(name = "version", nullable = false, precision = 9)
    private BigDecimal version;


    public AccionistasEmpresas() {
    }


    public AccionistasEmpresas(Integer id) {
        this.id = id;
    }


   

    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public Empresas getEmpresa() {
        return empresa;
    }


    public void setEmpresa(Empresas empresa) {
        this.empresa = empresa;
    }


    public Persona getPersonaParticipe() {
        return personaParticipe;
    }


    public void setPersonaParticipe(Persona personaParticipe) {
        this.personaParticipe = personaParticipe;
    }


    public Empresas getEmpresaParticipe() {
        return empresaParticipe;
    }


    public void setEmpresaParticipe(Empresas empresaParticipe) {
        this.empresaParticipe = empresaParticipe;
    }


    public BigDecimal getParticipacion() {
        return participacion;
    }


    public void setParticipacion(BigDecimal participacion) {
        this.participacion = participacion;
    }


    public String getTipoEntidadParticipe() {
        return tipoEntidadParticipe;
    }


    public void setTipoEntidadParticipe(String tipoEntidadParticipe) {
        this.tipoEntidadParticipe = tipoEntidadParticipe;
    }


    public String getEstado() {
        return estado;
    }


    public void setEstado(String estado) {
        this.estado = estado;
    }


    public Instant getFechaCreacion() {
        return fechaCreacion;
    }


    public void setFechaCreacion(Instant fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }


    public Instant getFechaUltimaModificacion() {
        return fechaUltimaModificacion;
    }


    public void setFechaUltimaModificacion(Instant fechaUltimaModificacion) {
        this.fechaUltimaModificacion = fechaUltimaModificacion;
    }


    public BigDecimal getVersion() {
        return version;
    }


    public void setVersion(BigDecimal version) {
        this.version = version;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AccionistasEmpresas other = (AccionistasEmpresas) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }


    @Override
    public String toString() {
        return "AccionistasEmpresas [id=" + id + ", empresa=" + empresa + ", personaParticipe=" + personaParticipe
                + ", empresaParticipe=" + empresaParticipe + ", participacion=" + participacion + ", tipoEntidadParticipe=" + tipoEntidadParticipe + ", estado="
                + estado + ", fechaCreacion=" + fechaCreacion + ", fechaUltimaModificacion=" + fechaUltimaModificacion
                + ", version=" + version + "]";
    }


    
    

}