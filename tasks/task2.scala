object Task2 extends App 
{
    //Task2a
    def initializeThread(inputFunc:() => Unit) : Thread = {
        return new Thread {
            override def run(): Unit = {
                inputFunc();
            }
        }
    }

    //Task2b and c
    private var counter: Int = 0
    def increaseCounter(): Unit = this.synchronized { //
        counter += 1
    }

    def printCurrentCounter() : Unit = {
        println(counter)
    }
    
    def initializeThreadsCounter(counterfunc:() => Unit, printfunc:() => Unit) : Unit = {
    var thread1 = initializeThread(counterfunc)
    var thread2 = initializeThread(counterfunc)
    var thread3 = initializeThread(printfunc)
    thread1.start
    thread2.start
    thread3.start
    }
   //var t = initializeThreadsCounter(increaseCounter, printCurrentCounter)
   //When I run the function several times the output varries. Sometimes the output is 1 and sometimes it is 2. 
   //If I change the order in which I start the threads and start thread3 first the output is zero and if I start thread3 between thread1 and thread2 
   //the output again varies between 1 and 2. 
   //This is due to the phenomenon race condition. 
   //Race condition arises in software when a computer program, to operate properly, depends on the sequence or timing of the programs's processes or threads. 
   //In our case, when we call the function initialize Threads, the program will start thread1 then thread2 and lastly thread3. 
   //However, the time at which each equation finishes varies. Sometimes thread3 will finish after the two other threads and we get the output 2. 
   //Other times only one of the threads thread1 and thread2 are able to finish before thread3 finish and we will get the output 1. 
   //When thread3 is started first it seems that it manages to finish before either of the other threads and then we get the output 0.
   //The phenomenon race condition can be problematic (critical) if the threads depend on some shared state. 
   
   //Task2d
   //One problem you will often meet in concurrency programming is deadlock. 
   //What is deadlock, and what can be done to prevent it? 
   //Write in Scala an example of a deadlock using lazy val. 
}