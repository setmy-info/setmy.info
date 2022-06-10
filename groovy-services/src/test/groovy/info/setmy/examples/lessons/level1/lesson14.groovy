import groovy.transform.ToString

println("============ LESSON 14 ==============")

sealed interface Tree permits Empty, Node {}

@Singleton
final class Empty implements Tree {
    String toString() { "Empty" }
}

@ToString
final class Node implements Tree {
    final String value

    Node(String value) {
        this.value = value
    }
}
