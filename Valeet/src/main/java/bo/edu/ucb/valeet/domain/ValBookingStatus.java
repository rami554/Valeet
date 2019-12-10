package bo.edu.ucb.valeet.domain;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ignacio
 */
@Entity
@Table(name = "val_booking_status")
@NamedQueries({
        @NamedQuery(name = "ValBookingStatus.findAll", query = "SELECT v FROM ValBookingStatus v"),
        @NamedQuery(name = "ValBookingStatus.findByIdvalBookingStatus", query = "SELECT v FROM ValBookingStatus v WHERE v.idvalBookingStatus = :idvalBookingStatus"),
        @NamedQuery(name = "ValBookingStatus.findByStatus", query = "SELECT v FROM ValBookingStatus v WHERE v.status = :status")})
public class ValBookingStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idval_booking_status")
    private Integer idvalBookingStatus;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "status")
    private String status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idvalBookingStatus", fetch = FetchType.LAZY)
    private Collection<ValBooking> valBookingCollection;

    public ValBookingStatus() {
    }

    public ValBookingStatus(Integer idvalBookingStatus) {
        this.idvalBookingStatus = idvalBookingStatus;
    }

    public ValBookingStatus(Integer idvalBookingStatus, String status) {
        this.idvalBookingStatus = idvalBookingStatus;
        this.status = status;
    }

    public Integer getIdvalBookingStatus() {
        return idvalBookingStatus;
    }

    public void setIdvalBookingStatus(Integer idvalBookingStatus) {
        this.idvalBookingStatus = idvalBookingStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Collection<ValBooking> getValBookingCollection() {
        return valBookingCollection;
    }

    public void setValBookingCollection(Collection<ValBooking> valBookingCollection) {
        this.valBookingCollection = valBookingCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idvalBookingStatus != null ? idvalBookingStatus.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ValBookingStatus)) {
            return false;
        }
        ValBookingStatus other = (ValBookingStatus) object;
        if ((this.idvalBookingStatus == null && other.idvalBookingStatus != null) || (this.idvalBookingStatus != null && !this.idvalBookingStatus.equals(other.idvalBookingStatus))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bo.edu.ucb.valeet.domain.ValBookingStatus[ idvalBookingStatus=" + idvalBookingStatus + " ]";
    }

}
