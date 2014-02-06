import com.xuaps.eTenancy.TenantManager;
import java.util.UUID;

class TenantManagerImpl extends TenantManager[UUID]{
	def getFieldName : String = {
   	return "tenantId"
	}

	def getValue : UUID = {
   	return UUID.fromString("550e8400-e29b-41d4-a716-446655440000")
	}
}
