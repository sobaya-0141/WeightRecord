package sobaya.app.resources.transformations

import android.graphics.Bitmap
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import androidx.core.graphics.applyCanvas
import coil.bitmap.BitmapPool
import coil.size.Size
import coil.transform.Transformation
import kotlin.math.min

class RadiusTransformations : Transformation {

    override fun key(): String = RadiusTransformations::class.java.name

    override suspend fun transform(pool: BitmapPool, input: Bitmap, size: Size): Bitmap {
        val paint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.FILTER_BITMAP_FLAG)

        val minSize = min(input.width, input.height)
        val radius = 32f
        val output = pool.get(minSize, minSize, input.config ?: Bitmap.Config.ARGB_8888)
        output.applyCanvas {
            drawRect(0f, input.width.toFloat(), 0f, input.height.toFloat(), paint)
            paint.xfermode = XFERMODE
            drawBitmap(input, radius - input.width, radius - input.height, paint)
        }

        return output
    }

    override fun equals(other: Any?) = other is RadiusTransformations

    override fun hashCode() = javaClass.hashCode()

    override fun toString() = "CircleCropTransformation()"

    private companion object {
        val XFERMODE = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
    }
}