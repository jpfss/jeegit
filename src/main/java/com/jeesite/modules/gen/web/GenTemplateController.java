package com.jeesite.modules.gen.web;

import com.jeesite.common.persistence.Page;
import com.jeesite.common.utils.StringUtils;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.gen.entity.GenTemplate;
import com.jeesite.modules.gen.service.GenTemplateService;
import com.jeesite.modules.sys.entity.User;
import com.jeesite.modules.sys.utils.UserUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping({ "${adminPath}/gen/genTemplate" })
public class GenTemplateController extends BaseController {
	@Autowired
	private GenTemplateService a;

	@ModelAttribute
	private GenTemplate a(@RequestParam(required = false) String id) {
		/* 40 */ if (StringUtils.isNotBlank(id)) {
			/* 41 */ return new GenTemplate(id);
		}
		/* 43 */ return new GenTemplate();
	}

	@RequiresPermissions({ "gen:genTemplate:view" })
	@RequestMapping({ "list", "" })
	private String a(GenTemplate genTemplate, HttpServletRequest request, HttpServletResponse response, Model model) {
		;
		User user;
		/* 51 */ if (!(user = UserUtils.getUser()).isAdmin()) {
			/* 52 */ genTemplate.setCreateBy(user);
		}
		/* 54 */ Page page = a.find(new Page(request, response), genTemplate);
		/* 55 */ model.addAttribute("page", page);
		/* 56 */ return "modules/gen/genTemplateList";
	}

	@RequiresPermissions({ "gen:genTemplate:view" })
	@RequestMapping({ "form" })
	private static String a(GenTemplate genTemplate, Model model) {
		/* 62 */ model.addAttribute("genTemplate", genTemplate);
		/* 63 */ return "modules/gen/genTemplateForm";
	}

	@RequiresPermissions({ "gen:genTemplate:edit" })
	@RequestMapping({ "save" })
	private String a(GenTemplate genTemplate, Model model, RedirectAttributes redirectAttributes) {
		/* 69 */ if (!beanValidator(model, genTemplate, new Class[0])) {
			/* 70 */ model = model;
			genTemplate = genTemplate;

			/* 2 */ model.addAttribute("genTemplate", genTemplate);
			return
			/* 3 */ "modules/gen/genTemplateForm";
		}
		/* 72 */ a.save(genTemplate);
		/* 73 */ addMessage(redirectAttributes, new String[] { "保存代码模板'" + genTemplate.getName() + "'成功" });
		/* 74 */ return "redirect:" + this.adminPath + "/gen/genTemplate/?repage";
	}

	@RequiresPermissions({ "gen:genTemplate:edit" })
	@RequestMapping({ "delete" })
	private String a(GenTemplate genTemplate, RedirectAttributes redirectAttributes) {
		/* 80 */ a.delete(genTemplate);
		/* 81 */ addMessage(redirectAttributes, new String[] { "删除代码模板成功" });
		/* 82 */ return "redirect:" + this.adminPath + "/gen/genTemplate/?repage";
	}
}
