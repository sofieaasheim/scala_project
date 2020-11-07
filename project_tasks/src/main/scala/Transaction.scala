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

      def doTransaction(): Unit = {
        if (attempt > allowedAttempts) {
            this.status = TransactionStatus.FAILED
        } else {
            lazy val withdrawn = from.withdraw(amount)
            lazy val deposited = to.deposit(amount)

            withdrawn match {
            case Right(error) => this.attempt += 1
            case Left(balance) => deposited match {
                case Right(error) => this.attempt += 1
                case Left(balance) => this.status = TransactionStatus.SUCCESS
                    }
                }
            }
        }

      // TODO - project task 3
      // make the code below thread safe
      if (status == TransactionStatus.PENDING) {
          doTransaction
          Thread.sleep(50) // you might want this to make more room for
                           // new transactions to be added to the queue
      }


    }
}
