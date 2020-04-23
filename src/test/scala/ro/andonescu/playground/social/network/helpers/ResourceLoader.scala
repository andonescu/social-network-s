package ro.andonescu.playground.social.network.helpers

trait ResourceLoader {
  def pathToFile(name: String) =
    getClass.getClassLoader.getResource(name).getPath
}
