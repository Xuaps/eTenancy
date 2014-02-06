package com.xuaps.eTenancy;

import java.util.UUID;

public interface TenantManager<T>{
	 String getFieldName();
	 T getValue();
}
