package com.example.test.imageVector

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

@Suppress("CheckReturnValue")
public val priority_high: ImageVector
  get() {
    if (_priority_high != null) {
      return _priority_high!!
    }
    _priority_high =
      ImageVector.Builder(
          name = "priority_high",
          defaultWidth = 24.dp,
          defaultHeight = 24.dp,
          viewportWidth = 24f,
          viewportHeight = 24f,
        )
        .apply {
          path(
            fill = SolidColor(Color.Black),
            fillAlpha = 1f,
            stroke = null,
            strokeAlpha = 1f,
            strokeLineWidth = 1f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Bevel,
            strokeLineMiter = 1f,
            pathFillType = PathFillType.Companion.NonZero,
          ) {
            moveTo(12f, 21f)
            quadToRelative(-0.82f, 0f, -1.41f, -0.59f)
            reflectiveQuadTo(10f, 19f)
            reflectiveQuadToRelative(0.59f, -1.41f)
            reflectiveQuadTo(12f, 17f)
            reflectiveQuadToRelative(1.41f, 0.59f)
            quadTo(14f, 18.18f, 14f, 19f)
            reflectiveQuadToRelative(-0.59f, 1.41f)
            reflectiveQuadTo(12f, 21f)
            close()
            moveTo(10f, 15f)
            verticalLineTo(3f)
            horizontalLineToRelative(4f)
            verticalLineTo(15f)
            horizontalLineTo(10f)
            close()
          }
        }
        .build()
    return _priority_high!!
  }

private var _priority_high: ImageVector? = null
