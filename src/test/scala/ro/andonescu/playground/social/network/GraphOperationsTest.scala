package ro.andonescu.playground.social.network

import org.specs2.Specification
import ro.andonescu.playground.social.network.GraphOperations._
import ro.andonescu.playground.social.network.model.{Connection => E}
import scalax.collection.{Graph, GraphEdge}


class GraphOperationsTest extends Specification {

  def is =
    s2"""
 This is a specification to check how graph functions works
 The 'GraphOperationsTest' should
   interpret properly a list of connection between users                                    $interpretConnections
   chainOfFriendsBetween a three friends connected  A ~ B, B ~ C, C ~ A                     $chainOfFriendsBetweenConnectedFriends
   chainOfFriendsBetween a small list of friends A ~ B, B ~ C                               $chainOfFriendsBetweenSmallListOfFriends
   chainOfFriendsBetween a complex list of friends                                          $chainOfFriendsBetweenComplexSocialNetwork
   chainOfFriendsBetween a clustered solution should be empty                               $chainOfFriendsBetweenClusters
  """

  def interpretConnections =
    interpret(List(E("A", "B"), E("B", "C"))) should beEqualTo(Graph(GraphEdge.~("A", "B"), GraphEdge.~("B", "C")))

  def chainOfFriendsBetweenConnectedFriends =
    chainOfFriendsBetween("A", "C")(Graph(GraphEdge.~("A", "B"), GraphEdge.~("B", "C"), GraphEdge.~("C", "A"))) should beEqualTo(Seq("A", "C"))

  def chainOfFriendsBetweenSmallListOfFriends =
    chainOfFriendsBetween("A", "C")(Graph(GraphEdge.~("A", "B"), GraphEdge.~("B", "C"))) should beEqualTo(Seq("A", "B", "C"))

  def chainOfFriendsBetweenComplexSocialNetwork =

    chainOfFriendsBetween("A", "M")(
      Graph(GraphEdge.~("A", "B"),
        GraphEdge.~("B", "C"),
        GraphEdge.~("B", "D"),
        GraphEdge.~("B", "E"),
        GraphEdge.~("E", "F"),
        GraphEdge.~("E", "C"),
        GraphEdge.~("F", "M")
      )
    ) should beEqualTo(Seq("A", "B", "E", "F", "M"))

  def chainOfFriendsBetweenClusters =

    chainOfFriendsBetween("A", "M")(
      Graph(GraphEdge.~("A", "B"),
        GraphEdge.~("B", "C"),
        GraphEdge.~("B", "D"),
        GraphEdge.~("B", "E"),
        GraphEdge.~("E", "F"),
        GraphEdge.~("E", "C"),
        GraphEdge.~("N", "M")
      )
    ) should beEmpty
}
