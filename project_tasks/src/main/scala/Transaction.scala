import exceptions._
import scala.collection.mutable._

object TransactionStatus extends Enumeration {
  val SUCCESS, PENDING, FAILED = Value
}

class TransactionQueue {
    
    // project task 1.1
    // Add datastructure to contain the transactions
    var queue = new Queue[Transaction]() //Usikker på om det skal være var eller val...

    // Remove and return the first element from the queue
    def pop: Transaction = this.synchronized {
        return queue.dequeue()
    }

    // Return whether the queue is empty
    def isEmpty: Boolean = this.synchronized {
        return queue.isEmpty
    }

    // Add new element to the back of the queue
    def push(t: Transaction): Unit = this.synchronized {
        return queue.enqueue(t)
    }

    // Return the first element from the queue without removing it
    def peek: Transaction = this.synchronized {
        return queue.front
    }

    // Return an iterator to allow you to iterate over the queue
    def iterator: Iterator[Transaction] = this.synchronized {
        return queue.iterator
    }
}

class Transaction(val transactionsQueue: TransactionQueue,
                  val processedTransactions: TransactionQueue,
                  val from: Account,
                  val to: Account,
                  val amount: Double,
                  val allowedAttemps: Int) extends Runnable {

  var status: TransactionStatus.Value = TransactionStatus.PENDING
  var attempt = 0

  override def run: Unit = {
    
    //project task 3
    // should transfer money meaning withdraw money from one account and deposit it to the other account
      def doTransaction() = {
        // check number of allowed attempts
        if (attempt >= allowedAttemps){
            this.status = TransactionStatus.FAILED   
        }
        // if there are any attempts left, transfer money
        else  {
            lazy val balanceAfterWithdraw = from.withdraw(amount)
            lazy val balanceAfterDeposit = to.deposit(amount)
        
            //finish error handling
            // sucesss iff both deposit and withdraw are successfull operations
            balanceAfterWithdraw match{
                case Right(string) => this.attempt +=1
                case Left(balance) => balanceAfterDeposit match {
                    case Right(string) => this.attempt += 1
                    case Left(balance) => this.status = TransactionStatus.SUCCESS
                } 
            }
        }
    }
      if (status == TransactionStatus.PENDING) this.synchronized {
          doTransaction()
          Thread.sleep(50) // you might want this to make more room for
                           // new transactions to be added to the queue
      }


    }
}
