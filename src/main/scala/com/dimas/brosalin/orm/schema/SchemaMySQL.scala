package com.dimas.brosalin.orm.schema

import com.dimas.brosalin.orm.models._
import org.squeryl.{Schema, Session}
import org.squeryl.PrimitiveTypeMode._

/**
  * Created by DmitriyBrosalin on 30/09/2016.
  */
object SchemaMySQL extends Schema{

  val user = table[User]("USER")
  val account = table[Account]("ACCOUNT")
  val wallet = table[Wallet]("WALLET")
  val legsTo = table[LegsTo]("LEGSTO")
  val legsFrom = table[LegsFrom]("LEGSFROM")
  val transaction = table[Transaction]("TRANSACTION")

  val userInAccount = oneToManyRelation(user, account)
    .via((us, ac) => us.id === ac.userId)

  val accountInWallet = oneToManyRelation(account, wallet)
    .via((acc, wal) => acc.id === wal.id)

  val accountInLegsTo = oneToManyRelation(account, legsTo)
    .via((acc, legto) => acc.id === legto.accountToID)

  val accountInLegsFrom = oneToManyRelation(account, legsFrom)
    .via((acc, legfrom) => acc.id === legfrom.accountFromID)

  val legsFromToTransaction = oneToManyRelation(transaction, legsFrom)
    .via((tran, legf) => tran.id === legf.transactionID)

  val legsToTransaction = oneToManyRelation(transaction, legsTo)
    .via((tran, legt) => tran.id === legt.transactionID)

  on(account)(a =>
    declare(
      a.id is (autoIncremented, primaryKey),
      a.accountBalance defaultsTo 0F,
      a.userId is unique
    )
  )

  on(user)(u =>
    declare(
      u.id is (autoIncremented, primaryKey)
    )
  )

  on(wallet)(w =>
    declare(
      w.id is (autoIncremented, primaryKey)
    )
  )

  on(legsFrom)(lf =>
    declare(
      lf.id is (autoIncremented, primaryKey),
      lf.transactionID is unique
    )
  )

  on(legsTo)(lt =>
    declare(
      lt.id is (autoIncremented, primaryKey),
      lt.transactionID is unique
    )
  )

  on(transaction)(t =>
    declare(
      t.id is (autoIncremented, primaryKey)
    )
  )

  override def drop = {

    Session.cleanupResources
    super.drop

  }

}
