import java.io.IOException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*

object FinalShell {
	@Throws(NoSuchAlgorithmException::class, IOException::class)
	@JvmStatic
	fun main(args: Array<String>) {
		print("请输入FinalShell的离线机器码：")
		val reader = Scanner(System.`in`)
		val machineCode = reader.nextLine()
		generateKey(machineCode)
	}

	@Throws(NoSuchAlgorithmException::class)
	fun generateKey(hardwareId: String) {
		val proKey = transform(61305.toString() + hardwareId + 8552)
		val pfKey = transform(2356.toString() + hardwareId + 13593)
		println("请按照所需版本将对应代码复制到离线激活提示框中：")
		println("高级版: $proKey")
		println("专业版: $pfKey")
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