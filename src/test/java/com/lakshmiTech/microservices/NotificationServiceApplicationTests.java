package com.lakshmiTech.microservices;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
class NotificationServiceApplicationTests {
/* static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:latest");

	static{
		postgreSQLContainer.start();
	}

	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setUp(){
		RestAssured.baseURI="http://localhost";
		RestAssured.port=port;
	}

	@Test
	void shouldSubmitOrder() {
		String submitOrderJson = """
                {
                     "skuCode": "iphone_15",
                     "price": 1000,
                     "quantity": 1
                }
                """;

		//InventoryStubs.stubInventoryCall("iphone_15", 1);

		var responseBodyString = RestAssured.given()
				.contentType("application/json")
				.body(submitOrderJson)
				.when()
				.post("/api/order")
				.then()
				.log().all()
				.statusCode(201)
				.extract()
				.body().asString();

		assertThat(responseBodyString, Matchers.is("Order Placed Successfully"));
	}

	 */


}
