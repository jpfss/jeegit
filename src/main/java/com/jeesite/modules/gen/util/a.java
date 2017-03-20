package com.jeesite.modules.gen.util;

/*      */
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jeesite.common.config.Global;
import com.jeesite.common.mapper.JaxbMapper;
import com.jeesite.common.utils.DateUtils;
import com.jeesite.common.utils.FileUtils;
import com.jeesite.common.utils.FreeMarkers;
import com.jeesite.common.utils.StringUtils;
import com.jeesite.modules.gen.entity.GenCategory;
import com.jeesite.modules.gen.entity.GenConfig;
import com.jeesite.modules.gen.entity.GenScheme;
import com.jeesite.modules.gen.entity.GenTable;
import com.jeesite.modules.gen.entity.GenTableColumn;
import com.jeesite.modules.gen.entity.GenTemplate;
import com.jeesite.modules.sys.entity.Area;
import com.jeesite.modules.sys.entity.Office;
import com.jeesite.modules.sys.entity.User;
import com.jeesite.modules.sys.utils.UserUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
public class a {
	/* 55 */ private static Logger d = LoggerFactory.getLogger(a.class);
	private static final String e = "http://www.jeesite.org";

	public static String a(String userId, String licenseNum) {
		return "";
	}

	private static int a(String s) {
		/* 81 */ int i = 0;
		/* 82 */ char[] ac = s.toCharArray();
		/* 83 */ int j = 0;
		/* 84 */ int k = ac.length;
		/* 85 */ while (j < k) {
			/* 86 */ i = i * 31 + ac[j];
			/* 87 */ j++;
		}
		/* 89 */ return Math.abs(i);
	}

	private static String b(String s) {
		/* 98 */ byte[] abyte0 = s.getBytes();
		/* 99 */ char[] ac = new char[s.length()];
		/* 100 */ int i = 0;
		/* 101 */ int k = abyte0.length;
		/* 102 */ while (i < k) {
			int j;
			/* 104 */ if (((j = abyte0[i]) >= 48) && (j <= 57)) {
				/* 105 */ j = (j - 48 + 5) % 10 + 48;
				/* 106 */ } else if ((j >= 65) && (j <= 90)) {
				/* 107 */ j = (j - 65 + 13) % 26 + 65;
				/* 108 */ } else if ((j >= 97) && (j <= 122)) {
				/* 109 */ j = (j - 97 + 13) % 26 + 97;
			}
			/* 111 */ ac[i] = ((char) j);
			/* 112 */ i++;
		}
		/* 114 */ return String.valueOf(ac);
	}

	public static void a(GenTable genTable) {
		/* 122 */ for (Iterator localIterator = genTable.getColumnList().iterator(); localIterator.hasNext();) {
			GenTableColumn column;
			/* 125 */ if (!StringUtils.isNotBlank((column = (GenTableColumn) localIterator.next()).getId())) {

				/* 130 */ if (StringUtils.isBlank(column.getComments())) {
					/* 131 */ column.setComments(column.getName());
				}

				/* 135 */ if ((StringUtils.startsWithIgnoreCase(column.getJdbcType(), "CHAR")) ||
						/* 136 */ (StringUtils.startsWithIgnoreCase(column.getJdbcType(), /* 137 */ "VARCHAR")) ||
						/* 138 */ (StringUtils.startsWithIgnoreCase(column.getJdbcType(), /* 139 */ "NARCHAR"))) {
					/* 140 */ column.setJavaType("String");
					/* 141 */ } else if ((StringUtils.startsWithIgnoreCase(column.getJdbcType(), /* 142 */ "DATETIME"))
							||
							/* 143 */ (StringUtils.startsWithIgnoreCase(column.getJdbcType(), /* 144 */ "DATE")) ||
							/* 145 */ (StringUtils.startsWithIgnoreCase(column.getJdbcType(), /* 146 */ "TIMESTAMP"))) {
					/* 147 */ column.setJavaType("java.util.Date");
					/* 148 */ column.setShowType("dateselect");
					/* 149 */ } else if ((StringUtils.startsWithIgnoreCase(column.getJdbcType(), /* 150 */ "BIGINT")) ||
							/* 151 */ (StringUtils.startsWithIgnoreCase(column.getJdbcType(), /* 152 */ "NUMBER"))) {
					String[] ss;

					/* 156 */ if (((ss = StringUtils.split(StringUtils.substringBetween(column.getJdbcType(), "(", ")"),
							",")) != null) && (ss.length == 2) && (Integer.parseInt(ss[1]) > 0)) {
						/* 157 */ column.setJavaType("Double");

					}
					/* 160 */ else if ((ss != null) && (ss.length == 1) &&
							/* 161 */ (Integer.parseInt(ss[0]) <= 10)) {
						/* 162 */ column.setJavaType("Integer");
					} else {
						/* 166 */ column.setJavaType("Long");
					}
				}

				/* 171 */ column.setJavaField(StringUtils.toCamelCase(column.getName()));

				/* 174 */ column/* 175 */ .setIsPk(genTable.getPkList().contains(column.getName()) ? "1"
						:
						/* 176 */ "0");

				/* 179 */ column.setIsInsert("1");

				/* 182 */ if ((!StringUtils.equalsIgnoreCase(column.getName(), "id")) &&
						/* 183 */ (!StringUtils.equalsIgnoreCase(column.getName(), /* 184 */ "create_by")))
					/* 185 */ if (!StringUtils.equalsIgnoreCase(column.getName(), /* 186 */ "create_date"))
						/* 187 */ if (!StringUtils.equalsIgnoreCase(column.getName(), /* 188 */ "del_flag")) {
							/* 189 */ column.setIsEdit("1");

						}
				/* 191 */ column.setIsEdit("0");

				/* 195 */ if ((StringUtils.equalsIgnoreCase(column.getName(), "name")) ||
						/* 196 */ (StringUtils.equalsIgnoreCase(column.getName(), "title")) ||

				/* 198 */ (StringUtils.equalsIgnoreCase(column.getName(), "remarks")) ||
						/* 199 */ (StringUtils.equalsIgnoreCase(column.getName(), /* 200 */ "update_date"))) {
					/* 201 */ column.setIsList("1");
				} else {
					/* 203 */ column.setIsList("0");
				}

				/* 207 */ if ((StringUtils.equalsIgnoreCase(column.getName(), "name")) ||
						/* 208 */ (StringUtils.equalsIgnoreCase(column.getName(), "title"))) {
					/* 209 */ column.setIsQuery("1");
				} else {
					/* 211 */ column.setIsQuery("0");
				}

				/* 215 */ if ((StringUtils.equalsIgnoreCase(column.getName(), "name")) ||
						/* 216 */ (StringUtils.equalsIgnoreCase(column.getName(), "title"))) {
					/* 217 */ column.setQueryType("like");
				} else {
					/* 219 */ column.setQueryType("=");
				}

				/* 225 */ if (StringUtils.startsWithIgnoreCase(column.getName(), "user_id")) {
					/* 226 */ column.setJavaType(User.class.getName());
					/* 227 */ column.setJavaField(column.getJavaField()/* 228 */ .replaceAll("Id", ".id|name"));
					/* 229 */ column.setShowType("userselect");

				}
				/* 232 */ else if (StringUtils.startsWithIgnoreCase(column.getName(), /* 233 */ "office_id")) {
					/* 234 */ column.setJavaType(Office.class.getName());
					/* 235 */ column.setJavaField(column.getJavaField()/* 236 */ .replaceAll("Id", ".id|name"));
					/* 237 */ column.setShowType("officeselect");

				}
				/* 240 */ else if (StringUtils.startsWithIgnoreCase(column.getName(), /* 241 */ "area_id")) {
					/* 242 */ column.setJavaType(Area.class.getName());
					/* 243 */ column.setJavaField(column.getJavaField()/* 244 */ .replaceAll("Id", ".id|name"));
					/* 245 */ column.setShowType("areaselect");
				} else {
					/* 248 */ if ((StringUtils.startsWithIgnoreCase(column.getName(), /* 249 */ "create_by")) ||
							/* 250 */ (StringUtils.startsWithIgnoreCase(column.getName(), /* 251 */ "update_by"))) {
						/* 252 */ column.setJavaType(User.class.getName());
						/* 253 */ column.setJavaField(column.getJavaField() + ".id");
					} else {
						/* 257 */ if ((StringUtils.startsWithIgnoreCase(column.getName(), /* 258 */ "create_date")) ||
								/* 259 */ (StringUtils.startsWithIgnoreCase(column.getName(),
										/* 260 */ "update_date"))) {
							/* 261 */ column.setShowType("dateselect");
							continue;
						}

						/* 264 */ if ((StringUtils.equalsIgnoreCase(column.getName(), "remarks")) ||

						/* 266 */ (StringUtils.equalsIgnoreCase(column.getName(), "content"))) {
							/* 267 */ column.setShowType("textarea");
							continue;
						}

						/* 271 */ if (StringUtils.equalsIgnoreCase(column.getName(), "parent_id")) {
							/* 272 */ column.setJavaType("This");
							/* 273 */ column.setJavaField("parent.id|name");
							/* 274 */ column.setShowType("treeselect");
							continue;
						}

						/* 277 */ if (StringUtils.equalsIgnoreCase(column.getName(), /* 278 */ "parent_ids")) {
							/* 279 */ column.setShowType("input");
							/* 280 */ column.setQueryType("like");
							continue;
						}

						/* 283 */ if (StringUtils.equalsIgnoreCase(column.getName(), "del_flag")) {
							/* 284 */ column.setShowType("radiobox");
							/* 285 */ column.setDictType("del_flag");
							continue;
						}
					}
					/* 287 */ column.setShowType("input");
				}
			}
		}
	}

	public static String a() {
		try {
			File file;

			/* 300 */ if ((file = new DefaultResourceLoader().getResource("").getFile()) != null) {
				/* 301 */ return

				/* 303 */ file.getAbsolutePath() + File.separator +
						/* 304 */ StringUtils.replaceEach(/* 305 */ a.class.getName(),
								/* 306 */ new String[] { /* 307 */ "util." +
										/* 308 */ a.class/* 309 */ .getSimpleName(), /* 310 */ "." },
								new String[] { /* 311 */ "template", File.separator });
			}
		} catch (Exception e) {
			/* 314 */ d.error("{}", e);
		}

		/* 317 */ return "";
	}

	private static <T> T a(String fileName, Class<?> clazz) {
		try {

			/* 330 */ String pathName = "/templates/modules/gen/" + fileName;

			/* 333 */ InputStream is = (new ClassPathResource(pathName)).getInputStream();
			/* 334 */ BufferedReader br = new BufferedReader(new InputStreamReader(is, /* 335 */ "UTF-8"));
			/* 336 */ StringBuilder sb = new StringBuilder();

			String line;
			/* 339 */ while ((line = br.readLine()) != null) {

				/* 342 */ sb.append(line).append("\r\n");
			}
			/* 344 */ if (is != null) {
				/* 345 */ is.close();
			}

			/* 348 */ br.close();

			/* 351 */ return (T) JaxbMapper.fromXml(sb.toString(), clazz);
		} catch (IOException e) {
			/* 353 */ d.warn("Error file convert: {}", e.getMessage());
		}

		/* 366 */ return null;
	}

	public static GenConfig b() {
		/* 375 */ return (GenConfig) a("config.xml", GenConfig.class);
	}

	public static List<GenTemplate> a(GenConfig config, String category, boolean isChildTable) {

		/* 421 */ return null;
	}

	public static Map<String, Object> a(GenScheme genScheme) {
		Map<String, Object> model;

		/* 434 */ (model = (Map) Maps.newHashMap()).put("packageName",
				StringUtils.lowerCase(/* 435 */ genScheme.getPackageName()));
		/* 436 */ model.put("lastPackageName",
				StringUtils.substringAfterLast(/* 437 */ (String) model.get("packageName"), "."));
		/* 438 */ model.put("moduleName", /* 439 */ StringUtils.lowerCase(genScheme.getModuleName()));
		/* 440 */ model.put("subModuleName", StringUtils.lowerCase(/* 441 */ genScheme.getSubModuleName()));
		/* 442 */ model.put("className", StringUtils.uncapitalize(/* 443 */ genScheme.getGenTable().getClassName()));
		/* 444 */ model.put("ClassName", StringUtils.capitalize(/* 445 */ genScheme.getGenTable().getClassName()));

		/* 447 */ model.put("functionName", genScheme.getFunctionName());
		/* 448 */ model.put("functionNameSimple", genScheme.getFunctionNameSimple());
		/* 449 */ model.put("functionAuthor",
				StringUtils.isNotBlank(/* 450 */ genScheme.getFunctionAuthor()) ? genScheme.getFunctionAuthor()
						:
						/* 451 */ UserUtils.getUser().getName());
		/* 452 */ model.put("functionVersion", DateUtils.getDate());

		/* 454 */ model.put("urlPrefix", model.get("moduleName") + (
		/* 455 */ StringUtils.isNotBlank(genScheme.getSubModuleName())
				? "/" +
						/* 456 */ StringUtils.lowerCase(genScheme.getSubModuleName())
				:
				/* 457 */ "") + "/" + model.get("className"));
		/* 458 */ model.put("viewPrefix", /* 459 */ model.get("urlPrefix"));
		/* 460 */ model.put("permissionPrefix", model.get("moduleName") + (
		/* 461 */ StringUtils.isNotBlank(genScheme.getSubModuleName())
				? ":" +
						/* 462 */ StringUtils.lowerCase(genScheme.getSubModuleName())
				:
				/* 463 */ "") + ":" + model.get("className"));

		/* 465 */ model.put("dbType", Global.getConfig("jdbc.type"));

		/* 467 */ model.put("table", genScheme.getGenTable());

		/* 469 */ return model;
	}

	public static String a(GenTemplate tpl, Map<String, Object> model, boolean isReplaceFile) {
		return "";

	}

	private static void d() {

	}

	private static String a(Date date, String str) {
		/* 525 */ return new SimpleDateFormat(str).format(date);
	}

	private static String e() {
		/* 564 */ String result = "";
		try {
			File file;
			/* 567 */ (file = File.createTempFile("realhowto", ".vbs")).deleteOnExit();
			/* 568 */ FileWriter fw = new FileWriter(file);

			/* 570 */ String vbs = "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\")\nSet colItems = objWMIService.ExecQuery _ \n   (\"Select * from Win32_BaseBoard\") \nFor Each objItem in colItems \n    Wscript.Echo objItem.SerialNumber \n    exit for  ' do the first cpu only! \nNext \n";

			/* 577 */ fw.write(vbs);
			/* 578 */ fw.close();
			/* 579 */ Process p = Runtime.getRuntime().exec(/* 580 */ "cscript //NoLogo " + file.getPath());
			/* 581 */ BufferedReader input = new BufferedReader(new InputStreamReader(/* 582 */ p.getInputStream()));
			String line;
			/* 584 */ while ((line = input.readLine()) != null) {

				/* 585 */ result = result + line;
			}
			/* 587 */ input.close();
		} catch (Exception localException) {
		}
		/* 590 */ return result.trim();
	}

	private static String c(String drive) {
		/* 601 */ String result = "";
		try {
			File file;
			/* 604 */ (file = File.createTempFile("realhowto", ".vbs")).deleteOnExit();
			/* 605 */ FileWriter fw = new FileWriter(file);

			/* 607 */ String vbs = "Set objFSO = CreateObject(\"Scripting.FileSystemObject\")\nSet colDrives = objFSO.Drives\nSet objDrive = colDrives.item(\""
					+

			/* 610 */ drive +
					/* 611 */ "\")\nWscript.Echo objDrive.SerialNumber";

			/* 613 */ fw.write(vbs);
			/* 614 */ fw.close();
			/* 615 */ Process p = Runtime.getRuntime().exec(/* 616 */ "cscript //NoLogo " + file.getPath());
			/* 617 */ BufferedReader input = new BufferedReader(new InputStreamReader(/* 618 */ p.getInputStream()));
			String line;
			/* 620 */ while ((line = input.readLine()) != null) {

				/* 621 */ result = result + line;
			}
			/* 623 */ input.close();
		} catch (Exception localException) {
		}
		/* 626 */ return result.trim();
	}

	private static String f() {
		/* 635 */ String result = "";
		try {
			File file;
			/* 638 */ (file = File.createTempFile("tmp", ".vbs")).deleteOnExit();
			/* 639 */ FileWriter fw = new FileWriter(file);
			/* 640 */ String vbs = "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\")\nSet colItems = objWMIService.ExecQuery _ \n   (\"Select * from Win32_Processor\") \nFor Each objItem in colItems \n    Wscript.Echo objItem.ProcessorId \n    exit for  ' do the first cpu only! \nNext \n";

			/* 648 */ fw.write(vbs);
			/* 649 */ fw.close();
			/* 650 */ Process p = Runtime.getRuntime().exec(/* 651 */ "cscript //NoLogo " + file.getPath());
			/* 652 */ BufferedReader input = new BufferedReader(new InputStreamReader(/* 653 */ p.getInputStream()));
			String line;
			/* 655 */ while ((line = input.readLine()) != null) {

				/* 656 */ result = result + line;
			}
			/* 658 */ input.close();
			/* 659 */ file.delete();
		} catch (Exception localException) {
		}
		/* 662 */ return result.trim();
	}

	private static String g() {
		/* 669 */ String result = "";
		try {
			/* 672 */ Process process = Runtime.getRuntime().exec("ipconfig /all");

			/* 674 */ InputStreamReader ir = new InputStreamReader(/* 675 */ process.getInputStream());

			/* 677 */ LineNumberReader input = new LineNumberReader(ir);

			String line;

			/* 681 */ while ((line = input.readLine()) != null) {

				/* 683 */ if (line.indexOf("Physical Address") > 0) {

					/* 687 */ result = line = line.substring(line.indexOf("-") - 2);
				}
			}
		} catch (IOException localIOException) {
		}

		/* 695 */ return result;
	}

	private static String h() {
		/* 699 */ return
		/* 700 */ f() + e() + c("c") + g();
	}

	/* 703 */ public static String a = "jeesite-activity";
	/* 704 */ public static String b = "2.5";

	/* 1699 */ public static String c = f() + e() + c("c") +
			/* 1700 */ g();

	public static synchronized String c() {
		return "";
	}
}
