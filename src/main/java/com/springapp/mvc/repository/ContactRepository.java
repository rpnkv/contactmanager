package com.springapp.mvc.repository;

import com.springapp.mvc.domain.Contact;
import com.springapp.mvc.domain.ContactRecord;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ContactRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public void addBook(Contact contact){
        this.sessionFactory.getCurrentSession().save(contact);
    }

    public List<Contact> listAll(){
        List<Contact> contacts = this.sessionFactory.getCurrentSession().createQuery("from Contact").list();
        Hibernate.initialize(contacts);
        return contacts;
    }

    public void deleteRecordById(int rId, int cId){
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Contact operable = (Contact) session.get(Contact.class,cId);
        ContactRecord removable = (ContactRecord) session.get(ContactRecord.class,operable.getContactRecords().get(rId).getRecordId());

        operable.getContactRecords().remove(removable);
        session.delete(removable);

        session.saveOrUpdate(operable);
        session.getTransaction().commit();

    }

    public void removeBook(Integer iD){
        Contact contact = (Contact)this.sessionFactory.getCurrentSession().load(Contact.class, iD);
        if (null!=contact){
            this.sessionFactory.getCurrentSession().delete(contact);
        }
    }

    public Contact getContactById(Integer iD){
        return (Contact) sessionFactory.getCurrentSession().get(Contact.class,iD);
    }

    public void updateContact(Contact contact){
        sessionFactory.getCurrentSession().update(contact);
        sessionFactory.getCurrentSession().flush();
    }
}

