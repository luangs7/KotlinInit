package br.com.squarebits.kotlininit.extras

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore

import java.io.File

/**
 * Created by ali on 4/11/15.
 */
class Camera
/**
 *
 * @param activity to return the camera results
 */
(private val activity: Activity) {

    /**
     * Private variables
     */
    private val context: Context
    private var cameraBitmapPath: String? = null
    private var cameraBitmap: Bitmap? = null
    private var dirName: String? = null
    private var imageName: String? = null
    private var imageType: String? = null
    private var imageHeight: Int = 0
    private var compression: Int = 0

    init {
        context = activity.applicationContext
        dirName = IMAGE_DEFAULT_DIR
        imageName = IMAGE_DEFAULT_NAME + System.currentTimeMillis()
        imageHeight = IMAGE_HEIGHT
        compression = IMAGE_COMPRESSION
        imageType = IMAGE_FORMAT_JPG
    }

    /**
     *
     * @return create camera builder
     */
    fun builder(): CameraBuilder {
        return CameraBuilder()
    }

    /**
     * Initiate the existing camera apps
     * @throws NullPointerException
     */
    @Throws(NullPointerException::class)
    fun takePicture() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(activity.packageManager) != null) {
            val photoFile = ImageUtil.createImageFile(context, dirName!!, imageName!!, imageType!!)
            if (photoFile != null) {
                cameraBitmapPath = photoFile.absolutePath
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile))
                activity.startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO)
            } else {
                throw NullPointerException("Bitmap received from camera is null")
            }
        } else {
            throw NullPointerException("Unable to open camera")
        }
    }

    /**
     *
     * @return the saved bitmap path but scaling bitmap as per builder
     */
    fun getCameraBitmapPath(): String? {
        val bitmap = getCameraBitmap()
        bitmap!!.recycle()
        return cameraBitmapPath
    }

    /**
     *
     * @return The scaled bitmap as per builder
     */
    fun getCameraBitmap(): Bitmap? {
        try {
            if (cameraBitmap != null) {
                cameraBitmap!!.recycle()
            }
            cameraBitmap = ImageUtil.decodeFile(File(cameraBitmapPath!!), imageHeight)
            if (cameraBitmap != null) {
                ImageUtil.saveBitmap(cameraBitmap!!, cameraBitmapPath!!, imageType!!, compression)
            }
            return cameraBitmap
        } catch (e: Exception) {
            return null
        }

    }

    /**
     *
     * @param imageHeight
     * @return Bitmap path with approx desired height
     */
    fun resizeAndGetCameraBitmapPath(imageHeight: Int): String? {
        val bitmap = resizeAndGetCameraBitmap(imageHeight)
        bitmap!!.recycle()
        return cameraBitmapPath
    }

    /**
     *
     * @param imageHeight
     * @return Bitmap with approx desired height
     */
    fun resizeAndGetCameraBitmap(imageHeight: Int): Bitmap? {
        try {
            if (cameraBitmap != null) {
                cameraBitmap!!.recycle()
            }
            cameraBitmap = ImageUtil.decodeFile(File(cameraBitmapPath!!), imageHeight)
            if (cameraBitmap != null) {
                ImageUtil.saveBitmap(cameraBitmap!!, cameraBitmapPath!!, imageType!!, compression)
            }
            return cameraBitmap
        } catch (e: Exception) {
            return null
        }

    }

    /**
     * Deletes the saved camera image
     */
    fun deleteImage() {
        if (cameraBitmapPath != null) {
            val image = File(cameraBitmapPath!!)
            if (image.exists()) {
                image.delete()
            }
        }
    }

    /**
     * Camera builder declaration
     */
    inner class CameraBuilder {
        fun setDirectory(dirName: String): CameraBuilder {
            this@Camera.dirName = dirName
            return this
        }

        fun setName(imageName: String): CameraBuilder {
            this@Camera.imageName = imageName
            return this
        }

        fun setImageFormat(imageFormat: String?): CameraBuilder {
            if (imageFormat == null) {
                this@Camera.imageType = IMAGE_FORMAT_JPG
            }
            if (imageFormat == "png" || imageFormat == "PNG" || imageFormat == ".png") {
                this@Camera.imageType = IMAGE_FORMAT_PNG
            } else if (imageFormat == "jpg" || imageFormat == "JPG" || imageFormat == ".jpg") {
                this@Camera.imageType = IMAGE_FORMAT_JPG
            } else if (imageFormat == "jpeg" || imageFormat == "JPEG" || imageFormat == ".jpeg") {
                this@Camera.imageType = IMAGE_FORMAT_JPEG
            } else {
                this@Camera.imageType = IMAGE_FORMAT_JPG
            }
            return this
        }

        fun setImageHeight(imageHeight: Int): CameraBuilder {
            this@Camera.imageHeight = imageHeight
            return this
        }

        fun setCompression(compression: Int): CameraBuilder {
            var compression = compression
            if (compression > 100) {
                compression = 100
            } else if (compression < 0) {
                compression = 0
            }
            this@Camera.compression = compression
            return this
        }
    }

    companion object {

        /**
         * public variables to be used in the builder
         */
        val REQUEST_TAKE_PHOTO = 1
        val IMAGE_JPG = "jpg"
        val IMAGE_JPEG = "jpeg"
        val IMAGE_PNG = "png"

        /**
         * default values used by camera
         */
        private val IMAGE_FORMAT_JPG = ".jpg"
        private val IMAGE_FORMAT_JPEG = ".jpeg"
        private val IMAGE_FORMAT_PNG = ".png"
        private val IMAGE_HEIGHT = 1000
        private val IMAGE_COMPRESSION = 75
        private val IMAGE_DEFAULT_DIR = "capture"
        private val IMAGE_DEFAULT_NAME = "img_"
    }
}

