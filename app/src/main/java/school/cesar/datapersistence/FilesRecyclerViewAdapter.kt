package school.cesar.datapersistence


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import school.cesar.datapersistence.databinding.RowFilesRecyclerviewBinding
import java.io.File

class FilesRecyclerViewAdapter (private val files : MutableList<File>, private val callback : (Int) -> Unit) : RecyclerView.Adapter<FilesRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_files_recyclerview, parent, false)
        val binding = RowFilesRecyclerviewBinding.bind(view)
        val viewHolder = ViewHolder(binding)

        viewHolder.deleteFile.setOnClickListener {
            val position = viewHolder.adapterPosition
            callback(position)
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fileName = files[position].name

        holder.fileName.text = fileName

    }

    override fun getItemCount(): Int = files.size

    class ViewHolder (view: RowFilesRecyclerviewBinding) : RecyclerView.ViewHolder(view.root) {
        val fileName = view.tvFileName
        val deleteFile = view.imgvDeleteFile
    }
}