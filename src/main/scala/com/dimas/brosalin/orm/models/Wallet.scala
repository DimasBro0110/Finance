package com.dimas.brosalin.orm.models

/**
  * Created by DmitriyBrosalin on 02/10/2016.
  */

import com.dimas.brosalin.orm.schema.SchemaMySQL
import org.squeryl.annotations.Column
import org.squeryl.dsl.{ManyToOne, OneToMany}
import org.squeryl.{KeyedEntity, Table}

@Table("WALLET")
class Wallet(
            @Column("WALLET_ID")
            val id: Long,
            @Column("ACCOUNT_ID")
            val accountId: Long,
            @Column("WALLET_CURRENCY")
            val walletCurrency: String
            ) extends KeyedEntity[Long]
{

  lazy val accountCOL: ManyToOne[Account] = SchemaMySQL.accountInWallet.right(this)
  def this() = this(0, 0, "")

}
