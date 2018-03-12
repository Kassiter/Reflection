import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import javax.swing.JFrame;

public class Reflector {
public static String getInfo(String name) throws ClassNotFoundException,NoClassDefFoundError
{
	String s="";
	Class c=null;
		c = Class.forName(name);
	s=getInfo(c);
	return s;
}

public static String getInfo(Object obj)
{
	String s="";
	
	Class c=obj.getClass();

	s=getInfo(c);
	return s; 
}
private static String getInfoConstructor(Constructor c)
{
	String s="";
	int mods=c.getModifiers();
	if (Modifier.isPublic(mods)) { 
	    s=s+"public ";
	} 
	if (Modifier.isPrivate(mods)) { 
	    s=s+"private ";
	} 
	if (Modifier.isProtected(mods)) { 
	    s=s+"protected ";
	} 
	if (Modifier.isSynchronized(mods)) { 
	    s=s+"synchronized ";
	} 
	if (Modifier.isStatic(mods)) { 
	    s=s+"static ";
	} 
	if (Modifier.isAbstract(mods)) { 
		s=s+"abstract "; 
	} 
	if (Modifier.isFinal(mods)) { 
		s=s+"final "; 
	}
	

	s=s+c.getName()+"(";
	Class[] c3=c.getParameterTypes();
	if(c3!=null && c3.length>0)
	{
	for(int z=0;z<c3.length;z++)
	{
		if(c3[z].isArray())
			s=s+getInfoArray(c3[z]);
		else
		s=s+cutName(c3[z].getName())+", ";
	}
	s=s.substring(0, s.length()-2);
	}
	s+=")";
	return s;
}
private static String getInfoConstructors(Class c)
{
	String s="";
	Constructor con[]=c.getConstructors();
	for(int i=0;i<con.length;i++)
	{
		s+=getInfoConstructor(con[i])+System.lineSeparator();
	}
	return s;
}
public static String getInfoConstructors(String name) throws ClassNotFoundException
{
	Class c=null;
		c = Class.forName(name);
	return(getInfoConstructors(c));
}
protected static String getInfoArray(Class c)
{
	String s="";
	Class z=c;
	while(cutName(z.getName()).equals("String")==false  &&  z.getComponentType()!=null && z.getComponentType().isPrimitive()==false )
	{
		s=s+" of array";
		z=z.getComponentType();
	}
	if(cutName(z.getName()).equals("String"))
		s+="String";
	else
	s=s+" of "+z.getComponentType().getName();
	//здесь решить проблему с String, который видит как массив и как объект одновременно
	return s;
}
protected static String getInfoObj(Class c)
{
	String s="";
	if(c.isArray())
	{
		s=s+"array";
	}
	else
	{
	int mods = c.getModifiers(); 
	
	if (Modifier.isPublic(mods)) { 
	    s=s+"public ";
	} 
	if (Modifier.isPrivate(mods)) { 
	    s=s+"private ";
	} 
	if (Modifier.isProtected(mods)) { 
	    s=s+"protected ";
	} 
	if (Modifier.isSynchronized(mods)) { 
	    s=s+"synchronized ";
	} 
	if (Modifier.isStatic(mods)) { 
	    s=s+"static ";
	} 
	if (Modifier.isAbstract(mods)) { 
		s=s+"abstract "; 
	} 
	if (Modifier.isFinal(mods)) { 
		s=s+"final "; 
	}
	if(c.isInterface())
	{
	s=s+"interface ";
	}
	else
		s=s+"class ";
	}
	String name=c.getName();
	if(c.isArray())
	{
		s=s+getInfoArray(c);
		
	}
	else
	s=s+cutName(name);
	Class c1=	c.getSuperclass();
	if(c1!=null)
		s=s+" extends "+cutName(c1.getName());
	Class c2[]=c.getInterfaces();
	if(c2!=null && c2.length>0)
	{
		s=s+" implements ";
		for(int i=0;i<c2.length;i++)
		{
			s=s+cutName(c2[i].getName())+", ";
		}
		s=s.substring(0, s.length()-2);
	}
	s=s+System.lineSeparator();
	Package p=c.getPackage();
	if(p!=null)
	s+="package "+p.getName()+System.lineSeparator();
	return s;
}
public static String getInfo(int k)
{
	String s="Это примитивный тип int";
	return s;
}
public static String getInfo(char c)
{
	String s="Это примитивный тип char";
	return s;
}
public static String getInfo(boolean b)
{
	String s="Это примитивный тип boolean";
	return s;
}
public static String getInfo(long l)
{
	String s="Это примитивный тип long";
	return s;
}
public static String getInfo(short s)
{
	String str="Это примитивный тип short";
	return str;
}
public static String getInfo(byte b)
{
	String s="Это примитивный тип byte";
	return s;
}
public static String getInfo(double d)
{
	String s="Это примитивный тип double";
	return s;
}
public static String getInfo(float f)
{
	String s="Это примитивный тип float";
	return s;
}

protected static String getInfoFields(Class c)
{
	String s="";
	Field[] f=c.getFields();
	for(int i=0;i<f.length;i++)
	{
		
		s+=getInfoField(f[i])+System.lineSeparator();
	}
	return s;
}
protected static String getInfoField(Field f)
{
	String s="";
	int mods=f.getModifiers();
	if (Modifier.isPublic(mods)) { 
	    s=s+"public ";
	} 
	if (Modifier.isPrivate(mods)) { 
	    s=s+"private ";
	} 
	if (Modifier.isProtected(mods)) { 
	    s=s+"protected ";
	} 
	if (Modifier.isStatic(mods)) { 
	    s=s+"static ";
	} 
	if (Modifier.isAbstract(mods)) { 
		s=s+"abstract "; 
	} 
	if (Modifier.isFinal(mods)) { 
		s=s+"final "; 
	}
	s=s+cutName(f.getType().getName())+" ";
	s=s+f.getName();
	return s;
}
protected static String getInfoMethods(Class c)
{
	String s="";
	Method[] m=c.getDeclaredMethods();
	
	for(int i=0;i<m.length;i++)
	{
		s+=getInfoMethod(m[i]);
	}
	Class z=c;
	while(z.getSuperclass()!=null)
	{
		z=z.getSuperclass();
		 m=z.getDeclaredMethods();
		for(int i=0;i<m.length;i++)
		{
			s+=getInfoMethod(m[i]);
		}
	}
	return s;
}
public static String getInfoOwnMethods(String name) throws ClassNotFoundException
{
	 
	 	String s="";
	 	Class c=null;
	 		c = Class.forName(name);
	 	Method[] m=c.getDeclaredMethods();
	 	for(int i=0;i<m.length;i++)
	 	{
	 		s+=getInfoMethod(m[i]);
	 	}
	 	return s;
	 	
}
public static String getInfoOwnInterfaceMethods(String name) throws ClassNotFoundException
{
	 
	 	String s="";
	 	Class c=null;
	 		c = Class.forName(name);
	 	Method[] m=c.getDeclaredMethods();
	 	for(int i=0;i<m.length;i++)
	 	{
	 		int mods=m[i].getModifiers();
	 		if(Modifier.isPublic(mods))
	 		s+=getInfoMethod(m[i]);
	 	}
	 	return s;
	 	
}
protected static String getInfoMethod(Method m)
{
	String s="";
	int mods=m.getModifiers();
	if (Modifier.isPublic(mods)) { 
	    s=s+"public ";
	} 
	if (Modifier.isPrivate(mods)) { 
	    s=s+"private ";
	} 
	if (Modifier.isProtected(mods)) { 
	    s=s+"protected ";
	} 
	if (Modifier.isSynchronized(mods)) { 
	    s=s+"synchronized ";
	} 
	if (Modifier.isStatic(mods)) { 
	    s=s+"static ";
	} 
	if (Modifier.isAbstract(mods)) { 
		s=s+"abstract "; 
	} 
	if (Modifier.isFinal(mods)) { 
		s=s+"final "; 
	}
	
	AnnotatedType AT=m.getAnnotatedReturnType();
	if(AT.getType().getClass().isArray())
		s=s+getInfoArray(AT.getType().getClass());
	s=s+cutName(AT.getType().getTypeName())+" ";
	s=s+m.getName()+"(";
	Class[] c3=m.getParameterTypes();
	if(c3!=null && c3.length>0)
	{
	for(int z=0;z<c3.length;z++)
	{
		if(c3[z].isArray())
			s=s+getInfoArray(c3[z]);
		else
		s=s+cutName(c3[z].getName())+", ";
	}
	s=s.substring(0, s.length()-2);
	}
	s+=")";
	s=s+System.lineSeparator();
	return s;
}
protected static String getInfoMethodsInterface(Class c)
{
	String s="";
	s+="Class Interface "+System.lineSeparator();
Method[] m=c.getMethods();
	
	for(int i=0;i<m.length;i++)
	{
		int mod=m[i].getModifiers();
		if(Modifier.isPublic(mod))
		s+=getInfoMethod(m[i]);
	}
	return s;
}
public static String InfoMethods(String name) throws ClassNotFoundException
{
	String s="";
	Class c=null;
		c = Class.forName(name);
	s=getInfoMethods(c);
	return s;
	
}
public static String InfoFields(String name) throws ClassNotFoundException
{
	String s="";
	Class c=null;
		c = Class.forName(name);
	
	s=getInfoFields(c);
	return s;
	
}
public static String InfoMethodsIntefrace(String name) throws ClassNotFoundException
{
	String s="";
	Class c=null;
		c = Class.forName(name);
	s=getInfoMethodsInterface(c);
	return s;
	
}
protected static String  getInfo(Class c)
{
	String s="";
	s=s+getInfoObj(c);
	s=s+getInfoConstructors(c);
	s+=getInfoMethods(c);
	s+=getInfoMethodsInterface(c);
	s+=getInfoFields(c);
	return s; 
}
protected static String cutName(String name)
{
	while(name.indexOf(".")!=-1)
	{
		name=name.substring(name.indexOf(".")+1);
		
	}
	return name;
}
public static void main(String args[])
{
	int k=0;
	Object a=k;
	System.out.println(getInfo(7));
}
}
