object Task2 extends App{

// Task 2a
def thread(f: () => Unit ): Thread = {
    new Thread{
        override def run(): Unit = f()
    }
}

// Task 2b
private var counter: Int = 0

def increaseCounter(): Unit = {
counter += 1
}

// prints the current counter variable
def printCounter(): Unit = {
    println(counter)
}

// print counter in task 2b
thread(increaseCounter).start()
thread(increaseCounter).start()
thread(printCounter).start()

// The program prints 2
// This phenomonen is called Race Condition and happens when two or more operations attempt to perform at the same time (threads in this case).
// The output will depend on the timing at which each statement gets executed, and as the timing of the threads in this task is not controlled,
// and can therefore happen in different order, the result can variate.
// An example of Race Condition:
// Two different users are buying tickets for a popular concert online at the same time. For both users it seems as their are tickets available. 
// But while user 1 is looking at the tickets, user 2 buys the last ticket. So when user 1 press purchase there are actually no tickets left for the concert.
// The interleaving actions on a shared resource caused this undesirable result. 

// Task 2c
def increaseCounterSafe(): Unit = this.synchronized {
counter += 1
}

// Task 2d
// What is deadlock, and what can be done to prevent it?
// A deadlock is a the sitation where a set of processes are blockes beacuse each process is holding a recourse while waiting for another recourse. 
// To prevent deadlocks, one has to eliminate any of the four deadlock conditions:
//  1. Mutual exclution: allow simultanous access to the same resources
//  2. Hold and Wait: a process holds a resource while simultaneously waiting for another recourse to complete its task.
//  3. No preemtion: Not possible to stop a process from realising its resource 
//  4. Circular wait: can be avoided by requiring processes to request resources in a given order.
object One {
    lazy val base = 1
    lazy val start = Two.next
}
 
object Two {
    lazy val next = One.base
}

object Deadlock {
    val t1 = thread( () => {println("Thread one :" + One.start) } )
    val t2 = thread( () => {println("Thread two :" + Two.next) } )

}

// Uncomment to run deadlock
// Deadlock.t1.start()
// Deadlock.t2.start()


}