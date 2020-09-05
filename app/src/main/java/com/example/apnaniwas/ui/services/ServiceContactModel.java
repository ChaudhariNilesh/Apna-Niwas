package com.example.apnaniwas.ui.services;

public class ServiceContactModel {
    String service_id;
    String service_name;
    String service_contact_no;

    public ServiceContactModel(String service_id, String service_name, String contact_no) {
        this.service_id=service_id;
        this.service_name=service_name;
        this.service_contact_no=contact_no;
    }

    public String getService_id() {
        return service_id;
    }

    public void setService_id(String service_id) {
        this.service_id = service_id;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public String getService_contact_no() {
        return service_contact_no;
    }

    public void setService_contact_no(String service_contact_no) {
        this.service_contact_no = service_contact_no;
    }


}
