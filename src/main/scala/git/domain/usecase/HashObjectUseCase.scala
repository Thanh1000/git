package git.domain.usecase

import zio.{UIO, ZIO}

import java.nio.charset.StandardCharsets
import java.security.MessageDigest

object HashObjectUseCase {
  def handleCommand(hashObjectCommand: HashObjectCommand): UIO[String] =
    ZIO.succeed(hash(hashObjectCommand.strToHash))

  private def hash(str: String): String = {
    val zeroByte = '\u0000'
    sha1(s"blob ${str.length}$zeroByte$str")
  }

  private def sha1(str: String): String =
    MessageDigest
      .getInstance("SHA-1")
      .digest(str.getBytes(StandardCharsets.UTF_8))
      .map("%02x".format(_))
      .mkString

  case class HashObjectCommand(strToHash: String)
}
