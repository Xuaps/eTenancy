import org.scalatest.FunSpec
import org.mockito.Mockito._;

import com.xuaps.eTenancy.TenantBeanPersistController;
import com.xuaps.eTenancy.TenantManager;
import java.util.UUID;

import com.avaje.ebean.event.BeanQueryRequest;
import com.avaje.ebean.Query;
import com.avaje.ebean.ExpressionList;

class TenantBeanPersistControllerSpec extends FunSpec{
  val tenantManager = new TenantManagerImpl

  describe("TenantBeanQueryAdapter") {
    describe("isRegisterFor") {
      it("should return if Class implements tenant property or not") {
       	val persistController = new TenantBeanPersistController(tenantManager);
			val withoutTenant = new WithoutTenant;
			val withTenant = new WithTenant;

			assertResult(false){persistController.isRegisterFor(classOf[WithoutTenant]);}
			assertResult(true){persistController.isRegisterFor(classOf[WithTenant]);}
      }
    }

	describe("preQuery"){
		it("should add tenant id filter to query"){
			//val queryAdapter = new TenantBeanQueryAdapter(fixture.tenantManager)
      	//val beanQueryRequestMock = mock(classOf[BeanQueryRequest[WithTenant]])
			//val queryMock = mock(classOf[Query[WithTenant]])
			//val expressionMock = mock(classOf[ExpressionList[WithTenant]])
			//val tenantId = fixture.tenantManager.getFieldName();
 			//val value = fixture.tenantManager.getValue.toString();

			//when(queryMock.where()).thenReturn(expressionMock);
			//when(beanQueryRequestMock.getQuery()).thenReturn(queryMock);
			
			//queryAdapter.preQuery(beanQueryRequestMock);

			//verify(expressionMock).eq(tenantId, value);
		}
	}
  }
}
