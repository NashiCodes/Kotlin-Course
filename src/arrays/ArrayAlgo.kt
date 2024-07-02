package arrays

class ArrayAlgo(size: Int) {
    private var array = IntArray(size)

    init {
        println("Array of size $size created")
    }

    fun printArray() {
        println("Array: ${array.joinToString()}")
    }

    fun populateArray() {
        for (i in array.indices) {
            println("Enter element $i:")
            array[i] = readln().toInt()
        }
    }

    fun randomizeArray() {
        for (i in array.indices) {
            array[i] = (0..100).random()
        }
    }

    fun maxElement(): String {
        var max = array[0]

        for (i in 1..<array.size) {
            if (array[i] > max) {
                max = array[i]
            }
        }
        return max.toString()
    }

    fun minElement(): String {
        var min = array[0]

        for (i in 1..<array.size) {
            if (array[i] < min) {
                min = array[i]
            }
        }
        return min.toString()

    }

    fun sum(): String {
        var sum = 0

        for (i in array) {
            sum += i
        }
        return sum.toString()
    }

    fun average(): String {
        return (sum().toDouble() / array.size).toString()
    }

    fun median(): String {
        array.sort() //Sort methods made by me only in "SortingAlgo.kt"

        return if (array.size % 2 == 0) {
            val mid = array.size / 2
            ((array[mid - 1] + array[mid]).toDouble() / 2).toString()
        } else {
            array[array.size / 2].toString()
        }
    }

    fun mode(): String {
        val map = mutableMapOf<Int, Int>()

        for (i in array) {
            if (i !in map) {
                map[i] = 1
            } else {
                map[i] = map.getValue(i) + 1
            }
        }

        var mode = map.keys.first()
        for (i in map)
            mode = if (i.value > map.getValue(mode)) i.key else mode

        return mode.toString()

    }

    fun reverse() {
        for (i in 0..<array.size / 2) {
            val temp = array[i]
            array[i] = array[array.size - i - 1]
            array[array.size - i - 1] = temp
        }
    }

    fun rotate(rotations: Int) {
        if (rotations == 0) return

        val temp = array.copyOfRange(array.size - rotations, array.size)

        for (i in array.size - 1 downTo rotations) {
            array[i] = array[i - rotations]
            array[i - rotations] = temp[i - rotations]
        }
    }

    fun search(element: Int): String {
        val idx = -1

        for (i in array.indices)
            return if (array[i] == element) i.toString() else continue

        return idx.toString()
    }
}