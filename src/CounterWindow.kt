import java.awt.*
import java.awt.Font
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.io.File
import java.util.*
import java.util.Timer
import javax.swing.*

class CounterWindow(capture: Capture) : JDialog() {
    val match = ImageMatch()
    val cap = capture
    var encounter = false
    var count = File("count.txt").readText(Charsets.UTF_8).toInt()

    var posX:Int = 0
    var posY:Int = 0

    private val timer = Timer()
    private val task = object: TimerTask() {
        var timesRan = 0
        override fun run() {
            //println("calling capture")
            cap.capture(true)
            if(!encounter) {
                if(match.compareImage(1)>70.0) {
                    count++
                    if (match.compareImage(2) > 70.0) {
                        count++
                    }
                    encounter = true
                    update(count)
                }
            }
            if(encounter) {
                if (match.compareImage(3) > 70.0) {
                    encounter = false
                }
            }
        }
    }

    private val testPane = JPanel(GridBagLayout())
    private val c = GridBagConstraints()
    private val counterText = JLabel("TemTem Encountered")
    private val counter = JLabel(count.toString())

    fun colorchange(color: Color) {
        counterText.foreground = color
        counter.foreground = color
        revalidate()
    }

    fun reset() {
        count = 0
        update(count)
    }

    fun update(count: Int) {
        File("count.txt").writeText(count.toString())
        counter.text = count.toString()
        revalidate()
    }

    init {
        EventQueue.invokeLater {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())
            } catch (ex: ClassNotFoundException) {
                ex.printStackTrace()
            } catch (ex: InstantiationException) {
                ex.printStackTrace()
            } catch (ex: IllegalAccessException) {
                ex.printStackTrace()
            } catch (ex: UnsupportedLookAndFeelException) {
                ex.printStackTrace()
            }


            //countertext
            counterText.font = Font("Serif", Font.BOLD, 30)
            counterText.foreground = Color.CYAN
            testPane.add(counterText, c)

            //counter
            counter.font = Font("Serif", Font.BOLD, 50)
            counter.foreground = Color.CYAN

            c.gridy = 1
            testPane.add(counter, c)

            testPane.isOpaque = false
            isUndecorated = true
            background = Color(0, 0, 0, 0)
            setLocationRelativeTo(null)
            add(testPane)
            isAlwaysOnTop = true
            pack()
            isVisible = true

            addMouseListener(object : MouseAdapter() {
                override fun mousePressed(e: MouseEvent) {
                    posX = e.getX()
                    posY = e.getY()
                }
            })
            addMouseMotionListener(object: MouseAdapter() {
                override fun mouseDragged(e: MouseEvent) {
                    //sets frame position when mouse dragged
                    setLocation (e.xOnScreen -posX,e.yOnScreen -posY);
                }
            })
            timer.schedule(task, 0, 1000)
        }
    }
}