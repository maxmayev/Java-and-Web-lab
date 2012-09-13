// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.java.styles.app.web;

import com.java.styles.app.domain.Family;
import com.java.styles.app.web.FamilyController;
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

privileged aspect FamilyController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String FamilyController.create(@Valid Family family, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, family);
            return "familys/create";
        }
        uiModel.asMap().clear();
        family.persist();
        return "redirect:/familys/" + encodeUrlPathSegment(family.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String FamilyController.createForm(Model uiModel) {
        populateEditForm(uiModel, new Family());
        return "familys/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String FamilyController.show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("family", Family.findFamily(id));
        uiModel.addAttribute("itemId", id);
        return "familys/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String FamilyController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("familys", Family.findFamilyEntries(firstResult, sizeNo));
            float nrOfPages = (float) Family.countFamilys() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("familys", Family.findAllFamilys());
        }
        return "familys/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String FamilyController.update(@Valid Family family, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, family);
            return "familys/update";
        }
        uiModel.asMap().clear();
        family.merge();
        return "redirect:/familys/" + encodeUrlPathSegment(family.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String FamilyController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, Family.findFamily(id));
        return "familys/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String FamilyController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Family family = Family.findFamily(id);
        family.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/familys";
    }
    
    void FamilyController.populateEditForm(Model uiModel, Family family) {
        uiModel.addAttribute("family", family);
    }
    
    String FamilyController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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