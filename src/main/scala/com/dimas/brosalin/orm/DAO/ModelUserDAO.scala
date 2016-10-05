package com.dimas.brosalin.orm.DAO

import com.dimas.brosalin.orm.models.{User}

/**
  * Created by DmitriyBrosalin on 02/10/2016.
  */
trait ModelUserDAO {

  def save(user: User)
  def update(user: User)
  def getModelById(id: Int): User
  def getModelList: List[User]

}
