package com.yunnex.boot.framework.boot_core;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 校验工程的jar包中是否存在相同的名称的class
 * @author yuwenjun
 * @date 2018年2月8日 下午2:26:36
 * <p>Copyright (c) 2017, www.yunnex.com All Rights Reserved.<／p>
 */
public class CheckJar {
	
	public static final String LIB_PATH = "C:/Users/Administrato/Desktop/plugins/";
	
	private static final Logger LOG = LoggerFactory.getLogger(CheckJar.class);  
	
	public static Map<String,String> getClazzInfos(){
		Map<String,String> classMap = new HashMap<String,String>();
		//相同类的jar包路径集合信息
		Map<String,String> sameClassMap = new HashMap<String,String>();
		File file = new File(LIB_PATH);
		if(file.isDirectory()){
			String[] jarFilePath = file.list();
			for (String jarPath : jarFilePath) {
				JarFile jarFile = null;
				try {
					String fullJarPath = LIB_PATH+File.separator+jarPath;
					jarFile = new JarFile(fullJarPath);
					Enumeration<JarEntry> files = jarFile.entries();  
					while (files.hasMoreElements()) {  
			            JarEntry entry = (JarEntry) files.nextElement();  
			            if (entry.getName().endsWith(".class")){
			            	String className  = entry.getName(); //entry.getName().replaceAll("/", ".");
			            	if(classMap.containsKey(className)){  //&&className.contains("yunnex")
			            		
			            		sameClassMap.put(className, classMap.get(className)+"@"+jarPath);
			            	}
			            	
			            	classMap.put(className, jarPath);
			            }  
			        }
					
				} catch (IOException e) {
					LOG.error("解析jar失败", e);
					continue;
				} finally {
					try {
						jarFile.close();
					} catch (IOException e) {
						LOG.error("", e);
					}
				}
			}
			
//			for(Map.Entry<String, String> entry : sameClassMap.entrySet()){
//				System.out.println("类名称："+entry.getKey()+"，jar包："+entry.getValue());
//			}
			
		}else{
			LOG.info("请输入正取的lib路径");
		}
		
		return sameClassMap;
	}
	
	public static void main(String[] args) {
		Map<String,String> classMap = new HashMap<String,String>();
		//相同类的jar包路径集合信息
		Map<String,String> sameClassMap = new HashMap<String,String>();
		File file = new File(LIB_PATH);
		if(file.isDirectory()){
			String[] jarFilePath = file.list();
			for (String jarPath : jarFilePath) {
				JarFile jarFile = null;
				try {
					String fullJarPath = LIB_PATH+File.separator+jarPath;
					jarFile = new JarFile(fullJarPath);
					Enumeration<JarEntry> files = jarFile.entries();  
					while (files.hasMoreElements()) {  
			            JarEntry entry = (JarEntry) files.nextElement();  
			            if (entry.getName().endsWith(".class")){
			            	String className  = entry.getName().replaceAll("/", ".");
			            	if(classMap.containsKey(className)){  //&&className.contains("yunnex")
			            		sameClassMap.put(className, classMap.get(className)+"@"+jarPath);
			            	}
			            	
			            	classMap.put(className, jarPath);
			            }  
			        }
					
				} catch (IOException e) {
					LOG.error("解析jar失败", e);
					continue;
				} finally {
					try {
						jarFile.close();
					} catch (IOException e) {
						LOG.error("", e);
					}
				}
			}
			
			for(Map.Entry<String, String> entry : sameClassMap.entrySet()){
				System.out.println("类名称："+entry.getKey()+"，jar包："+entry.getValue());
			}
			
			
		}else{
			LOG.info("请输入正取的lib路径");
		}
	}

}
