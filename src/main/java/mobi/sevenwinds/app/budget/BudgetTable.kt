package mobi.sevenwinds.app.budget

import mobi.sevenwinds.app.author.AuthorEntity
import mobi.sevenwinds.app.author.AuthorEntity.Companion.referrersOn
import mobi.sevenwinds.app.author.AuthorTable
import mobi.sevenwinds.app.author.AuthorTable.nullable
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.SqlExpressionBuilder.isNull
import org.jetbrains.exposed.sql.selectAll

object BudgetTable : IntIdTable("budget") {

    val authorId = reference("authorid", AuthorTable).nullable()
    val year = integer("year")
    val month = integer("month")
    val amount = integer("amount")
    val type = enumerationByName("type", 100, BudgetType::class)
}

class BudgetEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<BudgetEntity>(BudgetTable)
    var authorid by BudgetTable?.authorId
    var fullname by AuthorEntity.optionalReferencedOn(BudgetTable.authorId)
    var year by BudgetTable.year
    var month by BudgetTable.month
    var amount by BudgetTable.amount
    var type by BudgetTable.type

    fun toResponse(): BudgetRecord {
        return BudgetRecord(authorid?.value, fullname?.fullname, year, month, amount, type)
    }
}

