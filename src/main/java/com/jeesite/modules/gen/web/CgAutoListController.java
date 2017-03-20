package com.jeesite.modules.gen.web;

import com.jeesite.common.utils.StringUtils;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.gen.entity.GenScheme;
import com.jeesite.modules.gen.service.CgAutoListService;
import com.jeesite.modules.gen.service.GenSchemeService;
import com.jeesite.modules.gen.service.GenTableService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({ "${adminPath}/gen/cgAutoList" })
public class CgAutoListController extends BaseController {
	/* 45 */ private static Logger a = Logger.getLogger(CgAutoListController.class);
	@Autowired
	private GenSchemeService b;
	@Autowired
	private GenTableService c;
	@Autowired
	private CgAutoListService d;

	@ModelAttribute
	/* 54 */ private GenScheme a(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			/* 55 */ return new GenScheme(id);
		}
		/* 57 */ return new GenScheme();
	}

	@RequestMapping({ "list" })
	private void a(GenScheme genScheme, HttpServletResponse response) {
		/* 72 */ long start = System.currentTimeMillis();

		/* 77 */ new com.jeesite.modules.gen.template.a();

		/* 79 */ com.jeesite.modules.gen.util.a.a(genScheme);

		try {
			/* 85 */ response.setContentType("text/html");
			/* 86 */ response.setHeader("Cache-Control", "no-store");
			/* 87 */ (response.getWriter()).println("");
			/* 88 */
			/* 89 */ (response.getWriter()).flush();
		} catch (IOException localIOException) {

		}
		/* 93 */ long end = System.currentTimeMillis();
		/* 94 */ a.debug("动态列表生成耗时：" + (end - start) + " ms");
	}

	@RequestMapping({ "test", "" })
	private static ModelAndView a() {
		/* 100 */ return new ModelAndView("com/jeesite/modules/gen/template/viewList");
	}
}
