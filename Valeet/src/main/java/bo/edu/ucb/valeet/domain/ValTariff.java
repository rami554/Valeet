package bo.edu.ucb.valeet.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ignacio
 */
@Entity
@Table(name = "val_tariff")
@NamedQueries({
        @NamedQuery(name = "ValTariff.findAll", query = "SELECT v FROM ValTariff v"),
        @NamedQuery(name = "ValTariff.findByTariffId", query = "SELECT v FROM ValTariff v WHERE v.tariffId = :tariffId"),
        @NamedQuery(name = "ValTariff.findByDescription", query = "SELECT v FROM ValTariff v WHERE v.description = :description"),
        @NamedQuery(name = "ValTariff.findByRate", query = "SELECT v FROM ValTariff v WHERE v.rate = :rate"),
        @NamedQuery(name = "ValTariff.findByStatus", query = "SELECT v FROM ValTariff v WHERE v.status = :status")})
public class ValTariff implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "tariff_id")
    private Integer tariffId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "description")
    private String description;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "rate")
    private BigDecimal rate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;

    public ValTariff() {
    }

    public ValTariff(Integer tariffId) {
        this.tariffId = tariffId;
    }

    public ValTariff(Integer tariffId, String description, BigDecimal rate, int status) {
        this.tariffId = tariffId;
        this.description = description;
        this.rate = rate;
        this.status = status;
    }

    public Integer getTariffId() {
        return tariffId;
    }

    public void setTariffId(Integer tariffId) {
        this.tariffId = tariffId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tariffId != null ? tariffId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ValTariff)) {
            return false;
        }
        ValTariff other = (ValTariff) object;
        if ((this.tariffId == null && other.tariffId != null) || (this.tariffId != null && !this.tariffId.equals(other.tariffId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bo.edu.ucb.valeet.domain.ValTariff[ tariffId=" + tariffId + " ]";
    }

}
