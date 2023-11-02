package com.mycode.threeCS;

import java.util.ArrayList;
import java.util.List;

// Contact 实体类
 class Contact {
    private int id;
    private String name;
    private String phone;
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

// ContactService 类
public class ContactService {
    private List<Contact> contacts = new ArrayList<>();

    public boolean addContact(Contact contact) {
        if (isValidContact(contact)) {
            contact.setId(getNextAvailableId());
            contacts.add(contact);
            return true;
        }
        return false;
    }

    public boolean updateContact(Contact contact) {
        if (isValidContact(contact)) {
            for (Contact c : contacts) {
                if (c.getId() == contact.getId() || c.getName().equals(contact.getName())) {
                    c.setPhone(contact.getPhone());
                    c.setEmail(contact.getEmail());
                    return true;
                }
            }
        }
        return false;
    }

    public boolean deleteContact(int id) {
        for (Contact c : contacts) {
            if (c.getId() == id) {
                contacts.remove(c);
                return true;
            }
        }
        return false;
    }

    public List<Contact> searchContacts(String keyword) {
        List<Contact> results = new ArrayList<>();
        for (Contact c : contacts) {
            if (c.getName().contains(keyword) || c.getPhone().contains(keyword) || c.getEmail().contains(keyword)) {
                results.add(c);
            }
        }
        return results;
    }

    private boolean isValidContact(Contact contact) {
        // 验证逻辑
        return true;
    }

    private int getNextAvailableId() {
        // 返回可以使用的最小ID
        return 0;
    }
}