package utils

import arrays.ArrayMenu

class Menu {
    fun mainMenu() {
        println("Please select an option:")
        println("1. Array Algorithms #Not Implemented Yet#")
        println("2. String Algorithms #Not Implemented Yet#")
        println("3. Linked List Algorithms #Not Implemented Yet#")
        println("4. Stack Algorithms #Not Implemented Yet#")
        println("5. Queue Algorithms #Not Implemented Yet#")
        println("6. Tree Algorithms #Not Implemented Yet#")
        println("7. Graph Algorithms #Not Implemented Yet#")
        println("8. Sorting Algorithms #Not Implemented Yet#")
        println("9. Searching Algorithms #Not Implemented Yet#")
        println("10. Backtracking #Not Implemented Yet#")

        when (readlnOrNull()) {
            "1" -> ArrayMenu().create()
            "2" -> println("String Algorithms")
            "3" -> println("Linked List Algorithms")
            "4" -> println("Stack Algorithms")
            "5" -> println("Queue Algorithms")
            "6" -> println("Tree Algorithms")
            "7" -> println("Graph Algorithms")
            "8" -> println("Sorting Algorithms")
            "9" -> println("Searching Algorithms")
            "10" -> println("Backtracking")
            else -> {
                println("Invalid option")
                return mainMenu()
            }
        }
    }

}