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

    // Fiks denne deloppgaven med emma sin kode!

    // The program ...........virker ikke..

    // This phenomonen is called Race Condition and happens when two or more operations attempt to perform at the same time (threads in this case).
    // The output will depend on the timing at which each statement gets executed, and as the timing of the threads in this task is not controlled,
    // and can therefore happen in different order, the result can variate.
    // An example of Race Condition:
    // Two different users are buying tickets for a popular concert online at the same time. For both users it seems as their are tickets available. 
    // But while user 1 is looking at the tickets, user 2 buys the last ticket. So when user 1 press purchase there are actually no tickets left for the concert.
    // The interleaving actions on a shared resource caused this undesirable result. 

    // (c) Change increaseCounter so that it is thread-safe. Hint: atomicity. (4p)

    def increaseCounterSafe(): Unit = this.synchronized {
        counter += 1
    }

    // (d) One problem you will often meet in concurrency programming is deadlock. What is deadlock, and what
    // can be done to prevent it? Write in Scala an example of a deadlock using lazy val. (4p)

    // Deadlock is a problem that can arise when several executions are waiting for each other before
    // proceeding with their own actions.
    // To prevent deadlocks, one has to eliminate any of the four deadlock conditions:
    //  1. Mutual exclution: allow simultanous access to the same resources
    //  2. Hold and Wait: a process holds a resource while simultaneously waiting for another recourse to complete its task.
    //  3. No preemtion: Not possible to stop a process from realising its resource 
    //  4. Circular wait: can be avoided by requiring processes to request resources in a given order.  


    // An example on deadlock:
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
    // threadA.start()
    // threadB.start()
}
