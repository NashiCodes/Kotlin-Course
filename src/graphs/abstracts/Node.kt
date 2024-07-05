package graphs.abstracts

class Node(
    val value: Int, var weight: Int = 0,
    var x: Int = 0, var y: Int = 0
) {
    private var inDegree: Int = 0
    private var outDegree: Int = 0
    var vertices: MutableMap<Int, MutableSet<Vertice>> = mutableMapOf()

    fun addVertice(idNode: Int, vertice: Vertice) {
        require(idNode >= 0) { "Invalid node id" }

        if (vertices.containsKey(idNode)) {
            vertices[idNode]?.add(vertice)
        } else {
            vertices[idNode] = mutableSetOf(vertice)
        }

        increaseDegree(vertice.firstNode)
        if (vertice.directed.not()) {
            increaseDegree(vertice.secondNode)
        }
    }

    fun removeVertice(idNode: Int, vertice: Vertice) {
        require(idNode >= 0) { "Invalid node id" }

        if (vertices.containsKey(idNode)) {
            vertices[idNode]?.remove(vertice)
        }

        decreaseDegree(vertice.firstNode)
        if (vertice.directed.not()) {
            decreaseDegree(vertice.secondNode)
        }
    }

    private fun increaseDegree(node: Node) {
        if (node == this) {
            outDegree++
        } else {
            inDegree++
        }
    }

    private fun decreaseDegree(node: Node) {
        if (node == this) {
            outDegree--
        } else {
            inDegree--
        }
    }
}