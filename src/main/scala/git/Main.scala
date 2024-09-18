package git

import git.domain.usecase.HashObjectUseCase
import git.domain.usecase.HashObjectUseCase.HashObjectCommand

@main def main(str: String): Unit =
  println(HashObjectUseCase.handleCommand(HashObjectCommand(str)))
