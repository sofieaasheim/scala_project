// Task 2: Concurrency in Scala

object Task2 extends App{
    
// (a) Create a function that takes as argument a function and returns a Thread initialized with 
// the input function. Make sure that the returned thread is not started. (3p)

def initialize_thread(func: () => Unit): Thread = {
    val thread = new Thread {
        override def run(): Unit = {
            func();
        }
    }
    return thread
}

// (b) Create a function that prints the current counter variable. Start three threads, two that 
// initialize increaseCounter and one that initialize the print function. Run your program a few 
// times and notice the print output. What is this phenomenon called? Give one example of a 
// situation where it can be problematic.

var counter: Int = 0
def print_counter_var(): Unit = {
    println(counter)
}
def increase_counter(): Unit = {
    counter += 1
}

initialize_thread(increase_counter).start()
initialize_thread(increase_counter).start()
initialize_thread(print_counter_var).start()

// I think the program is supposed to print 1 instead of 2, but my program prints 2???

// (c) Change increaseCounter so that it is thread-safe. Hint: atomicity. (4p)

// add this.synchronized to the increase_counter function


// (d) One problem you will often meet in concurrency programming is deadlock. What is deadlock, and what
// can be done to prevent it? Write in Scala an example of a deadlock using lazy val. (4p)

// Deadlock is a problem that can arise when several ezecutions are waiting for each other before
// proceeding with their own actions. An example:

object A {
  lazy val a = B.b
  lazy val b = 13
}

object B {
  lazy val b = A.b
}

val threadA = initialize_thread(() => {
    println("Thread A")
    println(A.a)
})

val threadB = initialize_thread(() => {
    println("Thread B")
    println(B.b)
})

// Deadlock
threadA.start()
threadB.start()
}
