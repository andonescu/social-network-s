package ro.andonescu.playground.social.network

import org.specs2.Specification
import ro.andonescu.playground.social.network.helpers.ResourceLoader
import ro.andonescu.playground.social.network.loader.Loader._

class LoaderTest extends Specification with ResourceLoader {

  def is =
    s2"""
 This is a specification to check how files are loaded by the system
 The 'JsonLoader' should
   load an existing json file                                    $existingJsonFile
   return an error for a corrupt file                            $corruptFile
                                                                 """

  def existingJsonFile =
    inputData(pathToFile("small_network.json")) should beRight


  def corruptFile =
    inputData(pathToFile("corrupt_network_file.json")) should beLeft
}
