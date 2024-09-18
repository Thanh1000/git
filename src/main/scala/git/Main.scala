package git

import git.domain.usecase.HashObjectUseCase
import git.domain.usecase.HashObjectUseCase.HashObjectCommand
import zio.Console.ConsoleLive
import zio.{Chunk, Console, Scope, ZIO, ZIOAppArgs, ZIOAppDefault, ZLayer}

import java.io.IOException

object Main extends ZIOAppDefault {
  override def run: ZIO[ZIOAppArgs & Scope, Any, Any] = (for {
    args <- getArgs
    _ <- program(args).provide(ZLayer.succeed(ConsoleLive))
  } yield ()).exitCode

  def program(args: Chunk[String]): ZIO[Console, IOException, Unit] = for {
    console <- ZIO.service[Console]
    hash <- HashObjectUseCase.handleCommand(HashObjectCommand(args.head))
    _ <- console.printLine(hash)
  } yield ()
}
