import java.awt.*
import java.io.*
import javax.imageio.ImageIO
import kotlin.system.exitProcess


class SystemTrayMenu() {
    val capture = Capture()
    var counter = CounterWindow(capture)

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


        val displayMenu = Menu("Color")


        configItem.addActionListener { ConfigWindow(capture) }
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
        if (!File("Control1.jpg").exists() || !File("Control2.jpg").exists() ||
                File("config.txt").exists() && File("count.txt").readText(Charsets.UTF_8) != "Full Screen") {
            ConfigWindow(capture)
        }
    }
}