// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.java.styles.app.web;

import com.java.styles.app.domain.ObjectPropertyCatalog;
import com.java.styles.app.domain.StyleClass;
import com.java.styles.app.web.ObjectPropertyCatalogController;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect ObjectPropertyCatalogController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String ObjectPropertyCatalogController.create(@Valid ObjectPropertyCatalog objectPropertyCatalog, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, objectPropertyCatalog);
            return "objectpropertycatalogs/create";
        }
        uiModel.asMap().clear();
        objectPropertyCatalog.persist();
        return "redirect:/objectpropertycatalogs/" + encodeUrlPathSegment(objectPropertyCatalog.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String ObjectPropertyCatalogController.createForm(Model uiModel) {
        populateEditForm(uiModel, new ObjectPropertyCatalog());
        return "objectpropertycatalogs/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String ObjectPropertyCatalogController.show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("objectpropertycatalog", ObjectPropertyCatalog.findObjectPropertyCatalog(id));
        uiModel.addAttribute("itemId", id);
        return "objectpropertycatalogs/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String ObjectPropertyCatalogController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("objectpropertycatalogs", ObjectPropertyCatalog.findObjectPropertyCatalogEntries(firstResult, sizeNo));
            float nrOfPages = (float) ObjectPropertyCatalog.countObjectPropertyCatalogs() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("objectpropertycatalogs", ObjectPropertyCatalog.findAllObjectPropertyCatalogs());
        }
        return "objectpropertycatalogs/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String ObjectPropertyCatalogController.update(@Valid ObjectPropertyCatalog objectPropertyCatalog, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, objectPropertyCatalog);
            return "objectpropertycatalogs/update";
        }
        uiModel.asMap().clear();
        objectPropertyCatalog.merge();
        return "redirect:/objectpropertycatalogs/" + encodeUrlPathSegment(objectPropertyCatalog.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String ObjectPropertyCatalogController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, ObjectPropertyCatalog.findObjectPropertyCatalog(id));
        return "objectpropertycatalogs/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String ObjectPropertyCatalogController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        ObjectPropertyCatalog objectPropertyCatalog = ObjectPropertyCatalog.findObjectPropertyCatalog(id);
        objectPropertyCatalog.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/objectpropertycatalogs";
    }
    
    void ObjectPropertyCatalogController.populateEditForm(Model uiModel, ObjectPropertyCatalog objectPropertyCatalog) {
        uiModel.addAttribute("objectPropertyCatalog", objectPropertyCatalog);
        uiModel.addAttribute("styleclasses", StyleClass.findAllStyleClasses());
    }
    
    String ObjectPropertyCatalogController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        } catch (UnsupportedEncodingException uee) {}
        return pathSegment;
    }
    
}