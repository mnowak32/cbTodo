package pl.cdbr.tools.todo

import javafx.scene.paint.Color
import javafx.scene.text.FontPosture
import javafx.scene.text.FontWeight
import tornadofx.*

class Styles : Stylesheet() {
    companion object {
        val imminentTask by cssclass()
        val overdueTask by cssclass()

        val doneTask by cssclass()
        val goneTask by cssclass()
        val urgentTask by cssclass()
    }

    init {
        imminentTask {
            backgroundColor += Color.WHEAT
        }
        overdueTask {
            backgroundColor += Color.TOMATO
        }
        doneTask {
            fontStyle = FontPosture.ITALIC
        }
        goneTask {
            strikethrough = true
        }
        urgentTask {
            fontWeight = FontWeight.BOLD
        }
    }
}