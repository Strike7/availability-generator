import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._
import play.api.test.Helpers._

@RunWith(classOf[JUnitRunner])
class JogoResourceSepc extends Specification{
  "Resource Jogos" should {
    "send 400 on a malformatted json payload" in new WithApplication {
    }
  }
}
