package com.example.samplepdflib

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.github.barteksc.pdfviewer.PDFView
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener
import com.github.barteksc.pdfviewer.listener.OnPageScrollListener
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle
import java.io.File


class PdfViewActivity : AppCompatActivity(), OnPageChangeListener, OnPageScrollListener {

    var pdfView: PDFView? = null
    var pager: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf_view)

        pdfView = findViewById(R.id.pdfView)
        pager = findViewById(R.id.pages)

        val extra = intent
        val filepath = extra.getStringExtra("file")
        Log.i("TLogs", "onCreate1: $filepath")
        val file = File(filepath)

        pdfView?.fromFile(file)
            ?.enableSwipe(true)
            ?.enableDoubletap(true)
            ?.password(null)
            ?.swipeHorizontal(false)
            ?.enableAntialiasing(true)
            ?.invalidPageColor(Color.WHITE)
            ?.onPageScroll(this)
            ?.scrollHandle(DefaultScrollHandle(this))
            ?.load()

        pdfView?.maxZoom = 3f
        pdfView?.midZoom = 1.5f
        pdfView?.minZoom = 1f
        pdfView?.canScrollVertically(1)
        pdfView?.animate()

    }

    override fun onPageChanged(page: Int, pageCount: Int) {

    }

    override fun onPageScrolled(page: Int, positionOffset: Float) {
        val current = pdfView?.currentPage?.plus(1)
        pager?.text = "$current/${pdfView?.pageCount.toString()}"
    }


}