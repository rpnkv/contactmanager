package com.springapp.mvc.controller;

import com.springapp.mvc.domain.Contact;
import com.springapp.mvc.domain.ContactRecord;
import com.springapp.mvc.repository.ContactRepository;
import com.springapp.mvc.validation.ContactValidator;
import com.springapp.mvc.validation.RecordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ContactController {

    private ContactRepository contactRepository;
    private ContactValidator contactValidator;
    private RecordValidator recordValidator;

    @Autowired
    public ContactController(ContactRepository contactRepository, ContactValidator contactValidator,
                             RecordValidator recordValidator){
        this.contactRepository = contactRepository;
        this.contactValidator = contactValidator;
        this.recordValidator = recordValidator;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getContacts(Model model) {
        List<Contact> contacts = this.contactRepository.listAll();
        model.addAttribute("contacts", contacts);
        return "index";
    }

    @RequestMapping(value = "addContact", method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
    public String addBook(Model model) {
        model.addAttribute("contact", new Contact());

        return "addContact";
    }

    @RequestMapping(value = "addContact", method = RequestMethod.POST)
    @PreAuthorize("isAuthenticated()")
    public String addBook(@ModelAttribute("contact") Contact contact, BindingResult bindingResult){
        this.contactValidator.validate(contact, bindingResult);
        if(bindingResult.hasErrors()){
            return "addContact";
        }

        this.contactRepository.addBook(contact);
        return "redirect:/";
    }


    //js references
    @RequestMapping(value = "deleteBook/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('admin')")
    public String deleteBook(@PathVariable Integer id){
        this.contactRepository.removeBook(id);

        return "redirect:/";
    }

    @RequestMapping(value = "delContactRecord/contact_id={cid}+record_id={rid}", method = RequestMethod.GET)
    public String delContactRecord(@PathVariable Integer cid, @PathVariable int rid){
        contactRepository.deleteRecordById(rid, cid);
        return "redirect:/";
    }


    @RequestMapping(value = "addRecord/{id}", method = RequestMethod.GET)
    public String newContactRecord(Model model, @PathVariable int id){
        System.out.println("add new record to contact_id: " + id);

        model.addAttribute("contact_id", id);
        model.addAttribute("record", new ContactRecord());
        return "addRecord";
    }


    @RequestMapping(value = "addRecord/{id}", method = RequestMethod.POST)
    public String addRecord(@ModelAttribute("record") ContactRecord record, @PathVariable int id,
                            BindingResult bindingResult, Model model){

        recordValidator.validate(record,bindingResult);
        if(bindingResult.hasErrors()){
            return "addRecord";
        }

        Contact editable = contactRepository.getContactById(id);
        record.setContact(editable);
        editable.getContactRecords().add(record);
        contactRepository.updateContact(editable);

        return "redirect:/";
    }

    private ArrayList<ContactRecord> getDef(){
        ArrayList<ContactRecord> ww = new ArrayList<ContactRecord>(1);
        ww.add(new ContactRecord("email","example@gg.net"));
        ww.add(new ContactRecord("phone","+122890155"));
        return ww;
    }
}
