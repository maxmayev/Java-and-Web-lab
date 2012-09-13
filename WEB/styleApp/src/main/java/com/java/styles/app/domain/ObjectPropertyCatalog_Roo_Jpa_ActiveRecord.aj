// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.java.styles.app.domain;

import com.java.styles.app.domain.ObjectPropertyCatalog;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect ObjectPropertyCatalog_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager ObjectPropertyCatalog.entityManager;
    
    public static final EntityManager ObjectPropertyCatalog.entityManager() {
        EntityManager em = new ObjectPropertyCatalog().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long ObjectPropertyCatalog.countObjectPropertyCatalogs() {
        return entityManager().createQuery("SELECT COUNT(o) FROM ObjectPropertyCatalog o", Long.class).getSingleResult();
    }
    
    public static List<ObjectPropertyCatalog> ObjectPropertyCatalog.findAllObjectPropertyCatalogs() {
        return entityManager().createQuery("SELECT o FROM ObjectPropertyCatalog o", ObjectPropertyCatalog.class).getResultList();
    }
    
    public static ObjectPropertyCatalog ObjectPropertyCatalog.findObjectPropertyCatalog(Long id) {
        if (id == null) return null;
        return entityManager().find(ObjectPropertyCatalog.class, id);
    }
    
    public static List<ObjectPropertyCatalog> ObjectPropertyCatalog.findObjectPropertyCatalogEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM ObjectPropertyCatalog o", ObjectPropertyCatalog.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void ObjectPropertyCatalog.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void ObjectPropertyCatalog.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            ObjectPropertyCatalog attached = ObjectPropertyCatalog.findObjectPropertyCatalog(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void ObjectPropertyCatalog.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void ObjectPropertyCatalog.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public ObjectPropertyCatalog ObjectPropertyCatalog.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        ObjectPropertyCatalog merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}