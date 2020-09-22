package com.example.indeetvapp

import android.content.Context
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.indeetvapp.model.DataAdapter
import com.example.indeetvapp.model.TestData
import com.google.gson.Gson
import java.io.IOException
import java.io.InputStream


class MainActivity : AppCompatActivity(), FileRead {

    var adapter: DataAdapter? = null
    var recyclerView: RecyclerView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.posterList)
        ReadFileTask(this, this).execute()
    }


    class ReadFileTask(var callback : FileRead, val context: Context) : AsyncTask<Void, Void, String>() {


        var json: String? = null
        override fun doInBackground(vararg params: Void?): String? {

            json = try {
                val input: InputStream = context.resources.openRawResource(R.raw.resource)
                val size: Int = input.available()
                val buffer = ByteArray(size)
                input.read(buffer)
                input.close()
                String(buffer)
            } catch (ex: IOException) {
                ex.printStackTrace()
                return null
            }
            return json
        }


        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            callback.onFileRead(result!!)
        }
    }

    override fun onFileRead(str: String) {
        val data = Gson().fromJson(str, TestData::class.java)
        adapter = DataAdapter(data.testData)
        recyclerView?.layoutManager = LinearLayoutManager(this)
        recyclerView?.adapter = adapter
    }
}




