package com.freemindcafe.serialization;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamDriver;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.mapper.ClassAliasingMapper;
import com.thoughtworks.xstream.mapper.Mapper;
import com.thoughtworks.xstream.mapper.MapperWrapper;

public class SerializationSvc 
{
	private String aliasConfFileName = "XMLAliasing.xml";	
	private static SerializationSvc svc = null;
	private XStream xstream = new SerializeXStream(new StaxDriver());
	private XStream jsonxstream = new SerializeXStream(new JettisonMappedXmlDriver());
	
	private SerializationSvc()
	{
		loadSvcAliases();
	}
	public static SerializationSvc getSerializationSvc()
	{
		if(svc == null)
		{
			svc = new SerializationSvc();
			svc.jsonxstream.setMode(XStream.NO_REFERENCES);
		}
		return svc;
	}
	public void addAlias(String alias, Class klass)
	{
		xstream.alias(alias, klass);
		jsonxstream.alias(alias, klass);
	}
	public String toXML(Object obj)
	{
		return xstream.toXML(obj);
	}
	
	public Object fromXML(String s)
	{
		return xstream.fromXML(s);
	}
	public String toJSON(Object obj)
	{
		return jsonxstream.toXML(obj);
	}
	
	public Object fromJSON(String s)
	{
		return jsonxstream.fromXML(s);
	}
	
	public byte[] toBytes(Object obj)
	{
		return this.compress(this.toXML(obj));
	}
	public Object fromBytes(byte[] bytes)
	{
		String xml = this.deCompress(bytes);
		return this.fromXML(xml);
	}
	/**
	 * Loads the alias information from the configuration xml file.
	 */
	private void loadSvcAliases()
	{
		try 
		{
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		DocumentBuilder db = dbf.newDocumentBuilder();
		InputStream inputStream = SerializationSvc.class.getResourceAsStream("/com/freemindcafe/serialization/sample1/XMLAliasing.xml");
		Document dom = db.parse(inputStream);
		NodeList nodeL = dom.getElementsByTagName("class");
		
		for(int i = 0; i< nodeL.getLength(); i++)
		{
			Node node = nodeL.item(i);
			NamedNodeMap map  = node.getAttributes();
			Node classN = map.getNamedItem("className");
			Node aliasN = map.getNamedItem("aliasName");
			String className= classN.getNodeValue();
			Class klass;
			try 
			{
				klass = Class.forName(className);
				String aliasName = aliasN.getNodeValue();
				xstream.alias(aliasName, klass);
				jsonxstream.alias(aliasName, klass);
			} catch (ClassNotFoundException e) {
				System.err.println("Class not found for configuring XStream's Alias : "+className);
			}		
		}

		}  catch (FileNotFoundException e1) {
			System.err.println("Configuration File not found for configuring XStream's Alias : "+e1);
			//e1.printStackTrace();
		} catch (SAXException e) {
			System.err.println("Configuration File not parsable for configuring XStream's Alias : "+e);
		} catch (IOException e) {
			System.err.println("Configuration File not readable for configuring XStream's Alias : "+e);
		} catch (ParserConfigurationException e) {
			System.err.println("Configuration File not parsable because of configuration for configuring XStream's Alias : "+e);
		}
	}
	public String getAliasConfFileName() {
		return aliasConfFileName;
	}
	
	/**
	 * re loads the alias information from the configuration xml file.
	 */
	public void setAliasConfFileName(String aliasConfFileName) {
		this.aliasConfFileName = aliasConfFileName;
		loadSvcAliases();
	}
	
	/**
	 * extends xstream and over rides mapper to use simple classname if the alias is not used.
	 * @author KANAG00R
	 *
	 */
	class SerializeXStream extends XStream
	{
		SerializeXStream(HierarchicalStreamDriver hierarchicalStreamDriver)
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
	        	String name = super.serializedClass(clazz);
	            if (clazz.getName().equals(name)) 
	            {
	              return clazz.getSimpleName();
	            } else {
	              return name;
	            } 
	        }                      
	}   

	public String deCompress(byte[] bytes)
	{ 
		try 
		{
			if (bytes == null || bytes.length == 0) {
				return new String();
	    }
			ByteArrayInputStream in = new ByteArrayInputStream(bytes);
	    	GZIPInputStream gzip = new GZIPInputStream(in);
			InputStreamReader reader = new InputStreamReader(gzip);
			BufferedReader bReader = new BufferedReader(reader);
			StringBuffer readedBuffer = new StringBuffer();
			String readed = null;
			while ((readed = bReader.readLine()) != null)
			{
			   readedBuffer.append(readed);
			}

			return readedBuffer.toString();
		} catch (IOException e) 
		{
			e.printStackTrace();
			throw new RuntimeException("Could not compress the string. "+e);
		}	   
	 }

	public byte[] compress(String str)
	{
		try 
		{
			if (str == null || str.length() == 0) {
				return new byte[0];
	    }
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    GZIPOutputStream gzip;		
			gzip = new GZIPOutputStream(out);
			gzip.write(str.getBytes());
			gzip.close();
			return out.toByteArray();
		} catch (IOException e) 
		{
			e.printStackTrace();
			throw new RuntimeException("Could not compress the string. "+e);
		}	   
	 }


}
