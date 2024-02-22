package com.google.ar.core

import com.google.android.gms.maps.model.LatLng

class GeoMap {

    //street
    //val startPointLatLng = LatLng(52.34702874981366, 13.603557828071823)
    //val endPointLatLng = LatLng(52.34726794500502, 13.604129187698025)

    //my door
    val startPointLatLng = LatLng(52.346626600963134, 13.604093167008225)
    val endPointLatLng = LatLng(52.3465027666764, 13.604245027856027)

    val lightPoleLatLng = LatLng(52.347155917754165, 13.603753225489394)

    fun latLngToEcef(lat: Double, lon: Double, alt: Double): DoubleArray {
        val a = 6378137.0 // Earth radius in meters
        val e = 8.1819190842622e-2  // Eccentricity

        val sinLat = Math.sin(Math.toRadians(lat))
        val cosLat = Math.cos(Math.toRadians(lat))
        val sinLon = Math.sin(Math.toRadians(lon))
        val cosLon = Math.cos(Math.toRadians(lon))

        val c = 1 / Math.sqrt(cosLat * cosLat + (1 - e * e) * sinLat * sinLat)
        val s = (1 - e * e) * c

        val x = (a * c + alt) * cosLat * cosLon
        val y = (a * c + alt) * cosLat * sinLon
        val z = (a * s + alt) * sinLat

        return doubleArrayOf(x, y, z)
    }

}