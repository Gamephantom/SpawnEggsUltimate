package org.gp.SpawnEggsUltimate.API;

public class AccessGetterNBT<T> {

    public T invokeMethod(T instance, String methodName, Object... arguments)
    {
    	try
    	{
    		int counter = 0;
        	Class oclass = instance.getClass();
        	for(int i = 0; i < arguments.length;i++)
        		counter++;
    		Class[] parameter = new Class[counter];
    		for(int i = 0; i < counter; i++)
    			parameter[i] = arguments[i].getClass();
    		java.lang.reflect.Method invokeM = oclass.getMethod(methodName, parameter);
    		invokeM.invoke(instance, arguments);
    		return instance;
    	}catch(Exception e)
    	{
    		if(e instanceof NoSuchMethodException)
    		{
    		}else e.printStackTrace();
    	}
		return instance;
    }
    
    public static boolean invokeMethodBoolean(Object instance, String methodName, Object... arguments)
    {
    	try
    	{
    		int counter = 0;
        	Class oclass = instance.getClass();
        	for(int i = 0; i < arguments.length;i++)
        		counter++;
    		Class[] parameter = new Class[counter];
    		for(int i = 0; i < counter; i++)
    			parameter[i] = arguments[i].getClass();
    		java.lang.reflect.Method invokeM = oclass.getMethod(methodName, parameter);
    		invokeM.invoke(instance, arguments);
    		return true;
    	}catch(Exception e)
    	{
    		if(e instanceof NoSuchMethodException)
    		{
    		}else e.printStackTrace();
    	}
		return false;
    }
    
    //ONLY USE IT FOR READ CLASSES!
    @SuppressWarnings("unchecked")
	public <Z> Z invokeMethodWithReturn(T instance, Object typeToCopy, String methodName, Object... arguments) throws InstantiationException, IllegalAccessException
    {
    	Z meta = (Z) typeToCopy;
    	try
    	{
    		int counter = 0;
        	Class oclass = instance.getClass();
        	for(int i = 0; i < arguments.length;i++)
        		counter++;
    		Class[] parameter = new Class[counter];
    		for(int i = 0; i < counter; i++)
    			parameter[i] = arguments[i].getClass();
    		java.lang.reflect.Method invokeM = oclass.getMethod(methodName, parameter);
    		invokeM.invoke(instance, meta);
    	}catch(Exception e)
    	{
    		if(e instanceof NoSuchMethodException)
    		{
    			e.printStackTrace();
    		}else e.printStackTrace();
    	}
		return meta;
    }
}
