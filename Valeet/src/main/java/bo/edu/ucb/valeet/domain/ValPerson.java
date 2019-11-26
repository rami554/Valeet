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
@Table(name = "val_person")
@NamedQueries({
        @NamedQuery(name = "ValPerson.findAll", query = "SELECT v FROM ValPerson v"),
        @NamedQuery(name = "ValPerson.findByPersonId", query = "SELECT v FROM ValPerson v WHERE v.personId = :personId"),
        @NamedQuery(name = "ValPerson.findByFirstName", query = "SELECT v FROM ValPerson v WHERE v.firstName = :firstName"),
        @NamedQuery(name = "ValPerson.findByFirstLastName", query = "SELECT v FROM ValPerson v WHERE v.firstLastName = :firstLastName"),
        @NamedQuery(name = "ValPerson.findBySecondLastName", query = "SELECT v FROM ValPerson v WHERE v.secondLastName = :secondLastName"),
        @NamedQuery(name = "ValPerson.findByEmail", query = "SELECT v FROM ValPerson v WHERE v.email = :email"),
        @NamedQuery(name = "ValPerson.findByTelegramId", query = "SELECT v FROM ValPerson v WHERE v.telegramId = :telegramId"),
        @NamedQuery(name = "ValPerson.findByPersonalId", query = "SELECT v FROM ValPerson v WHERE v.personalId = :personalId"),
        @NamedQuery(name = "ValPerson.findByParkingAdmin", query = "SELECT v FROM ValPerson v WHERE v.parkingAdmin = :parkingAdmin"),
        @NamedQuery(name = "ValPerson.findByStatus", query = "SELECT v FROM ValPerson v WHERE v.status = :status")})
public class ValPerson implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "person_id")
    private Integer personId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "first_name")
    private String firstName;
    @Size(max = 50)
    @Column(name = "first_last_name")
    private String firstLastName;
    @Size(max = 50)
    @Column(name = "second_last_name")
    private String secondLastName;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Column(name = "telegram_id")
    private int telegramId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "personal_id")
    private String personalId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "parking_admin")
    private int parkingAdmin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personId", fetch = FetchType.LAZY)
    private Collection<ValGarage> valGarageCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personId", fetch = FetchType.LAZY)
    private Collection<ValVehicle> valVehicleCollection;

    public ValPerson() {
    }

    public ValPerson(Integer personId) {
        this.personId = personId;
    }

    public ValPerson(Integer personId, String firstName, String email, int telegramId, String personalId, int parkingAdmin, int status) {
        this.personId = personId;
        this.firstName = firstName;
        this.email = email;
        this.telegramId = telegramId;
        this.personalId = personalId;
        this.parkingAdmin = parkingAdmin;
        this.status = status;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstLastName() {
        return firstLastName;
    }

    public void setFirstLastName(String firstLastName) {
        this.firstLastName = firstLastName;
    }

    public String getSecondLastName() {
        return secondLastName;
    }

    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelegramId() {
        return telegramId;
    }

    public void setTelegramId(int telegramId) {
        this.telegramId = telegramId;
    }

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }

    public int getParkingAdmin() {
        return parkingAdmin;
    }

    public void setParkingAdmin(int parkingAdmin) {
        this.parkingAdmin = parkingAdmin;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Collection<ValGarage> getValGarageCollection() {
        return valGarageCollection;
    }

    public void setValGarageCollection(Collection<ValGarage> valGarageCollection) {
        this.valGarageCollection = valGarageCollection;
    }

    public Collection<ValVehicle> getValVehicleCollection() {
        return valVehicleCollection;
    }

    public void setValVehicleCollection(Collection<ValVehicle> valVehicleCollection) {
        this.valVehicleCollection = valVehicleCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personId != null ? personId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ValPerson)) {
            return false;
        }
        ValPerson other = (ValPerson) object;
        if ((this.personId == null && other.personId != null) || (this.personId != null && !this.personId.equals(other.personId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bo.edu.ucb.valeet.domain.ValPerson[ personId=" + personId + " ]";
    }

}
