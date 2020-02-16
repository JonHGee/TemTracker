import java.awt.AWTException
import java.awt.Rectangle
import java.awt.Robot
import java.awt.Toolkit
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO

class Capture {

    var x:Int = 0
    var y:Int = 0
    var res = "Full Screen"
    val robot = Robot()
    val format = "jpg"
    var fileName = "PartialScreenshot.$format"
    var fileName2 = "PartialScreenshot2.$format"
    val fileNameControl1 = "Control1.$format"
    val fileNameControl2 = "Control2.$format"
    val screen = Toolkit.getDefaultToolkit().screenSize

    var captureRect = Rectangle(1,1,1,1)
    var captureRect2 = Rectangle(1,1,1,1)
    var screenFullImage: BufferedImage = BufferedImage(360, 60, BufferedImage.TYPE_INT_RGB)
    var screenFullImage2: BufferedImage = BufferedImage(360, 60, BufferedImage.TYPE_INT_RGB)

    init {
        if (File("Control1.jpg").exists()) res = File("count.txt").readText(Charsets.UTF_8)
    }

    fun capture(mode:Boolean) {
        //println("mode: $mode.  res: $res")
        if (!mode) {
            fileName = fileNameControl1
            fileName2 = fileNameControl2
        } else {
            fileName = "PartialScreenshot.$format"
            fileName2 = "PartialScreenshot2.$format"
        }
        when(res) {
            "Full Screen" -> {
                when(screen.width) {
                    2560 -> {
                        captureRect = Rectangle(2100, 150, 360, 60)
                        captureRect2 = Rectangle(1570, 75, 360, 60)
                    }
                    1920 -> {
                        captureRect = Rectangle(1580, 115, 265, 40)
                        captureRect2 = Rectangle(1180, 60, 265, 40)
                    }
                }
            } else -> {
                captureRect = Rectangle(x+1268-215, y+105, 160, 16)
                captureRect2 = Rectangle(x+1268-468, y+71, 160, 16)
            }
        }


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
}