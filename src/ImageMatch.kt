import java.io.File
import javax.imageio.ImageIO

class ImageMatch() {

    fun compareImage(i: Int): Float {
        //println("calling compareimage")
        var percentage = 0f
        try { // take buffer data from both image files //

            when (i) {
                1 -> {
                    //println(File("Control1.jpg").absolutePath)
                    val biA = ImageIO.read(File("Control1.jpg"))
                    val dbA = biA.data.dataBuffer
                    val sizeA = dbA.size
                    val biB = ImageIO.read(File("PartialScreenshot.jpg"))
                    val dbB = biB.data.dataBuffer
                    val sizeB = dbB.size
                    var count = 0
                    // compare data-buffer objects //
                    if (sizeA == sizeB) {
                        for (i in 0 until sizeA) {
                            if (dbA.getElem(i) == dbB.getElem(i)) {
                                count = count + 1
                            }
                        }
                        percentage = count * 100 / sizeA.toFloat()
                        //println("""Image $i: ${percentage}match""")
                    }
                }
                2 -> {
                    val biA = ImageIO.read(File("Control2.jpg"))
                    val dbA = biA.data.dataBuffer
                    val sizeA = dbA.size
                    val biB =
                        ImageIO.read(File("PartialScreenshot2.jpg"))
                    val dbB = biB.data.dataBuffer
                    val sizeB = dbB.size
                    var count = 0
                    // compare data-buffer objects //
                    if (sizeA == sizeB) {
                        for (i in 0 until sizeA) {
                            if (dbA.getElem(i) == dbB.getElem(i)) {
                                count = count + 1
                            }
                        }
                        percentage = count * 100 / sizeA.toFloat()
                        //println("""Image $i: ${percentage}match""")
                    }
                }
                // code block

                else -> {
                    val biA = ImageIO.read(File("PartialScreenshot.jpg"))
                    val dbA = biA.data.dataBuffer
                    val sizeA = dbA.size
                    val biB = ImageIO.read(File("Control3.jpg"))
                    val dbB = biB.data.dataBuffer
                    val sizeB = dbB.size
                    var count = 0
                    // compare data-buffer objects //
                    if (sizeA == sizeB) {
                        for (i in 0 until sizeA) {
                            if (dbA.getElem(i) == dbB.getElem(i)) {
                                count = count + 1
                            }
                        }
                        percentage = count * 100 / sizeA.toFloat()
                        //println("""Image $i: ${percentage}match""")
                    } else {
                        //println("$sizeA $sizeB")
                        //println("size mismatch")
                    }
                }
            }
        } catch (e: Exception) {
            File("logs.txt").writeText("Failed to compare image files ...$e")
        }
        return percentage
    }
}
