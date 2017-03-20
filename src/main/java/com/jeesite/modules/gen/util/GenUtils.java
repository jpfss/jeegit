package com.jeesite.modules.gen.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class GenUtils {
	public static String a() {
		/* 28 */ String result = "";
		try {
			/* 30 */ File file = File.createTempFile("realhowto", ".vbs");
			/* 31 */ file.deleteOnExit();
			/* 32 */ FileWriter fw = new FileWriter(file);

			/* 34 */ String vbs = "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\")\nSet colItems = objWMIService.ExecQuery _ \n   (\"Select * from Win32_BaseBoard\") \nFor Each objItem in colItems \n    Wscript.Echo objItem.SerialNumber \n    exit for  ' do the first cpu only! \nNext \n";

			/* 41 */ fw.write(vbs);
			/* 42 */ fw.close();
			/* 43 */ Process p = Runtime.getRuntime().exec(/* 44 */ "cscript //NoLogo " + file.getPath());
			/* 45 */ BufferedReader input = new BufferedReader(new InputStreamReader(/* 46 */ p.getInputStream()));
			String line;
			/* 48 */ while ((line = input.readLine()) != null) {
				/* 49 */ result = result + line;
			}
			/* 51 */ input.close();
		} catch (Exception localException) {
		}
		/* 54 */ return result.trim();
	}

	public static String b(String drive) {
		/* 65 */ String result = "";
		try {
			/* 67 */ File file = File.createTempFile("realhowto", ".vbs");
			/* 68 */ file.deleteOnExit();
			/* 69 */ FileWriter fw = new FileWriter(file);

			/* 71 */ String vbs = "Set objFSO = CreateObject(\"Scripting.FileSystemObject\")\nSet colDrives = objFSO.Drives\nSet objDrive = colDrives.item(\""
					+

			/* 74 */ drive +
					/* 75 */ "\")\n" +
					/* 76 */ "Wscript.Echo objDrive.SerialNumber";
			/* 77 */ fw.write(vbs);
			/* 78 */ fw.close();
			/* 79 */ Process p = Runtime.getRuntime().exec(/* 80 */ "cscript //NoLogo " + file.getPath());
			/* 81 */ BufferedReader input = new BufferedReader(new InputStreamReader(/* 82 */ p.getInputStream()));
			String line;
			/* 84 */ while ((line = input.readLine()) != null) {
				/* 85 */ result = result + line;
			}
			/* 87 */ input.close();
		} catch (Exception localException) {
		}
		/* 90 */ return result.trim();
	}

	public static String c() {
		/* 99 */ String result = "";
		try {
			/* 101 */ File file = File.createTempFile("tmp", ".vbs");
			/* 102 */ file.deleteOnExit();
			/* 103 */ FileWriter fw = new FileWriter(file);
			/* 104 */ String vbs = "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\")\nSet colItems = objWMIService.ExecQuery _ \n   (\"Select * from Win32_Processor\") \nFor Each objItem in colItems \n    Wscript.Echo objItem.ProcessorId \n    exit for  ' do the first cpu only! \nNext \n";

			/* 112 */ fw.write(vbs);
			/* 113 */ fw.close();
			/* 114 */ Process p = Runtime.getRuntime().exec(/* 115 */ "cscript //NoLogo " + file.getPath());
			/* 116 */ BufferedReader input = new BufferedReader(new InputStreamReader(/* 117 */ p.getInputStream()));
			String line;
			/* 119 */ while ((line = input.readLine()) != null) {
				/* 120 */ result = result + line;
			}
			/* 122 */ input.close();
			/* 123 */ file.delete();
		} catch (Exception localException) {
		}
		/* 126 */ return result.trim();
	}

	public static String d() {
		/* 133 */ String result = "";
		try {
			/* 136 */ Process process = Runtime.getRuntime().exec("ipconfig /all");

			/* 138 */ InputStreamReader ir = new InputStreamReader(/* 139 */ process.getInputStream());

			/* 141 */ LineNumberReader input = new LineNumberReader(ir);

			String line;

			/* 145 */ while ((line = input.readLine()) != null) {

				/* 147 */ if (line.indexOf("Physical Address") > 0) {
					/* 149 */ String MACAddr = line.substring(line.indexOf("-") - 2);

					/* 151 */ result = MACAddr;
				}
			}
		} catch (IOException localIOException) {
		}

		/* 159 */ return result;
	}

	public static String e() {
		/* 163 */ return
		/* 164 */ c() + a() + b("c") + d();
	}

	/* 167 */ public static String versionType = "jeesite";
	/* 168 */ public static String versionNum = "2.4";

	/* 173 */ public static String computer = e();
}
