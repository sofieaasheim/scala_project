class Bank(val allowedAttempts: Integer = 3) {

    private val transactionsQueue: TransactionQueue = new TransactionQueue()
    private val processedTransactions: TransactionQueue = new TransactionQueue()
     
    // project task 2
    def addTransactionToQueue(from: Account, to: Account, amount: Double): Unit = {

        // create a new transaction object and put it in the queue
        val transaction = new Transaction(transactionsQueue, processedTransactions, from, to, amount, allowedAttempts)                                        
        transactionsQueue.push(transaction)

        // spawn a thread that calls processTransactions
        val t = new Thread{
            override def run() {
                processTransactions
            }
        }

        t.start()
    }   
    
    // project task 2
    private def processTransactions: Unit = {
        // pop a transaction from the queue
        val transaction = transactionsQueue.pop

        // spawn a thread to execute the transaction
        val t = new Thread(transaction)
        t.start()
        t.join()
        
        // if transaction is pending, put it back into queue
        // and recursively call processTransactions
        if (transaction.status == TransactionStatus.PENDING){
            transactionsQueue.push(transaction)
            processTransactions
        }

        // otherwise, put it in the processed transactions queue.
        else {
        processedTransactions.push(transaction) 
        }

    }
    
    def addAccount(initialBalance: Double): Account = {
        new Account(this, initialBalance)
    }

    def getProcessedTransactionsAsList: List[Transaction] = {
        processedTransactions.iterator.toList
    }

}
