package service

import play.api.Logger
import play.api.libs.Files.TemporaryFile
import play.api.mvc.{MultipartFormData, Request}

/**
  * Created by supriya on 15/2/16.
  */
class UploadService {

  private val log: Logger = Logger(this.getClass)

  /**
    * Get file from the request and move it in your location
    *
    * @param request
    * @return
    */
  def uploadFile(request: Request[MultipartFormData[TemporaryFile]]): String = {
    println("Called uploadFile function" + request)
    request.body.file("picture").map { picture =>
      import java.io.File
      val filename = picture.filename
      val contentType = picture.contentType
      log.error(s"File name : $filename, content type : $contentType")
      picture.ref.moveTo(new File(s"/tmp/picture/$filename"))
      "File uploaded"
    }.getOrElse {
      "Missing file"
    }
  }

}
