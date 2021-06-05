package school.cesar.datapersistence

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import school.cesar.datapersistence.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var internalFiles : MutableList<File>
    private lateinit var externalFiles : MutableList<File>
    private lateinit var files : MutableList<File>
    private val fileList by lazy {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fileName = "archive.txt"
        val fileContent = "Archive content"

        val file = File(filesDir, fileName)
        file.createNewFile()

        val extFile = File(getExternalFilesDir(null), fileName)
        extFile.createNewFile()

        file.outputStream().use {
            it.write(fileContent.toByteArray())
        }



        internalFiles = File(filesDir.toURI()).listFiles().toMutableList()
        externalFiles = File(getExternalFilesDir(null)?.toURI()).listFiles().toMutableList()

        files.apply {
            addAll(internalFiles)
            addAll(externalFiles)
        }



        val content = readContent(file)

        Toast.makeText(this, content, Toast.LENGTH_SHORT).show()
    }

    private fun readContent(file: File) : String{
        file.inputStream().use {
            return it.readBytes().decodeToString()
        }
    }
}