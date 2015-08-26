package com.springapp.mvc.domain;

import javax.persistence.*;

@Entity
@Table(name = "records")
public class ContactRecord{

    private Integer recordId;
    private Contact contact;
    private String type;
    private String value;

    public ContactRecord() {
    }

    public ContactRecord(String type, String value) {
        this.type = type;
        this.value = value;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id")
    public Integer getRecordId() {return recordId;}
    public void setRecordId(Integer recordId) {this.recordId = recordId;}

    @ManyToOne(optional = false)
    @JoinColumn(name = "contact_id", nullable = true)
    public Contact getContact(){return contact;}
    public void setContact(Contact contact){this.contact = contact;}

    @Column(name = "type", length = 20)
    public String getType() {return type;}
    public void setType(String type){this.type = type;}

    @Column(name = "value", length = 20)
    public String getValue() {return value;}
    public void setValue(String value) {this.value = value;}
}
