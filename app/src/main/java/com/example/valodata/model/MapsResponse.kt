package com.example.valodata.model

data class MapsResponse (
    val status: Long,
    val data: List<Map>
)

data class Map (
    val uuid: String,
    val displayName: String,
    val narrativeDescription: String? = null,
    val tacticalDescription: TacticalDescription? = null,
    val coordinates: String? = null,
    val displayIcon: String? = null,
    val listViewIcon: String,
    val splash: String,
    val assetPath: String,
    val mapURL: String,
    val xMultiplier: Double,
    val yMultiplier: Double,
    val xScalarToAdd: Double,
    val yScalarToAdd: Double,
    val callouts: List<Callout>? = null
)

data class Callout (
    val regionName: String,
    val superRegionName: SuperRegionName,
    val location: Location
)

data class Location (
    val x: Double,
    val y: Double
)

enum class SuperRegionName {
    A,
    AttackerSide,
    B,
    C,
    DefenderSide,
    Mid
}

enum class TacticalDescription {
    ABCSites,
    ABSites
}
