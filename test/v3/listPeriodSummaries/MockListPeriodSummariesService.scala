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

package v3.listPeriodSummaries

import shared.controllers.RequestContext
import shared.services.ServiceOutcome
import org.scalamock.handlers.CallHandler
import org.scalamock.scalatest.MockFactory
import org.scalatest.TestSuite
import v3.listPeriodSummaries.model.request.ListPeriodSummariesRequestData
import v3.listPeriodSummaries.model.response.{ListPeriodSummariesResponse, PeriodDetails}

import scala.concurrent.{ExecutionContext, Future}

trait MockListPeriodSummariesService extends TestSuite with MockFactory {

  val mockListPeriodSummariesService: ListPeriodSummariesService = mock[ListPeriodSummariesService]

  object MockListPeriodSummariesService {

    def listPeriodSummaries(
        requestData: ListPeriodSummariesRequestData): CallHandler[Future[ServiceOutcome[ListPeriodSummariesResponse[PeriodDetails]]]] = {
      (mockListPeriodSummariesService
        .listPeriodSummaries(_: ListPeriodSummariesRequestData)(_: RequestContext, _: ExecutionContext))
        .expects(requestData, *, *)
    }

  }

}
