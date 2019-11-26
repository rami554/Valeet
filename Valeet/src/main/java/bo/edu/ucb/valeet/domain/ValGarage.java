package bo.edu.ucb.valeet.domain;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "val_garage")
@NamedQueries({
        @NamedQuery(name = "ValGarage.findAll", query = "SELECT v FROM ValGarage v"),
        @NamedQuery(name = "ValGarage.findByGarageId", query = "SELECT v FROM ValGarage v WHERE v.garageId = :garageId"),
        @NamedQuery(name = "ValGarage.findByName", query = "SELECT v FROM ValGarage v WHERE v.name = :name"),
        @NamedQuery(name = "ValGarage.findByAddress", query = "SELECT v FROM ValGarage v WHERE v.address = :address"),
        @NamedQuery(name = "ValGarage.findByZone", query = "SELECT v FROM ValGarage v WHERE v.zone = :zone"),
        @NamedQuery(name = "ValGarage.findByRate", query = "SELECT v FROM ValGarage v WHERE v.rate = :rate"),
        @NamedQuery(name = "ValGarage.findByLat", query = "SELECT v FROM ValGarage v WHERE v.lat = :lat"),
        @NamedQuery(name = "ValGarage.findByLong1", query = "SELECT v FROM ValGarage v WHERE v.long1 = :long1"),
        @NamedQuery(name = "ValGarage.findByTotalSpots", query = "SELECT v FROM ValGarage v WHERE v.totalSpots = :totalSpots"),
        @NamedQuery(name = "ValGarage.findByFreeSpots", query = "SELECT v FROM ValGarage v WHERE v.freeSpots = :freeSpots"),
        @NamedQuery(name = "ValGarage.findByOccupiedSpots", query = "SELECT v FROM ValGarage v WHERE v.occupiedSpots = :occupiedSpots"),
        @NamedQuery(name = "ValGarage.findByStatus", query = "SELECT v FROM ValGarage v WHERE v.status = :status")})
public class ValGarage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "garage_id")
    private Integer garageId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "address")
    private String address;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "zone")
    private String zone;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "rate")
    private BigDecimal rate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "lat")
    private double lat;
    @Basic(optional = false)
    @NotNull
    @Column(name = "long")
    private double long1;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total_spots")
    private int totalSpots;
    @Basic(optional = false)
    @NotNull
    @Column(name = "free_spots")
    private int freeSpots;
    @Basic(optional = false)
    @NotNull
    @Column(name = "occupied_spots")
    private int occupiedSpots;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ValPerson personId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "garageId", fetch = FetchType.LAZY)
    private Collection<ValBooking> valBookingCollection;

    public ValGarage() {
    }

    public ValGarage(Integer garageId) {
        this.garageId = garageId;
    }

    public ValGarage(Integer garageId, String name, String address, String zone, BigDecimal rate, double lat, double long1, int totalSpots, int freeSpots, int occupiedSpots, int status) {
        this.garageId = garageId;
        this.name = name;
        this.address = address;
        this.zone = zone;
        this.rate = rate;
        this.lat = lat;
        this.long1 = long1;
        this.totalSpots = totalSpots;
        this.freeSpots = freeSpots;
        this.occupiedSpots = occupiedSpots;
        this.status = status;
    }

    public Integer getGarageId() {
        return garageId;
    }

    public void setGarageId(Integer garageId) {
        this.garageId = garageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLong1() {
        return long1;
    }

    public void setLong1(double long1) {
        this.long1 = long1;
    }

    public int getTotalSpots() {
        return totalSpots;
    }

    public void setTotalSpots(int totalSpots) {
        this.totalSpots = totalSpots;
    }

    public int getFreeSpots() {
        return freeSpots;
    }

    public void setFreeSpots(int freeSpots) {
        this.freeSpots = freeSpots;
    }

    public int getOccupiedSpots() {
        return occupiedSpots;
    }

    public void setOccupiedSpots(int occupiedSpots) {
        this.occupiedSpots = occupiedSpots;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ValPerson getPersonId() {
        return personId;
    }

    public void setPersonId(ValPerson personId) {
        this.personId = personId;
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
        hash += (garageId != null ? garageId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ValGarage)) {
            return false;
        }
        ValGarage other = (ValGarage) object;
        if ((this.garageId == null && other.garageId != null) || (this.garageId != null && !this.garageId.equals(other.garageId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bo.edu.ucb.valeet.domain.ValGarage[ garageId=" + garageId + " ]";
    }

}
