// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.java.styles.app.web;

import com.java.styles.app.domain.Style;
import com.java.styles.app.domain.StyleClass;
import com.java.styles.app.web.StyleClassController;
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

privileged aspect StyleClassController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String StyleClassController.create(@Valid StyleClass styleClass, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, styleClass);
            return "styleclasses/create";
        }
        uiModel.asMap().clear();
        styleClass.persist();
        return "redirect:/styleclasses/" + encodeUrlPathSegment(styleClass.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String StyleClassController.createForm(Model uiModel) {
        populateEditForm(uiModel, new StyleClass());
        return "styleclasses/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String StyleClassController.show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("styleclass", StyleClass.findStyleClass(id));
        uiModel.addAttribute("itemId", id);
        return "styleclasses/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String StyleClassController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("styleclasses", StyleClass.findStyleClassEntries(firstResult, sizeNo));
            float nrOfPages = (float) StyleClass.countStyleClasses() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("styleclasses", StyleClass.findAllStyleClasses());
        }
        return "styleclasses/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String StyleClassController.update(@Valid StyleClass styleClass, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, styleClass);
            return "styleclasses/update";
        }
        uiModel.asMap().clear();
        styleClass.merge();
        return "redirect:/styleclasses/" + encodeUrlPathSegment(styleClass.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String StyleClassController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, StyleClass.findStyleClass(id));
        return "styleclasses/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String StyleClassController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        StyleClass styleClass = StyleClass.findStyleClass(id);
        styleClass.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/styleclasses";
    }
    
    void StyleClassController.populateEditForm(Model uiModel, StyleClass styleClass) {
        uiModel.addAttribute("styleClass", styleClass);
        uiModel.addAttribute("styles", Style.findAllStyles());
    }
    
    String StyleClassController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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