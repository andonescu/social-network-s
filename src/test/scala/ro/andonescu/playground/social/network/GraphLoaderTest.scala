package ro.andonescu.playground.social.network

import org.specs2.Specification
import ro.andonescu.playground.social.network.model.Connection
import scalax.collection.Graph
import scalax.collection.GraphPredef._

class GraphLoaderTest extends Specification {

  def is =
    s2"""
 This is a specification to check how graph functions works
 The 'GraphLoaderTest' should
   interpret properly a list of connection between users                                    $interpretConnections

  """

  def interpretConnections =
    GraphLoader.interpret(List(Connection("A", "B"))) should beTheSameAs(Graph("A" ~ "B"))
}
