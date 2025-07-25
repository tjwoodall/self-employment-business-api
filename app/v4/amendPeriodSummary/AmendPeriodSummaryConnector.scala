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

package v4.amendPeriodSummary

import play.api.http.Status.OK
import shared.config.SharedAppConfig
import shared.connectors.DownstreamUri.{DesUri, IfsUri}
import shared.connectors.httpparsers.StandardDownstreamHttpParser._
import shared.connectors.{BaseDownstreamConnector, DownstreamOutcome}
import uk.gov.hmrc.http.HeaderCarrier
import uk.gov.hmrc.http.client.HttpClientV2
import v4.amendPeriodSummary.def2.model.request.Def2_AmendPeriodSummaryRequestData
import v4.amendPeriodSummary.model.request.AmendPeriodSummaryRequestData

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class AmendPeriodSummaryConnector @Inject() (val http: HttpClientV2, val appConfig: SharedAppConfig) extends BaseDownstreamConnector {

  def amendPeriodSummary(request: AmendPeriodSummaryRequestData)(implicit
      hc: HeaderCarrier,
      ec: ExecutionContext,
      correlationId: String): Future[DownstreamOutcome[Unit]] = {

    implicit val successCode: SuccessCode = SuccessCode(OK)

    request match {
      case def2: Def2_AmendPeriodSummaryRequestData =>
        import def2._
        val ifsUri = IfsUri[Unit](
          s"income-tax/${taxYear.asTysDownstream}/$nino/self-employments/$businessId/periodic-summaries?from=${periodId.from}&to=${periodId.to}")
        put(def2.body, ifsUri)

      case _ @def1 =>
        import def1._
        val desUri = DesUri[Unit](s"income-tax/nino/$nino/self-employments/$businessId/periodic-summaries?from=${periodId.from}&to=${periodId.to}")
        put(def1.body, desUri)

    }
  }

}
