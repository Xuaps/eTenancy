import org.scalatest.FunSpec
import ebean.tenancy.TenantBeanQueryAdapter
import ebean.tenancy.TenantManager;

class TenantBeanQueryAdapterSpec extends FunSpec {
  class WithoutTenant {

  }

  class WithTenant {
		val tenantId = "aaa";
  }

  class TenantManagerImpl extends TenantManager{
	def getFieldName(): String = {
		return "tenantId"
	}
  }

  describe("TenantBeanQueryAdapter") {
    describe("isRegisterFor") {
      it("should return if Class implements tenant property or not") {
       	val queryAdapter = new TenantBeanQueryAdapter(new TenantManagerImpl);
			val withoutTenant = new WithoutTenant;
			val withTenant = new WithTenant;

			assertResult(false){queryAdapter.isRegisterFor(classOf[WithoutTenant]);}
			assertResult(true){queryAdapter.isRegisterFor(classOf[WithTenant]);}
      }
    }
  }
}
