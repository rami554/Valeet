package bo.edu.ucb.valeet.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ignacio
 */
@Entity
@Table(name = "val_booking")
@NamedQueries({
        @NamedQuery(name = "ValBooking.findAll", query = "SELECT v FROM ValBooking v"),
        @NamedQuery(name = "ValBooking.findByBookingId", query = "SELECT v FROM ValBooking v WHERE v.bookingId = :bookingId"),
        @NamedQuery(name = "ValBooking.findByStartDate", query = "SELECT v FROM ValBooking v WHERE v.startDate = :startDate"),
        @NamedQuery(name = "ValBooking.findByEndDate", query = "SELECT v FROM ValBooking v WHERE v.endDate = :endDate"),
        @NamedQuery(name = "ValBooking.findByStartTime", query = "SELECT v FROM ValBooking v WHERE v.startTime = :startTime"),
        @NamedQuery(name = "ValBooking.findByEndTime", query = "SELECT v FROM ValBooking v WHERE v.endTime = :endTime"),
        @NamedQuery(name = "ValBooking.findByTotalTime", query = "SELECT v FROM ValBooking v WHERE v.totalTime = :totalTime"),
        @NamedQuery(name = "ValBooking.findByPenalty", query = "SELECT v FROM ValBooking v WHERE v.penalty = :penalty")})
public class ValBooking implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "booking_id")
    private Integer bookingId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "start_time")
    @Temporal(TemporalType.TIME)
    private Date startTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "end_time")
    @Temporal(TemporalType.TIME)
    private Date endTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total_time")
    private int totalTime;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "penalty")
    private BigDecimal penalty;
    @JoinColumn(name = "idval_booking_status", referencedColumnName = "idval_booking_status")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ValBookingStatus idvalBookingStatus;
    @JoinColumn(name = "garage_id", referencedColumnName = "garage_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ValGarage garageId;
    @JoinColumn(name = "vehicle_id", referencedColumnName = "vehicle_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ValVehicle vehicleId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bookingId", fetch = FetchType.LAZY)
    private Collection<ValBilling> valBillingCollection;

    public ValBooking() {
    }

    public ValBooking(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public ValBooking(Integer bookingId, Date startDate, Date endDate, Date startTime, Date endTime, int totalTime, BigDecimal penalty) {
        this.bookingId = bookingId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalTime = totalTime;
        this.penalty = penalty;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    public BigDecimal getPenalty() {
        return penalty;
    }

    public void setPenalty(BigDecimal penalty) {
        this.penalty = penalty;
    }

    public ValBookingStatus getIdvalBookingStatus() {
        return idvalBookingStatus;
    }

    public void setIdvalBookingStatus(ValBookingStatus idvalBookingStatus) {
        this.idvalBookingStatus = idvalBookingStatus;
    }

    public ValGarage getGarageId() {
        return garageId;
    }

    public void setGarageId(ValGarage garageId) {
        this.garageId = garageId;
    }

    public ValVehicle getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(ValVehicle vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Collection<ValBilling> getValBillingCollection() {
        return valBillingCollection;
    }

    public void setValBillingCollection(Collection<ValBilling> valBillingCollection) {
        this.valBillingCollection = valBillingCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bookingId != null ? bookingId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ValBooking)) {
            return false;
        }
        ValBooking other = (ValBooking) object;
        if ((this.bookingId == null && other.bookingId != null) || (this.bookingId != null && !this.bookingId.equals(other.bookingId))) {
            return false;
        }
        return true;
    }


    @Override
    public String toString() {
        return "bo.edu.ucb.valeet.domain.ValBooking[ bookingId=" + bookingId + " ]";
    }

}
