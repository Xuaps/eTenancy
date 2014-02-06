import org.scalatest.FunSpec
import org.mockito.Mockito._;

import com.xuaps.eTenancy.TenantBeanQueryAdapter
import com.xuaps.eTenancy.TenantManager;
import java.util.UUID;

import com.avaje.ebean.event.BeanQueryRequest;
import com.avaje.ebean.Query;
import com.avaje.ebean.ExpressionList;

class TenantBeanQueryAdapterSpec extends FunSpec{

	val tenantManager = new TenantManagerImpl();

  describe("TenantBeanQueryAdapter") {
    describe("isRegisterFor") {
      it("should return if Class implements tenant property or not") {
       	val queryAdapter = new TenantBeanQueryAdapter(tenantManager);
			val withoutTenant = new WithoutTenant;
			val withTenant = new WithTenant;

			assertResult(false){queryAdapter.isRegisterFor(classOf[WithoutTenant]);}
			assertResult(true){queryAdapter.isRegisterFor(classOf[WithTenant]);}
      }
    }

	describe("preQuery"){
		it("should add tenant id filter to query"){
			val queryAdapter = new TenantBeanQueryAdapter(tenantManager)
      	val beanQueryRequestMock = mock(classOf[BeanQueryRequest[WithTenant]])
			val queryMock = mock(classOf[Query[WithTenant]])
			val expressionMock = mock(classOf[ExpressionList[WithTenant]])

			when(queryMock.where()).thenReturn(expressionMock);
			when(beanQueryRequestMock.getQuery()).thenReturn(queryMock);
			
			queryAdapter.preQuery(beanQueryRequestMock);

			verify(expressionMock).eq(tenantManager.getFieldName(), tenantManager.getValue().toString());
		}
	}
  }
}
