package com.jeesite.modules.gen.template;

import freemarker.template.Configuration;
import freemarker.template.Template;
import java.io.StringWriter;
import java.util.Map;

public class a {
	private static Configuration a;

	static {
		/* 19 */ (a = new Configuration()).setClassForTemplateLoading(a.class, "/");
	}

	public static String a(String tplName, String encoding, Map<String, Object> paras) {
		try {
			/* 32 */ StringWriter swriter = new StringWriter();

			/* 34 */ (
			/* 35 */ a.getTemplate(tplName, encoding)).process(paras, swriter);
			/* 36 */ return swriter.toString();
		} catch (Exception localException1) {
			Exception e;
			/* 38 */ (e = localException1).printStackTrace();
			/* 39 */ return e.toString();
		}
	}

	public final String a(String tplName, Map<String, Object> paras) {
		/* 44 */ return a(tplName, "utf-8", paras);
	}
}

/*
 * Location:
 * C:\Users\admin\Desktop\org.jeeframework.gencode-1.5.jar!\com\jeesite\modules\
 * gen\template\a.class Java compiler version: 6 (50.0) JD-Core Version: 0.7.1
 */