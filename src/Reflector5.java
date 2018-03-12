import java.lang.reflect.*;

import javax.swing.JFrame;
public class Reflector5 {
public static void main(String[] args)
{
	Object ar=newArray(new Integer(4),4);
	Array.set(ar, 0, 4);
	Object el =Array.get(ar, 0);
	System.out.println(el.toString());
	
}
public static Object newArray(Object obj,int length)
{
	return Array.newInstance(obj.getClass(), length);
}
}
