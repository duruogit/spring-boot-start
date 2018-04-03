package com.yunnex.boot.framework.boot_core;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DynamicJarClassLoader extends ClassLoader {

	private static Logger logger = LoggerFactory.getLogger(DynamicJarClassLoader.class);

	private JarFile jarFile;

	private ClassLoader parent;

	public DynamicJarClassLoader(JarFile jarFile) {
		super(Thread.currentThread().getContextClassLoader());
		this.parent = Thread.currentThread().getContextClassLoader();
		this.jarFile = jarFile;
	}

	public DynamicJarClassLoader(JarFile jarFile, ClassLoader parent) {
		super(parent);
		this.parent = parent;
		this.jarFile = jarFile;
	}

	@Override
	protected synchronized Class<?> loadClass(String name, boolean resolve)
			throws ClassNotFoundException {
		try {
			Class<?> c = null;
			if (null != jarFile) {
				Enumeration<JarEntry> files = jarFile.entries();  
				while (files.hasMoreElements()) {  
		            JarEntry entry = (JarEntry) files.nextElement(); 
		            if (null != entry && entry.getName().endsWith(".class")) {
						InputStream is = jarFile.getInputStream(entry);
						int availableLen = is.available();
						int len = 0;
						byte[] bt1 = new byte[availableLen];
						while (len < availableLen) {
							len += is.read(bt1, len, availableLen - len);
						}
						c = defineClass(name, bt1, 0, bt1.length);
						System.out.println("类名称:"+c.getName());
						if (resolve) {
							resolveClass(c);
						}
					}
		        }
			}
			return c;
		} catch (IOException e) {
			throw new ClassNotFoundException("Class " + name + " not found.");
		}
	}

	@Override
	public InputStream getResourceAsStream(String name) {
		InputStream is = null;
		try {
			if (null != jarFile) {
				JarEntry entry = jarFile.getJarEntry(name);
				if (entry != null) {
					is = jarFile.getInputStream(entry);
				}
				if (is == null) {
					is = super.getResourceAsStream(name);
				}
			}
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		return is;
	}
	
	@SuppressWarnings({ "rawtypes", "unused" })
	public static void main(String[] args) {
		InputStream is = null;
		File file = null;
		JarFile jarFile = null;
		Class clazz = null;
		byte[] bytes = null;
		try {
			file = new File("C:\\Users\\Administrato\\Desktop\\plugins\\HelloWorld_1_0_0_201802071741 - 副本.jar");
			jarFile = new JarFile(file);
			DynamicJarClassLoader loader = new DynamicJarClassLoader(jarFile);
//			is = loader.getResourceAsStream("C:\\Users\\Administrato\\Desktop\\plugins\\HelloWorld_1.0.0.201802071741.jar\\Activator.class");
			clazz = loader.loadClass("helloworld.Activator",false);
			System.out.println(clazz);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				jarFile.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
