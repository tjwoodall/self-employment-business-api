/*
 * Copyright 2023 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package v4.retrieveAnnualSubmission.def3.model.response

import play.api.libs.json.Json
import shared.utils.UnitSpec
import v4.retrieveAnnualSubmission.def3.model.Def3_RetrieveAnnualSubmissionFixture

class Def3_RetrieveAnnualSubmissionResponseSpec extends UnitSpec with Def3_RetrieveAnnualSubmissionFixture {

  private val retrieveAnnualSubmissionResponse = Def3_RetrieveAnnualSubmissionResponse(
    allowances = Some(RetrieveAllowances(None, None, None, None, None, None, None, None, None, None, None)),
    adjustments = Some(RetrieveAdjustments(None, None, None, None, None, None, None, None, None, None, None)),
    nonFinancials = Some(RetrieveNonFinancials(businessDetailsChangedRecently = true, None))
  )

  "reads" should {
    "return a valid model" when {
      "given valid JSON" in {
        val result = Json
          .parse(s"""{
             |  "annualAllowances": {},
             |  "annualAdjustments": {},
             |  "annualNonFinancials": {
             |    "businessDetailsChangedRecently": true
             |  }
             |}
             |""".stripMargin)
          .as[Def3_RetrieveAnnualSubmissionResponse]

        result shouldBe retrieveAnnualSubmissionResponse
      }
    }
  }

  "writes" should {
    "return valid JSON" when {
      "given a valid Scala object" in {
        val result = Json.toJson(retrieveAnnualSubmissionResponse)

        result shouldBe
          Json.parse(s"""{
               |  "allowances": {},
               |  "adjustments": {},
               |  "nonFinancials": {
               |    "businessDetailsChangedRecently": true
               |  }
               |}
               |""".stripMargin)
      }
    }
  }

}
