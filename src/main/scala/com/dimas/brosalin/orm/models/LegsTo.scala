package com.dimas.brosalin.orm.models

/**
  * Created by DmitriyBrosalin on 04/10/2016.
  */

import java.sql.Timestamp

import com.dimas.brosalin.orm.schema.SchemaMySQL
import org.squeryl.{KeyedEntity, Table}
import org.squeryl.annotations.Column
import org.squeryl.dsl.{ManyToOne, OneToMany}
import org.squeryl.PrimitiveTypeMode._

@Table("LEGSTO")
class LegsTo(
            @Column("legsToID")
            val id: Long,
            @Column("transactionID")
            val transactionID: Long,
            @Column("accountID")
            val accountToID: Long,
            @Column("legsCreation")
            val legsToCreation: Timestamp,
            @Column("OPERATION_TYPE")
            val operationType: Int, // вид операции зачисление - 1, списание - 0
            @Column("VALUE")
            val moneyValue: Float
            ) extends KeyedEntity[Long]
{

  lazy val legsToAccount: ManyToOne[Account] = SchemaMySQL.accountInLegsTo.right(this)
  lazy val legsToTransaction: ManyToOne[Transaction] = SchemaMySQL.legsToTransaction.right(this)
  def this() = this(0, 0, 0, new Timestamp(System.currentTimeMillis()), 0, 0)

}
