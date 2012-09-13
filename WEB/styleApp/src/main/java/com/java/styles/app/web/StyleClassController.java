package com.java.styles.app.web;

import com.java.styles.app.domain.StyleClass;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/styleclasses")
@Controller
@RooWebScaffold(path = "styleclasses", formBackingObject = StyleClass.class)
public class StyleClassController {
}
