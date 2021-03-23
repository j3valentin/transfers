import io.gatling.core.Predef._
import io.gatling.http.Predef._

import java.util.concurrent.TimeUnit
import scala.concurrent.duration._

class ApiGatlingSimulationTest extends Simulation {

  val scn = scenario("Transfers").repeat(1000, "n") {
    exec(
      http("Transfer-API")
        .post("http://localhost:8080/api/transfer")
        .header("Content-Type", "application/json")
        .body(StringBody("""{"sourceAcct":"${n}","destinationAcct":"1${n}","date":"200${n}-01-01", "amount": ${n}}"""))
        .check(status.is(200))
    ).pause(Duration.apply(5, TimeUnit.MILLISECONDS))
  }

  setUp(scn.inject(atOnceUsers(30))).maxDuration(FiniteDuration.apply(10, "minutes"))

}