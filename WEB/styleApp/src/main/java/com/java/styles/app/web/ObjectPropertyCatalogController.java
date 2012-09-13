package com.java.styles.app.web;

import com.java.styles.app.domain.ObjectPropertyCatalog;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/objectpropertycatalogs")
@Controller
@RooWebScaffold(path = "objectpropertycatalogs", formBackingObject = ObjectPropertyCatalog.class)
public class ObjectPropertyCatalogController {
}
