package com.java.styles.app.web;

import com.java.styles.app.domain.Style;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/styles")
@Controller
@RooWebScaffold(path = "styles", formBackingObject = Style.class)
public class StyleController {
}
