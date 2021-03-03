package uk.gov.hmcts.reform.exui.performance.scenarios

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import uk.gov.hmcts.reform.exui.performance.scenarios.utils.Environment
import uk.gov.hmcts.reform.exui.performance.scenarios.utils.FR_Applicant_Header._

object EXUI_FR_Applicant  {

	val minThinkTime = Environment.minThinkTimeFR
	val maxThinkTime = Environment.maxThinkTimeFR


	/*======================================================================================
*Business process : As part of the create FR application for a specific divorce application
* Below group contains all the requests related to create FR case - click case create
======================================================================================*/

	val createCase = group("XUI${service}_030_CreateCase1") {
		exec(http("XUI${service}_030_CreateCase1")
			.get("/aggregated/caseworkers/:uid/jurisdictions?access=create")
			.headers(headers_2)
			.check(status in(200, 304)))
	}
		.pause(minThinkTime, maxThinkTime)

		/*======================================================================================
    *Business process : As part of the create FR application for a specific divorce application
    * Below group contains all the requests related to create FR case - create case event
    ======================================================================================*/

		.group("XUI${service}_040_CreateCaseEvent") {
			exec(http("XUI${service}_040_005_CreateCaseEvent")
				.get("/data/internal/case-types/FinancialRemedyMVP2/event-triggers/FR_solicitorCreate?ignore-warning=false")
				.headers(headers_6)
				.check(status in(200, 304)))

				.exec(http("XUI${service}_040_010_CreateCaseEvent2")
					.get("/data/internal/case-types/FinancialRemedyMVP2/event-triggers/FR_solicitorCreate?ignore-warning=false")
					.headers(headers_8)
					.check(jsonPath("$..event_token").saveAs("eventToken"))
					.check(status in(200, 304)))

				.exec(http("XUI${service}_040_015_CreateCaseProfile")
					.get("/data/internal/profile")
					.headers(headers_9)
					.check(status in(200, 304)))

		}
		.pause(minThinkTime, maxThinkTime)

		/*======================================================================================
    *Business process : As part of the create FR application for a specific divorce application
    * Below group contains all the requests related to create FR case - Create Solicitor
    ======================================================================================*/


		.group("XUI${service}_050_CreateSolicitor") {
			exec(http("XUI${service}_050_CreateSolicitor")
				.post("/data/case-types/FinancialRemedyMVP2/validate?pageId=FR_solicitorCreate1")
				.headers(headers_10)
				.body(StringBody(
					"""{
					|  "data": {
					|    "isAdmin": null
					|  },
					|  "event": {
					|    "id": "FR_solicitorCreate",
					|    "summary": "",
					|    "description": ""
					|  },
					|  "event_data": {
					|    "isAdmin": null
					|  },
					|  "event_token": "${eventToken}",
					|  "ignore_warning": false
					|}""".stripMargin))
				.check(status in(200, 304)))
		}
		.pause(minThinkTime, maxThinkTime)

		/*======================================================================================
    *Business process : As part of the create FR application for a specific divorce application
    * Below group contains all the requests related to create FR case - Get Share Case Orgs
    ======================================================================================*/

		.group("XUI${service}_060_GetShareCaseOrgs") {
			exec(http("XUI${service}_060_GetShareCaseOrgs")
				.get("/api/caseshare/orgs")
				.headers(headers_16)
				.check(status in(200, 304)))
		}
		.pause(minThinkTime, maxThinkTime)

		/*======================================================================================
    *Business process : As part of the create FR application for a specific divorce application
    * Below group contains all the requests related to create FR case - get address of solicitor
    ======================================================================================*/

		.group("XUI${service}_070_GetAddress") {
			exec(http("XUI${service}_070_GetAddress")
				.get("/api/addresses?postcode=SW1H9AJ")
				.headers(headers_15)
				.check(status in(200, 304)))
		}
		.pause(minThinkTime, maxThinkTime)

		/*======================================================================================
    *Business process : As part of the create FR application for a specific divorce application
    * Below group contains all the requests related to create FR case - Create Solicitor2
    ======================================================================================*/
		.group("XUI${service}_080_CreateSolicitor2") {
			exec(http("XUI${service}_080_CreateSolicitor2")
				.post("/data/case-types/FinancialRemedyMVP2/validate?pageId=FR_solicitorCreate2")
				.headers(headers_18)
				.body(StringBody(
					"""{
						|  "data": {
						|    "solicitorName": "Joe Bloggs",
						|    "ApplicantOrganisationPolicy": {
						|      "OrgPolicyCaseAssignedRole": "[APPSOLICITOR]",
						|      "OrgPolicyReference": null,
						|      "Organisation": {
						|        "OrganisationID": "${orgref}",
						|        "OrganisationName": "${orgname}"
						|      }
						|    },
						|    "solicitorFirm": "HMCTS",
						|    "solicitorReference": "${orgref}",
						|    "solicitorAddress": {
						|      "AddressLine1": "Ministry Of Justice",
						|      "AddressLine2": "Seventh Floor 102 Petty France",
						|      "AddressLine3": "",
						|      "PostTown": "London",
						|      "County": "",
						|      "PostCode": "TS1 1ST",
						|      "Country": "United Kingdom"
						|    },
						|    "solicitorPhone": null,
						|    "solicitorEmail": "sscs.ptest.4@mailinator.com",
						|    "solicitorDXnumber": null,
						|    "solicitorAgreeToReceiveEmails": "No"
						|  },
						|  "event": {
						|    "id": "FR_solicitorCreate",
						|    "summary": "",
						|    "description": ""
						|  },
						|  "event_data": {
						|    "isAdmin": "No",
						|    "solicitorName": "Joe Bloggs",
						|    "ApplicantOrganisationPolicy": {
						|      "OrgPolicyCaseAssignedRole": "[APPSOLICITOR]",
						|      "OrgPolicyReference": null,
						|      "Organisation": {
						|        "OrganisationID": "${orgref}",
						|        "OrganisationName": "${orgname}"
						|      }
						|    },
						|    "solicitorFirm": "HMCTS",
						|    "solicitorReference": "${orgref}",
						|    "solicitorAddress": {
						|      "AddressLine1": "Ministry Of Justice",
						|      "AddressLine2": "Seventh Floor 102 Petty France",
						|      "AddressLine3": "",
						|      "PostTown": "London",
						|      "County": "",
						|      "PostCode": "TS1 1ST",
						|      "Country": "United Kingdom"
						|    },
						|    "solicitorPhone": null,
						|    "solicitorEmail": "sscs.ptest.4@mailinator.com",
						|    "solicitorDXnumber": null,
						|    "solicitorAgreeToReceiveEmails": "No"
						|  },
						|  "event_token": "${eventToken}",
						|  "ignore_warning": false
						|}""".stripMargin))
				.check(status in(200, 304)))
		}
		.pause(minThinkTime, maxThinkTime)

		/*======================================================================================
    *Business process : As part of the create FR application for a specific divorce application
    * Below group contains all the requests related to create FR case - Create Solicitor3
    ======================================================================================*/
		.group("XUI${service}_090_CreateSolicitor3") {
			exec(http("XUI${service}_090_CreateSolicitor3")
				.post("/data/case-types/FinancialRemedyMVP2/validate?pageId=FR_solicitorCreate3")
				.headers(headers_22)
				.body(StringBody(
					"""{
						|  "data": {
						|    "divorceCaseNumber": "EZ11D81265",
						|    "divorceStageReached": "Petition Issued"
						|  },
						|  "event": {
						|    "id": "FR_solicitorCreate",
						|    "summary": "",
						|    "description": ""
						|  },
						|  "event_data": {
						|    "isAdmin": "No",
						|    "solicitorName": "Joe Bloggs",
						|    "ApplicantOrganisationPolicy": {
						|      "OrgPolicyCaseAssignedRole": "[APPSOLICITOR]",
						|      "OrgPolicyReference": null,
						|      "Organisation": {
						|        "OrganisationID": "${orgref}",
						|        "OrganisationName": "${orgname}"
						|      }
						|    },
						|    "solicitorFirm": "HMCTS",
						|    "solicitorReference": "${orgref}",
						|    "solicitorAddress": {
						|      "AddressLine1": "Ministry Of Justice",
						|      "AddressLine2": "Seventh Floor 102 Petty France",
						|      "AddressLine3": "",
						|      "PostTown": "London",
						|      "County": "",
						|      "PostCode": "TS1 1ST",
						|      "Country": "United Kingdom"
						|    },
						|    "solicitorPhone": null,
						|    "solicitorEmail": "sscs.ptest.4@mailinator.com",
						|    "solicitorDXnumber": null,
						|    "solicitorAgreeToReceiveEmails": "No",
						|    "divorceCaseNumber": "EZ11D81265",
						|    "divorceStageReached": "Petition Issued"
						|  },
						|  "event_token": "${eventToken}",
						|  "ignore_warning": false
						|}""".stripMargin))
				.check(status in(200, 304)))
		}
		.pause(minThinkTime, maxThinkTime)

		/*======================================================================================
    *Business process : As part of the create FR application for a specific divorce application
    * Below group contains all the requests related to create FR case - Create Solicitor4
    ======================================================================================*/

		.group("XUI${service}_100_CreateSolicitor4") {
			exec(http("XUI${service}_100_CreateSolicitor4")
				.post("/data/case-types/FinancialRemedyMVP2/validate?pageId=FR_solicitorCreate4")
				.headers(headers_26)
				.body(StringBody(
					"""{
						|  "data": {
						|    "applicantFMName": "John",
						|    "applicantLName": "Smith",
						|    "regionList": "wales",
						|    "walesFRCList": "northwales",
						|    "northWalesCourtList": "FR_northwalesList_5"
						|  },
						|  "event": {
						|    "id": "FR_solicitorCreate",
						|    "summary": "",
						|    "description": ""
						|  },
						|  "event_data": {
						|    "isAdmin": "No",
						|    "solicitorName": "Joe Bloggs",
						|    "ApplicantOrganisationPolicy": {
						|      "OrgPolicyCaseAssignedRole": "[APPSOLICITOR]",
						|      "OrgPolicyReference": null,
						|      "Organisation": {
						|        "OrganisationID": "${orgref}",
						|        "OrganisationName": "${orgname}"
						|      }
						|    },
						|    "solicitorFirm": "HMCTS",
						|    "solicitorReference": "${orgref}",
						|    "solicitorAddress": {
						|      "AddressLine1": "Ministry Of Justice",
						|      "AddressLine2": "Seventh Floor 102 Petty France",
						|      "AddressLine3": "",
						|      "PostTown": "London",
						|      "County": "",
						|      "PostCode": "TS1 1ST",
						|      "Country": "United Kingdom"
						|    },
						|    "solicitorPhone": null,
						|    "solicitorEmail": "sscs.ptest.4@mailinator.com",
						|    "solicitorDXnumber": null,
						|    "solicitorAgreeToReceiveEmails": "No",
						|    "divorceCaseNumber": "EZ11D81265",
						|    "divorceStageReached": "Petition Issued",
						|    "applicantFMName": "John",
						|    "applicantLName": "Smith",
						|    "regionList": "wales",
						|    "walesFRCList": "northwales",
						|    "northWalesCourtList": "FR_northwalesList_5"
						|  },
						|  "event_token": "${eventToken}",
						|  "ignore_warning": false
						|}""".stripMargin))
				.check(status in(200, 304)))
		}
		.pause(minThinkTime, maxThinkTime)

		/*======================================================================================
    *Business process : As part of the create FR application for a specific divorce application
    * Below group contains all the requests related to create FR case - Get Share Case Orgs
    ======================================================================================*/

		.group("XUI${service}_110_GetShareCaseOrgs") {
			exec(http("XUI${service}_110_GetShareCaseOrgs")
				.get("/api/caseshare/orgs")
				.headers(headers_27)
				.check(status in(200, 304)))
		}
		.pause(minThinkTime, maxThinkTime)
		/*======================================================================================
    *Business process : As part of the create FR application for a specific divorce application
    * Below group contains all the requests related to create FR case - Get address
    ======================================================================================*/
		.group("XUI${service}_120_GetAddress2") {
			exec(http("XUI${service}_120_GetAddress2")
				.get("/api/addresses?postcode=SW1H9AJ")
				.headers(headers_31)
				.check(status in(200, 304)))
		}
		.pause(minThinkTime, maxThinkTime)

		/*======================================================================================
    *Business process : As part of the create FR application for a specific divorce application
    * Below group contains all the requests related to create FR case - Create Solicitor 5
    ======================================================================================*/
		.group("XUI${service}_130_CreateSolicitor5") {
			exec(http("XUI${service}_130_CreateSolicitor5")
				.post("/data/case-types/FinancialRemedyMVP2/validate?pageId=FR_solicitorCreate5")
				.headers(headers_34)
				.body(StringBody(
					"""{
						|  "data": {
						|    "appRespondentFMName": "HMCTS",
						|    "appRespondentLName": "Bloggs",
						|    "appRespondentRep": "Yes",
						|    "rSolicitorName": "John Smith",
						|    "RespondentOrganisationPolicy": {
						|      "OrgPolicyCaseAssignedRole": "[RESPSOLICITOR]",
						|      "OrgPolicyReference": null,
						|      "Organisation": {
						|        "OrganisationID": "${respondent_orgref}",
						|        "OrganisationName": "${respondent_orgname}"
						|      }
						|    },
						|    "rSolicitorFirm": "probateorg-7v7n2",
						|    "rSolicitorReference": null,
						|    "rSolicitorAddress": {
						|      "AddressLine1": "Ministry Of Justice",
						|      "AddressLine2": "Seventh Floor 102 Petty France",
						|      "AddressLine3": "",
						|      "PostTown": "London",
						|      "County": "",
						|      "PostCode": "SW1H 9AJ",
						|      "Country": "United Kingdom"
						|    },
						|    "rSolicitorPhone": null,
						|    "rSolicitorEmail": null,
						|    "rSolicitorDXnumber": null
						|  },
						|  "event": {
						|    "id": "FR_solicitorCreate",
						|    "summary": "",
						|    "description": ""
						|  },
						|  "event_data": {
						|    "isAdmin": "No",
						|    "solicitorName": "Joe Bloggs",
						|    "ApplicantOrganisationPolicy": {
						|      "OrgPolicyCaseAssignedRole": "[APPSOLICITOR]",
						|      "OrgPolicyReference": null,
						|      "Organisation": {
						|        "OrganisationID": "${orgref}",
						|        "OrganisationName": "${orgname}"
						|      }
						|    },
						|    "solicitorFirm": "HMCTS",
						|    "solicitorReference": "${orgref}",
						|    "solicitorAddress": {
						|      "AddressLine1": "Ministry Of Justice",
						|      "AddressLine2": "Seventh Floor 102 Petty France",
						|      "AddressLine3": "",
						|      "PostTown": "London",
						|      "County": "",
						|      "PostCode": "TS1 1ST",
						|      "Country": "United Kingdom"
						|    },
						|    "solicitorPhone": null,
						|    "solicitorEmail": "sscs.ptest.4@mailinator.com",
						|    "solicitorDXnumber": null,
						|    "solicitorAgreeToReceiveEmails": "No",
						|    "divorceCaseNumber": "EZ11D81265",
						|    "divorceStageReached": "Petition Issued",
						|    "applicantFMName": "John",
						|    "applicantLName": "Smith",
						|    "regionList": "wales",
						|    "walesFRCList": "northwales",
						|    "northWalesCourtList": "FR_northwalesList_5",
						|    "appRespondentFMName": "Joe",
						|    "appRespondentLName": "Bloggs",
						|    "appRespondentRep": "Yes",
						|    "rSolicitorName": "John Smith",
						|    "RespondentOrganisationPolicy": {
						|      "OrgPolicyCaseAssignedRole": "[RESPSOLICITOR]",
						|      "OrgPolicyReference": null,
						|      "Organisation": {
						|        "OrganisationID": "${respondent_orgref}",
						|        "OrganisationName": "${respondent_orgname}"
						|      }
						|    },
						|    "rSolicitorFirm": "probateorg-7v7n2",
						|    "rSolicitorReference": null,
						|    "rSolicitorAddress": {
						|      "AddressLine1": "Ministry Of Justice",
						|      "AddressLine2": "Seventh Floor 102 Petty France",
						|      "AddressLine3": "",
						|      "PostTown": "London",
						|      "County": "",
						|      "PostCode": "SW1H 9AJ",
						|      "Country": "United Kingdom"
						|    },
						|    "rSolicitorPhone": null,
						|    "rSolicitorEmail": null,
						|    "rSolicitorDXnumber": null
						|  },
						|  "event_token": "${eventToken}",
						|  "ignore_warning": false
						|}""".stripMargin))
				.check(status in(200, 304)))
		}
		.pause(minThinkTime, maxThinkTime)

		/*======================================================================================
    *Business process : As part of the create FR application for a specific divorce application
    * Below group contains all the requests related to create FR case - Solicitor 6
    ======================================================================================*/
		.group("XUI${service}_140_CreateSolicitor6") {
			exec(http("XUI${service}_140_CreateSolicitor6")
				.post("/data/case-types/FinancialRemedyMVP2/validate?pageId=FR_solicitorCreate6")
				.headers(headers_37)
				.body(StringBody(
					"""{
						|  "data": {
						|    "natureOfApplication2": [
						|      "Lump Sum Order"
						|    ]
						|  },
						|  "event": {
						|    "id": "FR_solicitorCreate",
						|    "summary": "",
						|    "description": ""
						|  },
						|  "event_data": {
						|    "isAdmin": "No",
						|    "solicitorName": "Joe Bloggs",
						|    "ApplicantOrganisationPolicy": {
						|      "OrgPolicyCaseAssignedRole": "[APPSOLICITOR]",
						|      "OrgPolicyReference": null,
						|      "Organisation": {
						|        "OrganisationID": "${orgref}",
						|        "OrganisationName": "${orgname}"
						|      }
						|    },
						|    "solicitorFirm": "HMCTS",
						|    "solicitorReference": "${orgref}",
						|    "solicitorAddress": {
						|      "AddressLine1": "Ministry Of Justice",
						|      "AddressLine2": "Seventh Floor 102 Petty France",
						|      "AddressLine3": "",
						|      "PostTown": "London",
						|      "County": "",
						|      "PostCode": "TS1 1ST",
						|      "Country": "United Kingdom"
						|    },
						|    "solicitorPhone": null,
						|    "solicitorEmail": "sscs.ptest.4@mailinator.com",
						|    "solicitorDXnumber": null,
						|    "solicitorAgreeToReceiveEmails": "No",
						|    "divorceCaseNumber": "EZ11D81265",
						|    "divorceStageReached": "Petition Issued",
						|    "applicantFMName": "John",
						|    "applicantLName": "Smith",
						|    "regionList": "wales",
						|    "walesFRCList": "northwales",
						|    "northWalesCourtList": "FR_northwalesList_5",
						|    "appRespondentFMName": "Joe",
						|    "appRespondentLName": "Bloggs",
						|    "appRespondentRep": "Yes",
						|    "rSolicitorName": "John Smith",
						|    "RespondentOrganisationPolicy": {
						|      "OrgPolicyCaseAssignedRole": "[RESPSOLICITOR]",
						|      "OrgPolicyReference": null,
						|      "Organisation": {
						|        "OrganisationID": "${respondent_orgref}",
						|        "OrganisationName": "${respondent_orgname}"
						|      }
						|    },
						|    "rSolicitorFirm": "probateorg-7v7n2",
						|    "rSolicitorReference": null,
						|    "rSolicitorAddress": {
						|      "AddressLine1": "Ministry Of Justice",
						|      "AddressLine2": "Seventh Floor 102 Petty France",
						|      "AddressLine3": "",
						|      "PostTown": "London",
						|      "County": "",
						|      "PostCode": "SW1H 9AJ",
						|      "Country": "United Kingdom"
						|    },
						|    "rSolicitorPhone": null,
						|    "rSolicitorEmail": null,
						|    "rSolicitorDXnumber": null,
						|    "natureOfApplication2": [
						|      "Lump Sum Order"
						|    ]
						|  },
						|  "event_token": "${eventToken}",
						|  "ignore_warning": false
						|}""".stripMargin))
				.check(status in(200, 304)))
		}
		.pause(minThinkTime, maxThinkTime)

		/*======================================================================================
    *Business process : As part of the create FR application for a specific divorce application
    * Below group contains all the requests related to create FR case - Get Post documents upload
    ======================================================================================*/
		.group("XUI${service}_150_Upload Documents") {
			exec(http("XUI${service}_150_Upload Documents")
				.post("/documents")
				.headers(headers_41)
				.bodyPart(RawFileBodyPart("files", "dummy.pdf")
					.fileName("dummy.pdf")
					.transferEncoding("binary"))
				.asMultipartForm
				.formParam("classification", "PUBLIC")
				.check(jsonPath("$..href").saveAs("href1"))
				.check(status in(200, 304)))
		}
		.pause(minThinkTime, maxThinkTime)

		/*======================================================================================
    *Business process : As part of the create FR application for a specific divorce application
    * Below group contains all the requests related to create FR case - Create Solicitor
    ======================================================================================*/
		.group("XUI${service}_160_CreateSolicitor8") {
			exec(http("XUI${service}_160_CreateSolicitor8")
				.post("/data/case-types/FinancialRemedyMVP2/validate?pageId=FR_solicitorCreate8")
				.headers(headers_44)
				.body(StringBody(
					"""{
						|  "data": {
						|    "consentOrder": {
						|      "document_url": "${href1}",
						|      "document_binary_url": "${href1}/binary",
						|      "document_filename": "dummy.pdf"
						|    }
						|  },
						|  "event": {
						|    "id": "FR_solicitorCreate",
						|    "summary": "",
						|    "description": ""
						|  },
						|  "event_data": {
						|    "isAdmin": "No",
						|    "solicitorName": "Joe Bloggs",
						|    "ApplicantOrganisationPolicy": {
						|      "OrgPolicyCaseAssignedRole": "[APPSOLICITOR]",
						|      "OrgPolicyReference": null,
						|      "Organisation": {
						|        "OrganisationID": "${orgref}",
						|        "OrganisationName": "${orgname}"
						|      }
						|    },
						|    "solicitorFirm": "HMCTS",
						|    "solicitorReference": "${orgref}",
						|    "solicitorAddress": {
						|      "AddressLine1": "Ministry Of Justice",
						|      "AddressLine2": "Seventh Floor 102 Petty France",
						|      "AddressLine3": "",
						|      "PostTown": "London",
						|      "County": "",
						|      "PostCode": "TS1 1ST",
						|      "Country": "United Kingdom"
						|    },
						|    "solicitorPhone": null,
						|    "solicitorEmail": "sscs.ptest.4@mailinator.com",
						|    "solicitorDXnumber": null,
						|    "solicitorAgreeToReceiveEmails": "No",
						|    "divorceCaseNumber": "EZ11D81265",
						|    "divorceStageReached": "Petition Issued",
						|    "applicantFMName": "John",
						|    "applicantLName": "Smith",
						|    "regionList": "wales",
						|    "walesFRCList": "northwales",
						|    "northWalesCourtList": "FR_northwalesList_5",
						|    "appRespondentFMName": "Joe",
						|    "appRespondentLName": "Bloggs",
						|    "appRespondentRep": "Yes",
						|    "rSolicitorName": "John Smith",
						|    "RespondentOrganisationPolicy": {
						|      "OrgPolicyCaseAssignedRole": "[RESPSOLICITOR]",
						|      "OrgPolicyReference": null,
						|      "Organisation": {
						|        "OrganisationID": "${respondent_orgref}",
						|        "OrganisationName": "${respondent_orgname}"
						|      }
						|    },
						|    "rSolicitorFirm": "probateorg-7v7n2",
						|    "rSolicitorReference": null,
						|    "rSolicitorAddress": {
						|      "AddressLine1": "Ministry Of Justice",
						|      "AddressLine2": "Seventh Floor 102 Petty France",
						|      "AddressLine3": "",
						|      "PostTown": "London",
						|      "County": "",
						|      "PostCode": "SW1H 9AJ",
						|      "Country": "United Kingdom"
						|    },
						|    "rSolicitorPhone": null,
						|    "rSolicitorEmail": null,
						|    "rSolicitorDXnumber": null,
						|    "natureOfApplication2": [
						|      "Lump Sum Order"
						|    ],
						|    "consentOrder": {
						|      "document_url": "${href1}",
						|      "document_binary_url": "${href1}/binary",
						|      "document_filename": "dummy.pdf"
						|    }
						|  },
						|  "event_token": "${eventToken}",
						|  "ignore_warning": false
						|}""".stripMargin))
				.check(status in(200, 304)))
		}
		.pause(minThinkTime, maxThinkTime)

		/*======================================================================================
    *Business process : As part of the create FR application for a specific divorce application
    * Below group contains all the requests related to create FR case - Upload Document 2
    ======================================================================================*/

		.group("XUI${service}_170_Upload Documents2") {
			exec(http("XUI${service}_170_UploadDocuments2")
				.post("/documents")
				.headers(headers_48)
				.bodyPart(RawFileBodyPart("files", "dummy.pdf")
					.fileName("dummy.pdf")
					.transferEncoding("binary"))
				.asMultipartForm
				.formParam("classification", "PUBLIC")
				.check(jsonPath("$..href").saveAs("href2"))
				.check(status in(200, 304)))
		}
		.pause(minThinkTime, maxThinkTime)

		/*======================================================================================
    *Business process : As part of the create FR application for a specific divorce application
    * Below group contains all the requests related to create FR case - Create Solicitor
    ======================================================================================*/
		.group("XUI${service}_180_CreateSolicitor9") {
			exec(http("XUI${service}_180_CreateSolicitor9")
				.post("/data/case-types/FinancialRemedyMVP2/validate?pageId=FR_solicitorCreate9")
				.headers(headers_51)
				.body(StringBody(
					"""{
						|  "data": {
						|    "d81Question": "Yes",
						|    "d81Joint": {
						|      "document_url": "${href2}",
						|      "document_binary_url": "${href2}/binary",
						|      "document_filename": "dummy.pdf"
						|    }
						|  },
						|  "event": {
						|    "id": "FR_solicitorCreate",
						|    "summary": "",
						|    "description": ""
						|  },
						|  "event_data": {
						|    "isAdmin": "No",
						|    "solicitorName": "Joe Bloggs",
						|    "ApplicantOrganisationPolicy": {
						|      "OrgPolicyCaseAssignedRole": "[APPSOLICITOR]",
						|      "OrgPolicyReference": null,
						|      "Organisation": {
						|        "OrganisationID": "${orgref}",
						|        "OrganisationName": "${orgname}"
						|      }
						|    },
						|    "solicitorFirm": "HMCTS",
						|    "solicitorReference": "${orgref}",
						|    "solicitorAddress": {
						|      "AddressLine1": "Ministry Of Justice",
						|      "AddressLine2": "Seventh Floor 102 Petty France",
						|      "AddressLine3": "",
						|      "PostTown": "London",
						|      "County": "",
						|      "PostCode": "TS1 1ST",
						|      "Country": "United Kingdom"
						|    },
						|    "solicitorPhone": null,
						|    "solicitorEmail": "sscs.ptest.4@mailinator.com",
						|    "solicitorDXnumber": null,
						|    "solicitorAgreeToReceiveEmails": "No",
						|    "divorceCaseNumber": "EZ11D81265",
						|    "divorceStageReached": "Petition Issued",
						|    "applicantFMName": "John",
						|    "applicantLName": "Smith",
						|    "regionList": "wales",
						|    "walesFRCList": "northwales",
						|    "northWalesCourtList": "FR_northwalesList_5",
						|    "appRespondentFMName": "Joe",
						|    "appRespondentLName": "Bloggs",
						|    "appRespondentRep": "Yes",
						|    "rSolicitorName": "John Smith",
						|    "RespondentOrganisationPolicy": {
						|      "OrgPolicyCaseAssignedRole": "[RESPSOLICITOR]",
						|      "OrgPolicyReference": null,
						|      "Organisation": {
						|        "OrganisationID": "${respondent_orgref}",
						|        "OrganisationName": "${respondent_orgname}"
						|      }
						|    },
						|    "rSolicitorFirm": "probateorg-7v7n2",
						|    "rSolicitorReference": null,
						|    "rSolicitorAddress": {
						|      "AddressLine1": "Ministry Of Justice",
						|      "AddressLine2": "Seventh Floor 102 Petty France",
						|      "AddressLine3": "",
						|      "PostTown": "London",
						|      "County": "",
						|      "PostCode": "SW1H 9AJ",
						|      "Country": "United Kingdom"
						|    },
						|    "rSolicitorPhone": null,
						|    "rSolicitorEmail": null,
						|    "rSolicitorDXnumber": null,
						|    "natureOfApplication2": [
						|      "Lump Sum Order"
						|    ],
						|    "consentOrder": {
						|      "document_url": "${href1}",
						|      "document_binary_url": "${href1}/binary",
						|      "document_filename": "dummy.pdf"
						|    },
						|    "d81Question": "Yes",
						|    "d81Joint": {
						|      "document_url": "${href2}",
						|      "document_binary_url": "${href2}/binary",
						|      "document_filename": "dummy.pdf"
						|    }
						|  },
						|  "event_token": "${eventToken}",
						|  "ignore_warning": false
						|}""".stripMargin))
				.check(status in(200, 304)))
		}
		.pause(minThinkTime, maxThinkTime)

		/*======================================================================================
    *Business process : As part of the create FR application for a specific divorce application
    * Below group contains all the requests related to create FR case - Create Solicitor 11
    ======================================================================================*/
		.group("XUI${service}_190_CreateSolicitor11") {
			exec(http("XUI${service}_190_CreateSolicitor11")
				.post("/data/case-types/FinancialRemedyMVP2/validate?pageId=FR_solicitorCreate11")
				.headers(headers_55)
				.body(StringBody(
					"""{
						|  "data": {
						|    "otherCollection": []
						|  },
						|  "event": {
						|    "id": "FR_solicitorCreate",
						|    "summary": "",
						|    "description": ""
						|  },
						|  "event_data": {
						|    "isAdmin": "No",
						|    "solicitorName": "Joe Bloggs",
						|    "ApplicantOrganisationPolicy": {
						|      "OrgPolicyCaseAssignedRole": "[APPSOLICITOR]",
						|      "OrgPolicyReference": null,
						|      "Organisation": {
						|        "OrganisationID": "${orgref}",
						|        "OrganisationName": "${orgname}"
						|      }
						|    },
						|    "solicitorFirm": "HMCTS",
						|    "solicitorReference": "${orgref}",
						|    "solicitorAddress": {
						|      "AddressLine1": "Ministry Of Justice",
						|      "AddressLine2": "Seventh Floor 102 Petty France",
						|      "AddressLine3": "",
						|      "PostTown": "London",
						|      "County": "",
						|      "PostCode": "TS1 1ST",
						|      "Country": "United Kingdom"
						|    },
						|    "solicitorPhone": null,
						|    "solicitorEmail": "sscs.ptest.4@mailinator.com",
						|    "solicitorDXnumber": null,
						|    "solicitorAgreeToReceiveEmails": "No",
						|    "divorceCaseNumber": "EZ11D81265",
						|    "divorceStageReached": "Petition Issued",
						|    "applicantFMName": "John",
						|    "applicantLName": "Smith",
						|    "regionList": "wales",
						|    "walesFRCList": "northwales",
						|    "northWalesCourtList": "FR_northwalesList_5",
						|    "appRespondentFMName": "Joe",
						|    "appRespondentLName": "Bloggs",
						|    "appRespondentRep": "Yes",
						|    "rSolicitorName": "John Smith",
						|    "RespondentOrganisationPolicy": {
						|      "OrgPolicyCaseAssignedRole": "[RESPSOLICITOR]",
						|      "OrgPolicyReference": null,
						|      "Organisation": {
						|        "OrganisationID": "${respondent_orgref}",
						|        "OrganisationName": "${respondent_orgname}"
						|      }
						|    },
						|    "rSolicitorFirm": "probateorg-7v7n2",
						|    "rSolicitorReference": null,
						|    "rSolicitorAddress": {
						|      "AddressLine1": "Ministry Of Justice",
						|      "AddressLine2": "Seventh Floor 102 Petty France",
						|      "AddressLine3": "",
						|      "PostTown": "London",
						|      "County": "",
						|      "PostCode": "SW1H 9AJ",
						|      "Country": "United Kingdom"
						|    },
						|    "rSolicitorPhone": null,
						|    "rSolicitorEmail": null,
						|    "rSolicitorDXnumber": null,
						|    "natureOfApplication2": [
						|      "Lump Sum Order"
						|    ],
						|    "consentOrder": {
						|      "document_url": "${href1}",
						|      "document_binary_url": "${href1}/binary",
						|      "document_filename": "dummy.pdf"
						|    },
						|    "d81Question": "Yes",
						|    "d81Joint": {
						|      "document_url": "${href2}",
						|      "document_binary_url": "${href2}/binary",
						|      "document_filename": "dummy.pdf"
						|    },
						|    "otherCollection": []
						|  },
						|  "event_token": "${eventToken}",
						|  "ignore_warning": false
						|}""".stripMargin))
				.check(status in(200, 304)))
		}
		.pause(minThinkTime, maxThinkTime)

		/*======================================================================================
    *Business process : As part of the create FR application for a specific divorce application
    * Below group contains all the requests related to create FR case - Create Sol12
    ======================================================================================*/
		.group("XUI${service}_200_CreateSolicitor12") {
			exec(http("XUI${service}_200_CreateSolicitor12")
				.post("/data/case-types/FinancialRemedyMVP2/validate?pageId=FR_solicitorCreate12")
				.headers(headers_59)
				.body(StringBody(
					"""{
						|  "data": {},
						|  "event": {
						|    "id": "FR_solicitorCreate",
						|    "summary": "",
						|    "description": ""
						|  },
						|  "event_data": {
						|    "isAdmin": "No",
						|    "solicitorName": "Joe Bloggs",
						|    "ApplicantOrganisationPolicy": {
						|      "OrgPolicyCaseAssignedRole": "[APPSOLICITOR]",
						|      "OrgPolicyReference": null,
						|      "Organisation": {
						|        "OrganisationID": "${orgref}",
						|        "OrganisationName": "${orgname}"
						|      }
						|    },
						|    "solicitorFirm": "HMCTS",
						|    "solicitorReference": "${orgref}",
						|    "solicitorAddress": {
						|      "AddressLine1": "Ministry Of Justice",
						|      "AddressLine2": "Seventh Floor 102 Petty France",
						|      "AddressLine3": "",
						|      "PostTown": "London",
						|      "County": "",
						|      "PostCode": "TS1 1ST",
						|      "Country": "United Kingdom"
						|    },
						|    "solicitorPhone": null,
						|    "solicitorEmail": "sscs.ptest.4@mailinator.com",
						|    "solicitorDXnumber": null,
						|    "solicitorAgreeToReceiveEmails": "No",
						|    "divorceCaseNumber": "EZ11D81265",
						|    "divorceStageReached": "Petition Issued",
						|    "applicantFMName": "John",
						|    "applicantLName": "Smith",
						|    "regionList": "wales",
						|    "walesFRCList": "northwales",
						|    "northWalesCourtList": "FR_northwalesList_5",
						|    "appRespondentFMName": "Joe",
						|    "appRespondentLName": "Bloggs",
						|    "appRespondentRep": "Yes",
						|    "rSolicitorName": "John Smith",
						|    "RespondentOrganisationPolicy": {
						|      "OrgPolicyCaseAssignedRole": "[RESPSOLICITOR]",
						|      "OrgPolicyReference": null,
						|      "Organisation": {
						|        "OrganisationID": "${respondent_orgref}",
						|        "OrganisationName": "${respondent_orgname}"
						|      }
						|    },
						|    "rSolicitorFirm": "probateorg-7v7n2",
						|    "rSolicitorReference": null,
						|    "rSolicitorAddress": {
						|      "AddressLine1": "Ministry Of Justice",
						|      "AddressLine2": "Seventh Floor 102 Petty France",
						|      "AddressLine3": "",
						|      "PostTown": "London",
						|      "County": "",
						|      "PostCode": "SW1H 9AJ",
						|      "Country": "United Kingdom"
						|    },
						|    "rSolicitorPhone": null,
						|    "rSolicitorEmail": null,
						|    "rSolicitorDXnumber": null,
						|    "natureOfApplication2": [
						|      "Lump Sum Order"
						|    ],
						|    "consentOrder": {
						|      "document_url": "${href1}",
						|      "document_binary_url": "${href1}/binary",
						|      "document_filename": "dummy.pdf"
						|    },
						|    "d81Question": "Yes",
						|    "d81Joint": {
						|      "document_url": "${href2}",
						|      "document_binary_url": "${href2}/binary",
						|      "document_filename": "dummy.pdf"
						|    },
						|    "otherCollection": []
						|  },
						|  "event_token": "${eventToken}",
						|  "ignore_warning": false
						|}""".stripMargin))
				.check(status in(200, 304)))
		}
		.pause(minThinkTime, maxThinkTime)

		/*======================================================================================
    *Business process : As part of the create FR application for a specific divorce application
    * Below group contains all the requests related to create FR case - Get Share Case Orgs
    ======================================================================================*/

		.group("XUI${service}_210_GetShareCaseOrgs") {
			exec(http("XUI${service}_210_GetShareCaseOrgs")
				.get("/api/caseshare/orgs")
				.headers(headers_60)
				.check(status in(200, 304)))
		}
		.pause(minThinkTime, maxThinkTime)

		/*======================================================================================
    *Business process : As part of the create FR application for a specific divorce application
    * Below group contains all the requests related to create FR case - Create Case
    ======================================================================================*/
		.group("XUI${service}_220_CreateCase5") {
			exec(http("XUI${service}_220_CreateCase5")
				.get("/data/internal/profile")
				.headers(headers_61)
				.check(status in(200, 304)))
		}
		.pause(minThinkTime, maxThinkTime)

		/*======================================================================================
    *Business process : As part of the create FR application for a specific divorce application
    * Below group contains all the requests related to create FR case - Create Case 6
    ======================================================================================*/

		.group("XUI${service}_230_CreateCase6") {
			exec(http("XUI${service}_230_CreateCase6")
				.post("/data/case-types/FinancialRemedyMVP2/cases?ignore-warning=false")
				.headers(headers_64)
				.body(StringBody(
					"""{
						|  "data": {
						|    "isAdmin": "No",
						|    "solicitorName": "Joe Bloggs",
						|    "ApplicantOrganisationPolicy": {
						|      "OrgPolicyCaseAssignedRole": "[APPSOLICITOR]",
						|      "OrgPolicyReference": null,
						|      "Organisation": {
						|        "OrganisationID": "${orgref}",
						|        "OrganisationName": "${orgname}"
						|      }
						|    },
						|    "solicitorFirm": "HMCTS",
						|    "solicitorReference": "${orgref}",
						|    "solicitorAddress": {
						|      "AddressLine1": "Ministry Of Justice",
						|      "AddressLine2": "Seventh Floor 102 Petty France",
						|      "AddressLine3": "",
						|      "PostTown": "London",
						|      "County": "",
						|      "PostCode": "TS1 1ST",
						|      "Country": "United Kingdom"
						|    },
						|    "solicitorPhone": null,
						|    "solicitorEmail": "sscs.ptest.4@mailinator.com",
						|    "solicitorDXnumber": null,
						|    "solicitorAgreeToReceiveEmails": "No",
						|    "divorceCaseNumber": "EZ11D81265",
						|    "divorceStageReached": "Petition Issued",
						|    "applicantFMName": "John",
						|    "applicantLName": "Smith",
						|    "regionList": "wales",
						|    "walesFRCList": "northwales",
						|    "northWalesCourtList": "FR_northwalesList_5",
						|    "appRespondentFMName": "Joe",
						|    "appRespondentLName": "Bloggs",
						|    "appRespondentRep": "Yes",
						|    "rSolicitorName": "John Smith",
						|    "RespondentOrganisationPolicy": {
						|      "OrgPolicyCaseAssignedRole": "[RESPSOLICITOR]",
						|      "OrgPolicyReference": null,
						|      "Organisation": {
						|        "OrganisationID": "${respondent_orgref}",
						|        "OrganisationName": "${respondent_orgname}"
						|      }
						|    },
						|    "rSolicitorFirm": "probateorg-7v7n2",
						|    "rSolicitorReference": null,
						|    "rSolicitorAddress": {
						|      "AddressLine1": "Ministry Of Justice",
						|      "AddressLine2": "Seventh Floor 102 Petty France",
						|      "AddressLine3": "",
						|      "PostTown": "London",
						|      "County": "",
						|      "PostCode": "SW1H 9AJ",
						|      "Country": "United Kingdom"
						|    },
						|    "rSolicitorPhone": null,
						|    "rSolicitorEmail": null,
						|    "rSolicitorDXnumber": null,
						|    "natureOfApplication2": [
						|      "Lump Sum Order"
						|    ],
						|    "consentOrder": {
						|      "document_url": "${href1}",
						|      "document_binary_url": "${href1}/binary",
						|      "document_filename": "dummy.pdf"
						|    },
						|    "d81Question": "Yes",
						|    "d81Joint": {
						|      "document_url": "${href2}",
						|      "document_binary_url": "${href2}/binary",
						|      "document_filename": "dummy.pdf"
						|    },
						|    "otherCollection": []
						|  },
						|  "event": {
						|    "id": "FR_solicitorCreate",
						|    "summary": "",
						|    "description": ""
						|  },
						|  "event_token": "${eventToken}",
						|  "ignore_warning": false,
						|  "draft_id": null
						|}""".stripMargin))
				.check(jsonPath("$..id").saveAs("caseId"))
				.check(status in(200, 304, 201)))


		}
		.pause(minThinkTime, maxThinkTime)

		/*======================================================================================
    *Business process : As part of the create FR application for a specific divorce application
    * Below group contains all the requests related to create FR case - Get case
    ======================================================================================*/
		.group("XUI${service}_240_GetCase") {
			exec(http("XUI${service}_240_GetCase")
				.get("/data/internal/cases/${caseId}")
				.headers(headers_66)
				.check(status in(200, 304)))
		}
		.pause(minThinkTime, maxThinkTime)

}