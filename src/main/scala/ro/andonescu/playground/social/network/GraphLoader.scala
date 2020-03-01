package ro.andonescu.playground.social.network

import ro.andonescu.playground.social.network.model.Connection
import scalax.collection.Graph
import scalax.collection.GraphPredef._


object GraphLoader {

  def interpret(connections: Seq[Connection]) =
    Graph() ++ connections.map(con => con.userA ~ con.userB)

}
