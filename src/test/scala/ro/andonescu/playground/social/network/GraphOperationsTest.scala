package ro.andonescu.playground.social.network

import org.specs2.Specification
import ro.andonescu.playground.social.network.model.Connection
import scalax.collection.{Graph, GraphEdge}

class GraphOperationsTest extends Specification {

  def is =
    s2"""
 This is a specification to check how graph functions works
 The 'GraphLoaderTest' should
   interpret properly a list of connection between users                                    $interpretConnections

  """

  def interpretConnections =
    GraphOperations.interpret(List(Connection("A", "B"), Connection("B", "C"))) should beEqualTo(Graph(GraphEdge.UnDiEdge("A", "B"), GraphEdge.UnDiEdge("B", "C")))

}
