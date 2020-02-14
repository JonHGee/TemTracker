import java.awt.*
import java.awt.Font
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import java.util.*
import java.util.Timer
import javax.imageio.ImageIO
import javax.swing.*

class CounterWindow() : JDialog() {
    val match = ImageMatch()
    val robot = Robot()
    var encounter = false
    var count = File("count.txt").readText(Charsets.UTF_8).toInt()

    var posX:Int = 0
    var posY:Int = 0

    val format = "jpg"
    val fileName = "PartialScreenshot.$format"
    val fileName2 = "PartialScreenshot2.$format"
    val screenSize = Toolkit.getDefaultToolkit().screenSize

    var captureRect = Rectangle(1,1,1,1)
    var captureRect2 = Rectangle(1,1,1,1)
    var screenFullImage: BufferedImage = BufferedImage(360, 60, BufferedImage.TYPE_INT_RGB)
    var screenFullImage2: BufferedImage = BufferedImage(360, 60, BufferedImage.TYPE_INT_RGB)

    private val timer = Timer()
    private val task = object: TimerTask() {
        var timesRan = 0
        override fun run() {
            //println("calling capture")
            capture()
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
    private val counterText = JLabel("TemTems Encountered")
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

    fun capture() {
        try {
            screenFullImage = robot.createScreenCapture(captureRect)
            ImageIO.write(screenFullImage, format, File(fileName))
            screenFullImage2 = robot.createScreenCapture(captureRect2)
            ImageIO.write(screenFullImage2, format, File(fileName2))
            //println("A partial screenshot saved!")
        } catch (ex: AWTException) {
            System.err.println(ex)
        } catch (ex: IOException) {
            System.err.println(ex)
        }
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
                    setLocation (e.getXOnScreen()-posX,e.getYOnScreen()-posY);
                }
            })

            when(screenSize.width) {
                2560 -> {
                    captureRect = Rectangle(2100, 150, 360, 60)
                    captureRect2 = Rectangle(1570, 75, 360, 60)
                }
                1920 -> {
                    captureRect = Rectangle(1580, 115, 265, 40)
                    captureRect2 = Rectangle(1180, 60, 265, 40)
                }
            }

            timer.schedule(task, 0, 1000)
        }
    }
}

/*inner class TestPane : JPanel() {
        private val counterText = JLabel("TemTems Encountered")


        init {


            layout = GridBagLayout()
            val c = GridBagConstraints()
            val exitButton = JButton("Exit")
            if (!movin) {
                isOpaque = false
                counterText.foreground = YELLOW
                counter.foreground = YELLOW
            } else {
                counterText.foreground = BLACK
                counter.foreground = BLACK

                c.gridy = 2
                add(exitButton,c)
            }
            exitButton.addActionListener { exitProcess(0) }
            //counterText
            counterText.font = Font("Serif", Font.BOLD, 30)
            c.gridy = 0
            add(counterText, c)

            //counter
            counter.font = Font("Serif", Font.BOLD, 50)
            c.gridy = 1
            add(counter, c)
        }
    }*/
