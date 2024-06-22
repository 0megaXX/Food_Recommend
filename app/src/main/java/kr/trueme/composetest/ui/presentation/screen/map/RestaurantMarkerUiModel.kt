package kr.trueme.composetest.ui.presentation.screen.map

sealed class RestaurantMarkerUiModel {
    abstract val name: String
    abstract val lng: Double
    abstract val lat: Double
    abstract val mapUrl: String
}

internal class KoreaMarkerUiModel(
    override val name: String,
    override val lng: Double,
    override val lat: Double,
    override val mapUrl: String
): RestaurantMarkerUiModel()

internal class ChinaMarkerUiModel(
    override val name: String,
    override val lng: Double,
    override val lat: Double,
    override val mapUrl: String
): RestaurantMarkerUiModel()

internal class JapanMarkerUiModel(
    override val name: String,
    override val lng: Double,
    override val lat: Double,
    override val mapUrl: String
): RestaurantMarkerUiModel()

internal class WesternMarkerUiModel(
    override val name: String,
    override val lng: Double,
    override val lat: Double,
    override val mapUrl: String
): RestaurantMarkerUiModel()

internal class FastMarkerUiModel(
    override val name: String,
    override val lng: Double,
    override val lat: Double,
    override val mapUrl: String
): RestaurantMarkerUiModel()


internal class DesertMarkerUiModel(
    override val name: String,
    override val lng: Double,
    override val lat: Double,
    override val mapUrl: String
): RestaurantMarkerUiModel()