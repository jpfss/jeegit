package com.jeesite.modules.gen.web;

import com.google.common.collect.Lists;
import com.jeesite.common.persistence.Page;
import com.jeesite.common.utils.StringUtils;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.gen.entity.GenScheme;
import com.jeesite.modules.gen.service.GenSchemeService;
import com.jeesite.modules.gen.service.GenTableService;
import com.jeesite.modules.gen.util.a;
import com.jeesite.modules.sys.entity.Menu;
import com.jeesite.modules.sys.entity.User;
import com.jeesite.modules.sys.service.SystemService;
import com.jeesite.modules.sys.utils.UserUtils;
import java.util.List;
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
@RequestMapping({ "${adminPath}/gen/genScheme" })
public class GenSchemeController extends BaseController {
	@Autowired
	public GenSchemeService genSchemeService;
	@Autowired
	public GenTableService genTableService;
	@Autowired
	public SystemService systemService;

	@ModelAttribute
	private GenScheme a(@RequestParam(required = false) String id) {
		/* 54 */ if (StringUtils.isNotBlank(id)) {
			/* 55 */ return this.genSchemeService.get(id);
		}
		/* 57 */ return new GenScheme();
	}

	@RequiresPermissions({ "gen:genScheme:view" })
	@RequestMapping({ "list", "" })
	private String a(GenScheme genScheme, HttpServletRequest request, HttpServletResponse response, Model model) {
		;
		User user;
		/* 65 */ if (!(user = UserUtils.getUser()).isAdmin()) {
			/* 66 */ genScheme.setCreateBy(user);
		}
		/* 68 */Page page = this.genSchemeService.find(new Page(request, response), genScheme);
		/* 69 */ model.addAttribute("page", page);

		/* 71 */ return "modules/gen/genSchemeList";
	}

	@RequiresPermissions({ "gen:genScheme:view" })
	@RequestMapping({ "form" })
	private String a(GenScheme genScheme, Model model) {
		/* 77 */ if (StringUtils.isBlank(genScheme.getPackageName())) {
			/* 78 */ genScheme.setPackageName("com.jeesite.modules");
		}

		/* 83 */ model.addAttribute("genScheme", genScheme);
		/* 84 */ model.addAttribute("config", a.b());
		/* 85 */ model.addAttribute("tableList", this.genTableService.findAll());
		/* 86 */ return "modules/gen/genSchemeForm";
	}

	@RequiresPermissions({ "gen:genScheme:edit" })
	@RequestMapping({ "save" })
	private String a(GenScheme genScheme, Model model, RedirectAttributes redirectAttributes) {

		/* 97 */ addMessage(redirectAttributes, new String[] { "操作生成方案'" + genScheme.getName() + "'成功<br/>" });
		/* 98 */ return "redirect:" + this.adminPath + "/gen/genScheme/?repage";
	}

	@RequiresPermissions({ "gen:genScheme:edit" })
	@RequestMapping({ "delete" })
	private String a(GenScheme genScheme, RedirectAttributes redirectAttributes) {
		/* 104 */ this.genSchemeService.delete(genScheme);
		/* 105 */ addMessage(redirectAttributes, new String[] { "删除生成方案成功" });
		/* 106 */ return "redirect:" + this.adminPath + "/gen/genScheme/?repage";
	}

	@RequestMapping({ "menuForm" })
	private String a(String gen_table_id, Menu menu, Model model) {
		/* 111 */ if ((menu.getParent() == null) || (menu.getParent().getId() == null)) {
			/* 112 */ menu.setParent(new Menu(Menu.getRootId()));
		}
		/* 114 */ menu.setParent(this.systemService.getMenu(menu.getParent().getId()));

		/* 116 */ if (StringUtils.isBlank(menu.getId())) {
			/* 117 */ List<Menu> list = (List) Lists.newArrayList();
			/* 118 */ List<Menu> sourcelist = this.systemService.findAllMenu();
			/* 119 */ Menu.sortList(list, sourcelist, menu.getParentId(), false);
			/* 120 */ if (list.size() > 0) {
				/* 121 */ menu.setSort(Integer.valueOf(((Menu) list.get(list.size() - 1)).getSort().intValue() + 30));
			}
		}

		GenScheme genScheme;
		/* 126 */ if ((genScheme = this.genSchemeService.findUniqueByProperty("gen_table_id", gen_table_id)) != null) {
			/* 127 */ menu.setName(genScheme.getFunctionName());
		}

		/* 130 */ model.addAttribute("menu", menu);
		/* 131 */ model.addAttribute("gen_table_id", gen_table_id);
		/* 132 */ return "modules/gen/genMenuForm";
	}

	@RequestMapping({ "createMenu" })
	private String a(String gen_table_id, Menu menu, RedirectAttributes redirectAttributes) {
		GenScheme genScheme;
		/* 140 */ if ((genScheme = this.genSchemeService.findUniqueByProperty("gen_table_id", gen_table_id)) == null) {
			/* 141 */ addMessage(redirectAttributes, new String[] { "创建菜单失败,请先生成代码!" });
			/* 142 */ return "redirect:" + this.adminPath + "/gen/genTable/?repage";
		}
		/* 144 */ genScheme.setGenTable(this.genTableService.get(gen_table_id));
		/* 145 */ this.genSchemeService.createMenu(genScheme, menu);
		/* 146 */ addMessage(redirectAttributes, new String[] { "创建菜单'" + genScheme.getFunctionName() + "'成功<br/>" });
		/* 147 */ return "redirect:" + this.adminPath + "/gen/genTable/?repage";
	}
}

/*
 * Location:
 * C:\Users\admin\Desktop\org.jeeframework.gencode-1.5.jar!\com\jeesite\modules\
 * gen\web\GenSchemeController.class Java compiler version: 6 (50.0) JD-Core
 * Version: 0.7.1
 */