// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.java.styles.app.domain;

import com.java.styles.app.domain.StyleClass;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

privileged aspect StyleClass_Roo_Jpa_Entity {
    
    declare @type: StyleClass: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long StyleClass.id;
    
    @Version
    @Column(name = "version")
    private Integer StyleClass.version;
    
    public Long StyleClass.getId() {
        return this.id;
    }
    
    public void StyleClass.setId(Long id) {
        this.id = id;
    }
    
    public Integer StyleClass.getVersion() {
        return this.version;
    }
    
    public void StyleClass.setVersion(Integer version) {
        this.version = version;
    }
    
}
