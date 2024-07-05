package graphs.abstracts

class Vertice(val firstNode: Node, val secondNode: Node, var weight: Int, var directed: Boolean) {
    override fun toString(): String {
        return "Vertice($firstNode, $secondNode, $weight)"
    }

    companion object CompareByWeight {
        fun compare(o1: Vertice, o2: Vertice): Int {
            return o1.weight - o2.weight
        }
    }
}