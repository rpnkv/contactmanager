package com.springapp.mvc.domain;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "contact")
public class Contact {

    private int iD;
    private String firstname;
    private String lastname;
    private List<ContactRecord> contactRecords = new ArrayList<ContactRecord>(10);

    public Contact() {
    }

    public Contact(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Contact(String firstname, String lastname, List<ContactRecord> contactRecords) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.contactRecords = contactRecords;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id", unique = true,nullable = false)
    public int getiD() {return iD;}
    public void setiD(Integer id){this.iD = id;}

    @Column(name = "firstname",unique = false,nullable = false,length = 20)
    public String getFirstname() {return firstname;}
    public void setFirstname(String firstname1){firstname=firstname1;}

    @Column(name = "lastname",unique = false,nullable = true, length = 20)
    public String getLastname() {return lastname;}
    public void setLastname(String lastname1){lastname=lastname1;}

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "contact"/*,orphanRemoval = true, cascade = CascadeType.ALL*/)
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE})
    public List<ContactRecord> getContactRecords() {return contactRecords;}
    public void setContactRecords(List<ContactRecord> records){contactRecords = records;}

    @Override
    public String toString() {
        return "contact id: " + iD +", firstname: " + firstname + ", lastname: " + lastname;
    }
}
