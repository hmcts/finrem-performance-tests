package uk.gov.hmcts.reform.exui.performance.scenarios.utils

object CaseworkerHeader {

val baseURL = Environment.baseURL

val headers_2 = Map(
    "accept" -> "application/json",
    "accept-encoding" -> "gzip, deflate, br",
    "accept-language" -> "en-GB,en-US;q=0.9,en;q=0.8",
    "content-type" -> "application/json",
    "origin" -> baseURL,
    "sec-ch-ua" -> """Chromium";v="88", "Google Chrome";v="88", ";Not A Brand";v="99""",
    "sec-ch-ua-mobile" -> "?0",
    "sec-fetch-dest" -> "empty",
    "sec-fetch-mode" -> "cors",
    "sec-fetch-site" -> "same-origin",
    "x-dtpc" -> "3$86414219_500h17vQCAUNKDPAQEHAVVUOSPMUPTQGGLFGMFD-0e16",
    "x-dtreferer" -> {baseURL + "/"},
    "x-xsrf-token" -> "${xsrfToken2}")

val headers_4 = Map(
    "Pragma" -> "no-cache",
    "Sec-Fetch-Dest" -> "empty",
    "Sec-Fetch-Mode" -> "cors",
    "Sec-Fetch-Site" -> "same-origin")

    val headers_search = Map(
        "accept" -> "application/json, text/plain, */*",
        "sec-fetch-dest" -> "empty",
        "sec-fetch-mode" -> "cors",
        "sec-fetch-site" -> "same-origin",
        "x-dtpc" -> "2$595308963_803h13vGPUVHCQAOWPMASHWHNFGLKUMKEKFNFBO-0e7",
        "x-dtreferer" -> ({baseURL+"/cases/case-search"}))

    val headers_undefined = Map(
        "accept" -> "application/json, text/plain, */*",
        "sec-fetch-dest" -> "empty",
        "sec-fetch-mode" -> "cors",
        "sec-fetch-site" -> "same-origin",
        "x-dtpc" -> "2$595308963_803h14vGPUVHCQAOWPMASHWHNFGLKUMKEKFNFBO-0e7",
        "x-dtreferer" -> ({baseURL+"/cases/case-search"}))

val headers_5 = Map(
    "accept" -> "application/vnd.uk.gov.hmcts.ccd-data-store-api.ui-case-view.v2+json",
    "content-type" -> "application/json",
    "experimental" -> "true",
    "sec-fetch-dest" -> "empty",
    "sec-fetch-mode" -> "cors",
    "sec-fetch-site" -> "same-origin",
    "x-dtpc" -> "2$595308963_803h12vGPUVHCQAOWPMASHWHNFGLKUMKEKFNFBO-0e7",
    "x-xsrf-token" -> "${xsrfToken2}")

val headers_documents = Map(
  "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3",
  "Accept-Encoding" -> "gzip, deflate, br",
  "Accept-Language" -> "en-US,en;q=0.9",
  "Sec-Fetch-Mode" -> "navigate",
  "Sec-Fetch-Site" -> "none",
  "Upgrade-Insecure-Requests" -> "1",
  "x-xsrf-token" -> "${xsrfToken2}")

}