package com.dimas.brosalin.orm.models

import java.sql.Timestamp

import com.dimas.brosalin.orm.schema.SchemaMySQL
import org.squeryl.annotations.Column
import org.squeryl.dsl.{ManyToOne, OneToMany}
import org.squeryl.{KeyedEntity, Table}

/**
  * Created by DmitriyBrosalin on 04/10/2016.
  */

@Table("TRANSACTION")
class Transaction(
                 @Column("TRANSACTION_ID")
                 val id: Long,
                 @Column("TRANSACTION_TIME")
                 val transactionTime: Timestamp
                 ) extends KeyedEntity[Long]
{

  def this() = this(1, new Timestamp(System.currentTimeMillis()))
  lazy val legInFromInTransaction: OneToMany[LegsFrom] = SchemaMySQL.legsFromToTransaction.left(this)
  lazy val legToTransaction: OneToMany[LegsTo] = SchemaMySQL.legsToTransaction.left(this)

}
