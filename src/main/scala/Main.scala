import com.typesafe.scalalogging.Logger
import io.circe.parser.decode
import ro.andonescu.playground.social.network.GraphOperations
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
    decode[Seq[Connection[String]]](Loader.inputDataAsString(jsonFile)) match {

      case Right(connections) =>
        // connection graph can be properly assembled
        println(connections)

        val graph = GraphOperations.interpret(connections)
        val nodes = graph.nodes
        // as demo purpose we will take / analyze the chain of friends between first and last & printed to stdout
        println(s"The list of friends between ${nodes.head} & ${nodes.last} are : ${GraphOperations.chainOfFriendsBetween(nodes.head.value, nodes.last.value)(graph)}")

        // TODO: additional validation to check that there are at least two persons.

      case Left(err) =>
        logger.error(err.getLocalizedMessage, err.fillInStackTrace())
        println("Errors in reading the json data. Please check the input file.")
    }
  }
}