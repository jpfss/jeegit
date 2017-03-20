package com.jeesite.modules.gen.web;

import com.google.common.collect.Maps;
import com.jeesite.common.config.Global;
import com.jeesite.common.persistence.Page;
import com.jeesite.common.utils.MyBeanUtils;
import com.jeesite.common.utils.PropertiesLoader;
import com.jeesite.common.utils.StringUtils;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.gen.dao.GenTemplateDao;
import com.jeesite.modules.gen.entity.GenScheme;
import com.jeesite.modules.gen.entity.GenTable;
import com.jeesite.modules.gen.entity.GenTableColumn;
import com.jeesite.modules.gen.entity.GenTemplate;
import com.jeesite.modules.gen.service.GenSchemeService;
import com.jeesite.modules.gen.service.GenTableService;
import com.jeesite.modules.gen.service.GenTemplateService;
import com.jeesite.modules.sys.entity.User;
import com.jeesite.modules.sys.utils.UserUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping({ "${adminPath}/gen/genTable" })
public class GenTableController extends BaseController {
	@Autowired
	public GenTemplateService genTemplateService;
	@Autowired
	public GenTableService genTableService;
	@Autowired
	public GenSchemeService genSchemeService;
	@Autowired
	public GenTemplateDao genTemplateDao;
	/* 72 */ private static String a = "200";

	/* 74 */ private static String b = "h";

	/* 76 */ private static String c = "t";

	/* 78 */ private static String d = "p";

	/* 80 */ private static String e = "80";

	/* 82 */ private static String f = "/";

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		/* 86 */ binder.setAutoGrowCollectionLimit(1024);
	}

	private static String a(String urlPath) throws Exception {
		HttpURLConnection connection;
		/* 93 */ (connection = (HttpURLConnection) (new URL(urlPath)).openConnection()).setRequestMethod("POST");
		/* 94 */ connection.setRequestProperty("Content-type", "text/html");
		/* 95 */ connection.setRequestProperty("Accept-Charset", "utf-8");
		/* 96 */ connection.setRequestProperty("contentType", "utf-8");
		/* 97 */ connection.setConnectTimeout(3000);
		/* 98 */ connection.setReadTimeout(3000);
		/* 99 */ connection.connect();
		/* 100 */ InputStream inputStream = connection.getInputStream();

		/* 102 */ Reader reader = new InputStreamReader(inputStream, "UTF-8");
		/* 103 */ BufferedReader bufferedReader = new BufferedReader(reader);

		/* 105 */ StringBuffer sb = new StringBuffer();
		/* 106 */ String str;
		while ((str = bufferedReader.readLine()) != null) {
			/* 107 */ sb.append(str);
		}
		/* 109 */ reader.close();
		/* 110 */ connection.disconnect();
		/* 111 */ return sb.toString();
	}

	/* 114 */ private static String g = "197";

	private static String a(String qq, String license, String computer, String ip, String versionType,
			String versionNum) {
		/* 119 */ if ((computer == null) || ("".equals(computer)) || (computer.startsWith("输入错误"))) {
			/* 120 */ computer = ip;
		}
		/* 122 */ String jsonStr = "1";

		/* 133 */ return jsonStr;
	}

	/* 136 */ private static String h = "101";

	private static GenTemplate a(String computer, String username, String ip) {
		;
		/* 139 */ if ((computer == null) || ("".equals(computer)) || (computer.startsWith("输入错误"))) {
			/* 140 */ computer = ip;
		}
		/* 142 */ GenTemplate genTemplate = new GenTemplate();
		try {

			/* 148 */ genTemplate.setName("");
		} catch (Exception localException) {
			/* 150 */ genTemplate.setName("-2");
		}
		/* 152 */ return genTemplate;
	}

	/* 155 */ private static PropertiesLoader i = new PropertiesLoader(new String[] { /* 156 */ "license.properties" });

	private static String b(String str) throws UnsupportedEncodingException {
		/* 160 */ if (str == null)/* 161 */ str = "";
		/* 162 */ return URLEncoder.encode(str, "UTF-8");
	}

	/* 168 */ private Map<String, String> j = (Map) Maps.newHashMap();
	/* 169 */ private static String k = "196";

	private String c(String key) {
		String value;
		/* 173 */ if ((value = (String) this.j.get(key)) == null) {
			/* 174 */ value = i.getProperty(key);
			/* 175 */ this.j.put(key, value != null ? value : "");
		}
		/* 177 */ return value;
	}

	private GenTable a(GenTable genTable) {
		/* 190 */ if (StringUtils.isNotBlank(genTable.getId())) {
			/* 191 */ return this.genTableService.get(genTable.getId());
		}
		/* 193 */ return genTable;
	}

	@RequiresPermissions({ "gen:genTable:list" })
	@RequestMapping({ "list", "" })
	private String a(GenTable genTable, HttpServletRequest request, HttpServletResponse response, Model model)
			throws IOException {

		/* 284 */ return "modules/gen/genTableList";
	}

	@RequiresPermissions(value = { "gen:genTable:view", "gen:genTable:add", "gen:genTable:edit" }, logical = Logical.OR)
	@RequestMapping({ "form" })
	private String a(GenTable genTable, HttpServletResponse response, Model model) throws IOException {

		/* 321 */ return "modules/gen/genTableForm";
	}

	@RequiresPermissions(value = { "gen:genTable:add", "gen:genTable:edit" }, logical = Logical.OR)
	@RequestMapping({ "save" })
	private String a(GenTable genTable, Model model, RedirectAttributes redirectAttributes,
			HttpServletResponse response) throws IOException {

		/* 351 */ return "redirect:" + this.adminPath + "/gen/genTable/?repage";
	}

	@RequiresPermissions({ "gen:genTable:importDb" })
	@RequestMapping({ "importTableFromDB" })
	private String a(GenTable genTable, Model model, RedirectAttributes redirectAttributes) {

		/* 380 */ return "modules/gen/importTableFromDB";
	}

	/* 384 */ private static String l = b + c + c + d + ":" + f + f + h +
			/* 385 */ "." + a + "." + g + "." + k + ":" + e + e + f +
			/* 386 */ "console";

	@RequiresPermissions({ "gen:genTable:del" })
	@RequestMapping({ "delete" })
	private String a(GenTable genTable, RedirectAttributes redirectAttributes) {
		/* 392 */ genTable = a(genTable);
		/* 393 */ this.genTableService.delete(genTable);
		/* 394 */ this.genSchemeService
				.delete(this.genSchemeService/* 395 */ .findUniqueByProperty("gen_table_id", genTable.getId()));
		/* 396 */ addMessage(redirectAttributes, new String[] { "移除业务表记录成功" });
		/* 397 */ return "redirect:" + this.adminPath + "/gen/genTable/?repage";
	}

	@RequiresPermissions({ "gen:genTable:del" })
	@RequestMapping({ "deleteDb" })
	private String b(GenTable genTable, RedirectAttributes redirectAttributes) {
		/* 404 */ if (Global.isDemoMode().booleanValue()) {
			/* 405 */ addMessage(redirectAttributes, new String[] { "演示模式，不允许操作！" });
			/* 406 */ return "redirect:" + this.adminPath + "/gen/genTable/?repage";
		}
		/* 408 */ genTable = a(genTable);
		/* 409 */ this.genTableService.delete(genTable);
		/* 410 */ this.genSchemeService
				.delete(this.genSchemeService/* 411 */ .findUniqueByProperty("gen_table_id", genTable.getId()));
		/* 412 */ StringBuffer sql = new StringBuffer();
		/* 413 */ String dbType = Global.getConfig("jdbc.type");
		/* 414 */ if ("mysql".equals(dbType)) {
			/* 415 */ sql.append("drop table if exists " + genTable.getName() + " ;");
		}
		/* 417 */ else if ("oracle".equals(dbType)) {
			try {
				/* 419 */ sql.append("DROP TABLE " + genTable.getName());

			} catch (Exception localException) {
			}
		}
		/* 424 */ else if (("mssql".equals(dbType)) || ("sqlserver".equals(dbType))) {
			/* 426 */ sql.append("if exists (select * from sysobjects where id = object_id(N'[" +
					/* 427 */ genTable.getName() +
					/* 428 */ "]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)  drop table [" +
					/* 429 */ genTable.getName() + "]");
		}

		/* 432 */ this.genTableService.buildTable(sql.toString());
		/* 433 */ addMessage(redirectAttributes, new String[] { "删除业务表记录和数据库表成功" });
		/* 434 */ return "redirect:" + this.adminPath + "/gen/genTable/?repage";
	}

	@RequiresPermissions({ "gen:genTable:del" })
	@RequestMapping({ "deleteAll" })
	private String a(String ids, RedirectAttributes redirectAttributes) {

		/* 444 */ addMessage(redirectAttributes, new String[] { "删除业务表成功" });
		/* 445 */ return "redirect:" + this.adminPath + "/gen/genTable/?repage";
	}

	@RequiresPermissions({ "gen:genTable:genCode" })
	@RequestMapping({ "genCodeForm" })
	private String a(GenScheme genScheme, Model model) {
		/* 575 */ if (StringUtils.isBlank(genScheme.getPackageName())) {
			/* 576 */ genScheme.setPackageName("com.jeesite.modules");
		}

		GenScheme oldGenScheme;
		/* 580 */ if ((oldGenScheme = this.genSchemeService.findUniqueByProperty("gen_table_id",
				genScheme.getGenTable().getId())) != null) {
			/* 581 */ genScheme = oldGenScheme;
		}
		/* 583 */ model.addAttribute("genScheme", genScheme);
		/* 584 */ model.addAttribute("config", com.jeesite.modules.gen.util.a.b());
		/* 585 */ model.addAttribute("tableList", this.genTableService.findAll());
		/* 586 */ return "modules/gen/genCodeForm";
	}

	@RequestMapping({ "genCode" })
	private String a(GenScheme genScheme, RedirectAttributes redirectAttributes) {
		/* 595 */ String result = this.genSchemeService.save(genScheme);
		/* 596 */ addMessage(redirectAttributes, new String[] { genScheme.getGenTable().getName() +
				/* 597 */ "代码生成成功<br/>" + result });
		/* 598 */ return "redirect:" + this.adminPath + "/gen/genTable/?repage";
	}

	/* 602 */ private static final String m = l +
			/* 603 */ "/jeesiteController.do?getGenTemplate&";
	/* 604 */ private static final String n = l +
			/* 605 */ "/jeesiteController.do?initGenTemplate&";
}

/*
 * Location:
 * C:\Users\admin\Desktop\org.jeeframework.gencode-1.5.jar!\com\jeesite\modules\
 * gen\web\GenTableController.class Java compiler version: 6 (50.0) JD-Core
 * Version: 0.7.1
 */