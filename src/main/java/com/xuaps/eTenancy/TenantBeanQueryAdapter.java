package com.xuaps.eTenancy;

import com.avaje.ebean.Query;
import com.avaje.ebean.event.BeanQueryAdapter;
import com.avaje.ebean.event.BeanQueryRequest;

public class TenantBeanQueryAdapter implements BeanQueryAdapter {
	
	 TenantManager tenantManager;

	 public TenantBeanQueryAdapter(TenantManager tenantManager){
		  this.tenantManager = tenantManager; 
	 }

    @Override
    public boolean isRegisterFor(Class<?> aClass) {
        try{
            aClass.getDeclaredField(this.tenantManager.getFieldName());
            return true;
        }catch(NoSuchFieldException e){
            return false;
        }
    }

    @Override
    public int getExecutionOrder() {
        return 0;
    }

    @Override
    public void preQuery(BeanQueryRequest<?> beanQueryRequest) {
        Query query=beanQueryRequest.getQuery();

        query.where().eq(this.tenantManager.getFieldName(),this.tenantManager.getValue().toString());
    }
}
