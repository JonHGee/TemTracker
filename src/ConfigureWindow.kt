import java.awt.*
import java.awt.Color.*
import java.awt.Font
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO
import javax.swing.*

class ConfigureWindow : JFrame() {
    private val counterText1 = JLabel("Get into a battle with two")
    private val counterText2 = JLabel("TemTems and press Configure!")
    inner class TestPane : JPanel() {
        private val configButton = JButton("Configure")

        override fun getPreferredSize(): Dimension {
            return Dimension(350, 150)
        }

        init {
            counterText1.foreground = BLACK
            counterText2.foreground = BLACK
            layout = GridBagLayout()
            val c = GridBagConstraints()

            // counterText = JLabel("TemTems Encountered")
            counterText1.font = Font("Serif", Font.BOLD, 20)
            add(counterText1, c)

            counterText2.font = Font("Serif", Font.BOLD, 20)
            c.gridy = 1
            add(counterText2, c)

            // val counter = JLabel("82")


            configButton.addActionListener { captureConfig() }
            c.weighty = 0.7
            c.gridy = 2

            add(configButton, c)
        }
    }

    fun captureConfig() {
        try {
            val robot = Robot()
            val format = "jpg"
            val fileName = "Control1.$format"
            val fileName2 = "Control2.$format"
            val screenSize = Toolkit.getDefaultToolkit().screenSize

            when(screenSize.width) {
                2560-> {
                    val captureRect = Rectangle(2100, 150, 360, 60)
                    val screenFullImage = robot.createScreenCapture(captureRect)
                    ImageIO.write(screenFullImage, format, File(fileName))
                    val captureRect2 = Rectangle(1570, 75, 360, 60)
                    val screenFullImage2 = robot.createScreenCapture(captureRect2)
                    ImageIO.write(screenFullImage2, format, File(fileName2))

                    dispose()
                }
                1920 -> {
                    val captureRect = Rectangle(1580, 115, 265, 40)
                    val screenFullImage = robot.createScreenCapture(captureRect)
                    ImageIO.write(screenFullImage, format, File(fileName))
                    val captureRect2 = Rectangle(1180, 60, 265, 40)
                    val screenFullImage2 = robot.createScreenCapture(captureRect2)
                    ImageIO.write(screenFullImage2, format, File(fileName2))
                    dispose()
                }
                else -> {
                    counterText1.text = ("Screen size not yet supported")
                    counterText2.text = ("Sorry!")
                }
            }
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

            //isUndecorated = true
            background = white
            isAlwaysOnTop = true
            defaultCloseOperation = JFrame.DISPOSE_ON_CLOSE
            add(TestPane())
            title = "TemTracker Configuration"
            pack()
            setLocationRelativeTo(null)
            isVisible = true
        }
    }
}