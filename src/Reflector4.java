import java.lang.reflect.Field;

public class Reflector4 {
public static String getString(Object obj) throws IllegalArgumentException, IllegalAccessException
{
	String s="";
	Class c=obj.getClass();
	Field[] f=c.getFields();
	for(int i=0;i<f.length;i++)
	{
	
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
}
