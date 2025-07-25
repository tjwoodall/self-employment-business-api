/*
 * Copyright 2024 HM Revenue & Customs
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

package v4.createPeriodSummary.model.request

import play.api.libs.json.JsValue
import shared.models.domain.{BusinessId, Nino, TaxYear}

sealed trait CreatePeriodSummaryRequestData {
  val nino: Nino
  val businessId: BusinessId
  val body: CreatePeriodSummaryRequestBody
  lazy val taxYear: TaxYear = TaxYear.fromIso(body.periodDates.periodEndDate)
}

object CreatePeriodSummaryRequestData {

  def rawTaxYear(body: JsValue): Option[String] = (body \ "periodDates" \ "periodEndDate").asOpt[String]
}

/** Applicable from minimumTaxYear to 2022-23 (pre-TYS).
  */
case class Def1_CreatePeriodSummaryRequestData(nino: Nino, businessId: BusinessId, body: Def1_CreatePeriodSummaryRequestBody)
    extends CreatePeriodSummaryRequestData

/** Applicable from 2023-24 onwards.
  */
case class Def2_CreatePeriodSummaryRequestData(nino: Nino, businessId: BusinessId, body: Def2_CreatePeriodSummaryRequestBody)
    extends CreatePeriodSummaryRequestData
