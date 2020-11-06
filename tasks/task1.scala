object Task1 extends App
{
    //Task1a
    // var z = new Array[Int](0)
    // for (w <- 1 to 50)
    //     z = z :+ w;
    // for (i <- z)
    //     println(i)

    //Task1b
    def sumArray(array:Array[Int]) : Int = {
        var sum:Int = 0
        for (w <- array)
            sum += w
        return sum
    }
    //println(sumArray(z))

    //Task1c
    def sumArrayRec(array:Array[Int]) : Int = {
        if (array.length < 2) {
            return array.head
        }
        else {
            return array.head + sumArrayRec(array.tail)
        }
    }
    //println(sumArrayRec(z))

    //Task1d
    def fibonacci(n:BigInt) : BigInt = {
        var f0:BigInt = 0
        var f1:BigInt = 1
        var i = 1
        while (i < n) {
            val f = f0 + f1
            f0 = f1
            f1 = f
            i += 1
        }
        return f0
    }
    //println(fibonacci(10))
    //Use BigInt instead of Int. What is the difference between these two data types?
}