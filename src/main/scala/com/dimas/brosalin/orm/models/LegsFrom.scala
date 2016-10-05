package com.dimas.brosalin.orm.models

import java.sql.Timestamp

import com.dimas.brosalin.orm.schema.SchemaMySQL
import org.squeryl.{KeyedEntity, Table}
import org.squeryl.annotations._
import org.squeryl.dsl.ManyToOne

/**
  * Created by DmitriyBrosalin on 04/10/2016.
  */

@Table("LEGSFROM")
class LegsFrom(
                @Column("legsFromID")
                val id: Long,
                @Column("transactionID")
                val transactionID: Long,
                @Column("accountID")
                val accountFromID: Long,
                @Column("legsCreation")
                val legsToCreation: Timestamp,
                @Column("OPERATION_TYPE")
                val operationType: Int, // вид операции зачисление - 1, списание - 0,
                @Column("VALUE")
                val moneyValue: Float
              ) extends KeyedEntity[Long]
{

  lazy val legsFromAccount: ManyToOne[Account] = SchemaMySQL.accountInLegsFrom.right(this)
  lazy val legsFromToTransaction: ManyToOne[Transaction] = SchemaMySQL.legsFromToTransaction.right(this)
  def this() = this(1, 1, 1, new Timestamp(System.currentTimeMillis()), 0, 0)

}
