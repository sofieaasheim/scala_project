// Task 1: Scala introduction

object Task1 extends App{
    
    // Function to print arrays
    def print_array(array: Array[Int]) {
        println("Elements of array are: ")
        for(i <- 0 to array.length-1)
        {
            print(array(i)+ " ")
        }
    }
    
    // (a) Generate an array containing the values 1 up to (and including 50 using a for loop. (3p)
    var number_array = Array.empty[Int]
    for(w <- 1 to 50)
    {
        number_array = number_array :+ w
    }
    // Print number_array
    // print_array(number_array)
    
    // (b) Create a function that sums the elements in an array of integers using a for loop. (4p)
    def sum_integers(array: Array[Int]) {
        var sum = 0
        for(i <- 0 to array.length-1)
        {
            sum += array(i)
        }
        print("The sum of the elements in the array are: ")
        println(sum)
    }

    // Run sum_integers
    // sum_integers(number_array)

    // (c) Create a function that sums the elements in an array of integers using recursion. (4p)
    def sum_integers_recursion(array: List[Int]): Int = {
        if (array.isEmpty)
            return 0
        else
            return array.head + sum_integers_recursion(array.tail)
    }
    
    // Run sum_integers_recursion. The result should equal 6
    // print("The sum of the elements in the array are: ")
    // var array_sum = sum_integers_recursion(List(1,2,3)) 
    // println(array_sum)
    

    // (d) Create a function to compute the nth Fibonacci number using recursion without using meoization 
    // (or other optimizations). Use BigInt instead of Int. What is the difference between these two data types? (5p) 
    def fibonacci(n: Int): BigInt = {
        if (n <= 0) {
            return BigInt(0)
        } else if (n == 1)  {
            return BigInt(1)
        } else {
            return fibonacci(n - 1) + fibonacci(n -2)
        }
    }
    // Run fibonacci. The result should equal 1597
    // var fibonacci_17 = fibonacci(17)
    // print("The 17th Fibonacci number is equal to: ")
    // println(fibonacci_17)

    // // Difference between Int and BigInt:
    // BigInt can take up a greater amount of space in memory and will therefore accept a greater range of values,
    // which is an advantage when working with integer artichmetric where the values can exceed the range for Int.
    // An Int is 32 bits/4 bytes while an BigInt is 64 bits/8 bytes.

}
