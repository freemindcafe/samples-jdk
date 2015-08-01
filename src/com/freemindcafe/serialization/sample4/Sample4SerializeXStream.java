package com.freemindcafe.serialization.sample4;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamDriver;
import com.thoughtworks.xstream.mapper.ClassAliasingMapper;
import com.thoughtworks.xstream.mapper.Mapper;
import com.thoughtworks.xstream.mapper.MapperWrapper;

/**
 * extends xstream and over rides mapper to use simple classname if the alias is not used.
 * @author KANAG00R
 *
 */
public class Sample4SerializeXStream extends XStream
{

	Sample4SerializeXStream(HierarchicalStreamDriver hierarchicalStreamDriver)
	{
		super(hierarchicalStreamDriver);

	}

	@Override
	protected MapperWrapper wrapMapper(MapperWrapper next) {
		return new ClassMapper(next);
	}

}

class ClassMapper extends ClassAliasingMapper
{

	public ClassMapper(Mapper wrapped) {
		super(wrapped);
	}

	@Override
	public String serializedClass(Class clazz) {
		if(clazz == null){
			return "null";
		}
		String name = super.serializedClass(clazz);
		if (clazz.getName().equals(name)) 
		{
			return clazz.getSimpleName();
		} else {
			return name;
		} 
	}                      
} 

