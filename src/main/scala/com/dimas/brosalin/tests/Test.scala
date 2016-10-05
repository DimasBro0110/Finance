package com.dimas.brosalin.tests

import java.io.File
import java.sql.Timestamp

import com.dimas.brosalin.orm.DAOImpl._
import com.dimas.brosalin.orm.Session.SessionConnection
import com.dimas.brosalin.orm.models._
import com.dimas.brosalin.orm.schema.SchemaMySQL
import com.typesafe.config.ConfigFactory
import org.squeryl.adapters.MySQLAdapter
import org.squeryl.{Session, SessionFactory}
import org.squeryl.PrimitiveTypeMode._

/**
  * Created by DmitriyBrosalin on 30/09/2016.
  */
object Test {

  def startDatabaseSession(): Unit = {
    Class.forName("com.mysql.jdbc.Driver")
    SessionFactory.concreteFactory = Some(()
    => Session.create(
        java.sql.DriverManager.getConnection(
          "jdbc:mysql://192.168.0.12:3306/BANK_SERVICE", "DIMA", "DIMA"),
        new MySQLAdapter)
    )
  }


  def main(args: Array[String]): Unit = {

//    startDatabaseSession()
//
//    transaction {
//
//      SchemaMySQL.create
//      SchemaMySQL.printDdl(println(_))
//
//    }
//
//    val userDAO = new UserDAO()
//    val user = new User(1, "dimas", "brosalin", 21, 1, "dimas")
//    val accountDAO = new AccountDAO()
//    val account = new Account(1, user.id, new Timestamp(System.currentTimeMillis()), 9000000.0f)
//
//    inTransaction {
//
//      userDAO.save(user)
//      accountDAO.save(account)
//
//    }

    val session = new SessionConnection
    session.sessionConnection()

    transaction{

      SchemaMySQL.create
      SchemaMySQL.printDdl(println(_))

    }

    /*
    *
    * Creation of users in system
    *
    * */

    val user_1 = new User(1, "Dima", "Brosalin", 21, 1, "PASS")
    val user_2 = new User(1, "Kolyan", "Matkheev", 21, 1, "PASS")
    val user_3 = new User(1, "Alex", "Vavilov", 22, 1, "PASS")

    val userDAO_1 = new UserDAO(session)
    userDAO_1.save(user_1)

    val userDAO_2 = new UserDAO(session)
    userDAO_2.save(user_2)

    val userDAO_3 = new UserDAO(session)
    userDAO_3.save(user_3)

    val account_1 = new Account(1, user_1.id, new Timestamp(System.currentTimeMillis()), 0)
    val account_2 = new Account(1, user_2.id, new Timestamp(System.currentTimeMillis()), 0)
    val account_3 = new Account(1, user_3.id, new Timestamp(System.currentTimeMillis()), 0)

    val accountDAO_1 = new AccountDAO(session)
    accountDAO_1.save(account_1)

    val accountDAO_2 = new AccountDAO(session)
    accountDAO_2.save(account_2)

    val accountDAO_3 = new AccountDAO(session)
    accountDAO_3.save(account_3)

    val wallet_1 = new Wallet(1, account_1.id, "RUB")
    val wallet_2 = new Wallet(1, account_2.id, "RUB")
    val wallet_3 = new Wallet(1, account_3.userId, "RUB")

    val walletDAO_1 = new WalletDAO(session)
    walletDAO_1.save(wallet_1)

    val walletDAO_2 = new WalletDAO(session)
    walletDAO_2.save(wallet_2)

    val walletDAO_3 = new WalletDAO(session)
    walletDAO_3.save(wallet_3)

    /*
    *
    * Money transfer
    * user_1 -> user_3 300R
    *
    * */

    val transaction_1 = new Transaction(1, new Timestamp(System.currentTimeMillis()))

    val legsFrom_1 = new LegsFrom(
      1, transaction_1.id, account_1.id,
      new Timestamp(System.currentTimeMillis()), 0, 300)

    val transDAO_1 = new TransactionDAO(session)
    transDAO_1.save(transaction_1)

    val legs_to_1 = new LegsTo(
      1, transaction_1.id, account_3.id,
      new Timestamp(System.currentTimeMillis()), 1, 300)

    val LegsFromDAO_1 = new LegsFromDAO(session)
    LegsFromDAO_1.save(legsFrom_1)

    val LegsToDAO_1 = new LegsToDAO(session)
    LegsToDAO_1.save(legs_to_1)



  }

}
