import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;

import javax.swing.JFrame;

public class Reflector2 extends Reflector{
	public static String getInfoValues(Object obj) throws IllegalArgumentException, IllegalAccessException
	{
		String s="";
		Class c=obj.getClass();
		Field[] f=c.getFields();
		for(int i=0;i<f.length;i++)
		{
			s+=getInfoField(f[i]);
		
			Class z=f[i].getType();
			if(z.isPrimitive())
			{
				if(z.getName().equals("int"))
				s+=" = "+f[i].getInt(obj)+System.lineSeparator();
				if(z.getName().equals("float"))
					s+=" = "+f[i].getFloat(obj)+System.lineSeparator();
					if(z.getName().equals("short"))
						s+=" = "+f[i].getShort(obj)+System.lineSeparator();
					if(z.getName().equals("byte"))
						s+=" = "+f[i].getByte(obj)+System.lineSeparator();
					if(z.getName().equals("long"))
						s+=" = "+f[i].getLong(obj)+System.lineSeparator();
					if(z.getName().equals("double"))
						s+=" = "+f[i].getDouble(obj)+System.lineSeparator();
					if(z.getName().equals("char"))
						s+=" = "+f[i].getChar(obj)+System.lineSeparator();
					if(z.getName().equals("Boolean"))
						s+=" = "+f[i].getBoolean(obj)+System.lineSeparator();
			}
			
			else
				s+=System.lineSeparator();
		}
		
		return s;
		
	}
	public static String ListMethods(String s)
	{
		String str="";
		String s1=s.substring(0);
		int count=1;
		while(s1.indexOf(System.lineSeparator())!=-1)
		{
			str+=count+") " + s1.substring(0,s1.indexOf(System.lineSeparator()))+System.lineSeparator();
			s1=s1.substring(s1.indexOf(System.lineSeparator())+2);
			count++;
		}
		return str;
	}
	public static String getInfoMethods(Object obj) 
	{
		 
		 	String s="";
		 	Class c=obj.getClass();
		 	Method[] m=c.getDeclaredMethods();
		 	for(int i=0;i<m.length;i++)
		 	{
		 		int mods=m[i].getModifiers();
		 		if(Modifier.isPublic(mods) && m[i].getAnnotatedParameterTypes().length==0)
		 		s+=getInfoMethod(m[i]);
		 	}
		 	return s;
		 	
	}
	public static Method[] getMethods(Object obj) 
	{
		 
		 	String s="";
		 	Class c=obj.getClass();
		 	Method[] m=c.getDeclaredMethods();
		 	int count=0;
		 	for(int i=0;i<m.length;i++)
		 	{
		 		int mods=m[i].getModifiers();
		 		if(Modifier.isPublic(mods) && m[i].getAnnotatedParameterTypes().length==0)
		 		count++;
		 	}
		 	Method[] meth=new Method[count];
		 	count=0;
		 	for(int i=0;i<m.length;i++)
		 	{
		 		int mods=m[i].getModifiers();
		 		if(Modifier.isPublic(mods) && m[i].getAnnotatedParameterTypes().length==0)
		 		meth[count++]=m[i];
		 	}
		 	return meth;
		 	
	}
	public static void main(String[] args)
	{
		try {
			while(true)
			{
				int k=0;
				Object obj=new Integer(k);
				
			System.out.println(getInfoValues(obj));
			System.out.println(ListMethods(getInfoMethods(obj)));
			if(getInfoMethods(obj).length()>0)
			{
			System.out.println("Выберите номер метода для вызова ");
			Scanner scan=new Scanner(System.in);
			Method[] m=getMethods(obj);
			int i=scan.nextInt();
			while(i<1 || i>m.length)
			{
				System.out.println("Должно быть от 1 до "+m.length);
				i=scan.nextInt();
			}
			try {
				System.out.println("Результат вызова метода: "+m[i-1].invoke(obj));
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
