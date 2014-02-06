package com.xuaps.eTenancy;

import com.avaje.ebean.event.BeanPersistController;
import com.avaje.ebean.event.BeanPersistRequest;
import java.lang.*;
import java.util.Set;
import java.lang.reflect.Field;
import java.util.UUID;

public class TenantBeanPersistController implements BeanPersistController{
	
	@Override	 
	public void postLoad(Object bean, Set<String> includedProperties){
	 	
	}

	@Override
	public void postDelete(BeanPersistRequest<?> request){
		
	}

	@Override
	public void postInsert(BeanPersistRequest<?> request){
		
	}

	@Override
	public void postUpdate(BeanPersistRequest<?> request){
		
	}

	@Override
	public boolean preDelete(BeanPersistRequest<?> request){
		 return true;
	}

	@Override
	public boolean preUpdate(BeanPersistRequest<?> request){
		 return true;
	}

	@Override
	public boolean preInsert(BeanPersistRequest<?> request){
		 try{
			Field field = request.getBean().getClass().getDeclaredField("tenant_id");
		 	field.set(request.getBean(),UUID.fromString("a8e9d420-8829-11e3-baa7-0800200c9a66"));
	  
		 	return true;
		 }catch(Exception e){		  
			  return false;
		 }
  	}

	@Override
	public boolean isRegisterFor(Class<?> cls){
		 try{
		 	cls.getDeclaredField("tenant_id");
			return true;
		 }catch(NoSuchFieldException e){
			return false;
		 }
	}

	@Override
	public int getExecutionOrder(){
		 return 0;
	}
}
