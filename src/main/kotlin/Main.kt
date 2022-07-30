import java.awt.BorderLayout
import java.awt.Color
import java.awt.Container
import java.awt.Toolkit
import javax.swing.*
import java.security.NoSuchAlgorithmException
import java.security.MessageDigest

object Main {
    private val frame = JFrame("FinalShellActivator")
    private const val frameWidth = 300
    private const val frameHeight = 150
    private val screenWidth = Toolkit.getDefaultToolkit().screenSize.width
    private val screenHeight = Toolkit.getDefaultToolkit().screenSize.height
    private val label1 = JLabel("请输入FinalShell离线机器码:")
    private val field = JTextField("", 50)
    private val button = JButton("OK")
    private val c : Container = frame.contentPane

    @Throws(NoSuchAlgorithmException::class)
    fun swingInit(){
        frame.apply {
            isVisible = true
            background = Color.BLUE
            defaultCloseOperation = JFrame.EXIT_ON_CLOSE
            setBounds(
                (screenWidth/2)-(frameWidth/2),
                (screenHeight/2)-(frameHeight/2),
                frameWidth,
                frameHeight
            )
        }
        button.apply{
            setBounds(30, 30, 50, 50)
            addActionListener {
                button.isVisible = false
                field.isVisible = false
                label1.isVisible = false
                generateKey(field.text)
            }
        }
        c.add(label1, BorderLayout.NORTH)
        c.add(field)
        c.add(button, BorderLayout.SOUTH)
    }

    @Throws(NoSuchAlgorithmException::class)
    @JvmStatic
    fun main(args: Array<String>){
        swingInit()
    }

    @Throws(NoSuchAlgorithmException::class)
    fun generateKey(hardwareId: String) {
        val proKey = transform(61305.toString() + hardwareId + 8552)
        val pfKey = transform(2356.toString() + hardwareId + 13593)
        c.add(JLabel("请按照所需版本将对应代码复制到离线激活提示框中："), BorderLayout.NORTH)
        c.add(JTextArea("高级版: $proKey\n专业版: $pfKey"))
//        c.add(JLabel("高级版: $proKey"))
//        c.add(JLabel("专业版: $pfKey"))
    }

    @Throws(NoSuchAlgorithmException::class)
    fun transform(str: String): String {
        return hashMD5(str).substring(8, 24)
    }

    @Throws(NoSuchAlgorithmException::class)
    fun hashMD5(str: String): String {
        val digest = MessageDigest.getInstance("MD5")
        val hashed = digest.digest(str.toByteArray())
        val sb = StringBuilder()
        for (b in hashed) {
            val len = b.toInt() and 0xFF
            if (len < 16) {
                sb.append("0")
            }
            sb.append(Integer.toHexString(len))
        }
        return sb.toString()
    }
}
