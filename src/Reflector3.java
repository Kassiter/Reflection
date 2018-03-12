import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class Reflector3 extends Reflector {
public static void main(String[] args)
{
	Class c=new Object().getClass();
	Integer I=new Integer(8);
	Object p[] =new Object[1];
	p[0]=new String("4");
	try {
		Function(I,"getInteger",p );
	} catch (FunctionNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
public static void Function(Object obj,String name, Object[] parametrs) throws FunctionNotFoundException
{
	Method[] m=obj.getClass().getMethods();
	boolean b=false;
	int count=-1;
	for(int i=0;i<m.length;i++)
	{
		if(m[i].getName().equals(name))
		{
			if(parametrs.length==m[i].getParameterCount())
			{
				boolean b1=true;
				Parameter[] pr=m[i].getParameters();
				for(int j=0;j<parametrs.length;j++)
				{
					if(pr[j].getParameterizedType().getTypeName().equals(parametrs[j].getClass().getName())==false)
						b1=false;
				}
				b=b1;
				count=i;
			}
		}
	}
	if(b==false) throw new FunctionNotFoundException();
	else
		try {
			System.out.println(m[count].invoke(obj, parametrs));
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
}
class FunctionNotFoundException extends Exception{
	 
    public FunctionNotFoundException(){
     
        super("Function not found");
        
    }
}
