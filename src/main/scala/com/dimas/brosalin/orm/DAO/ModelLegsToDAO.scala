package com.dimas.brosalin.orm.DAO

import com.dimas.brosalin.orm.models.LegsTo

/**
  * Created by DmitriyBrosalin on 04/10/2016.
  */
trait ModelLegsToDAO {

  def save(legs: LegsTo)
  def update(legs: LegsTo)
  def getById(id: Int): LegsTo
  def getModelList(): List[LegsTo]

}
