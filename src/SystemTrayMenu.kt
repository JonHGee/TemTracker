import java.awt.*
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.ItemListener
import java.awt.image.BufferedImage
import java.io.*
import java.util.*
import javax.imageio.ImageIO
import kotlin.system.exitProcess


class SystemTrayMenu() {
    var counter = CounterWindow()
    init {
        if (!SystemTray.isSupported()) {
            println("SystemTray is not supported")
        }
        val popup = PopupMenu()
        val trayIcon = TrayIcon(ImageIO.read(File("temcard.jpg")), "TemTracker")
        val tray = SystemTray.getSystemTray()

        // Create a pop-up menu components

        val helpItem = MenuItem("Help")

        val resetItem = MenuItem("Reset")
        val configItem = MenuItem("Configure")
        val exitItem = MenuItem("Exit")

        val jcb1 = MenuItem("Yellow")
        val jcb2 = MenuItem("Teal")
        val jcb3 = MenuItem("Red")
        val jcb4 = MenuItem("Black")


        /*val grp = CheckboxMenuItemGroup()
        val jcb1 = CheckboxMenuItem("Yellow", false)
        val jcb2 = CheckboxMenuItem("Teal", true)
        val jcb3 = CheckboxMenuItem("Red", false)
        grp.add(jcb1)
        grp.add(jcb2)
        grp.add(jcb3)*/
        val displayMenu = Menu("Color")


        configItem.addActionListener { ConfigureWindow() }
        exitItem.addActionListener { exitProcess(0) }
        resetItem.addActionListener{ counter.reset() }
        jcb1.addActionListener {counter.colorchange(Color.YELLOW)}
        jcb2.addActionListener {counter.colorchange(Color.CYAN)}
        jcb3.addActionListener {counter.colorchange(Color.RED)}
        jcb4.addActionListener {counter.colorchange(Color.BLACK)}


        popup.add(helpItem)
        popup.add(displayMenu)
        popup.add(resetItem)
        popup.add(configItem)
        displayMenu.add(jcb1)
        displayMenu.add(jcb2)
        displayMenu.add(jcb3)
        displayMenu.add(jcb4)
        popup.add(exitItem)
        trayIcon.popupMenu = popup

        try {
            tray.add(trayIcon)
        } catch (e: AWTException) {
            try { // Open given file in append mode.
                val out = BufferedWriter(
                    FileWriter("logs.txt", true)
                )
                out.write(e.toString())
                out.close()
            } catch (e: IOException) {
            }
        }
        if (!File("Control1.jpg").exists() || !File("Control2.jpg").exists() ) {
            ConfigureWindow()
        }
    }
}

class CheckboxMenuItemGroup {
    private val group = Vector<CheckboxMenuItem>()

    fun add(menuItem: CheckboxMenuItem) {
        group.addElement(menuItem)
        menuItem.addItemListener(itemListener)
        if (menuItem.state) selectedIndex = group.indexOf(menuItem)
    }

    private var selectedIndex = -1

    fun setSelectedIndex(index: Int) {
        selectedIndex = index
        for (i in group.indices) {
            group.elementAt(i).state = i == index
        }
        val source = group.elementAt(index)
        val id = ActionEvent.ACTION_PERFORMED
        val command = source.actionCommand
        fireActionPerformed(ActionEvent(source, id, command))
    }

    private val itemListener = ItemListener { ev ->
        val source = ev.source as CheckboxMenuItem
        val id = ev.id
        val command = source.actionCommand
        fireActionPerformed(ActionEvent(source, id, command))
    }

    private val actionListener = ActionListener { ev ->
        val index = group.indexOf(ev.source)
        for (i in group.indices) {
            group.elementAt(i).state = i == index
        }
    }

    val selected: CheckboxMenuItem
        get() = group.elementAt(selectedIndex)

    private val actionSupport: ActionSupport = ActionSupport()

    fun addActionListener(l: ActionListener) {
        actionSupport.addActionListener(l)
    }

    fun removeActionListener(l: ActionListener) {
        actionSupport.removeActionListener(l)
    }

    protected fun fireActionPerformed(ev: ActionEvent) {
        actionSupport.fireActionPerformed(ev)
    }

    init {
        addActionListener(actionListener)
    }

    class ActionSupport : Serializable {
        private val listeners = Vector<ActionListener>()

        fun addActionListener(l: ActionListener) {
            listeners.addElement(l)
        }

        fun removeActionListener(l: ActionListener?) {
            listeners.removeElement(l)
        }

        @Synchronized
        fun fireActionPerformed(ev: ActionEvent) {
            for (i in listeners.indices) {
                listeners.elementAt(i).actionPerformed(ev)
            }
        }
    }
}