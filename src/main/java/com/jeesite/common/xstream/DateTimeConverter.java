package com.jeesite.common.xstream;

import java.util.Date;

import com.jeesite.common.utils.DateUtils;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

/**
 * XStream 日期转换类
 *
 * @since JDK 1.7.0+
 * @version V1.2.1 2017年3月18日
 * @author www.jeegit.org
 */
public class DateTimeConverter implements Converter {

	public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
		Date date = (Date) source;
		if (date != null) {
			writer.setValue(DateUtils.formatDateTime(date));
		} else {
			writer.setValue("");
		}
	}

	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
		try {
			Date date = DateUtils.parseDate(reader.getValue());
			return date;
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	public boolean canConvert(Class type) {
		return type.equals(Date.class);
	}

}
