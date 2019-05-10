package org.spring.samples.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;

@MappedSuperclass
public class UnitEntity extends BaseEntity {

    @NotEmpty
    @Column(name = "information")
    private String information;

    @NotEmpty
    @Column(name = "boss")
    private String boss;

    @NotEmpty
    @Column(name = "contact_inf")
    private String contact_inf;

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getBoss() {
        return boss;
    }

    public void setBoss(String boss) {
        this.boss = boss;
    }

    public String getContact_inf() {
        return contact_inf;
    }

    public void setContact_inf(String contact_inf) {
        this.contact_inf = contact_inf;
    }
}