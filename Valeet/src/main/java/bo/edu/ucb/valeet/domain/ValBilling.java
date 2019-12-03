package bo.edu.ucb.valeet.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ignacio
 */
@Entity
@Table(name = "val_billing")
@NamedQueries({
        @NamedQuery(name = "ValBilling.findAll", query = "SELECT v FROM ValBilling v"),
        @NamedQuery(name = "ValBilling.findByBillingId", query = "SELECT v FROM ValBilling v WHERE v.billingId = :billingId"),
        @NamedQuery(name = "ValBilling.findByName", query = "SELECT v FROM ValBilling v WHERE v.name = :name"),
        @NamedQuery(name = "ValBilling.findByNit", query = "SELECT v FROM ValBilling v WHERE v.nit = :nit"),
        @NamedQuery(name = "ValBilling.findByTotalDue", query = "SELECT v FROM ValBilling v WHERE v.totalDue = :totalDue"),
        @NamedQuery(name = "ValBilling.findByAuthNumber", query = "SELECT v FROM ValBilling v WHERE v.authNumber = :authNumber"),
        @NamedQuery(name = "ValBilling.findByControlNumber", query = "SELECT v FROM ValBilling v WHERE v.controlNumber = :controlNumber"),
        @NamedQuery(name = "ValBilling.findByBillingDate", query = "SELECT v FROM ValBilling v WHERE v.billingDate = :billingDate"),
        @NamedQuery(name = "ValBilling.findByPaymentStatus", query = "SELECT v FROM ValBilling v WHERE v.paymentStatus = :paymentStatus")})
public class ValBilling implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "billing_id")
    private Integer billingId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nit")
    private long nit;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "total_due")
    private BigDecimal totalDue;
    @Basic(optional = false)
    @NotNull
    @Column(name = "auth_number")
    private long authNumber;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "control_number")
    private String controlNumber;
    @Basic(optional = false)
    @NotNull
    @Column(name = "billing_date")
    @Temporal(TemporalType.DATE)
    private Date billingDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "payment_status")
    private int paymentStatus;
    @JoinColumn(name = "booking_id", referencedColumnName = "booking_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ValBooking bookingId;

    public ValBilling() {
    }

    public ValBilling(Integer billingId) {
        this.billingId = billingId;
    }

    public ValBilling(Integer billingId, String name, long nit, BigDecimal totalDue, long authNumber, String controlNumber, Date billingDate, int paymentStatus) {
        this.billingId = billingId;
        this.name = name;
        this.nit = nit;
        this.totalDue = totalDue;
        this.authNumber = authNumber;
        this.controlNumber = controlNumber;
        this.billingDate = billingDate;
        this.paymentStatus = paymentStatus;
    }

    public Integer getBillingId() {
        return billingId;
    }

    public void setBillingId(Integer billingId) {
        this.billingId = billingId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getNit() {
        return nit;
    }

    public void setNit(long nit) {
        this.nit = nit;
    }

    public BigDecimal getTotalDue() {
        return totalDue;
    }

    public void setTotalDue(BigDecimal totalDue) {
        this.totalDue = totalDue;
    }

    public long getAuthNumber() {
        return authNumber;
    }

    public void setAuthNumber(long authNumber) {
        this.authNumber = authNumber;
    }

    public String getControlNumber() {
        return controlNumber;
    }

    public void setControlNumber(String controlNumber) {
        this.controlNumber = controlNumber;
    }

    public Date getBillingDate() {
        return billingDate;
    }

    public void setBillingDate(Date billingDate) {
        this.billingDate = billingDate;
    }

    public int getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(int paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public ValBooking getBookingId() {
        return bookingId;
    }

    public void setBookingId(ValBooking bookingId) {
        this.bookingId = bookingId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (billingId != null ? billingId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ValBilling)) {
            return false;
        }
        ValBilling other = (ValBilling) object;
        if ((this.billingId == null && other.billingId != null) || (this.billingId != null && !this.billingId.equals(other.billingId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bo.edu.ucb.valeet.domain.ValBilling[ billingId=" + billingId + " ]";
    }

}
