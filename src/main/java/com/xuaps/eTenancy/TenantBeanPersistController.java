package com.xuaps.eTenancy;

import com.avaje.ebean.event.BeanPersistController;
import com.avaje.ebean.event.BeanPersistRequest;
import java.lang.*;
import java.util.Set;
import java.lang.reflect.Field;
import java.util.UUID;

public class TenantBeanPersistController implements BeanPersistController{

   TenantManager tenantManager;

	public TenantBeanPersistController (TenantManager tenantManager){
		 this.tenantManager = tenantManager;		 
	}	

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
		 return false;
  	}

	@Override
	public boolean isRegisterFor(Class<?> cls){
		 try{
			  cls.getDeclaredField(tenantManager.getFieldName());
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
