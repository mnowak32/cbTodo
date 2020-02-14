package pl.cdbr.tools.todo.view

import pl.cdbr.tools.todo.Status
import pl.cdbr.tools.todo.Styles
import pl.cdbr.tools.todo.TodoItem
import tornadofx.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainView : View("My View") {
    val items = mutableListOf(
            TodoItem("WASP", "przepięcie na słownik acctType na BGS-WS", listOf("SRX"), null, Status.ACTIVE),
            TodoItem("Thor", "link doładowania z aktywacją 1", emptyList(), LocalDateTime.now().plusDays(1), Status.ACTIVE),
            TodoItem("Thor", "link doładowania z aktywacją 2", emptyList(), LocalDateTime.now().plusDays(2), Status.ACTIVE),
            TodoItem("Thor", "link doładowania z aktywacją 3", emptyList(), LocalDateTime.now().plusDays(1), Status.DONE),
            TodoItem("Ferb2", "odczyt nowych EventDetails (WIP)", listOf("SRX", "mkamin", "Ambroziak"), null, Status.GONE)
    ).toObservable()

    override val root = borderpane {
        center {
            tableview(items) {
                isEditable = true


                column("Projekt", TodoItem::project).makeEditable()
                column("Zadanie", TodoItem::description).cellFormat {
                    text = it
                    isEditable = true
                    when {
                        rowItem.status == Status.DONE -> tableRow.addClass(Styles.doneTask)
                        rowItem.status == Status.GONE -> tableRow.addClass(Styles.goneTask)
                        rowItem.isImminent() -> tableRow.addClass(Styles.urgentTask)
                    }
                }
                column("Osoby", TodoItem::contacts).cellFormat {
                    text = it.joinToString()
                }
                column("Deadline", TodoItem::deadline).cellFormat {
                    text = if (it == null) {
                        "-"
                    } else {
                        it.format(DateTimeFormatter.ISO_DATE)
                    }
                    if (rowItem.status == Status.ACTIVE) {
                        when {
                            rowItem.isOverdue() -> addClass(Styles.overdueTask)
                            rowItem.isImminent() -> addClass(Styles.imminentTask)
                        }
                    }
                }
                column("Status", TodoItem::status)
            }
        }
    }
}
