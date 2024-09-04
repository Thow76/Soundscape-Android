package org.scottishtecharmy.soundscape.utils


import android.content.Context
import android.content.res.Configuration
import android.util.Log
import org.scottishtecharmy.soundscape.R

/*fun getCompassLabelFacingDirection(context: Context, degrees: Int):String{
    return when (degrees) {
        in 338 .. 360, in 0 .. 22 -> context.getString(R.string.directions_facing_n)
        in 23 .. 67 -> context.getString(R.string.directions_facing_ne)
        in 68 .. 112 -> context.getString(R.string.directions_facing_e)
        in 113 .. 157 -> context.getString(R.string.directions_facing_se)
        in 158 .. 202 -> context.getString(R.string.directions_facing_s)
        in 203 .. 247 -> context.getString(R.string.directions_facing_sw)
        in 248 .. 292 -> context.getString(R.string.directions_facing_w)
        in 293 .. 337 -> context.getString(R.string.directions_facing_nw)
        else -> ""
    }
}*/

fun getCompassLabelFacingDirection(context: Context, degrees: Int, locale: java.util.Locale): String{
    val configuration = Configuration(context.resources.configuration)
    configuration.setLocale(locale)
    val localizedContext = context.createConfigurationContext(configuration)

    return when (degrees) {
        in 338..360, in 0..22 -> localizedContext.getString(R.string.directions_facing_n)
        in 23..67 -> localizedContext.getString(R.string.directions_facing_ne)
        in 68..112 -> localizedContext.getString(R.string.directions_facing_e)
        in 113..157 -> localizedContext.getString(R.string.directions_facing_se)
        in 158..202 -> localizedContext.getString(R.string.directions_facing_s)
        in 203..247 -> localizedContext.getString(R.string.directions_facing_sw)
        in 248..292 -> localizedContext.getString(R.string.directions_facing_w)
        in 293..337 -> localizedContext.getString(R.string.directions_facing_nw)
        else -> ""
    }
}

/*
fun getCompassLabelFacingDirectionAlong(context: Context, degrees: Int, placeholder: String):String{
    return when (degrees) {
        in 338 .. 360, in 0 .. 22 -> context.getString(R.string.directions_along_facing_n, placeholder)
        in 23 .. 67 -> context.getString(R.string.directions_along_facing_ne, placeholder)
        in 68 .. 112 -> context.getString(R.string.directions_along_facing_e, placeholder)
        in 113 .. 157 -> context.getString(R.string.directions_along_facing_se, placeholder)
        in 158 .. 202 -> context.getString(R.string.directions_along_facing_s, placeholder)
        in 203 .. 247 -> context.getString(R.string.directions_along_facing_sw, placeholder)
        in 248 .. 292 -> context.getString(R.string.directions_along_facing_w, placeholder)
        in 293 .. 337 -> context.getString(R.string.directions_along_facing_nw, placeholder)
        else -> ""
    }
}*/

fun getCompassLabelFacingDirectionAlong(context: Context, degrees: Int, placeholder: String, locale: java.util.Locale):String{
    val configuration = Configuration(context.resources.configuration)
    configuration.setLocale(locale)
    val localizedContext = context.createConfigurationContext(configuration)
    Log.d("Locale", "Locale: ${locale.language}")
    return when (degrees) {
        in 338 .. 360, in 0 .. 22 -> context.getString(R.string.directions_along_facing_n, placeholder)
        in 23 .. 67 -> localizedContext.getString(R.string.directions_along_facing_ne, placeholder)
        in 68 .. 112 -> localizedContext.getString(R.string.directions_along_facing_e, placeholder)
        in 113 .. 157 -> localizedContext.getString(R.string.directions_along_facing_se, placeholder)
        in 158 .. 202 -> localizedContext.getString(R.string.directions_along_facing_s, placeholder)
        in 203 .. 247 -> localizedContext.getString(R.string.directions_along_facing_sw, placeholder)
        in 248 .. 292 -> localizedContext.getString(R.string.directions_along_facing_w, placeholder)
        in 293 .. 337 -> localizedContext.getString(R.string.directions_along_facing_nw, placeholder)
        else -> ""
    }
}

