package com.java.styles.app.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class ObjectPropertyCatalog {

    @NotNull
    @Size(min = 2)
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<StyleClass> styleClasses = new HashSet<StyleClass>();
}
