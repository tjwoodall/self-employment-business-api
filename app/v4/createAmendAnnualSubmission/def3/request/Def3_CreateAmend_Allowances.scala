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

package v4.createAmendAnnualSubmission.def3.request

import play.api.libs.functional.syntax._
import play.api.libs.json.{JsPath, Json, OWrites, Reads}

case class Def3_CreateAmend_Allowances(annualInvestmentAllowance: Option[BigDecimal],
                                       businessPremisesRenovationAllowance: Option[BigDecimal],
                                       capitalAllowanceMainPool: Option[BigDecimal],
                                       capitalAllowanceSpecialRatePool: Option[BigDecimal],
                                       enhancedCapitalAllowance: Option[BigDecimal],
                                       allowanceOnSales: Option[BigDecimal],
                                       capitalAllowanceSingleAssetPool: Option[BigDecimal],
                                       tradingIncomeAllowance: Option[BigDecimal],
                                       zeroEmissionsCarAllowance: Option[BigDecimal],
                                       structuredBuildingAllowance: Option[Seq[Def3_CreateAmend_StructuredBuildingAllowance]],
                                       enhancedStructuredBuildingAllowance: Option[Seq[Def3_CreateAmend_StructuredBuildingAllowance]])

object Def3_CreateAmend_Allowances {
  implicit val reads: Reads[Def3_CreateAmend_Allowances] = Json.reads[Def3_CreateAmend_Allowances]

  implicit val writes: OWrites[Def3_CreateAmend_Allowances] = (
    (JsPath \ "annualInvestmentAllowance").writeNullable[BigDecimal] and
      (JsPath \ "businessPremisesRenovationAllowance").writeNullable[BigDecimal] and
      (JsPath \ "capitalAllowanceMainPool").writeNullable[BigDecimal] and
      (JsPath \ "capitalAllowanceSpecialRatePool").writeNullable[BigDecimal] and
      (JsPath \ "enhanceCapitalAllowance").writeNullable[BigDecimal] and
      (JsPath \ "allowanceOnSales").writeNullable[BigDecimal] and
      (JsPath \ "capitalAllowanceSingleAssetPool").writeNullable[BigDecimal] and
      (JsPath \ "tradingIncomeAllowance").writeNullable[BigDecimal] and
      (JsPath \ "zeroEmissionsCarAllowance").writeNullable[BigDecimal] and
      (JsPath \ "structuredBuildingAllowance").writeNullable[Seq[Def3_CreateAmend_StructuredBuildingAllowance]] and
      (JsPath \ "enhancedStructuredBuildingAllowance").writeNullable[Seq[Def3_CreateAmend_StructuredBuildingAllowance]]
  )(unlift(Def3_CreateAmend_Allowances.unapply))

}
