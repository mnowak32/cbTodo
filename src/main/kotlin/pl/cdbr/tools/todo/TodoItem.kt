package pl.cdbr.tools.todo

import javafx.beans.property.SimpleListProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.*
import java.time.Duration
import java.time.LocalDateTime

class TodoItem(project: String, description: String, contacts: List<String>, deadline: LocalDateTime?, status: Status) {
    val projectProperty = SimpleStringProperty(project)
    var project by projectProperty

    val descriptionProperty = SimpleStringProperty(description)
    var description by descriptionProperty

    val contactsProperty = SimpleListProperty<String>(contacts.toObservable())
    var contacts by contactsProperty

    val deadlineProperty = SimpleObjectProperty(deadline)
    var deadline by deadlineProperty

    val statusProperty = SimpleObjectProperty(status)
    var status by statusProperty

    val daysLeft: Long? = this.deadline?.let { Duration.between(LocalDateTime.now(), it).toDays() }
    fun isOverdue() = if (daysLeft != null) { daysLeft < 1 } else { false }
    fun isImminent() = if (daysLeft != null) { daysLeft < 3 } else { false }


}

enum class Status {
    ACTIVE, DONE, GONE
}