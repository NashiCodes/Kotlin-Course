package arrays

class ArrayMenu {
    private var array: ArrayAlgo? = null
    fun create() {
        println("Please select an option:")
        println("1. Populate an array with random size and elements")
        println("2. Populate an array with user input")
        println("3. Back")
        println("#Default option is 1#")
        println("#Default size is 100#")

        when (readlnOrNull()) {
            "2" -> {
                println("Enter the size of the array:")
                val size = readln().toInt()
                array = ArrayAlgo(size)
                array!!.populateArray()
                array!!.printArray()
            }

            "3" -> return

            else -> {
                array = ArrayAlgo(100)
                array!!.randomizeArray()
                array!!.printArray()
            }
        }

        algorithmOption()
    }

    private fun algorithmOption() {
        for (i in 1..10) {
            println()
        }

        println("Please select an option:")
        println("1. Find the maximum element in the array")
        println("2. Find the minimum element in the array")
        println("3. Find the sum of all elements in the array")
        println("4. Find the average of all elements in the array")
        println("5. Find the median of all elements in the array")
        println("6. Find the mode of all elements in the array")
        println("7. Reverse the array")
        println("8. Rotate the array")
        println("9. Search for an element in the array")
        println("10. Exit")
        println("#Exit will take you back to the main menu#")

        when (readlnOrNull()) {
            "1" -> println("Maximum element: ${array!!.maxElement()}")
            "2" -> println("Minimum element: ${array!!.minElement()}")
            "3" -> println("Sum of all elements: ${array!!.sum()}")
            "4" -> println("Average of all elements: ${array!!.average()}")
            "5" -> println("Median of all elements: ${array!!.median()}")
            "6" -> println("Mode of all elements: ${array!!.mode()}")
            "7" -> {
                array!!.reverse()
                array!!.printArray()
            }

            "8" -> {
                println("Enter the number of rotations:")
                val rotations = readln().toInt()
                array!!.rotate(rotations)
                array!!.printArray()
            }

            "9" -> {
                println("Enter the element to search:")
                val element = readln().toInt()
                println("Element found at index: ${array!!.search(element)}")
            }

            else -> {
                println("Invalid option")
            }
        }
        println("Press enter to continue...")
        readln()
        algorithmOption()
    }

}