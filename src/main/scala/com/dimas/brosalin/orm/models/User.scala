package com.dimas.brosalin.orm.models

import com.dimas.brosalin.orm.schema.SchemaMySQL
import org.squeryl.annotations.Column
import org.squeryl.dsl.OneToMany
import org.squeryl.{KeyedEntity, Table}

/**
  * Created by DmitriyBrosalin on 30/09/2016.
  */

@Table("USER")
class User(
            @Column("USER_ID")
            override val id: Long,
            @Column("FIRST_NAME")
            val firstName: String,
            @Column("LAST_NAME")
            val lastName: String,
            @Column("AGE")
            val age: Int,
            @Column("GENDER")
            val gender: Int, // либо 0 - женщина, либо 1 - мужчина
            @Column("PASSWORD")
            val password: String
          ) extends KeyedEntity[Long]
{
  lazy val accountCOL: OneToMany[Account] = SchemaMySQL.userInAccount.left(this)
  def this() = this(0, "", "", 0, 1, "")

}
