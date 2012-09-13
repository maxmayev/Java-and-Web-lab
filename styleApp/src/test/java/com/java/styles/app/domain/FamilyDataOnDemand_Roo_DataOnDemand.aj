// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.java.styles.app.domain;

import com.java.styles.app.domain.Family;
import com.java.styles.app.domain.FamilyDataOnDemand;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.stereotype.Component;

privileged aspect FamilyDataOnDemand_Roo_DataOnDemand {
    
    declare @type: FamilyDataOnDemand: @Component;
    
    private Random FamilyDataOnDemand.rnd = new SecureRandom();
    
    private List<Family> FamilyDataOnDemand.data;
    
    public Family FamilyDataOnDemand.getNewTransientFamily(int index) {
        Family obj = new Family();
        setName(obj, index);
        return obj;
    }
    
    public void FamilyDataOnDemand.setName(Family obj, int index) {
        String name = "name_" + index;
        obj.setName(name);
    }
    
    public Family FamilyDataOnDemand.getSpecificFamily(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Family obj = data.get(index);
        Long id = obj.getId();
        return Family.findFamily(id);
    }
    
    public Family FamilyDataOnDemand.getRandomFamily() {
        init();
        Family obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return Family.findFamily(id);
    }
    
    public boolean FamilyDataOnDemand.modifyFamily(Family obj) {
        return false;
    }
    
    public void FamilyDataOnDemand.init() {
        int from = 0;
        int to = 10;
        data = Family.findFamilyEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Family' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<Family>();
        for (int i = 0; i < 10; i++) {
            Family obj = getNewTransientFamily(i);
            try {
                obj.persist();
            } catch (ConstraintViolationException e) {
                StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getConstraintDescriptor()).append(":").append(cv.getMessage()).append("=").append(cv.getInvalidValue()).append("]");
                }
                throw new RuntimeException(msg.toString(), e);
            }
            obj.flush();
            data.add(obj);
        }
    }
    
}
