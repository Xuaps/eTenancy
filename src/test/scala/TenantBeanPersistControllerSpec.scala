import org.scalatest.FunSpec
import org.mockito.Mockito._;

import com.xuaps.eTenancy.TenantBeanPersistController;
import com.xuaps.eTenancy.TenantManager;
import java.util.UUID;

import com.avaje.ebean.event.BeanPersistRequest;

class TenantBeanPersistControllerSpec extends FunSpec{
  val tenantManager = new TenantManagerImpl

  describe("TenantBeanPersistController") {
    describe("isRegisterFor") {
      it("should return if Class implements tenant property or not") {
       	val persistController = new TenantBeanPersistController(tenantManager);
			val withoutTenant = new WithoutTenant;
			val withTenant = new WithTenant;

			assertResult(false){persistController.isRegisterFor(classOf[WithoutTenant]);}
			assertResult(true){persistController.isRegisterFor(classOf[WithTenant]);}
      }
    }

	describe("preInsert"){
		it("should set tenant id value"){
			val persistController = new TenantBeanPersistController(tenantManager)
      	val beanPersistRequestMock = mock(classOf[BeanPersistRequest[WithTenant]])
			val bean = new WithTenant

 			when(beanPersistRequestMock.getBean()).thenReturn(bean);
			
			persistController.preInsert(beanPersistRequestMock);

			assert(tenantManager.getValue() == bean.tenantId);
		}
	}
  }
}
