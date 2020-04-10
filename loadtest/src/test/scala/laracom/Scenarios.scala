package laracom

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

object Scenarios {

	val rampUpTimeSecs = 60

	// SayHello
	val names = csv("names.csv").circular
	val scn_SayHello = scenario("SayHello")
		.during(Conf.duration) {
			feed(names)
			.exec(http("SayHello")
            	.post("/demo/DemoService/SayHello")
            	.headers(Headers.http_header)
            	.body(StringBody("""{ "name": "${name}" }""")))
		}

	// SayHelloByUserId
    val ids = csv("userids.csv").circular
    val scn_SayHelloByUserId = scenario("SayHelloByUserId")
        .during(Conf.duration) {
            feed(ids)
            .exec(http("SayHelloByUserId")
                .post("/demo/DemoService/SayHelloByUserId")
                .headers(Headers.http_header)
                .body(StringBody("""{ "id": "${id}" }""")))
        }
}