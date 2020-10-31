
object Task1 extends App{
    
    // method that print each elements of array using iteration
    def printArrayElements(array: Array[Int]) {
        println("Elements of Array are : ")
        for(i <- 0 to array.length-1 )
            print(array(i) + " ")
    }

    // Task 1a
    var array = Array.empty[Int]
    for( num <- 1 to 50){
        array = array :+ num
    }

    // print array from 1a
    println("\nTask 1a:")
    printArrayElements(array)


    // Task 1b
    def sumIterative(array: Array[Int]): Int = {
        var sum = 0
        for(i <- array)
            sum += i         
        return sum
    }

    // print iterative sum of an array 1b
    println("\nTask 1b: ")
    var arrayToBeSummed = Array(1,2,3,4,5)
    val sum = sumIterative(arrayToBeSummed)
    printArrayElements(arrayToBeSummed)
    print(", and the iterative sum is ")
    print(sum)

    // Task 1c
    def sumRecursive(array: Array[Int], sum: Int): Int = {
        if (array.length == 0) sum
        else sumRecursive(array.tail,array.head + sum)   
    }

    // print recursive sum of array 
    println("\nTask 1c: ")
    printArrayElements(arrayToBeSummed)
    print(", and the recursive sum is ")
    val newSum = sumRecursive(arrayToBeSummed,0)
    println(newSum)

    // Task 1d
    def fibonacci(n: Int): BigInt = {
        if (n==0) BigInt(0)
        else if (n==1) BigInt(1)
        else fibonacci(n-1)+fibonacci(n-2)
    
    }
    // print recursive sum of array 1d
    println("Task 1d: ")
    val nr = 8
    print("Fibonacci number of " + nr + " is ")
    val fnr= fibonacci(nr)
    println(fnr)

}

// Difference between Int and BigInt:
// BigInt can take up a greater amount of space in memory and will therefore accept a greater range of values,
// which is an advantage when working with integer artichmetric where the values can exceed the range for Int.
// An Int is 32 bits/4 bytes while an BigInt is 64 bits/8 bytes.




