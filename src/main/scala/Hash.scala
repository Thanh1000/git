import java.nio.charset.StandardCharsets
import java.security.MessageDigest

def hash(str: String): String = {
  val zeroByte = '\u0000'
  sha1(s"blob ${str.length}$zeroByte$str")
}

def sha1(str: String): String =
  MessageDigest
    .getInstance("SHA-1")
    .digest(str.getBytes(StandardCharsets.UTF_8))
    .map("%02x".format(_))
    .mkString
