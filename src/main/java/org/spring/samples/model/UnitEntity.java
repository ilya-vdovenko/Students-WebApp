package org.spring.samples.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/**
 * Simple JavaBean domain object adds institute property to BaseEntity.
 * Used as a base class for objects needing these properties.
 *
 **/

@MappedSuperclass
public class UnitEntity extends BaseEntity {

    @NotEmpty
    @Column(name = "title")
    private String title;

    @NotEmpty
    @Column(name = "information")
    private String information;

    @NotEmpty
    @OneToOne
    @JoinColumn(name = "boss")
    private Employee boss;

    @NotEmpty
    @Column(name = "contact_inf")
    private String contact_inf;

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public Employee getBoss() {
        return boss;
    }

    public void setBoss(Employee boss) {
        this.boss = boss;
    }

    public String getContact_inf() {
        return contact_inf;
    }

    public void setContact_inf(String contact_inf) {
        this.contact_inf = contact_inf;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}