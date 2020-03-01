import com.typesafe.scalalogging.Logger
import io.circe.parser.decode
import ro.andonescu.playground.social.network.loader.Loader
import ro.andonescu.playground.social.network.model.Connection

object Main {

  val logger = Logger("run")

  def main(args: Array[String]): Unit = {

    if (args.length != 1) {
      println("Please add a json file.")
      return
    }

    resolveSocialNetwork(args(0))
  }

  private def resolveSocialNetwork(jsonFile: String) = {
    import ro.andonescu.playground.social.network.model.LoaderJson._

    // read json data from the received file
    decode[Seq[Connection]](Loader.inputDataAsString(jsonFile)) match {

      case Right(connections) =>
        // connection graph can be properly assembled
        println(connections)

      case Left(err) =>
        logger.error(err.getLocalizedMessage, err.fillInStackTrace())
        println("Errors in reading the json data. Please check the input file.")
    }
  }
}