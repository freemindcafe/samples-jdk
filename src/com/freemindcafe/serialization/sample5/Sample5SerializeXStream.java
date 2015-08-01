package com.freemindcafe.serialization.sample5;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.reflection.PureJavaReflectionProvider;
import com.thoughtworks.xstream.io.HierarchicalStreamDriver;
import com.thoughtworks.xstream.mapper.ClassAliasingMapper;
import com.thoughtworks.xstream.mapper.Mapper;
import com.thoughtworks.xstream.mapper.MapperWrapper;

public class Sample5SerializeXStream extends XStream {
	
	Sample5SerializeXStream(HierarchicalStreamDriver hierarchicalStreamDriver)
	{
		super(new PureJavaReflectionProvider(), hierarchicalStreamDriver);
		
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
		String name = super.serializedClass(clazz);
		if (clazz.getName().equals(name)) 
		{
			return clazz.getSimpleName();
		} else {
			return name;
		} 
	}                      
} 

