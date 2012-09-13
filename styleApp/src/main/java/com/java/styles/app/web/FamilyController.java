package com.java.styles.app.web;

import com.java.styles.app.domain.Family;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/familys")
@Controller
@RooWebScaffold(path = "familys", formBackingObject = Family.class)
public class FamilyController {
}
