import exceptions._

class Account(val bank: Bank, initialBalance: Double) {

    class Balance(var amount: Double) {}

    val balance = new Balance(initialBalance)

    def withdraw(amount: Double): Either[Double, String] = {
        if amount < 0 {
            return Right("Cannot withdraw a negative amount")
        }
        if amount > this.getBalanceAmount {
            return Right("Not enough available funds")
        }
        balance.amount -= amount
        return Left(this.getBalanceAmount)
    }

    def deposit (amount: Double): Either[Double, String] {
        if amount < 0 {
            return Right("Cannot deposit a negative amount")
        }
        balance.amount += amount
        return Left(this.getBalanceAmount)
    }
    
    def getBalanceAmount: Double = {
        balance.amount
    }

    def transferTo(account: Account, amount: Double) = {
        bank addTransactionToQueue (this, account, amount)
    }


}
