package uk.gov.hmcts.reform.exui.performance.simulations

import io.gatling.core.Predef._
import uk.gov.hmcts.reform.exui.performance.scenarios._
import uk.gov.hmcts.reform.exui.performance.scenarios.utils._

class ExUI extends Simulation {

	val BaseURL = Environment.baseURL
	val feedUserDataFR = csv("FRSolicitorData.csv").circular


  val FRhttpProtocol = Environment.HttpProtocol
		.baseUrl(BaseURL)
		.inferHtmlResources()
		.silentResources

	val EXUIFinancialRemedyScn = scenario("Scenario FR")
		.exitBlockOnFail {
			feed(feedUserDataFR)
				.exec(EXUIMCLogin.manageCasesHomePage)
				.exec(EXUIMCLogin.manageCaseslogin)
				.exec(EXUI_FR_Applicant.createFRCase)
				.exec(EXUIMCLogin.manageCase_Logout)
		}

	setUp(
		EXUIFinancialRemedyScn.inject(rampUsers(10) during (180))
	).protocols(FRhttpProtocol)
		.assertions(global.successfulRequests.percent.gte(95),
		forAll.successfulRequests.percent.gte(80))

}
