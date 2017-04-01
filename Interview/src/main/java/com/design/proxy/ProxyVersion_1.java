/**
 * 
 */
package com.design.proxy;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

/***
 * 设计模式：代理模式测试
 * 动态代理,动态生成 java类，动态编译class，动态加载class
 * @author LingMin 
 * @date 2016-7-19<br>
 * @version 1.0<br>
 */
public class ProxyVersion_1 {

	private static final long serialVersionUID = 1L;
	 
    public static Object newProxyInstance(Class<?> interfaces) throws Exception
    {
        Method[] methods = interfaces.getMethods();
 
        StringBuilder sb = new StringBuilder(700);
 
        sb.append("package com.design.proxy;\n\n");
        sb.append("public class DynamicProxy implements " +  interfaces.getSimpleName() + "\n");
        sb.append("{\n");
        sb.append("\t" + interfaces.getSimpleName() + " interfaces;\n\n");
        sb.append("\tpublic DynamicProxy(" + interfaces.getSimpleName() +  " interfaces)\n");
        sb.append("\t{\n");
        sb.append("\t\tthis.interfaces = interfaces;\n");
        sb.append("\t}\n\n");
        for (Method m : methods)
        {
            sb.append("\tpublic " + m.getReturnType() + " " + m.getName() + "()\n");
            sb.append("\t{\n");
            sb.append("\t\tSystem.out.println(\"Before Hello World!\");\n");
            sb.append("\t\tinterfaces." + m.getName() + "();\n");
            sb.append("\t\tSystem.out.println(\"After Hello World!\");\n");
            sb.append("\t}\n");
        }
        sb.append("}");
 
        /** 生成一段Java代码 */
        String fileDir = System.getProperty("user.dir");
        String fileName = fileDir + "\\src\\main\\java\\com\\design\\proxy\\DynamicProxy.java";
        File javaFile = new File(fileName);
        Writer writer = new FileWriter(javaFile);
        writer.write(sb.toString());
        writer.close();
 
        /** 动态编译这段Java代码,生成.class文件 */
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager sjfm = compiler.getStandardFileManager(null, null, null);
        Iterable<? extends JavaFileObject> iter = sjfm.getJavaFileObjects(fileName);
        CompilationTask ct = compiler.getTask(null, sjfm, null, null, null, iter);
        ct.call();
        sjfm.close();
 
        /** 将生成的.class文件载入内存，默认的ClassLoader只能载入CLASSPATH下的.class文件 */
        URL[] urls = new URL[] {(new URL("file:\\" + System.getProperty("user.dir") + "\\src"))};
        URLClassLoader ul = new URLClassLoader(urls);
        Class<?> c = ul.loadClass("com.design.proxy.DynamicProxy");
 
        /** 利用反射将c实例化出来 */
        Constructor<?> constructor = c.getConstructor(HelloWorld.class);
        HelloWorld helloWorldImpl = new HelloWorldImpl();
        Object obj = constructor.newInstance(helloWorldImpl);
 
        /** 使用完毕删除生成的代理.java文件和.class文件，这样就看不到动态生成的内容了 */
        /*File classFile = new File(fileDir + "\\src\\main\\java\\com\\design\\proxy\\DynamicProxy.class");
        javaFile.delete();
        classFile.delete();*/
 
        return obj;
    }
}
