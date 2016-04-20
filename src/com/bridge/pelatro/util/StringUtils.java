package com.bridge.pelatro.util;

import java.io.ByteArrayOutputStream;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtils {

	public static String formatDate(Date date) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}
	
	/**
	public static String getPrettyPrintXML(Element element) {
		try {
			String xmlContent = element.asXML();
			ByteArrayOutputStream out = new ByteArrayOutputStream(xmlContent.getBytes().length);
			Document document = DocumentHelper.parseText(xmlContent);
			OutputFormat format = OutputFormat.createPrettyPrint();
			XMLWriter writer = new XMLWriter(out, format);
			writer.write(document);
			return new String(out.toByteArray());
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	**/
}
