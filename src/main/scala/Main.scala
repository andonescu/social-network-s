import com.typesafe.scalalogging.Logger

object Main {

  val logger = Logger("run")

  def main(args: Array[String]): Unit = {

    if (args.length != 1) {
      println("Please add a json file.")
      return
    }

    // read json data from the received file

  }

  private def isExit(readline: String) =
    readline.trim.toLowerCase == "exit"
}