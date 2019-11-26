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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "val_vehicle")
@NamedQueries({
        @NamedQuery(name = "ValVehicle.findAll", query = "SELECT v FROM ValVehicle v"),
        @NamedQuery(name = "ValVehicle.findByVehicleId", query = "SELECT v FROM ValVehicle v WHERE v.vehicleId = :vehicleId"),
        @NamedQuery(name = "ValVehicle.findByLicensePlate", query = "SELECT v FROM ValVehicle v WHERE v.licensePlate = :licensePlate"),
        @NamedQuery(name = "ValVehicle.findByStatus", query = "SELECT v FROM ValVehicle v WHERE v.status = :status")})
public class ValVehicle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "vehicle_id")
    private Integer vehicleId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "license_plate")
    private String licensePlate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vehicleId", fetch = FetchType.LAZY)
    private Collection<ValBooking> valBookingCollection;
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ValPerson personId;

    public ValVehicle() {
    }

    public ValVehicle(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public ValVehicle(Integer vehicleId, String licensePlate, int status) {
        this.vehicleId = vehicleId;
        this.licensePlate = licensePlate;
        this.status = status;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Collection<ValBooking> getValBookingCollection() {
        return valBookingCollection;
    }

    public void setValBookingCollection(Collection<ValBooking> valBookingCollection) {
        this.valBookingCollection = valBookingCollection;
    }

    public ValPerson getPersonId() {
        return personId;
    }

    public void setPersonId(ValPerson personId) {
        this.personId = personId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vehicleId != null ? vehicleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ValVehicle)) {
            return false;
        }
        ValVehicle other = (ValVehicle) object;
        if ((this.vehicleId == null && other.vehicleId != null) || (this.vehicleId != null && !this.vehicleId.equals(other.vehicleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bo.edu.ucb.valeet.domain.ValVehicle[ vehicleId=" + vehicleId + " ]";
    }

}
