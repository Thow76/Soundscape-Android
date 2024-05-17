package com.kersnazzle.soundscapealpha.utils

import com.kersnazzle.soundscapealpha.geojsonparser.geojson.FeatureCollection
import kotlin.math.PI
import kotlin.math.asinh
import kotlin.math.floor
import kotlin.math.tan

/**
 * Gets Slippy Map Tile Name from X and Y GPS coordinates and Zoom (fixed at 16 for Soundscape)
 * @param lat
 * latitude in decimal degrees
 * @param lon
 * longitude in decimal degrees
 * @param zoom
 * the zoom level.
 * @return a Pair(xtile, ytile)
 */
fun getXYTile(lat: Double, lon: Double, zoom: Int = 16): Pair<Int, Int> {
    val latRad = toRadians(lat)
    var xtile = floor((lon + 180) / 360 * (1 shl zoom)).toInt()
    var ytile = floor((1.0 - asinh(tan(latRad)) / PI) / 2 * (1 shl zoom)).toInt()

    if (xtile < 0) {
        xtile = 0
    }
    if (xtile >= (1 shl zoom)) {
        xtile = (1 shl zoom) - 1
    }
    if (ytile < 0) {
        ytile = 0
    }
    if (ytile >= (1 shl zoom)) {
        ytile = (1 shl zoom) - 1
    }
    return Pair(xtile, ytile)
}

/**
 * Given a valid Tile features collection this will parse the collection and return a roads
 * feature collection. Uses the "highway" feature_type to extract roads from GeoJSON.
 * @param tileFeatureCollection
 * valid Tile Feature Collection
 * @return a FeatureCollection that contains only roads.
 */
fun getRoadsFeatureCollectionFromTileFeatureCollection(tileFeatureCollection: FeatureCollection): FeatureCollection {

    val roadsFeatureCollection = FeatureCollection()

    // Original Soundscape excludes the below feature_value (s) even though they have the
    // feature_type == highway
    // and creates a separate Paths Feature Collection for them
    // "footway", "path", "cycleway", "bridleway"
    // gd_intersection are a special case and get their own Intersections Feature Collection

    for (feature in tileFeatureCollection) {
        if (feature.foreign!!["feature_type"] == "highway"
            && feature.foreign!!["feature_value"] != "gd_intersection"
            && feature.foreign!!["feature_value"] != "footway"
            && feature.foreign!!["feature_value"] != "path"
            && feature.foreign!!["feature_value"] != "cycleway"
            && feature.foreign!!["feature_value"] != "bridleway") {
            roadsFeatureCollection.addFeature(feature)
        }
    }
    return roadsFeatureCollection
}