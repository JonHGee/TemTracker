import java.awt.*
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.io.File
import javax.swing.*
import kotlin.system.exitProcess


class ConfigWindow(private val capture: Capture) : JFrame(){
    private val counterText1 = JLabel("Get into a battle with two")
    private val counterText2 = JLabel("TemTems and press Configure!")
    private val configButton = JButton("Configure")
    private val sizes = arrayOf("Full Screen", "1280x720", "800x600")
    private val sizeset = JComboBox(sizes)
    private var res:String = "Full Screen"
    val test = TestPane()
    val con = Config()
    var posX:Int = 0
    var posY:Int = 0
    var windowed:Boolean = false


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
            add(Config())
            isAlwaysOnTop = true
            pack()
            setLocationRelativeTo(null)
            isVisible = true

            addMouseListener(object : MouseAdapter() {
                override fun mousePressed(e: MouseEvent) {
                    posX = e.x
                    posY = e.y
                }
            })
            addMouseMotionListener(object: MouseAdapter() {
                override fun mouseDragged(e: MouseEvent) {
                    //sets frame position when mouse dragged
                    setLocation (e.xOnScreen -posX,e.yOnScreen -posY);
                }
            })
        }
    }

    fun cap() {
        File("config.txt").writeText(res)
        if(windowed) {
            capture.x = location.x
            capture.y = location.y
            capture.res = res
        }
        capture.capture(false)
        dispose()
    }

    fun redraw() {
        when (res) {
            "Full Screen" -> {
                dispose()
                Thread.sleep(100)
                add(con)
                isAlwaysOnTop = true
                pack()
                setLocationRelativeTo(null)
                isVisible = true
            }
            else -> {
                dispose()
                Thread.sleep(100)
                val frame = JLayeredPane()
                frame.preferredSize = Dimension(1268, 715)
                isUndecorated = true
                background = Color(0, 0, 0, 0)
                defaultCloseOperation = EXIT_ON_CLOSE

                var insets: Insets = frame.insets
                var size: Dimension = test.preferredSize
                test.setBounds(
                    0, 0,
                    size.width, size.height
                )
                frame.add(test, 0, 0)

                /*con.setBounds(
                    size.width / 4, size.height / 2,
                    300, 150
                );*/
                //frame.setSize(1280, 720)
                frame.add(con, 1, 1)

                add(frame)
                isAlwaysOnTop = true
                pack()
                setLocationRelativeTo(null)
                isVisible = true
            }
        }
        addMouseListener(object : MouseAdapter() {
            override fun mousePressed(e: MouseEvent) {
                posX = e.x
                posY = e.y
            }
        })
                addMouseMotionListener(object: MouseAdapter() {
            override fun mouseDragged(e: MouseEvent) {
                //sets frame position when mouse dragged
                setLocation (e.xOnScreen -posX,e.yOnScreen -posY);
            }
        })
    }

    inner class TestPane : JPanel() {
        override fun getPreferredSize(): Dimension {
            return Dimension(1268, 715)
        }

        override fun paintComponent(g: Graphics) {
            super.paintComponent(g)
            val g2d = g.create() as Graphics2D
            g2d.color = background
            //            g2d.setComposite(AlphaComposite.SrcOver.derive(0.5f));
//            g2d.fillRect(0, 0, getWidth(), getHeight());
            g2d.color = Color.RED
            g2d.drawRect(0, 0, width - 1, height - 1)
            g2d.drawRect(1, 1, width - 3, height - 3)

            //first TemTem HP bar
            g2d.drawRect(1270-216, 104, 162, 18)
            g2d.drawRect(1270-217, 103, 164, 20)

            //Second TemTem HP Bar
            g2d.drawRect(1270-469, 70, 162, 18)
            g2d.drawRect(1270-470, 69, 164, 20)
            g2d.dispose()
        }

        init {
            isOpaque = false
        }
    }

    inner class Config : JPanel() {


        override fun getPreferredSize(): Dimension {
            return Dimension(300, 150)
        }

        init {
            counterText1.foreground = Color.BLACK
            counterText2.foreground = Color.BLACK
            layout = null


            // counterText = JLabel("TemTems Encountered")
            counterText1.font = Font("Serif", Font.BOLD, 20)
            add(counterText1)

            counterText2.font = Font("Serif", Font.BOLD, 20)

            add(counterText2)
            add(sizeset)

            // val counter = JLabel("82")


            configButton.addActionListener { cap() }
            sizeset.addActionListener {
                // Get the source of the component, which is our combo
                // box.

                val comboBox: JComboBox<Any> = it.source as JComboBox<Any>;
                // Print the selected items
                res = comboBox.selectedItem as String;
                redraw()
                windowed = when(res) {
                    "Full Screen"-> false
                    else -> true
                }
            }


            add(configButton)
            val insets: Insets = insets
            var size: Dimension = counterText1.preferredSize
            counterText1.setBounds(
                30 + insets.left, 5 + insets.top,
                size.width, size.height
            )
            size = counterText2.preferredSize
            counterText2.setBounds(
                10 + insets.left, 30 + insets.top,
                size.width, size.height
            )
            size = sizeset.preferredSize
            sizeset.setBounds(
                85 + insets.left, 70 + insets.top,
                size.width + 50, size.height
            )
            size = configButton.preferredSize
            configButton.setBounds(
                85 + insets.left, 110 + insets.top,
                size.width + 50, size.height + 10
            )
        }
    }
}