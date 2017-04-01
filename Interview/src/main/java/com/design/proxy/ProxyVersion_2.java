/**
 * 
 */
package com.design.proxy;

import java.io.File;
import java.io.FileWriter;
import java.io.Serializable;
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

/**
 * 设计模式：代理模式测试<p>
 * @author LingMin 
 * @date 2016-12-5<br>
 * @version 1.0<br>
 */
public class ProxyVersion_2 implements Serializable{
    private static final long serialVersionUID = 1L;
 
    public static Object newProxyInstance(Class<?> interfaces, MyInvocationHandler h) throws Exception
    {
        Method[] methods = interfaces.getMethods();        
        StringBuilder sb = new StringBuilder(1024);
 
        sb.append("package com.design.proxy;\n\n");
        sb.append("import java.lang.reflect.Method;\n\n");
        sb.append("public class DynamicProxy implements " +  interfaces.getSimpleName() + "\n");
        sb.append("{\n");
        sb.append("\tMyInvocationHandler h;\n\n");
        sb.append("\tpublic DynamicProxy(MyInvocationHandler h)\n");
        sb.append("\t{\n");
        sb.append("\t\tthis.h = h;\n");
        sb.append("\t}\n\n");
        for (Method m : methods)
        {
            sb.append("\tpublic " + m.getReturnType() + " " + m.getName() + "()\n");
            sb.append("\t{\n");
            sb.append("\t\ttry\n");
            sb.append("\t\t{\n");
            sb.append("\t\t\tMethod md = " + interfaces.getName() + ".class.getMethod(\"" + m.getName() + "\");\n");
            sb.append("\t\t\th.invoke(this, md);\n");
            sb.append("\t\t}\n");
            sb.append("\t\tcatch (Exception e)\n");
            sb.append("\t\t{\n");
            sb.append("\t\t\te.printStackTrace();\n");
            sb.append("\t\t}\n");
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
        Class<?> c = Class.forName("com.design.proxy.DynamicProxy", false, ul);
 
        /** 利用反射将c实例化出来 */
        Constructor<?> constructor = c.getConstructor(MyInvocationHandler.class);
        Object obj = constructor.newInstance(h);
 
        /** 使用完毕删除生成的代理.java文件和.class文件，这样就看不到动态生成的内容了 */
        File classFile = new File(fileDir + "\\src\\main\\java\\com\\design\\proxy\\DynamicProxy.class");
        javaFile.delete();
        classFile.delete();
 
        return obj;
    }
}
