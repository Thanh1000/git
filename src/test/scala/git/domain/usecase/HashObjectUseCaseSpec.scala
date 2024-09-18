package git.domain.usecase

import git.domain.usecase.HashObjectUseCase.HashObjectCommand
import zio.test.*
import zio.test.Assertion.equalTo
import zio.{Scope, ZIOApp}

class HashObjectUseCaseSpec extends ZIOSpecDefault {
  override def spec: Spec[TestEnvironment & Scope, Any] =
    suite("Hash object usecase")(
      test("hash 'test content'") {
        for {
          hash <-
            HashObjectUseCase.handleCommand(
              HashObjectCommand(strToHash = "test content")
            )
        } yield assert(hash)(
          equalTo("08cf6101416f0ce0dda3c80e627f333854c4085c")
        )
      }
    )
}

object HashObjectUseCaseSpecProxy extends ZIOApp.Proxy(HashObjectUseCaseSpec())
