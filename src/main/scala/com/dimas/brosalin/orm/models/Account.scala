package com.dimas.brosalin.orm.models

import java.sql.Timestamp

import com.dimas.brosalin.orm.schema.SchemaMySQL
import org.squeryl.{KeyedEntity, Table}
import org.squeryl.annotations.Column
import org.squeryl.dsl.{ManyToOne, OneToMany}
import org.squeryl.PrimitiveTypeMode._

/**
  * Created by DmitriyBrosalin on 30/09/2016.
  */

@Table("ACCOUNT")
class Account(
               @Column("accountID")
               val id: Long,
               @Column("userID")
               val userId: Long,
               @Column("accountCreation")
               val accountCreation: Timestamp,
               @Column("accountBalance")
               val accountBalance: Float
             ) extends KeyedEntity[Long]
{

  lazy val userCOL: ManyToOne[User] = SchemaMySQL.userInAccount.right(this)
  lazy val walletCOL: OneToMany[Wallet] = SchemaMySQL.accountInWallet.left(this)
  lazy val accountInLegsTo: OneToMany[LegsTo] = SchemaMySQL.accountInLegsTo.left(this)
  lazy val accountInLegsFrom: OneToMany[LegsFrom] = SchemaMySQL.accountInLegsFrom.left(this)
  def this() = this(0, 0, new Timestamp(System.currentTimeMillis()), 0)

}
