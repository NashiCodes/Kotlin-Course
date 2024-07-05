package graphs.abstracts

import java.io.BufferedReader
import java.io.FileReader

class Truck(private val capacity: Int = 0) {
    private var currentCapacity: Int = 0

    private val route = mutableListOf<Int>()

    fun load(weight: Int) {
        require(weight > 0) { "Invalid weight" }
        require(weight <= capacity) { "Over capacity" }

        currentCapacity += weight
    }

    fun unload(weight: Int) {
        require(weight > 0) { "Invalid weight" }
        require(weight <= currentCapacity) { "Over capacity" }

        currentCapacity -= weight
    }
}

abstract class AGraph(
    private var size: Int = 0,
    private var numVertices: Int = 0,
    private var directed: Boolean = false
) {
    private var nodes: MutableMap<Int, Node> = mutableMapOf()
    private var vertices: MutableMap<Int, Vertice> = mutableMapOf()
    private var trucks: MutableList<Truck> = mutableListOf()

    private fun containsNode(idNode: Int): Boolean {
        return nodes.containsKey(idNode)
    }

    private fun getPairNodes(
        firstNodeValue: Int,
        secondNodeValue: Int
    ): Pair<Node, Node> {
        require(firstNodeValue >= 0) { "Invalid first node" }
        require(secondNodeValue >= 0) { "Invalid second node" }
        require(containsNode(firstNodeValue) || containsNode(secondNodeValue)) { "Invalid nodes" }

        val firstNode = nodes.getValue(firstNodeValue)
        val secondNode = nodes.getValue(secondNodeValue)
        return Pair(firstNode, secondNode)
    }

    fun createNode(value: Int, weight: Int = 0) {
        require(value >= 0) { "Invalid value" }
        require(containsNode(value).not()) { "Node already exists" }

        val node = Node(value, weight)
        nodes[value] = node
        size = nodes.size
    }

    fun removeNode(value: Int) {
        require(value >= 0) { "Invalid value" }
        require(containsNode(value)) { "Node does not exist" }

        val node = nodes.getValue(value)
        for (vertex in node.vertices) {
            removeVertex(value, vertex.key)
        }

        nodes.remove(value)
        size = nodes.size
    }

    fun createVertex(firstNodeValue: Int, secondNodeValue: Int, weight: Int = 0) {
        val (firstNode, secondNode) = getPairNodes(firstNodeValue, secondNodeValue)
        val vertice = Vertice(firstNode, secondNode, weight, directed)

        firstNode.addVertice(secondNodeValue, vertice)
        if (directed.not()) {
            secondNode.addVertice(firstNodeValue, vertice)
        }

        vertices[vertice.hashCode()] = vertice
        numVertices = vertices.size
    }

    private fun removeVertex(firstNodeValue: Int, secondNodeValue: Int) {
        val (firstNode, secondNode) = getPairNodes(firstNodeValue, secondNodeValue)
        val vertice = Vertice(firstNode, secondNode, 0, directed)

        firstNode.removeVertice(secondNodeValue, vertice)
        if (directed.not()) {
            secondNode.removeVertice(firstNodeValue, vertice)
        }

        vertices.remove(vertice.hashCode())
        numVertices = vertices.size
    }

    fun create() {
        val path = "./src/graphs/instances/A-n34-k5.txt"
        var reader: BufferedReader? = null


        try {
            reader = BufferedReader(FileReader(path))
            var line: String?
            readProperties(reader)
            while (reader.readLine().also { line = it } != null) {
                if (line.equals("NODE_COORD_SECTION ")) continue
                if (line!!.contains("DEMAND_SECTION")) {
                    readDemands(reader)
                    break
                }


            }

        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                reader?.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun readProperties(reader: BufferedReader) {
        val strTruck = "No of trucks: "
        val strCapacity = "CAPACITY : "
        var numTrucks = 0
        var capacity = 0

        var line: String?
        while (reader.readLine().also { line = it } != null && !line.equals("NODE_COORD_SECTION ")) {
            var subString: String
            if (line!!.contains(strTruck)) {
                subString = line!!.substring(strTruck.length)
                numTrucks = subString.toInt()
                println(subString)
            }
            if (line!!.contains(strCapacity)) {
                subString = line!!.substring(strCapacity.length)
                capacity = subString.toInt()
                println(subString)
            }
        }

        while (numTrucks > 0) {
            trucks.add(Truck(capacity))
            numTrucks--
        }

    }

    private fun readDemands(reader: BufferedReader) {
        var line: String?

        while (reader.readLine().also { line = it } != null) {
            val values = line!!.split(" ")
            val node = nodes.getValue(values[0].toInt())

            node.weight = values[1].toInt()
        }
    }
}

