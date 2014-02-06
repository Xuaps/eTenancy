import org.scalatest.FunSpec
import org.mockito.Mockito._;

import ebean.tenancy.TenantBeanQueryAdapter
import ebean.tenancy.TenantManager;
import java.util.UUID;

import com.avaje.ebean.event.BeanQueryRequest;
import com.avaje.ebean.Query;
import com.avaje.ebean.ExpressionList;

class TenantBeanQueryAdapterSpec extends FunSpec{
  class WithoutTenant {

  }

  class WithTenant {
		val tenantId = null;
  }

	def fixture = new {
	  	val tenantManager = mock(classOf[TenantManager]);
		when(tenantManager.getFieldName()).thenReturn("tenantId");
		when(tenantManager.getValue()).thenReturn(UUID.fromString("550e8400-e29b-41d4-a716-446655440000"));
   }

  describe("TenantBeanQueryAdapter") {
    describe("isRegisterFor") {
      it("should return if Class implements tenant property or not") {
       	val queryAdapter = new TenantBeanQueryAdapter(fixture.tenantManager);
			val withoutTenant = new WithoutTenant;
			val withTenant = new WithTenant;

			assertResult(false){queryAdapter.isRegisterFor(classOf[WithoutTenant]);}
			assertResult(true){queryAdapter.isRegisterFor(classOf[WithTenant]);}
      }
    }

	describe("preQuery"){
		it("should add tenant id filter to query"){
			val queryAdapter = new TenantBeanQueryAdapter(fixture.tenantManager)
      	val beanQueryRequestMock = mock(classOf[BeanQueryRequest[WithTenant]])
			val queryMock = mock(classOf[Query[WithTenant]])
			val expressionMock = mock(classOf[ExpressionList[WithTenant]])
			val tenantId = fixture.tenantManager.getFieldName();
			val value = fixture.tenantManager.getValue.toString();

			when(queryMock.where()).thenReturn(expressionMock);
			when(beanQueryRequestMock.getQuery()).thenReturn(queryMock);
			
			queryAdapter.preQuery(beanQueryRequestMock);

			verify(expressionMock).eq(tenantId, value);
		}
	}
  }
}
