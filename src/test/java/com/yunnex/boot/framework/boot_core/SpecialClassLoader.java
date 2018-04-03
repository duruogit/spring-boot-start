package com.yunnex.boot.framework.boot_core;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class SpecialClassLoader extends ClassLoader {
	
	//彻底改变加载类的方式，可以不遵循类的委托加载机制。

	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		System.out.println("loadClass:" + name);
		//Object类由系统自行加载。
	    if("java.lang.Object".equals(name)){
	    	return super.loadClass(name);
	    }
	    
	    //特殊加载方式。
	    return specialLoadClass(name);
	}

	/**
	 * 加载class文件。
	 * @param name
	 * @return
	 */
	private Class<?> specialLoadClass(String name)  throws ClassNotFoundException  {
		Class<?>  clazz;
		String fullName = name; //CheckJar.LIB_PATH +"/"+
		System.out.println("fullname:"+fullName);
		clazz = this.loadClassFromFile(fullName);
		System.out.println("---resolveClass--");
		super.resolveClass(clazz);
		return clazz;
	}

	 

	/**
	 * 从文件名加载class文件。
	 * @param name class文件名
	 * @return
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings({ "rawtypes", "deprecation" })
	private Class<?> loadClassFromFile(String name) throws ClassNotFoundException{
		InputStream is = null;
		Class clazz = null;
		byte[] bytes = null;
		try{
			//读取class文件的内容。
			is = SpecialClassLoader.class.getResourceAsStream(name);
			int size = is.available();
			bytes = new byte[size];
			is.read(bytes);
			
			//定义类
			clazz = super.defineClass(bytes, 0, size);
		}catch(Exception e){
			e.printStackTrace();
			throw new ClassNotFoundException(name);
		}finally{
			if(is!=null){
				try {
					is.close();
					is = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	
			if(bytes!=null){
				bytes = null;
			}
		}
		return clazz;
	}

	
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		Class  clazz = null;
		ClassLoader classLoader;
		try {
			classLoader =  new SpecialClassLoader ();
			clazz = classLoader.loadClass("C:\\Users\\Administrato\\Desktop\\plugins\\calss\\HelloWorld_1.0.0.201802071741\\helloworld\\Activator.class");
			
//			Map<String,String> map = new HashMap<String,String>();
//			map = CheckJar.getClazzInfos();
//			for(Map.Entry<String, String> entry : map.entrySet()){
//				System.out.println("类名称："+entry.getKey()+"，jar包："+entry.getValue());
//				String[] jarNames = entry.getValue().split("@");
//				for (String jarName : jarNames) {
//					clazz = classLoader.loadClass(jarName+"/"+entry.getKey());
//					System.out.println(clazz);
//					System.out.println(clazz.getClassLoader());
//				}
//				
//			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		
		Class CheckJarClazz;
		CheckJarClazz = CheckJar.class;
		System.out.println("CheckJarClazz:" + CheckJarClazz);
		System.out.println(CheckJarClazz.getClassLoader());
		System.out.println(CheckJarClazz == clazz);
		System.out.println(CheckJarClazz.equals(clazz));
		
//		String ss  = "helloworld.Activator.class";
//		System.out.println(ss.substring(0, ss.lastIndexOf(".")));
	}
	
}
