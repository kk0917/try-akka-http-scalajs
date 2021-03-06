package com.example.tryakkahttpscalajs.routes

import akka.http.scaladsl.server.{Directives, Route}
import com.example.tryakkahttpscalajs.shared.SharedMessages
import com.example.tryakkahttpscalajs.twirl.Implicits._

class WebService() extends Directives {

  val route: Route = {
    pathSingleSlash {
      get {
        complete {
          com.example.tryakkahttpscalajs.html.index.render(SharedMessages.itWorks)
        }
      }
    } ~
      pathPrefix("assets" / Remaining) { file =>
        // optionally compresses the response with Gzip or Deflate
        // if the client accepts compressed responses
        encodeResponse {
          getFromResource("public/" + file)
        }
      }
  }
}
