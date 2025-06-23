package com.banquito.core.clientes.modelo;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "accionistas_empresas", schema = "public")
public class AccionistasEmpresas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('accionistas_empresas_id_accionista_seq')")
    @Column(name = "id_accionista", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_empresa", nullable = false)
    private Empresas idEmpresa;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_participe", nullable = false)
    private Empresas idParticipe;

    @Column(name = "participacion", nullable = false, precision = 5, scale = 2)
    private BigDecimal participacion;

    @Column(name = "tipo_entidad_participe", nullable = false, length = 10)
    private String tipoEntidadParticipe;

    @Column(name = "estado", nullable = false, length = 15)
    private String estado;

    @Column(name = "version", nullable = false, precision = 9)
    private BigDecimal version;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Empresas getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Empresas idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Empresas getIdParticipe() {
        return idParticipe;
    }

    public void setIdParticipe(Empresas idParticipe) {
        this.idParticipe = idParticipe;
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

    public BigDecimal getVersion() {
        return version;
    }

    public void setVersion(BigDecimal version) {
        this.version = version;
    }

    public void setFechaActualizacion(Instant now) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setFechaActualizacion'");
    }

}