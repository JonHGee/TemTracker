import java.awt.*
import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import java.io.File
import javax.imageio.ImageIO
import javax.swing.JFrame
import javax.swing.JPanel


fun main(args: Array<String>) {
    File("logs.txt").writeText("startup")

    if (!File("count.txt").exists()) {
        File("count.txt").writeText("0")
    }
    if (!File("Control3.jpg").exists()) {
        val temp = JFrame()
        temp.preferredSize = (Dimension(400,100))
        temp.contentPane.background = Color.BLACK
        temp.isUndecorated = true
        temp.pack()
        temp.isVisible = true
        temp.isAlwaysOnTop = true
        Thread.sleep(200)
        val robot = Robot()
        val captureRect = Rectangle(1,1,400,100)
        val screenFullImage = robot.createScreenCapture(captureRect)
        ImageIO.write(screenFullImage, "jpg", File("Control3.jpg"))
        Thread.sleep(200)
        temp.dispose()
    }
    if (!File("temcard.jpg").exists()) {
        val tem = byteArrayOf(
            -119,
            80,
            78,
            71,
            13,
            10,
            26,
            10,
            0,
            0,
            0,
            13,
            73,
            72,
            68,
            82,
            0,
            0,
            0,
            20,
            0,
            0,
            0,
            20,
            8,
            2,
            0,
            0,
            0,
            2,
            -21,
            -118,
            90,
            0,
            0,
            2,
            -94,
            73,
            68,
            65,
            84,
            120,
            94,
            125,
            -108,
            -63,
            75,
            98,
            97,
            20,
            -59,
            -5,
            79,
            70,
            69,
            -104,
            -105,
            105,
            -106,
            38,
            25,
            -127,
            -94,
            65,
            84,
            83,
            68,
            99,
            -72,
            80,
            -62,
            52,
            -63,
            6,
            117,
            17,
            -74,
            -47,
            69,
            65,
            4,
            81,
            -127,
            24,
            77,
            -48,
            -90,
            -123,
            24,
            86,
            -88,
            72,
            74,
            88,
            106,
            84,
            -120,
            32,
            17,
            -24,
            70,
            29,
            -55,
            -96,
            101,
            32,
            -126,
            -87,
            -31,
            -62,
            -84,
            57,
            -12,
            -122,
            71,
            -13,
            -26,
            99,
            -50,
            74,
            125,
            -25,
            -9,
            125,
            -9,
            -98,
            123,
            -97,
            93,
            -17,
            -1,
            -43,
            -53,
            -53,
            -53,
            -81,
            82,
            41,
            26,
            -115,
            70,
            34,
            -111,
            78,
            -89,
            -61,
            122,
            -38,
            -59,
            -6,
            -50,
            82,
            32,
            16,
            -104,
            -104,
            -104,
            -104,
            -98,
            -98,
            -34,
            -36,
            -36,
            -52,
            -27,
            114,
            -84,
            -89,
            127,
            -32,
            118,
            -69,
            -3,
            -4,
            -4,
            -116,
            -57,
            -59,
            98,
            -79,
            82,
            -87,
            92,
            94,
            94,
            110,
            109,
            109,
            105,
            -75,
            -38,
            100,
            50,
            57,
            48,
            48,
            32,
            16,
            8,
            -44,
            106,
            -11,
            -38,
            -38,
            -38,
            -37,
            -37,
            27,
            1,
            -122,
            -37,
            100,
            50,
            41,
            20,
            10,
            -115,
            70,
            -109,
            74,
            -91,
            -122,
            -122,
            -122,
            -70,
            -69,
            -69,
            41,
            -118,
            -6,
            -71,
            -73,
            -25,
            -13,
            -7,
            -60,
            98,
            49,
            62,
            27,
            12,
            6,
            92,
            64,
            -128,
            -115,
            70,
            35,
            -113,
            -57,
            -125,
            99,
            112,
            112,
            -16,
            -30,
            -30,
            66,
            38,
            -109,
            81,
            31,
            -22,
            -19,
            -19,
            117,
            58,
            -99,
            -15,
            120,
            -4,
            -69,
            70,
            35,
            -105,
            -53,
            -17,
            -18,
            -18,
            8,
            -16,
            -2,
            -2,
            62,
            -121,
            -53,
            -123,
            -37,
            106,
            -75,
            30,
            30,
            30,
            10,
            -123,
            66,
            26,
            -122,
            80,
            2,
            -18,
            60,
            59,
            59,
            51,
            -102,
            76,
            39,
            39,
            39,
            4,
            -72,
            80,
            40,
            72,
            -92,
            82,
            -76,
            23,
            12,
            6,
            117,
            58,
            29,
            24,
            -124,
            -76,
            -67,
            -67,
            -115,
            62,
            23,
            22,
            22,
            112,
            -106,
            84,
            42,
            5,
            89,
            -81,
            -41,
            9,
            112,
            -93,
            -47,
            -128,
            9,
            0,
            70,
            -126,
            120,
            -12,
            122,
            125,
            54,
            -101,
            -123,
            27,
            103,
            61,
            62,
            62,
            -18,
            -18,
            -18,
            -118,
            68,
            34,
            -16,
            -24,
            -97,
            0,
            67,
            -89,
            -89,
            -89,
            30,
            -113,
            7,
            87,
            -95,
            -49,
            88,
            44,
            -122,
            -76,
            -51,
            102,
            -77,
            -35,
            110,
            -33,
            -40,
            -40,
            64,
            -26,
            -56,
            -116,
            -53,
            -29,
            125,
            -101,
            -100,
            -4,
            -60,
            126,
            -126,
            91,
            -83,
            86,
            -87,
            84,
            -102,
            -99,
            -99,
            69,
            102,
            -73,
            -73,
            -73,
            51,
            51,
            51,
            -31,
            112,
            56,
            -109,
            -55,
            -60,
            -50,
            -49,
            67,
            -95,
            16,
            -54,
            -31,
            -13,
            -7,
            95,
            41,
            -22,
            -23,
            -23,
            -119,
            0,
            67,
            15,
            15,
            15,
            -93,
            -93,
            -93,
            42,
            -107,
            10,
            -45,
            -98,
            -102,
            -102,
            -62,
            -40,
            -14,
            -7,
            -4,
            -3,
            -3,
            -67,
            -53,
            -27,
            -94,
            39,
            7,
            125,
            -82,
            -4,
            47,
            -72,
            -47,
            104,
            34,
            -19,
            -66,
            -66,
            -66,
            -29,
            -29,
            -29,
            -85,
            -85,
            43,
            -100,
            50,
            50,
            50,
            98,
            -79,
            88,
            -42,
            -41,
            -41,
            -107,
            74,
            37,
            13,
            59,
            -106,
            -105,
            95,
            95,
            95,
            9,
            48,
            -108,
            78,
            -89,
            -31,
            -24,
            -23,
            -23,
            -39,
            -39,
            -39,
            41,
            -105,
            -53,
            -104,
            2,
            110,
            78,
            36,
            18,
            -88,
            -120,
            -122,
            -99,
            46,
            23,
            -77,
            -28,
            108,
            24,
            -99,
            -93,
            103,
            -104,
            -6,
            -5,
            -5,
            81,
            -10,
            -46,
            -46,
            18,
            82,
            24,
            30,
            30,
            70,
            -49,
            52,
            -116,
            -46,
            -32,
            -95,
            -51,
            108,
            24,
            114,
            -69,
            -35,
            -76,
            15,
            -110,
            72,
            36,
            40,
            -37,
            -21,
            -11,
            -94,
            -94,
            -79,
            -79,
            49,
            -22,
            -93,
            40,
            102,
            85,
            8,
            -16,
            -51,
            -51,
            -51,
            -8,
            -8,
            56,
            -42,
            27,
            86,
            -60,
            -26,
            -9,
            -5,
            -25,
            -26,
            -26,
            22,
            127,
            44,
            98,
            -55,
            -16,
            122,
            -32,
            -57,
            -107,
            -107,
            21,
            -38,
            73,
            -128,
            107,
            -75,
            26,
            18,
            58,
            56,
            56,
            64,
            -62,
            -85,
            -85,
            -85,
            -40,
            109,
            -70,
            10,
            -99,
            94,
            127,
            125,
            125,
            -19,
            112,
            56,
            48,
            118,
            -38,
            73,
            -128,
            -15,
            -34,
            97,
            -43,
            -111,
            -13,
            23,
            14,
            -25,
            -24,
            -24,
            104,
            126,
            126,
            -98,
            -122,
            81,
            54,
            -26,
            -113,
            -105,
            -105,
            121,
            49,
            9,
            48,
            84,
            -83,
            86,
            -47,
            -86,
            -51,
            102,
            -61,
            -110,
            98,
            55,
            113,
            22,
            38,
            -57,
            -28,
            -60,
            -120,
            12,
            -65,
            127,
            108,
            123,
            -77,
            -39,
            -60,
            -121,
            127,
            -1,
            125,
            24,
            -3,
            6,
            61,
            -95,
            67,
            -28,
            -33,
            -128,
            32,
            -85,
            0,
            0,
            0,
            0,
            73,
            69,
            78,
            68,
            -82,
            66,
            96,
            -126
        )
        val bis = ByteArrayInputStream(tem)
        val bImage2 = ImageIO.read(bis)
        ImageIO.write(bImage2, "jpg", File("temcard.jpg"))
    }
    val sys = SystemTrayMenu()
    //CounterWindow()
}



